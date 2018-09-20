package com.casic.oarp.datavisual.api;

import com.casic.oarp.datavisual.exception.ZXFKException;
import com.casic.oarp.datavisual.model.RestResult;
import com.casic.oarp.datavisual.model.zxfk.AuditProjectTableModel;
import com.casic.oarp.datavisual.model.zxfk.LineChartModel;
import com.casic.oarp.datavisual.model.zxfk.SumModel;
import com.casic.oarp.datavisual.service.ShenjfxwtbajzggzqkbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/shenji")
@CrossOrigin
@Api(value = "审计问题3+1", tags = "审计问题3+1")
public class ShenjfxwtbajzggzqkbAPI {

    @Autowired
    private ShenjfxwtbajzggzqkbService shenjfxwtbajzggzqkbService;

    /**
     * 审计项目信息
     *
     * @param type 1表示重要度排名 2表示审计问题金额
     * @return
     */
    @ApiOperation(value = "表格图")
    @RequestMapping(value = "/tableChart", method = {RequestMethod.GET})
    public RestResult<List<AuditProjectTableModel>> auditProjectInfoTable(
            @ApiParam(value = "1表示重要度排名 2表示审计问题金额")
            @RequestParam(defaultValue = "1") Integer type,
            String token) {
        RestResult<List<AuditProjectTableModel>> result = new RestResult<>();
        switch (type) {
            // 重要度排名
            case 1:
                result.setData(shenjfxwtbajzggzqkbService.rankOfImportance());
                break;
            // 审计问题金额
            case 2:
                result.setData(shenjfxwtbajzggzqkbService.amountOfAuditIssues());
                break;
        }
        return result;
    }

    /**
     * 审计项目信息饼状图
     *
     * @param type 1表示审计项目类型 2表示问题类型，3表示问题金额，4审计次数，5被审计次数
     * @return
     */
    @ApiOperation(value = "饼状图")
    @RequestMapping(value = "/pieChart", method = {RequestMethod.GET})
    public RestResult<List<SumModel>> pieChart(
            @ApiParam(value = "1表示审计项目类型, 2表示问题类型，3表示问题金额，4审计次数，5被审计次数")
            @RequestParam(defaultValue = "1") Integer type,
            @ApiParam(value = "asc表示升序，desc表示降序")
            @RequestParam(defaultValue = "asc") String sort,
            String token) {
        RestResult<List<SumModel>> result = new RestResult<>();
        switch (type) {
            // 审计项目类型
            case 1:
                result.setData(shenjfxwtbajzggzqkbService.getSumCountByAuditProjectType());
                break;
            case 2:
                result.setData(shenjfxwtbajzggzqkbService.getSumCountByIssueType());
                break;
            case 3:
                result.setData(shenjfxwtbajzggzqkbService.getSumAmountByIssueImportance());
                break;
            case 4:
                result.setData(shenjfxwtbajzggzqkbService.getSumCountByAudit(sort));
                break;
            case 5:
                result.setData(shenjfxwtbajzggzqkbService.getSumCountBuAudited(sort));
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
    @ApiOperation(value = "柱状图")
    @RequestMapping(value = "/barChart", method = {RequestMethod.GET})
    public RestResult<List<LineChartModel>> barChart(
            @ApiParam(value = "1表示各二级单位审计数量及金额,")
            @RequestParam(defaultValue = "1") Integer type,
            @ApiParam(value = "格式必须是yyyy-MM-dd")
            @RequestParam(defaultValue = "", required = false) String startDate,
            @ApiParam(value = "格式必须是yyyy-MM-dd")
            @RequestParam(defaultValue = "", required = false) String endDate,
            String token) throws ZXFKException {
        RestResult<List<LineChartModel>> result = new RestResult<>();
        switch (type) {
            // 各二级单位审计数量及金额
            case 1:
                Date[] dates = APIUtils.parseDate(startDate, endDate);
                result.setData(shenjfxwtbajzggzqkbService.getSumCountAndAmountBarChart(dates[0], dates[1]));
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
            @ApiParam(value = "1表示集团审计问题数量和金额趋势")
            @RequestParam(defaultValue = "1") Integer type,
            String token) {
        RestResult<List<LineChartModel>> result = new RestResult<>();
        switch (type) {
            // 集团审计问题数量和金额趋势(前10月数据，不含本月)
            case 1:
                result.setData(shenjfxwtbajzggzqkbService.getSumCountAndAmountLineChart());
                break;
        }
        return result;
    }
}

