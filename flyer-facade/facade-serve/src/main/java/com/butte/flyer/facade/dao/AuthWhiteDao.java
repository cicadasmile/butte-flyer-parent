package com.butte.flyer.facade.dao;

import com.butte.flyer.facade.entity.PO.AuthWhite;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 授权白名单Dao层
 * @author 公众号:知了一笑
 * @since 2021-09-05 17:33
 */
public interface AuthWhiteDao extends IService<AuthWhite> {

    /**
     * 查询指定类型数据集合
     * @param authType 类型
     * @return java.util.List<com.butte.flyer.facade.entity.PO.AuthWhite>
     * @since 2021-09-05 17:37
     */
    List<AuthWhite> listByType (Integer authType) ;

}
