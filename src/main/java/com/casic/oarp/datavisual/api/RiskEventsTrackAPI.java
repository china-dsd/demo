package com.casic.oarp.datavisual.api;

import com.casic.oarp.datavisual.exception.ZXFKException;
import com.casic.oarp.datavisual.model.RestResult;
import com.casic.oarp.datavisual.model.zxfk.LineChartModel;
import com.casic.oarp.datavisual.model.zxfk.RiskEventTableModel;
import com.casic.oarp.datavisual.model.zxfk.SumModel;
import com.casic.oarp.datavisual.service.RiskEventsTrackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/fengxsj")
@Api(value="风险事件", tags = "风险事件")
public class RiskEventsTrackAPI {

    @Autowired
    private RiskEventsTrackService riskEventsTrackService;

    /**
     * 表格图
     *
     * @param type 1表示重要度排名 2表示审计问题金额
     * @return
     */
    @ApiOperation(value = "表格图")
    @RequestMapping(value = "/tableChart", method = {RequestMethod.GET})
    public RestResult<List<RiskEventTableModel>> tableChart(
            @ApiParam(value = "1表示风险事件金额排名 2表示创建时间排名")
            @RequestParam(defaultValue = "1") Integer type,
            String token) {
        RestResult<List<RiskEventTableModel>> result = new RestResult<>();
        switch (type) {
            // 风险事件金额排名
            case 1:
                result.setData(riskEventsTrackService.rankOfAmount());
                break;
            // 创建时间排名
            case 2:
                result.setData(riskEventsTrackService.rankOfCreateTime());
                break;
        }
        return result;
    }


    /**
     * 审计项目信息饼状图
     * @param type 1表示风险事件类型, 2表示风险事件进展，3表示是否进入诉讼程序占比，4各二级单位统计期末金额占比，5各二级单位避免金额损失占比
     * @return
     */
    @ApiOperation(value = "饼状图")
    @RequestMapping(value = "/pieChart", method = {RequestMethod.GET})
    public RestResult<List<SumModel>> auditProjectInfoPie(
            @ApiParam(value = "1表示风险事件类型, 2表示风险事件进展，3表示是否进入诉讼程序占比，4各二级单位统计期末金额占比，5各二级单位避免金额损失占比")
            @RequestParam(defaultValue = "1") Integer type,
            @ApiParam(value = "asc表示升序，desc表示降序")
            @RequestParam(defaultValue = "asc") String sort,
            String token) {
        RestResult<List<SumModel>> result = new RestResult<>();
        switch (type) {
            case 1 :
                result.setData(riskEventsTrackService.getSumCountByEventType());
                break;
            case 2:
                result.setData(riskEventsTrackService.getSumCountByEventProgress());
                break;
            case 3:
                result.setData(riskEventsTrackService.getSumCountByLawsuit());
                break;
            case 4:
                result.setData(riskEventsTrackService.getSumAmountByEndMoney(sort));
                break;
            case 5:
                result.setData(riskEventsTrackService.getDiffAmountByMoney(sort));
                break;
        }
        return result;
    }

    /**
     * 审计项目信息柱状图
     * @param type
     * @return
     */
    @ApiOperation(value = "柱状图")
    @RequestMapping(value = "/barChart", method = {RequestMethod.GET})
    public RestResult<List<LineChartModel>> barChart (
            @ApiParam(value = "1表示各二级单位风险事件金额")
            @RequestParam(defaultValue = "1") Integer type,
            @RequestParam(defaultValue = "", required = false) String startDate,
            @RequestParam(defaultValue = "", required = false) String endDate,
            String token) throws ZXFKException {
        RestResult<List<LineChartModel>> result = new RestResult<>();
        switch (type) {
            // 各二级单位风险事件金额
            case 1 :
                // 解析时间对象
                Date[] dates = APIUtils.parseDate(startDate, endDate);
                result.setData(riskEventsTrackService.getSumAmountBarChart(dates[0], dates[1]));
                break;
        }
        return result;
    }

    /**
     * 审计项目信息折线图
     *
     * @param type
     * @return
     */
    @ApiOperation(value = "折线图")
    @RequestMapping(value = "/lineChart", method = {RequestMethod.GET})
    public RestResult<List<LineChartModel>> lineChart(
            @ApiParam(value = "1表示风险事件金额对比图")
            @RequestParam(defaultValue = "1") Integer type,
            String token) {
        RestResult<List<LineChartModel>> result = new RestResult<>();
        switch (type) {
            // 风险事件金额对比图(前10月数据，不含本月)
            case 1:
                result.setData(riskEventsTrackService.getSumAmountLineChart());
                break;
        }
        return result;
    }


}
