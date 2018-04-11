# micro-services
service-eureka 为服务注册中心
common-core 为公共工具类
api-user 单独的一个服务或者系统

启动时 先启动服务注册中心，再启动api-user、api-shop 访问 localhost:8761 会看到 api-user、 已经注册到服务中api-shop

api-shop 中定义了一个controller，里面只有一个sayHello的方法，api-user通过feign 调用此方法，详见api-user下的feign包中的 client和 hystrix
中的代码
