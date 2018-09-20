package com.casic.oarp.datavisual.api;

import com.casic.oarp.datavisual.common.ZXFKUtils;
import com.casic.oarp.datavisual.model.RestResult;
import com.casic.oarp.datavisual.service.RiskEventsTrackService;
import com.casic.oarp.datavisual.service.ShenjfxwtbajzggzqkbService;
import com.casic.oarp.datavisual.service.SsajqkbgbService;
import com.casic.oarp.datavisual.service.ZhongdjcfxpgtjService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/index")
@CrossOrigin
@Api(value = "首页", tags = "首页")
public class IndexAPI {

    // 风险事件
    @Autowired
    private RiskEventsTrackService riskEventsTrackService;
    // 审计问题3+1
    @Autowired
    private ShenjfxwtbajzggzqkbService shenjfxwtbajzggzqkbService;
    // 风险评估
    @Autowired
    private ZhongdjcfxpgtjService zhongdjcfxpgtjService;
    // 法务系统
    @Autowired
    private SsajqkbgbService ssajqkbgbService;

    /**
     * 柱状图
     *
     * @param type 1表示风险事件数量及金额，2表示法律案件的数量及金额，3审计问题状态记录，4风险事件挽回金额
     * @return
     */
    @ApiOperation(value = "柱状图")
    @RequestMapping(value = "/barChart", method = {RequestMethod.GET})
    public RestResult barChart(
            @ApiParam(value = "1表示风险事件数量及金额，2表示法律案件的数量及金额，3审计问题状态记录，4风险事件挽回金额")
            @RequestParam(defaultValue = "1") Integer type,
            String token) {
        RestResult result = new RestResult();
        switch (type) {
            case 1:
                result.setData(riskEventsTrackService.getSumCountAndAmountForIndex());
                break;
            case 2:
                result.setData(ssajqkbgbService.getSumCountAndAmountForIndex());
                break;
            case 3:
                result.setData(shenjfxwtbajzggzqkbService.getSumCountByStateForIndex());
                break;
            case 4:
                result.setData(riskEventsTrackService.getSumAmountLineChart());
                break;
        }
        return result;
    }

    /**
     * 饼状图
     *
     * @param
     * @return
     */
    @ApiOperation(value = "饼状图")
    @RequestMapping(value = "/pieChart", method = {RequestMethod.GET})
    public RestResult pieChart(
            @ApiParam(value = "1表示风险评估, 2表示审计问题整改记录")
            @RequestParam(defaultValue = "1") Integer type,
            String token) {
        RestResult result = new RestResult();
        Date nowDate = ZXFKUtils.nowDate();
        Date startDate = null;
        switch (type) {
            case 1:
                startDate = ZXFKUtils.calcDateByNowDate(nowDate, 0, -6, 0);
                result.setData(zhongdjcfxpgtjService.getSumCountByRiskLevel(startDate, nowDate));
                break;
            case 2:
                startDate = ZXFKUtils.calcDateByNowDate(nowDate, 0, -6, 0);
                result.setData(shenjfxwtbajzggzqkbService.getSumCountByZhenggjlForIndex(startDate, nowDate));
                break;
        }
        return result;
    }

    /**
     * 字符云
     *
     * @param
     * @return
     */
    @ApiOperation(value = "字符云")
    @RequestMapping(value = "/wordCloud", method = {RequestMethod.GET})
    public RestResult wordCloud(
            @ApiParam(value = "1重大风险事件")
            @RequestParam(defaultValue = "1") Integer type,
            String token) {
        RestResult result = new RestResult();
        switch (type) {
            case 1:
                result.setData(zhongdjcfxpgtjService.getRiskAmountOver500w());
                break;
        }
        return result;
    }

}
