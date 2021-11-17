package com.butte.flyer.gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 路由实体描述
 * @author 公众号:知了一笑
 * @since 2021-11-14 18:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigRoute {

    private Integer id;

    private String routeId;

    private String uri;

    private Integer orders;

    private String metadata;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    private String predicates;

    private String filters;

}