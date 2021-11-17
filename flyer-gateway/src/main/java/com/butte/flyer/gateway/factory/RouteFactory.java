package com.butte.flyer.gateway.factory;

import com.butte.flyer.gateway.service.RouteService;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import javax.annotation.Resource;

/**
 * 路由中心
 * @author 公众号:知了一笑
 * @since 2021-11-14 18:08
 */
@Component
public class RouteFactory implements RouteDefinitionRepository {

    @Resource
    private RouteService routeService ;

    /**
     * 加载全部路由
     * @since 2021-11-14 18:08
     */
    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        return Flux.fromIterable(routeService.getRouteDefinitions());
    }

    /**
     * 添加路由
     * @since 2021-11-14 18:08
     */
    @Override
    public Mono<Void> save(Mono<RouteDefinition> routeMono) {
        return routeMono.flatMap(routeDefinition -> {
            routeService.saveRouter(routeDefinition);
            return Mono.empty();
        });
    }

    /**
     * 删除路由
     * @since 2021-11-14 18:08
     */
    @Override
    public Mono<Void> delete(Mono<String> idMono) {
        return idMono.flatMap(routeId -> {
            routeService.removeRouter(routeId);
            return Mono.empty();
        });
    }
}