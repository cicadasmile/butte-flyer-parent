package com.butte.flyer.facade.beans.constant;

/**
 * 白名单授权类型
 * @author 公众号:知了一笑
 * @since 2021-09-05 20:48
 */
public enum AuthType {

    IP_AUTH(1, "IP地址"),
    API_AUTH(2, "接口地址");

    private int type;
    private String desc;

    AuthType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

}
