package com.casic.oarp.datavisual.model.zxfk;

import java.io.Serializable;

// 统计模型
public class SumModel<T extends Object> implements Serializable {

    // 名称
    private String name;
    // 出现的次数
    private T value;

    public SumModel() {
    }

    public SumModel(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SumModel{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
