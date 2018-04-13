package com.zm.filter;

import com.google.common.collect.ImmutableList;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tianshenglong
 * @date 2018/4/13
 * @time 16:16
 */
@Component
public class AccessFilter extends ZuulFilter {

    //需要排除拦截的地址
    public final static ImmutableList<String> EXCLUDE_PATH = ImmutableList.of(
            "/api-weixin/v1/manageUser/login",
            "/api-study/v1/users/login",
            "/api-study/v1/wxUser/bandUser",
            "/api-study/v1//adminUser/login",
            "/api-public/v1/pay/wxOrderCallback",
            "/api-public/v1/pay/aliPayOrderCallback");

    /**
     * 过滤器的类型，它决定过滤器在请求的哪个生命周期中执行。这里定义为pre，代表会在请求被路由之前执行。
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器的执行顺序。当请求在一个阶段中存在多个过滤器时，需要根据该方法返回的值来依次执行。
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断该过滤器是否需要被执行。这里我们直接返回了true，因此该过滤器对所有请求都会生效。实际运用中我们可以利用该函数来指定过滤器的有效范围
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return filterPath();
    }

    /**
     * 过滤器的具体逻辑。这里我们通过ctx.setSendZuulResponse(false)令zuul过滤该请求，不对其进行路由，然后通过ctx.setResponseStatusCode(401)
     * 设置了其返回的错误码，当然我们也可以进一步优化我们的返回，比如，通过ctx.setResponseBody(body)对返回body内容进行编辑等。
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        //此处只是 简单的根据url中是否有参数token来进行验证客户端是否有权限访问我们的实例。
        //正常的话一般都是利用jwt生成token放到请求头中，关于jwt详见 https://jwt.io/，这里不再赘述
        Object token = request.getParameter("token");

        if(token == null){
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
            ctx.getResponse().setContentType("application/json;charset=UTF-8");
            ctx.setResponseBody("未经授权的调用");
        }else{
            ctx.setSendZuulResponse(Boolean.TRUE);
        }
        return null;
    }

    /**
     * 仅拦截验证非GET方法以及不在EXCLUDE_PATH常量中存在的地址
     * @return
     */
    public Boolean filterPath(){
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String requestServletPath = request.getServletPath();
        PathMatcher matcher = new AntPathMatcher();
        if(request.getMethod()=="GET"){
            return false;
        }else{
            for (String path : EXCLUDE_PATH) {
                if(matcher.match(path,requestServletPath)){
                    return false;
                }
            }
            return true;
        }
    }

}
