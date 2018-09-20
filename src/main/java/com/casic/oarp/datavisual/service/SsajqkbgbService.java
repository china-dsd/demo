package com.casic.oarp.datavisual.service;

import com.casic.oarp.datavisual.model.zxfk.LineChartModel;
import com.casic.oarp.datavisual.model.zxfk.SsajqkbgbModel;
import com.casic.oarp.datavisual.model.zxfk.SumModel;

import java.util.Date;
import java.util.List;

public interface SsajqkbgbService {

    List<LineChartModel> getSumCountAndAmountBarChart(Date startDate, Date endDate);

    List<SumModel> getSumCountByType1();

    List<SumModel> getSumCountByType2();

    // 法律案件金额统计图
    List<LineChartModel> getSumAmountLineChart();

    // 结案方式
    List<SumModel> getSumCountByFinishType();

    // 诉讼角色
    List<SumModel> getSumCountByRole(String sort, String role);

    // 标的金额排名
    List<SsajqkbgbModel> rankOfBidAmount();

    // 两金金额排名
    List<SsajqkbgbModel> rankOfLJJEAmount();

    List<LineChartModel> getSumCountAndAmountForIndex();
}
