package com.butte.flyer.gateway.service.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.butte.flyer.gateway.entity.ConfigRoute;
import com.butte.flyer.gateway.mapper.ConfigRouteMapper;
import com.butte.flyer.gateway.service.RouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 路由管理服务实现层
 * @author 公众号:知了一笑
 * @since 2021-11-14 18:16
 */
@Service
public class RouteServiceImpl implements RouteService {

    private static final Logger logger = LoggerFactory.getLogger(RouteServiceImpl.class) ;

    private final Map<String, RouteDefinition> routeMap = new ConcurrentHashMap<>();

    @Resource
    private ConfigRouteMapper configRouteMapper ;

    /**
     * 初始化加载
     * @since 2021-11-14 18:16
     */
    @PostConstruct
    public void initLoadRouter (){
        logger.info("【{}...begin...】","initLoadRouter");
        List<ConfigRoute> initList = configRouteMapper.selectList(new LambdaUpdateWrapper<>()) ;
        if (CollUtil.isNotEmpty(initList)){
            List<RouteDefinition> routeDefinitions = new ArrayList<>() ;
            initList.forEach(configRoute -> {
                routeDefinitions.add(buildRoute(configRoute));
            } );
            routeDefinitions.forEach(this::saveRouter);
        }
    }

    @Override
    public Collection<RouteDefinition> getRouteDefinitions (){
        return routeMap.values() ;
    }

    @Override
    public void saveRouter (RouteDefinition routeDefinition) {
        routeMap.put(routeDefinition.getId(), routeDefinition);
    }

    @Override
    public void removeRouter (String routeId) {
        routeMap.remove(routeId) ;
    }

    private RouteDefinition buildRoute (ConfigRoute configRoute){
        RouteDefinition routeDefinition = new RouteDefinition () ;
        routeDefinition.setId(configRoute.getRouteId());
        routeDefinition.setOrder(configRoute.getOrders());
        routeDefinition.setUri(URI.create(configRoute.getUri()));
        routeDefinition.setFilters(JSONUtil.parseArray(
                configRoute.getFilters()).toList(FilterDefinition.class));
        routeDefinition.setPredicates(JSONUtil.parseArray(
                configRoute.getPredicates()).toList(PredicateDefinition.class));
        Map<String,Object> metadata = new HashMap<>();
        JSONObject jsonObject = JSONUtil.toBean(configRoute.getMetadata(), JSONObject.class) ;
        if (!jsonObject.isEmpty()){
            for (String key:jsonObject.keySet()) {
                metadata.put(key,jsonObject.get(key)) ;
            }
        }
        routeDefinition.setMetadata(metadata);
        return routeDefinition ;
    }

}
