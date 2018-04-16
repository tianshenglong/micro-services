# micro-services
####参考 https://my.oschina.net/u/1249109/blog/1789442

|模块名称|模块说明|
|---|---
|service-eureka|服务注册中心|
|common-core|公共工具类|
|api-user|user|
|api-shop|shop|
|service-turbine|熔断聚合监控|
|service-gateway|网关提供负载均衡、反向代理、权限认证| 

Spring Cloud Zuul在整合了Eureka之后，具备默认的服务路由功能，所以service-gateway的application.yml中可以不配置任何zuul相关的东西


启动时 先启动服务注册中心，再启动api-user、api-shop 访问 localhost:8761 会看到 api-user、 已经注册到服务中api-shop

api-shop 中定义了一个controller，里面只有一个sayHello的方法，api-user通过feign 调用此方法，详见api-user下的feign包中的 client和 hystrix
中的代码

#######参考  https://blog.csdn.net/qq_20094989/article/details/79530995
启动service-turbine ，在浏览器中输入 http://localhost:8100/hystrix  进入熔断监控面板，然后输入
http://localhost:8300/actuator/turbine.stream，api-user 需要通过feign 调用下 api-shop，地址：localhost:8300/v1/users/feignShop
（注意
springboot 2.0 出现Unable to connect to Command Metric Stream. 所以使用1.5搭建

如果使用springboot2.0 和spring cloud Finchley.M8 版本搭建 使用（/actuator/hystrix.stream  而不是/hystrix.stream 为插入点），否则会出现 Unable to connect to Command Metric Stream）


##### Hystrix： DashBoard http://localhost:8100/hystrix  页面说明
        默认的集群监控：通过URLhttp://turbine-hostname:port/turbine.stream开启，实现对默认集群的监控。
        指定的集群监控：通过URLhttp://turbine-hostname:port/turbine.stream?cluster=[clusterName]开启，实现对clusterName集群的监控。
        单体应用的监控：通过URLhttp://hystrix-app:port/hystrix.stream开启，实现对具体某个服务实例的监控。
        
        Delay：该参数用来控制服务器上轮询监控信息的延迟时间，默认为2000毫秒，我们可以通过配置该属性来降低客户端的网络和CPU消耗。
        Title：该参数对应了上图头部标题Hystrix Stream之后的内容，默认会使用具体监控实例的URL，我们可以通过配置该信息来展示更合适的标题。
