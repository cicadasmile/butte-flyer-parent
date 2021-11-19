package com.butte.flyer.admin.controller.search;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户索引实体
 * @author 公众号:知了一笑
 * @since 2021-11-03 20:21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "search_user",type = "_doc")
public class SearchUser implements Serializable {

    private static final long serialVersionUID=1L;

    public SearchUser (Integer id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public SearchUser (Integer id, String userName, String remark) {
        this.id = id;
        this.userName = userName;
        this.remark = remark;
    }

    @Field(type= FieldType.Long)
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @Field(type=FieldType.Keyword)
    @ApiModelProperty(value = "用户名称")
    private String userName;

    @Field(type = FieldType.Text)
    @ApiModelProperty(value = "账号密码")
    private String passWord;

    @Field(type=FieldType.Keyword)
    @ApiModelProperty(value = "手机号")
    private String phone;

    @Field(type=FieldType.Keyword)
    @ApiModelProperty(value = "邮箱")
    private String email;

    @Field(type=FieldType.Date)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @Field(type=FieldType.Date)
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @Field(type=FieldType.Integer)
    @ApiModelProperty(value = "状态-1启用，2禁用，3删除")
    private Integer state;

    @Field(type=FieldType.Text)
    @ApiModelProperty(value = "备注信息")
    private String remark;

}
