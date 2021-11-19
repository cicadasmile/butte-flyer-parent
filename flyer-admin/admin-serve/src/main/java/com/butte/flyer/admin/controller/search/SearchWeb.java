package com.butte.flyer.admin.controller.search;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.butte.search.entity.DataVO;
import com.butte.search.entity.IndexVO;
import com.butte.search.entity.QueryVO;
import com.butte.search.operate.DataOperate;
import com.butte.search.operate.IndexOperate;
import com.butte.search.operate.QueryOperate;
import com.butte.search.operate.TemplateOperate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ElasticSearch组件
 * @author 公众号:知了一笑
 * @since 2021-11-03 20:17
 */
@RestController
@RequestMapping("/search")
public class SearchWeb {

    private static final Logger logger = LoggerFactory.getLogger(SearchWeb.class);

    private static final String INDEX_NAME = "search_user" ;

    @Resource
    private TemplateOperate templateOperate ;

    @Resource
    private IndexOperate indexOperate ;

    @Resource
    private DataOperate dataOperate ;

    @Resource
    private QueryOperate queryOperate ;

    /**
     * 管理索引操作
     * @since 2021-11-06 16:49
     */
    @GetMapping("/indexOperate")
    public String indexOperate (){
        boolean createFlag = templateOperate.createPut(SearchUser.class);
        boolean existsFlag = indexOperate.exists(new IndexVO(INDEX_NAME));
        boolean deleteFlag = indexOperate.delete(new IndexVO(INDEX_NAME));
        return "createFlag:"+createFlag+"；existsFlag:"+existsFlag+"；deleteFlag:"+deleteFlag;
    }

    /**
     * 索引结构操作
     * @since 2021-11-06 16:49
     */
    @GetMapping("/putOperate")
    public String putOperate (){

        boolean createFlag = templateOperate.createPut(SearchUser.class);

        if (createFlag){

            // 查询指定字段结构
            IndexVO mappingVO = new IndexVO(INDEX_NAME) ;
            mappingVO.setFields(new String[]{"id","userName"});
            Map<String, Object> mapping = indexOperate.getMapping(mappingVO) ;

            // 创建备份索引
            IndexVO createVO = new IndexVO(INDEX_NAME+"_bak") ;
            boolean createVOFlag = indexOperate.create(createVO) ;

            if (createVOFlag){

                // 添加备份索引结构
                Map<String, Object> properties = new HashMap<>() ;
                properties.put("properties",mapping) ;
                IndexVO putVO = new IndexVO(createVO.getIndexName()) ;
                putVO.setProperties(properties);
                indexOperate.putMapping(putVO) ;
            }
        }

        return "OK" ;
    }

    /**
     * 索引数据写入
     * @since 2021-11-06 16:49
     */
    @GetMapping("/dataInsert")
    public String dataInsert (){

        // 单条写入
        DataVO dataVO = new DataVO (INDEX_NAME) ;
        SearchUser searchUser = new SearchUser(1,"张三01") ;
        dataVO.setDataMap(BeanUtil.beanToMap(searchUser));
        dataOperate.insert(dataVO);

        // 批量写入
        DataVO bulkVO = new DataVO (INDEX_NAME) ;
        SearchUser user02 = new SearchUser(2,"张三02") ;
        SearchUser user03 = new SearchUser(3,"张三03") ;

        List<Map<String, Object>> dataList = new ArrayList<>() ;
        dataList.add(BeanUtil.beanToMap(user02));
        dataList.add(BeanUtil.beanToMap(user03));
        bulkVO.setDataList(dataList);

        dataOperate.bulkInsert(bulkVO);

        return "OK" ;
    }

    /**
     * 索引数据更新
     * @since 2021-11-06 16:49
     */
    @GetMapping("/dataUpdate")
    public String dataUpdate (){

        // 单条更新
        DataVO dataVO = new DataVO (INDEX_NAME) ;
        SearchUser updateUser = new SearchUser(1,"张三01", "活跃用户") ;
        dataVO.setDataMap(BeanUtil.beanToMap(updateUser));
        dataOperate.update(dataVO);

        // 批量更新
        DataVO updateVO = new DataVO (INDEX_NAME) ;
        SearchUser bulk02 = new SearchUser(2,"张三02", "普通用户") ;
        SearchUser bulk03 = new SearchUser(3,"张三03", "静默用户") ;

        List<Map<String, Object>> dataList = new ArrayList<>() ;
        dataList.add(BeanUtil.beanToMap(bulk02));
        dataList.add(BeanUtil.beanToMap(bulk03));
        updateVO.setDataList(dataList);
        dataOperate.bulkUpdate(updateVO);

        return "OK" ;
    }

    /**
     * 索引数据查询
     * @since 2021-11-06 16:49
     */
    @GetMapping("/dataQuery")
    public String dataQuery (){

        // 单条查询
        QueryVO queryVO = new QueryVO(INDEX_NAME) ;
        queryVO.setIndexId("1");
        Map<String, Object> idMap = queryOperate.getById(queryVO) ;
        logger.info("\n：{}", JSONUtil.toJsonPrettyStr(idMap));

        // 批量查询
        QueryVO bulkVO = new QueryVO(INDEX_NAME) ;
        List<String> indexIdList = new ArrayList<>() ;
        indexIdList.add("2") ;
        indexIdList.add("3") ;
        bulkVO.setIndexIdList(indexIdList);
        List<Map<String, Object>> idsMap = queryOperate.getByIds(bulkVO) ;
        logger.info("\n：{}", JSONUtil.toJsonPrettyStr(idsMap));

        // 分组查询
        QueryVO groupVO = new QueryVO(INDEX_NAME) ;
        groupVO.setGroupField("userName");
        Map<String, Object> groupMap = queryOperate.groupByField(groupVO);
        logger.info("\n：{}", JSONUtil.toJsonPrettyStr(groupMap));

        return "OK" ;
    }

    /**
     * 索引数据删除
     * @since 2021-11-06 16:49
     */
    @GetMapping("/dataDelete")
    public String dataDelete (){

        // 单条删除
        DataVO dataVO = new DataVO (INDEX_NAME) ;
        dataVO.setIndexId("1");
        dataOperate.delete(dataVO);

        // 批量删除
        DataVO bulkVO = new DataVO (INDEX_NAME) ;
        List<String> indexIdList = new ArrayList<>() ;
        indexIdList.add("2") ;
        indexIdList.add("3") ;
        bulkVO.setIndexIdList(indexIdList);
        dataOperate.bulkDelete(bulkVO);

        return "OK" ;
    }


}
