# cycfc-demo-ribbon（已抛弃）
使用 ribbon + hystrix 方式，进行负载均衡及服务熔断处理，该方式处理不够优雅，已抛弃。现采用 feign 方式进行处理。
- 端口：8765
    - 监控流：http://localhost:8765/actuator/hystrix.stream