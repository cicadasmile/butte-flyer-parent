package com.butte.flyer.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局过滤器
 * @author 公众号:知了一笑
 * @since 2021-11-14 18:10
 */
@Order(-1)
@Component
public class GatewayFilter implements GlobalFilter {

    private static final Logger logger = LoggerFactory.getLogger(GatewayFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String uri = request.getURI().getPath() ;
        String host = String.valueOf(request.getHeaders().getHost()) ;
        logger.info("request host : {} , uri : {}",host,uri);
        return chain.filter(exchange);
    }
}
