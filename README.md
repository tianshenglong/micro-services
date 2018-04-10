# micro-services
service-eureka 为服务注册中心
api-user 单独的一个服务或者系统

启动时 先启动服务注册中心，再启动api-user 访问 localhost:8761 会看到 api-user 已经注册到服务中
