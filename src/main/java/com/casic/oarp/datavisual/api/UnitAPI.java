package com.casic.oarp.datavisual.api;

import com.casic.oarp.datavisual.common.ZXFKUtils;
import com.casic.oarp.datavisual.model.RestResult;
import com.casic.oarp.datavisual.model.zxfk.SumModel;
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
import java.util.List;

@RestController
@RequestMapping("/unit")
@CrossOrigin
@Api(value = "单位画像", tags = "单位画像")
public class UnitAPI {

    @Autowired
    private RiskEventsTrackService riskEventsTrackService;
    @Autowired
    private ShenjfxwtbajzggzqkbService shenjfxwtbajzggzqkbService;
    @Autowired
    private SsajqkbgbService ssajqkbgbService;
    @Autowired
    private ZhongdjcfxpgtjService zhongdjcfxpgtjService;

    /**
     * 柱状图
     *
     * @param type 1表示风险事件数量及金额，2表示审计问题记录，3法律案件数量及金额
     * @return
     */
    @ApiOperation(value = "柱状图")
    @RequestMapping(value = "/barChart", method = {RequestMethod.GET})
    public RestResult barChart(
            @ApiParam(value = "1表示风险事件数量及金额，2表示审计问题记录，3法律案件数量及金额")
            @RequestParam(defaultValue = "1") Integer type,
            String token) {
        RestResult result = new RestResult();
        switch (type) {
            case 1:
                result.setData(riskEventsTrackService.getSumCountAndAmountForIndex());
                break;
            case 2:
                result.setData(shenjfxwtbajzggzqkbService.getSumCountByStateForIndex());
                break;
            case 3:
                result.setData(ssajqkbgbService.getSumCountAndAmountForIndex());
                break;
        }
        return result;
    }

    /**
     * 饼状图
     *
     * @param type 1审计问题整改记录，2风险评估
     * @return
     */
    @ApiOperation(value = "饼状图")
    @RequestMapping(value = "/pieChart", method = {RequestMethod.GET})
    public RestResult<List<SumModel>> pieChart(
            @ApiParam(value = "1审计问题整改记录，2风险评估")
            @RequestParam(defaultValue = "1") Integer type,
            String token) {
        RestResult<List<SumModel>> result = new RestResult<>();
        Date nowDate = ZXFKUtils.nowDate();
        Date startDate = null;
        switch (type) {
            case 1:
                startDate = ZXFKUtils.calcDateByNowDate(nowDate, 0, -6, 0);
                result.setData(shenjfxwtbajzggzqkbService.getSumCountByZhenggjlForIndex(startDate, nowDate));
                break;
            case 2:
                startDate = ZXFKUtils.calcDateByNowDate(nowDate, 0, -6, 0);
                result.setData(zhongdjcfxpgtjService.getSumCountByRiskLevel(startDate, nowDate));
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
