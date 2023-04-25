@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("order-service", r -> r.path("/order/**")
                        .uri("http://localhost:8081"))
                .route("product-service", r -> r.path("/product/**")
                        .uri("http://localhost:8082"))
                .build();
    }

    @Bean
    public GatewayFilterFactory<RewritePathGatewayFilterFactory.Config> rewritePathGatewayFilterFactory() {
        return new RewritePathGatewayFilterFactory();
    }

    @Bean
    public GatewayFilter prefixPathGatewayFilter() {
        return new PrefixPathGatewayFilterFactory().apply("/api");
    }

    @Bean
    public GlobalFilter customGlobalFilter() {
        return new CustomGlobalFilter();
    }
}