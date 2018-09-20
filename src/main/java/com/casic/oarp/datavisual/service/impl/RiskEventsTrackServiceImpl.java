package com.casic.oarp.datavisual.service.impl;

import com.casic.oarp.datavisual.common.ZXFKUtils;
import com.casic.oarp.datavisual.mapper.RiskEventsTrackMapper;
import com.casic.oarp.datavisual.model.zxfk.LineChartModel;
import com.casic.oarp.datavisual.model.zxfk.RiskEventTableModel;
import com.casic.oarp.datavisual.model.zxfk.SumModel;
import com.casic.oarp.datavisual.po.RiskEventsTrackExample;
import com.casic.oarp.datavisual.po.RiskEventsTrackWithBLOBs;
import com.casic.oarp.datavisual.service.AbsBaseServiceImpl;
import com.casic.oarp.datavisual.service.RiskEventsTrackService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RiskEventsTrackServiceImpl extends AbsBaseServiceImpl implements RiskEventsTrackService {

    @Autowired
    private RiskEventsTrackMapper riskEventsTrackMapper;

    private List<RiskEventsTrackWithBLOBs> findByDate(Date startDate, Date endDate) {
        RiskEventsTrackExample riskEventsTrackExample = new RiskEventsTrackExample();
        riskEventsTrackExample.createCriteria().andZCreatetimeBetween(startDate, endDate);
        List<RiskEventsTrackWithBLOBs> result = riskEventsTrackMapper.selectByExampleWithBLOBs(
                riskEventsTrackExample);
        return result;
    }

    @Override
    public List<LineChartModel> getSumAmountLineChart() {
        Date endMonth = ZXFKUtils.nowMonth();
        Date startMonth = ZXFKUtils.calcDateByNowDate(endMonth, 0, -10, 0);
        List<RiskEventsTrackWithBLOBs> dataList = findByDate(startMonth, endMonth);
        Map<String, BigDecimal> sumAmountBeginMap = new TreeMap<>();
        Map<String, BigDecimal> sumAmountEndMap = new TreeMap<>();
        for (RiskEventsTrackWithBLOBs item : dataList) {
            SimpleDateFormat sdf = new SimpleDateFormat(ZXFKUtils.MONTH_PATTERN);
            String key = "";
            try {
                key = sdf.format(item.getzCreatetime());
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            assembleAmountMap(item, key, sumAmountBeginMap, sumAmountEndMap);
        }

        Iterator<String> it = sumAmountBeginMap.keySet().iterator();
        LineChartModel<BigDecimal> amountBeginLineChartModel = new LineChartModel<>();
        LineChartModel<BigDecimal> amountEndLineChartModel = new LineChartModel<>();
        List<String> nameList = new ArrayList<>();
        List<BigDecimal> amountBeginList = new ArrayList<>();
        List<BigDecimal> amountEndList = new ArrayList<>();
        while (it.hasNext()) {
            String key = it.next();
            nameList.add(key);
            amountBeginList.add(sumAmountBeginMap.get(key));
            amountEndList.add(sumAmountEndMap.get(key));
        }
        amountBeginLineChartModel.setData(amountBeginList);
        amountBeginLineChartModel.setName(nameList);
        amountBeginLineChartModel.setTitle("期初金额");
        amountEndLineChartModel.setTitle("期末金额");
        amountEndLineChartModel.setData(amountEndList);
        amountEndLineChartModel.setName(nameList);
        List<LineChartModel> result = new ArrayList<>();
        result.add(amountBeginLineChartModel);
        result.add(amountEndLineChartModel);
        return result;
    }

    @Override
    public List<LineChartModel> getSumAmountBarChart(Date startDate, Date endDate) {
        List<RiskEventsTrackWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, BigDecimal> sumAmountBeginMap = new TreeMap<>();
        Map<String, BigDecimal> sumAmountEndMap = new TreeMap<>();
        for (RiskEventsTrackWithBLOBs item : dataList) {
            // 二级单位名称
            assembleAmountMap(item, item.getNameTwo(), sumAmountBeginMap, sumAmountEndMap);
        }

        Iterator<String> it = sumAmountBeginMap.keySet().iterator();
        LineChartModel<BigDecimal> amountBeginLineChartModel = new LineChartModel<>();
        LineChartModel<BigDecimal> amountEndLineChartModel = new LineChartModel<>();
        List<String> nameList = new ArrayList<>();
        List<BigDecimal> amountEndList = new ArrayList<>();
        List<BigDecimal> amountBeginList = new ArrayList<>();
        while (it.hasNext()) {
            String key = it.next();
            nameList.add(key);
            amountBeginList.add(sumAmountBeginMap.get(key));
            amountEndList.add(sumAmountEndMap.get(key));
        }
        amountBeginLineChartModel.setData(amountBeginList);
        amountBeginLineChartModel.setName(nameList);
        amountBeginLineChartModel.setTitle("期初金额");
        amountEndLineChartModel.setTitle("期末金额");
        amountEndLineChartModel.setData(amountEndList);
        amountEndLineChartModel.setName(nameList);
        List<LineChartModel> result = new ArrayList<>();
        result.add(amountBeginLineChartModel);
        result.add(amountEndLineChartModel);
        return result;
    }

    @Override
    public List<SumModel> getSumCountByEventType() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<RiskEventsTrackWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumMap = new TreeMap<>();
        for (RiskEventsTrackWithBLOBs item : dataList) {
            String eventType = item.getEventsType();
            if (StringUtils.isEmpty(eventType)) {
                eventType = "其他";
            }
            if (sumMap.containsKey(eventType)) {
                sumMap.put(eventType, sumMap.get(eventType) + 1);
            } else {
                sumMap.put(eventType, 1);
            }
        }

        return assembleSumModelList(sumMap);
    }

    @Override
    public List<SumModel> getSumCountByEventProgress() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<RiskEventsTrackWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumMap = new TreeMap<>();
        for (RiskEventsTrackWithBLOBs item : dataList) {
            String eventProgress = item.getRiskEventsProgress();
            if (StringUtils.isEmpty(eventProgress)) {
                eventProgress = "其他";
            }
            if (sumMap.containsKey(eventProgress)) {
                sumMap.put(eventProgress, sumMap.get(eventProgress) + 1);
            } else {
                sumMap.put(eventProgress, 1);
            }
        }

        return assembleSumModelList(sumMap);
    }

    @Override
    public List<SumModel> getSumCountByLawsuit() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<RiskEventsTrackWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumMap = new TreeMap<>();
        for (RiskEventsTrackWithBLOBs item : dataList) {
            String lawsuitProcedure = item.getLawsuitProcedure();
            if (StringUtils.isEmpty(lawsuitProcedure)) {
                lawsuitProcedure = "其他";
            }
            if (sumMap.containsKey(lawsuitProcedure)) {
                sumMap.put(lawsuitProcedure, sumMap.get(lawsuitProcedure) + 1);
            } else {
                sumMap.put(lawsuitProcedure, 1);
            }
        }

        return assembleSumModelList(sumMap);
    }

    @Override
    public List<SumModel> getSumAmountByEndMoney(String sort) {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<RiskEventsTrackWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, BigDecimal> sumAmountEndMap = new TreeMap<>();
        for (RiskEventsTrackWithBLOBs item : dataList) {
            String nameTwo = item.getNameTwo();
            if (StringUtils.isEmpty(nameTwo)) {
                nameTwo = "其他";
            }
            BigDecimal endAmount = item.getEndMoney();
            if (null == endAmount) {
                endAmount = new BigDecimal(0);
            }

            if (sumAmountEndMap.containsKey(nameTwo)) {
                sumAmountEndMap.put(nameTwo, sumAmountEndMap.get(nameTwo).add(endAmount));
            } else {
                sumAmountEndMap.put(nameTwo, endAmount);
            }
        }

        List<SumModel> result = assembleSumModelList(sumAmountEndMap);
        Collections.sort(result, new SumModelBigDecimalComparator(sort));
        return result;
    }

    @Override
    public List<SumModel> getDiffAmountByMoney(String sort) {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<RiskEventsTrackWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, BigDecimal> sumAmountBeginMap = new TreeMap<>();
        Map<String, BigDecimal> sumAmountEndMap = new TreeMap<>();
        for (RiskEventsTrackWithBLOBs item : dataList) {
            String nameTwo = item.getNameTwo();
            if (StringUtils.isEmpty(nameTwo)) {
                nameTwo = "其他";
            }
            BigDecimal endAmount = item.getEndMoney();
            if (null == endAmount) {
                endAmount = new BigDecimal(0);
            }

            if (sumAmountEndMap.containsKey(nameTwo)) {
                sumAmountEndMap.put(nameTwo, sumAmountEndMap.get(nameTwo).add(endAmount));
            } else {
                sumAmountEndMap.put(nameTwo, endAmount);
            }

            BigDecimal beginAmount = item.getBeginMoney();
            if (null == beginAmount) {
                beginAmount = new BigDecimal(0);
            }

            if (sumAmountBeginMap.containsKey(nameTwo)) {
                sumAmountBeginMap.put(nameTwo, sumAmountBeginMap.get(nameTwo).add(beginAmount));
            } else {
                sumAmountBeginMap.put(nameTwo, beginAmount);
            }
        }

        Iterator<String> it = sumAmountBeginMap.keySet().iterator();
        List<SumModel> result = new ArrayList<>();
        while (it.hasNext()) {
            String key = it.next();
            SumModel sumModel = new SumModel();
            sumModel.setName(key);
            // 差值
            sumModel.setValue(sumAmountEndMap.get(key).subtract(sumAmountBeginMap.get(key)));
            result.add(sumModel);
        }
        Collections.sort(result, new SumModelBigDecimalComparator(sort));
        return result;
    }

    // 风险事件金额排名
    @Override
    public List<RiskEventTableModel> rankOfAmount() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<RiskEventsTrackWithBLOBs> dataList = findByDate(startDate, endDate);
        List<RiskEventTableModel> result = new ArrayList<>();
        RiskEventTableModel riskEventTableModel = null;
        for (RiskEventsTrackWithBLOBs item : dataList) {
            riskEventTableModel = new RiskEventTableModel();
            BeanUtils.copyProperties(item, riskEventTableModel);
            result.add(riskEventTableModel);
        }
        // 根据endMoney降序排序
        Collections.sort(result, new Comparator<RiskEventTableModel>() {
            @Override
            public int compare(RiskEventTableModel o1, RiskEventTableModel o2) {
                try {
                    BigDecimal oldValue = o1.getEndMoney();
                    BigDecimal newValue = o2.getEndMoney();
                    if (oldValue == null) {
                        oldValue = new BigDecimal(0);
                    }
                    if (newValue == null) {
                        newValue = new BigDecimal(0);
                    }
                    return newValue.compareTo(oldValue);
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        });
        return result;
    }

    // 创建时间排名
    @Override
    public List<RiskEventTableModel> rankOfCreateTime() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<RiskEventsTrackWithBLOBs> dataList = findByDate(startDate, endDate);
        List<RiskEventTableModel> result = new ArrayList<>();
        RiskEventTableModel riskEventTableModel = null;
        for (RiskEventsTrackWithBLOBs item : dataList) {
            riskEventTableModel = new RiskEventTableModel();
            BeanUtils.copyProperties(item, riskEventTableModel);
            result.add(riskEventTableModel);
        }
        // 根据创建时间降序排序
        Collections.sort(result, new Comparator<RiskEventTableModel>() {
            @Override
            public int compare(RiskEventTableModel o1, RiskEventTableModel o2) {
                try {
                    Date oldDate = o1.getzCreatetime();
                    Date newDate = o2.getzCreatetime();
                    if (oldDate == null) {
                        oldDate = new Date();
                    }
                    if (newDate == null) {
                        newDate = new Date();
                    }
                    return newDate.compareTo(oldDate);
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        });
        return result;
    }

    @Override
    public List<LineChartModel> getSumCountAndAmountForIndex() {
        Date endMonth = ZXFKUtils.nowMonth();
        Date startMonth = ZXFKUtils.calcDateByNowDate(endMonth, 0, -10, 0);
        List<RiskEventsTrackWithBLOBs> dataList = findByDate(startMonth, endMonth);
        Map<String, Integer> sumCountMap = new TreeMap<>();
        Map<String, BigDecimal> sumAmountEndMap = new TreeMap<>();
        for (RiskEventsTrackWithBLOBs item : dataList) {
            SimpleDateFormat sdf = new SimpleDateFormat(ZXFKUtils.MONTH_PATTERN);
            String key = "";
            try {
                key = sdf.format(item.getzCreatetime());
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            // 设置数量
            if (sumCountMap.containsKey(key)) {
                sumCountMap.put(key, sumCountMap.get(key) + 1);
            } else {
                sumCountMap.put(key, 1);
            }

            BigDecimal amountEndMoney = item.getEndMoney();
            if (null == amountEndMoney) {
                amountEndMoney = new BigDecimal(0);
            }
            if (sumAmountEndMap.containsKey(key)) {
                sumAmountEndMap.put(key, sumAmountEndMap.get(key).add(amountEndMoney));
            } else {
                sumAmountEndMap.put(key, amountEndMoney);
            }
        }

        Iterator<String> it = sumCountMap.keySet().iterator();
        LineChartModel<Integer> countLineChartModel = new LineChartModel<>();
        LineChartModel<BigDecimal> amountEndLineChartModel = new LineChartModel<>();
        List<String> nameList = new ArrayList<>();
        List<Integer> countList = new ArrayList<>();
        List<BigDecimal> amountEndList = new ArrayList<>();
        while (it.hasNext()) {
            String key = it.next();
            nameList.add(key);
            countList.add(sumCountMap.get(key));
            amountEndList.add(sumAmountEndMap.get(key));
        }
        countLineChartModel.setData(countList);
        countLineChartModel.setName(nameList);
        countLineChartModel.setTitle("风险事件数量");
        amountEndLineChartModel.setTitle("风险事件金额");
        amountEndLineChartModel.setData(amountEndList);
        amountEndLineChartModel.setName(nameList);
        List<LineChartModel> result = new ArrayList<>();
        result.add(countLineChartModel);
        result.add(amountEndLineChartModel);
        return result;
    }

    private void assembleAmountMap(RiskEventsTrackWithBLOBs item, String key,
                                   Map<String, BigDecimal> sumAmountBeginMap, Map<String, BigDecimal> sumAmountEndMap) {
        BigDecimal amountBegin = item.getBeginMoney();
        BigDecimal amountEnd = item.getEndMoney();
        // 有可能是null的，这里进行判断
        if (StringUtils.isEmpty(key)) {
            key = "其他";
        }

        // 期初金额
        if (null == amountBegin) {
            amountBegin = new BigDecimal(0);
        }
        if (sumAmountBeginMap.containsKey(key)) {
            sumAmountBeginMap.put(key, sumAmountBeginMap.get(key).add(amountBegin));
        } else {
            sumAmountBeginMap.put(key, amountBegin);
        }

        // 期末金额
        if (null == amountEnd) {
            amountEnd = new BigDecimal(0);
        }
        if (sumAmountEndMap.containsKey(key)) {
            sumAmountEndMap.put(key, sumAmountEndMap.get(key).add(amountEnd));
        } else {
            sumAmountEndMap.put(key, amountEnd);
        }
    }

}
