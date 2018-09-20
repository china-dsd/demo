package com.casic.oarp.datavisual.service;

import com.casic.oarp.datavisual.model.zxfk.LineChartModel;
import com.casic.oarp.datavisual.model.zxfk.RiskEventTableModel;
import com.casic.oarp.datavisual.model.zxfk.SumModel;

import java.util.Date;
import java.util.List;


public interface RiskEventsTrackService {
    List<LineChartModel> getSumAmountLineChart();

    List<LineChartModel> getSumAmountBarChart(Date startDate, Date endDate);

    List<SumModel> getSumCountByEventType();

    List<SumModel> getSumCountByEventProgress();

    List<SumModel> getSumCountByLawsuit();

    List<SumModel> getSumAmountByEndMoney(String sort);

    List<SumModel> getDiffAmountByMoney(String sort);

    List<RiskEventTableModel> rankOfAmount();

    List<RiskEventTableModel> rankOfCreateTime();

    List<LineChartModel> getSumCountAndAmountForIndex();
}
