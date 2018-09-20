package com.casic.oarp.datavisual.model.zxfk;

public enum ImportanceLevelEnum {

    DEFAULT("默认值", 0),
    GUANLIJIANYI("管理建议", 1),
    XIANQIZHENGGAI("限期整改", 10),
    TONGBAOPIPING("通报批评", 20),
    WEIFAWEIJICHUFAN("违法违纪处分", 30),
    KENENGDAOZHIWEIFAZUOLAI("可能导致违法坐牢", 40);


    // 数字越大，重要性越大
    private String name;
    private Integer code;

    ImportanceLevelEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ImportanceLevelEnum{" +
                "name='" + name + '\'' +
                ", code=" + code +
                '}';
    }

}
