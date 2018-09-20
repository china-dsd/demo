package com.casic.oarp.datavisual.service.impl;

import com.casic.oarp.datavisual.common.ZXFKUtils;
import com.casic.oarp.datavisual.mapper.SsajqkbgbMapper;
import com.casic.oarp.datavisual.model.zxfk.LineChartModel;
import com.casic.oarp.datavisual.model.zxfk.SsajqkbgbModel;
import com.casic.oarp.datavisual.model.zxfk.SumModel;
import com.casic.oarp.datavisual.po.SsajqkbgbExample;
import com.casic.oarp.datavisual.po.SsajqkbgbWithBLOBs;
import com.casic.oarp.datavisual.service.AbsBaseServiceImpl;
import com.casic.oarp.datavisual.service.SsajqkbgbService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SsajqkbgbServiceImpl extends AbsBaseServiceImpl implements SsajqkbgbService {

    @Autowired
    private SsajqkbgbMapper ssajqkbgbMapper;

    /**
     * 根据时间查询数据
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public List<SsajqkbgbWithBLOBs> findByDate(Date startDate, Date endDate) {
        SsajqkbgbExample ssajqkbgbExample = new SsajqkbgbExample();
        ssajqkbgbExample.createCriteria().andProsecutionDateBetween(startDate, endDate);
        List<SsajqkbgbWithBLOBs> result = ssajqkbgbMapper.selectByExampleWithBLOBs(ssajqkbgbExample);
        return result;
    }

    // 法律案件的数量及金额
    @Override
    public List<LineChartModel> getSumCountAndAmountBarChart(Date startDate, Date endDate) {
        List<SsajqkbgbWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumCountMap = new TreeMap<>();
        Map<String, BigDecimal> sumAmountMap = new TreeMap<>();
        for (SsajqkbgbWithBLOBs item : dataList) {
            // 二级单位名称
            String unitName = item.getSusejdw();
            // 涉及两金金额
            BigDecimal amount = item.getSjljje();
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
        countLineChartModel.setTitle("法律案件数量");
        countLineChartModel.setData(countList);
        countLineChartModel.setName(nameList);
        amountLineChartModel.setTitle("涉及两金金额");
        amountLineChartModel.setData(amountList);
        amountLineChartModel.setName(nameList);
        List<LineChartModel> result = new ArrayList<>();
        result.add(countLineChartModel);
        result.add(amountLineChartModel);
        return result;
    }

    @Override
    public List<SumModel> getSumCountByType1() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<SsajqkbgbWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumMap = new HashMap<>();
        for (SsajqkbgbWithBLOBs item : dataList) {
            // 案件类型
            String type1 = item.getCaseType1();
            if (StringUtils.isEmpty(type1)) {
                type1 = "其他";
            }
            if (sumMap.containsKey(type1)) {
                sumMap.put(type1, sumMap.get(type1) + 1);
            } else {
                sumMap.put(type1, 1);
            }
        }

        return assembleSumModelList(sumMap);
    }

    @Override
    public List<SumModel> getSumCountByType2() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<SsajqkbgbWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumMap = new HashMap<>();
        for (SsajqkbgbWithBLOBs item : dataList) {
            // 案件类别
            String type2 = item.getCaseType2();
            if (StringUtils.isEmpty(type2)) {
                type2 = "其他";
            }
            if (sumMap.containsKey(type2)) {
                sumMap.put(type2, sumMap.get(type2) + 1);
            } else {
                sumMap.put(type2, 1);
            }
        }

        return assembleSumModelList(sumMap);
    }

    @Override
    public List<SumModel> getSumCountByFinishType() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<SsajqkbgbWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumMap = new HashMap<>();
        for (SsajqkbgbWithBLOBs item : dataList) {
            // 结案方式
            String finishType = item.getJafs();
            if (StringUtils.isEmpty(finishType)) {
                finishType = "其他";
            }
            if (sumMap.containsKey(finishType)) {
                sumMap.put(finishType, sumMap.get(finishType) + 1);
            } else {
                sumMap.put(finishType, 1);
            }
        }

        return assembleSumModelList(sumMap);
    }

    @Override
    public List<SumModel> getSumCountByRole(String sort, String role) {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<SsajqkbgbWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumMap = new HashMap<>();
        for (SsajqkbgbWithBLOBs item : dataList) {
            // 二级单位
            String unitName = item.getSusejdw();
            if (StringUtils.isEmpty(unitName)) {
                unitName = "其他";
            }
            // 诉讼角色
            String arbitration = item.getLitigationArbitration();
            // 原告或者被告
            if (role.equalsIgnoreCase(arbitration)) {
                if (sumMap.containsKey(unitName)) {
                    sumMap.put(unitName, sumMap.get(unitName) + 1);
                } else {
                    sumMap.put(unitName, 1);
                }
            }
        }

        List<SumModel> result = assembleSumModelList(sumMap);
        Collections.sort(result, new SumModelIntegerComparator(sort));
        return result;
    }

    @Override
    public List<SsajqkbgbModel> rankOfBidAmount() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<SsajqkbgbWithBLOBs> dataList = findByDate(startDate, endDate);
        List<SsajqkbgbModel> result = new ArrayList<>();
        SsajqkbgbModel ssajqkbgbModel = null;
        for (SsajqkbgbWithBLOBs item : dataList) {
            ssajqkbgbModel = new SsajqkbgbModel();
            BeanUtils.copyProperties(item, ssajqkbgbModel);
            result.add(ssajqkbgbModel);
        }
        // 法律案件标的金额排名
        Collections.sort(result, new Comparator<SsajqkbgbModel>() {

            @Override
            public int compare(SsajqkbgbModel o1, SsajqkbgbModel o2) {
                try {
                    BigDecimal oldValue = o1.getBdje();
                    BigDecimal newValue = o2.getBdje();
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

    @Override
    public List<SsajqkbgbModel> rankOfLJJEAmount() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<SsajqkbgbWithBLOBs> dataList = findByDate(startDate, endDate);
        List<SsajqkbgbModel> result = new ArrayList<>();
        SsajqkbgbModel ssajqkbgbModel = null;
        for (SsajqkbgbWithBLOBs item : dataList) {
            ssajqkbgbModel = new SsajqkbgbModel();
            BeanUtils.copyProperties(item, ssajqkbgbModel);
            result.add(ssajqkbgbModel);
        }
        // 涉及两金金额排名
        Collections.sort(result, new Comparator<SsajqkbgbModel>() {

            @Override
            public int compare(SsajqkbgbModel o1, SsajqkbgbModel o2) {
                try {
                    BigDecimal oldValue = o1.getSjljje();
                    BigDecimal newValue = o2.getSjljje();
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

    @Override
    public List<LineChartModel> getSumCountAndAmountForIndex() {
        Date endMonth = ZXFKUtils.nowMonth();
        Date startMonth = ZXFKUtils.calcDateByNowDate(endMonth, 0, -6, 0);
        List<SsajqkbgbWithBLOBs> dataList = findByDate(startMonth, endMonth);
        // 涉及两金金额
        Map<String, BigDecimal> sumSJLJJEAmountMap = new TreeMap<>();
        Map<String, Integer> sumCountMap = new TreeMap<>();
        for (SsajqkbgbWithBLOBs item : dataList) {
            SimpleDateFormat sdf = new SimpleDateFormat(ZXFKUtils.MONTH_PATTERN);
            String key = "";
            try {
                key = sdf.format(item.getProsecutionDate());
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            // 计算数量
            if (sumCountMap.containsKey(key)) {
                sumCountMap.put(key, sumCountMap.get(key) + 1);
            } else {
                sumCountMap.put(key, 1);
            }

            // 计算涉及两金金额
            BigDecimal SJLJJEAmount = item.getSjljje();
            if (null == SJLJJEAmount) {
                SJLJJEAmount = new BigDecimal(0);
            }
            if (sumSJLJJEAmountMap.containsKey(key)) {
                sumSJLJJEAmountMap.put(key, sumSJLJJEAmountMap.get(key).add(SJLJJEAmount));
            } else {
                sumSJLJJEAmountMap.put(key, SJLJJEAmount);
            }
        }

        Iterator<String> it = sumSJLJJEAmountMap.keySet().iterator();
        LineChartModel<BigDecimal> SJLJJEAmountLineChartModel = new LineChartModel<>();
        LineChartModel<Integer> countLineChartModel = new LineChartModel<>();
        List<String> nameList = new ArrayList<>();
        List<BigDecimal> SJLJJEAmountList = new ArrayList<>();
        List<Integer> countList = new ArrayList<>();
        while (it.hasNext()) {
            String key = it.next();
            nameList.add(key);
            SJLJJEAmountList.add(sumSJLJJEAmountMap.get(key));
            countList.add(sumCountMap.get(key));
        }
        SJLJJEAmountLineChartModel.setData(SJLJJEAmountList);
        SJLJJEAmountLineChartModel.setName(nameList);
        SJLJJEAmountLineChartModel.setTitle("涉及两金金额");
        countLineChartModel.setTitle("法律案件数量");
        countLineChartModel.setData(countList);
        countLineChartModel.setName(nameList);
        List<LineChartModel> result = new ArrayList<>();
        result.add(countLineChartModel);
        result.add(SJLJJEAmountLineChartModel);
        return result;
    }


    // 法律案件金额统计图
    @Override
    public List<LineChartModel> getSumAmountLineChart() {
        Date endMonth = ZXFKUtils.nowMonth();
        Date startMonth = ZXFKUtils.calcDateByNowDate(endMonth, 0, -10, 0);
        List<SsajqkbgbWithBLOBs> dataList = findByDate(startMonth, endMonth);
        // 涉及两金金额
        Map<String, BigDecimal> sumSJLJJEAmountMap = new TreeMap<>();
        // 挽回经济所示金额
        Map<String, BigDecimal> sumWHJJSSJEAmountMap = new TreeMap<>();
        for (SsajqkbgbWithBLOBs item : dataList) {
            SimpleDateFormat sdf = new SimpleDateFormat(ZXFKUtils.MONTH_PATTERN);
            String key = "";
            try {
                key = sdf.format(item.getProsecutionDate());
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            // 计算涉及两金金额
            BigDecimal SJLJJEAmount = item.getSjljje();
            if (null == SJLJJEAmount) {
                SJLJJEAmount = new BigDecimal(0);
            }
            if (sumSJLJJEAmountMap.containsKey(key)) {
                sumSJLJJEAmountMap.put(key, sumSJLJJEAmountMap.get(key).add(SJLJJEAmount));
            } else {
                sumSJLJJEAmountMap.put(key, SJLJJEAmount);
            }

            // 计算挽回经济所示金额
            BigDecimal WHJJSSJEAmount = item.getWhjjssje();
            if (null == WHJJSSJEAmount) {
                WHJJSSJEAmount = new BigDecimal(0);
            }
            if (sumWHJJSSJEAmountMap.containsKey(key)) {
                sumWHJJSSJEAmountMap.put(key, sumWHJJSSJEAmountMap.get(key).add(WHJJSSJEAmount));
            } else {
                sumWHJJSSJEAmountMap.put(key, WHJJSSJEAmount);
            }
        }

        Iterator<String> it = sumSJLJJEAmountMap.keySet().iterator();
        LineChartModel<BigDecimal> SJLJJEAmountLineChartModel = new LineChartModel<>();
        LineChartModel<BigDecimal> WHJJSSJEAamountLineChartModel = new LineChartModel<>();
        List<String> nameList = new ArrayList<>();
        List<BigDecimal> SJLJJEAmountList = new ArrayList<>();
        List<BigDecimal> WHJJSSJEAamountList = new ArrayList<>();
        while (it.hasNext()) {
            String key = it.next();
            nameList.add(key);
            SJLJJEAmountList.add(sumSJLJJEAmountMap.get(key));
            WHJJSSJEAamountList.add(sumWHJJSSJEAmountMap.get(key));
        }
        SJLJJEAmountLineChartModel.setData(SJLJJEAmountList);
        SJLJJEAmountLineChartModel.setName(nameList);
        SJLJJEAmountLineChartModel.setTitle("涉及两金金额");
        WHJJSSJEAamountLineChartModel.setTitle("挽回经济损失金额");
        WHJJSSJEAamountLineChartModel.setData(WHJJSSJEAamountList);
        WHJJSSJEAamountLineChartModel.setName(nameList);
        List<LineChartModel> result = new ArrayList<>();
        result.add(SJLJJEAmountLineChartModel);
        result.add(WHJJSSJEAamountLineChartModel);
        return result;
    }

}
