package com.casic.oarp.datavisual.model.zxfk;

import java.io.Serializable;
import java.util.List;

/**
 * 折线图模型
 */
public class LineChartModel<T> implements Serializable {

    private String title; // 名称
    private List<T> data; // 对应多个列的值
    private List<String> name; // 对应多个列的名字

    public LineChartModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LineChartModel{" +
                "title='" + title + '\'' +
                ", data=" + data +
                ", name=" + name +
                '}';
    }
}
