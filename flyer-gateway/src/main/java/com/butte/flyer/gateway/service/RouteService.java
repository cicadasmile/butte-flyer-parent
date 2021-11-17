package com.butte.flyer.gateway.service;

import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.Collection;

/**
 * 路由管理服务层
 * @author 公众号:知了一笑
 * @since 2021-11-14 18:16
 */
public interface RouteService {

    Collection<RouteDefinition> getRouteDefinitions() ;

    void saveRouter(RouteDefinition routeDefinition) ;

    void removeRouter(String routeId) ;

}
