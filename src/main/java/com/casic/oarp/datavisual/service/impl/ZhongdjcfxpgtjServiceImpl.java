package com.casic.oarp.datavisual.service.impl;

import com.casic.oarp.datavisual.common.ZXFKConsts;
import com.casic.oarp.datavisual.common.ZXFKUtils;
import com.casic.oarp.datavisual.mapper.ZhongdjcfxpgtjMapper;
import com.casic.oarp.datavisual.model.zxfk.LineChartModel;
import com.casic.oarp.datavisual.model.zxfk.SumModel;
import com.casic.oarp.datavisual.model.zxfk.ZhongdjcfxpgtjModel;
import com.casic.oarp.datavisual.po.Zhongdjcfxpgtj;
import com.casic.oarp.datavisual.po.ZhongdjcfxpgtjExample;
import com.casic.oarp.datavisual.service.AbsBaseServiceImpl;
import com.casic.oarp.datavisual.service.ZhongdjcfxpgtjService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ZhongdjcfxpgtjServiceImpl extends AbsBaseServiceImpl implements ZhongdjcfxpgtjService {

    @Autowired
    private ZhongdjcfxpgtjMapper zhongdjcfxpgtjMapper;

    /**
     * 根据时间查询数据
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public List<Zhongdjcfxpgtj> findByDate(Date startDate, Date endDate) {
        ZhongdjcfxpgtjExample zhongdjcfxpgtjExample = new ZhongdjcfxpgtjExample();
        zhongdjcfxpgtjExample.createCriteria().andZCreatetimeBetween(startDate, endDate);
        List<Zhongdjcfxpgtj> result = zhongdjcfxpgtjMapper.selectByExample(zhongdjcfxpgtjExample);
        return result;
    }

    // 事项风险程度排名
    @Override
    public List<ZhongdjcfxpgtjModel> rankOfEventRisk() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<Zhongdjcfxpgtj> dataList = findByDate(startDate, endDate);
        List<ZhongdjcfxpgtjModel> result = new ArrayList<>();
        ZhongdjcfxpgtjModel zhongdjcfxpgtjModel = null;
        for (Zhongdjcfxpgtj item : dataList) {
            zhongdjcfxpgtjModel = new ZhongdjcfxpgtjModel();
            BeanUtils.copyProperties(item, zhongdjcfxpgtjModel);
            result.add(zhongdjcfxpgtjModel);
        }
        // 根据项风险程度降序排序
        Collections.sort(result, new Comparator<ZhongdjcfxpgtjModel>() {

            @Override
            public int compare(ZhongdjcfxpgtjModel o1, ZhongdjcfxpgtjModel o2) {
                try {
                    String sOldValue = o1.getShixfxcd();
                    String sNewValue = o2.getShixfxcd();
                    if (StringUtils.isEmpty(sOldValue)) {
                        sOldValue = "";
                    }
                    if (StringUtils.isEmpty(sNewValue)) {
                        sNewValue = "";
                    }
                    return parseRiskLevel(sNewValue).compareTo(parseRiskLevel(sOldValue));
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        });
        return result;
    }

    /**
     * 获取重要程度的ID
     *
     * @param riskLevel
     * @return
     */
    private Integer parseRiskLevel(String riskLevel) {
        if (riskLevel.contains(ZXFKConsts.HIGH_RISK_LEVEL)) {
            return 3;
        } else if (riskLevel.contains(ZXFKConsts.MIDDLE_RISK_LEVEL)) {
            return 2;
        } else if (riskLevel.contains(ZXFKConsts.LOW_RISK_LEVEL)) {
            return 1;
        } else {
            return 0;
        }
    }


    // 风险事项的数量及审计金额
    @Override
    public List<LineChartModel> getSumCountAndAmountBarChart(Date startDate, Date endDate) {
        List<Zhongdjcfxpgtj> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumCountMap = new TreeMap<>();
        Map<String, BigDecimal> sumAmountMap = new TreeMap<>();
        for (Zhongdjcfxpgtj item : dataList) {
            // 二级单位名称
            String unitName = item.getErjdwmc();
            // 审计金额
            BigDecimal amount = item.getShejje();
            // 有可能是null的，这里进行判断
            if (StringUtils.isEmpty(unitName)) {
                unitName = "其他";
            }

            // 计算数量
            if (sumCountMap.containsKey(unitName)) {
                sumCountMap.put(unitName, sumCountMap.get(unitName) + 1);
            } else {
                sumCountMap.put(unitName, 1);
            }
            // 计算金额
            if (null == amount) {
                amount = new BigDecimal(0);
            }
            if (sumAmountMap.containsKey(unitName)) {
                sumAmountMap.put(unitName, sumAmountMap.get(unitName).add(amount));
            } else {
                sumAmountMap.put(unitName, amount);
            }
        }

        Iterator<String> it = sumCountMap.keySet().iterator();
        LineChartModel<BigDecimal> amountLineChartModel = new LineChartModel<>();
        LineChartModel<Integer> countLineChartModel = new LineChartModel<>();
        List<String> nameList = new ArrayList<>();
        List<Integer> countList = new ArrayList<>();
        List<BigDecimal> amountList = new ArrayList<>();
        while (it.hasNext()) {
            String key = it.next();
            nameList.add(key);
            countList.add(sumCountMap.get(key));
            amountList.add(sumAmountMap.get(key));
        }
        countLineChartModel.setTitle("风险事项数量");
        countLineChartModel.setData(countList);
        countLineChartModel.setName(nameList);
        amountLineChartModel.setTitle("审计金额");
        amountLineChartModel.setData(amountList);
        amountLineChartModel.setName(nameList);
        List<LineChartModel> result = new ArrayList<>();
        result.add(countLineChartModel);
        result.add(amountLineChartModel);
        return result;
    }

    // 风险事项统计图
    @Override
    public List<LineChartModel> getSumAmountBarChart() {
        Date endMonth = ZXFKUtils.nowMonth();
        Date startMonth = ZXFKUtils.calcDateByNowDate(endMonth, 0, -10, 0);
        List<Zhongdjcfxpgtj> dataList = findByDate(startMonth, endMonth);
        Map<String, Integer> lowSumCountMap = new TreeMap<>();
        Map<String, Integer> middleSumCountMap = new TreeMap<>();
        Map<String, Integer> highSumCountMap = new TreeMap<>();
        for (Zhongdjcfxpgtj item : dataList) {
            SimpleDateFormat sdf = new SimpleDateFormat(ZXFKUtils.MONTH_PATTERN);
            String key = "";
            try {
                key = sdf.format(item.getzCreatetime());
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            String riskLevel = item.getShixfxcd();
            if (StringUtils.isEmpty(riskLevel)) {
                continue;
            }

            switch (riskLevel) {
                case "高":
                    // highSumCountMap计算数量
                    increaseMapValue(highSumCountMap, key, 1, 1);
                    increaseMapValue(middleSumCountMap, key, 0, 0);
                    increaseMapValue(lowSumCountMap, key, 0, 0);
                    break;
                case "中":
                    // middleSumCountMap计算数量
                    increaseMapValue(highSumCountMap, key, 0, 0);
                    increaseMapValue(middleSumCountMap, key, 1, 1);
                    increaseMapValue(lowSumCountMap, key, 0, 0);
                    break;
                case "低":
                    // lowSumCountMap计算数量
                    increaseMapValue(highSumCountMap, key, 0, 0);
                    increaseMapValue(middleSumCountMap, key, 0, 0);
                    increaseMapValue(lowSumCountMap, key, 1, 1);
                    break;
            }
        }

        // 选择size最大的一个，否则顺序有问题
        Iterator<String> it = lowSumCountMap.keySet().iterator();
        if (middleSumCountMap.size() > lowSumCountMap.size()) {
            it = middleSumCountMap.keySet().iterator();
        } else if (highSumCountMap.size() > middleSumCountMap.size()) {
            it = highSumCountMap.keySet().iterator();
        }
        List<String> nameList = new ArrayList<>();
        List<Integer> highList = new ArrayList<>();
        List<Integer> middleList = new ArrayList<>();
        List<Integer> lowList = new ArrayList<>();
        while (it.hasNext()) {
            String key = it.next();
            nameList.add(key);
            lowList.add(lowSumCountMap.get(key));
            middleList.add(middleSumCountMap.get(key));
            highList.add(highSumCountMap.get(key));
        }

        LineChartModel<Integer> lowLineChartModel = new LineChartModel<>();
        lowLineChartModel.setName(nameList);
        lowLineChartModel.setData(lowList);
        lowLineChartModel.setTitle("低");

        LineChartModel<Integer> middleLineChartModel = new LineChartModel<>();
        middleLineChartModel.setName(nameList);
        middleLineChartModel.setData(middleList);
        middleLineChartModel.setTitle("中");

        LineChartModel<Integer> highLineChartModel = new LineChartModel<>();
        highLineChartModel.setData(highList);
        highLineChartModel.setName(nameList);
        highLineChartModel.setTitle("高");

        List<LineChartModel> result = new ArrayList<>();
        result.add(highLineChartModel);
        result.add(middleLineChartModel);
        result.add(lowLineChartModel);
        return result;
    }

    private void increaseMapValue(Map<String, Integer> map, String key, Integer value, Integer initValue) {
        if (map.containsKey(key)) {
            map.put(key, map.get(key) + value);
        } else {
            map.put(key, initValue);
        }
    }

    @Override
    public List<SumModel> getSumCountByProjectType() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<Zhongdjcfxpgtj> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumMap = new HashMap<>();
        for (Zhongdjcfxpgtj item : dataList) {
            String projectType = item.getShixlx();
            if (StringUtils.isEmpty(projectType)) {
                projectType = "其他";
            }
            if (sumMap.containsKey(projectType)) {
                sumMap.put(projectType, sumMap.get(projectType) + 1);
            } else {
                sumMap.put(projectType, 1);
            }
        }

        return assembleSumModelList(sumMap);
    }

    @Override
    public List<SumModel> getSumCountByRiskLevel(Date startDate, Date endDate) {
        List<Zhongdjcfxpgtj> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumMap = new HashMap<>();
        // 初始化
        sumMap.put("高", 0);
        sumMap.put("中", 0);
        sumMap.put("低", 0);
        for (Zhongdjcfxpgtj item : dataList) {
            String riskLevel = item.getShixfxcd();
            if (StringUtils.isEmpty(riskLevel)) {
                riskLevel = "其他";
            }
            //  规范下叫法，如果不是一个字的话
            if (riskLevel.contains(ZXFKConsts.HIGH_RISK_LEVEL)) {
                riskLevel = "高";
            } else if (riskLevel.contains(ZXFKConsts.MIDDLE_RISK_LEVEL)) {
                riskLevel = "中";
            } else if (riskLevel.contains(ZXFKConsts.LOW_RISK_LEVEL)) {
                riskLevel = "低";
            } else {
            continue;
        }
        // 数量+1
            sumMap.put(riskLevel, sumMap.get(riskLevel) + 1);
        }

        return assembleSumModelList(sumMap);
    }


    /**
     * 是，否，其他三种
     *
     * @return
     */
    @Override
    public List<SumModel> getSumCountByAdoptions() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<Zhongdjcfxpgtj> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumMap = new HashMap<>();
        for (Zhongdjcfxpgtj item : dataList) {
            String adoptions = item.getCainqk();
            if (StringUtils.isEmpty(adoptions)) {
                adoptions = "其他";
            }
            // 兼容 字段填写不明确
            if (adoptions.contains("否") || adoptions.contains("不")) {
                adoptions = "否";
            } else if (adoptions.contains("是") || adoptions.contains("采纳")) {
                adoptions = "是";
            } else {
                adoptions = "其他";
            }

            if (sumMap.containsKey(adoptions)) {
                sumMap.put(adoptions, sumMap.get(adoptions) + 1);
            } else {
                sumMap.put(adoptions, 1);
            }
        }

        return assembleSumModelList(sumMap);
    }

    @Override
    public List<SumModel> getSumCountByAdoptionsed(String sort, boolean isYes) {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<Zhongdjcfxpgtj> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumMap = new HashMap<>();
        for (Zhongdjcfxpgtj item : dataList) {
            String unitName = item.getErjdwmc();
            // 有可能是null的，这里进行判断
            if (StringUtils.isEmpty(unitName)) {
                unitName = "其他";
            }
            String adoptions = item.getCainqk();
            if (isYes) {
                // 只统计采纳的，过滤掉不采纳的
                if (StringUtils.isEmpty(adoptions) || adoptions.contains("否") || adoptions.contains("不")) {
                    continue;
                }
            } else {
                // 只统计未被采纳的，过滤掉采纳的
                if (StringUtils.isEmpty(adoptions) || adoptions.contains("是") || adoptions.contains("采纳")) {
                    continue;
                }
            }

            if (sumMap.containsKey(unitName)) {
                sumMap.put(unitName, sumMap.get(unitName) + 1);
            } else {
                sumMap.put(unitName, 1);
            }
        }

        List<SumModel> result = assembleSumModelList(sumMap);
        Collections.sort(result, new SumModelIntegerComparator(sort));
        return result;
    }

    @Override
    public List<SumModel> getRiskAmountOver500w() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<Zhongdjcfxpgtj> dataList = findByDate(startDate, endDate);
        List<SumModel> result = new ArrayList<>();
        for (Zhongdjcfxpgtj item : dataList) {
            String unitName = item.getErjdwmc();
            // 有可能是null的，这里进行判断
            if (StringUtils.isEmpty(unitName)) {
                unitName = "其他";
            }

            BigDecimal amounFromDB = item.getShejje();
            // 大于500W
            if (null != amounFromDB && amounFromDB.compareTo(new BigDecimal(5000000)) > 0) {
                // 单位名称:审议事项名称+金额
                result.add(new SumModel(unitName + ":" + item.getShenysxmc(), amounFromDB));
            }
        }
        return result;
    }


}
