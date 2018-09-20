package com.casic.oarp.datavisual.service;

import com.casic.oarp.datavisual.model.zxfk.LineChartModel;
import com.casic.oarp.datavisual.model.zxfk.SumModel;
import com.casic.oarp.datavisual.model.zxfk.ZhongdjcfxpgtjModel;

import java.util.Date;
import java.util.List;

public interface ZhongdjcfxpgtjService {

    List<ZhongdjcfxpgtjModel> rankOfEventRisk();

    List<LineChartModel> getSumCountAndAmountBarChart(Date date, Date date1);

    List<LineChartModel> getSumAmountBarChart();

    List<SumModel> getSumCountByProjectType();

    List<SumModel> getSumCountByRiskLevel(Date startDate, Date endDate);

    List<SumModel> getSumCountByAdoptions();

    /**
     * 按照采纳情况进行统计
     * @param sort
     * @param isYes 是否采纳
     * @return
     */
    List<SumModel> getSumCountByAdoptionsed(String sort, boolean isYes);

    List<SumModel> getRiskAmountOver500w();
}
