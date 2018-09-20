package com.casic.oarp.datavisual.service;

import com.casic.oarp.datavisual.model.zxfk.AuditProjectTableModel;
import com.casic.oarp.datavisual.model.zxfk.LineChartModel;
import com.casic.oarp.datavisual.model.zxfk.SumModel;

import java.util.Date;
import java.util.List;

public interface ShenjfxwtbajzggzqkbService {

    List<AuditProjectTableModel> rankOfImportance();

    List<AuditProjectTableModel> amountOfAuditIssues();

    List<SumModel> getSumCountByAuditProjectType();

    List<SumModel> getSumAmountByIssueImportance();

    List<SumModel> getSumCountByIssueType();

    List<SumModel> getSumCountByAudit(String sort);

    List<SumModel> getSumCountBuAudited(String sort);

    List<LineChartModel> getSumCountAndAmountBarChart(Date startDate, Date endDate);

    List<LineChartModel> getSumCountAndAmountLineChart();

    List<LineChartModel> getSumCountByStateForIndex();

    // 整改记录
    List<SumModel> getSumCountByZhenggjlForIndex(Date startDate, Date endDate);
}
