package com.butte.flyer.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.butte.flyer.account.entity.PO.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户基础表 Mapper 接口
 * @author 公众号:知了一笑
 * @since 2021-09-05 15:32
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
