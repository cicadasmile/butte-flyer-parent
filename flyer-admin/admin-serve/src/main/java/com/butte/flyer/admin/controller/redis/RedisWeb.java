package com.butte.flyer.admin.controller.redis;

import com.butte.redis.operate.RedisKvOperate;
import com.butte.redis.operate.RedisLockOperate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Redis组件
 * @author 公众号:知了一笑
 * @since 2021-10-31 16:21
 */
@RestController
@RequestMapping("/redis")
public class RedisWeb {

    @Resource
    private RedisLockOperate redisLockOperate ;

    @Resource
    private RedisKvOperate redisKvOperate ;

    /**
     * KV结构数据缓存
     * @since 2021-11-06 16:48
     */
    @GetMapping("/tesKv")
    public String tesKv (){
        redisKvOperate.set("tesKey","tesValue",100) ;
        return redisKvOperate.get("tesKey") ;
    }

    /**
     * Lock加锁机制
     * @since 2021-11-06 16:48
     */
    @GetMapping("/tesLock")
    public Boolean tesLock () throws Exception {
        boolean lockFlag = true;
        try {
            lockFlag = redisLockOperate.tryLock("tesLock",2000L) ;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (lockFlag){
                Thread.sleep(10000);
                redisLockOperate.unlock("tesLock");
            }
        }
        return lockFlag ;
    }
}
