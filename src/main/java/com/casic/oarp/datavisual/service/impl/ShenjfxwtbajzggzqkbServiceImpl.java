package com.casic.oarp.datavisual.service.impl;

import com.casic.oarp.datavisual.common.ZXFKUtils;
import com.casic.oarp.datavisual.mapper.ShenjfxwtbajzggzqkbMapper;
import com.casic.oarp.datavisual.model.zxfk.AuditProjectTableModel;
import com.casic.oarp.datavisual.model.zxfk.ImportanceLevelEnum;
import com.casic.oarp.datavisual.model.zxfk.LineChartModel;
import com.casic.oarp.datavisual.model.zxfk.SumModel;
import com.casic.oarp.datavisual.po.ShenjfxwtbajzggzqkbExample;
import com.casic.oarp.datavisual.po.ShenjfxwtbajzggzqkbWithBLOBs;
import com.casic.oarp.datavisual.service.AbsBaseServiceImpl;
import com.casic.oarp.datavisual.service.ShenjfxwtbajzggzqkbService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class ShenjfxwtbajzggzqkbServiceImpl extends AbsBaseServiceImpl implements ShenjfxwtbajzggzqkbService {

    @Autowired
    private ShenjfxwtbajzggzqkbMapper shenjfxwtbajzggzqkbMapper;

    /**
     * 根据时间查询数据
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public List<ShenjfxwtbajzggzqkbWithBLOBs> findByDate(Date startDate, Date endDate) {
        ShenjfxwtbajzggzqkbExample shenjfxwtbajzggzqkbExample = new ShenjfxwtbajzggzqkbExample();
        shenjfxwtbajzggzqkbExample.createCriteria().andWentfssjBetween(startDate, endDate);
        List<ShenjfxwtbajzggzqkbWithBLOBs> result = shenjfxwtbajzggzqkbMapper.selectByExampleWithBLOBs(shenjfxwtbajzggzqkbExample);
        return result;
    }

    // 重要度排名
    @Override
    public List<AuditProjectTableModel> rankOfImportance() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<ShenjfxwtbajzggzqkbWithBLOBs> dataList = findByDate(startDate, endDate);
        List<AuditProjectTableModel> result = new ArrayList<>();
        AuditProjectTableModel auditProjectTableModel = null;
        for (ShenjfxwtbajzggzqkbWithBLOBs item : dataList) {
            auditProjectTableModel = new AuditProjectTableModel();
            BeanUtils.copyProperties(item, auditProjectTableModel);
            result.add(auditProjectTableModel);
        }
        // 根据重要性排序
        Collections.sort(result, new Comparator<AuditProjectTableModel>() {
            @Override
            public int compare(AuditProjectTableModel o1, AuditProjectTableModel o2) {
                ImportanceLevelEnum oldImportanceLevelEnum = ZXFKUtils.getEnumByName(o1.getZhongycd());
                ImportanceLevelEnum newImportanceLevelEnum = ZXFKUtils.getEnumByName(o2.getZhongycd());
                return newImportanceLevelEnum.getCode().compareTo(oldImportanceLevelEnum.getCode());
            }
        });
        return result;
    }

    // 审计问题金额
    @Override
    public List<AuditProjectTableModel> amountOfAuditIssues() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<ShenjfxwtbajzggzqkbWithBLOBs> dataList = findByDate(startDate, endDate);
        List<AuditProjectTableModel> result = new ArrayList<>();
        AuditProjectTableModel auditProjectTableModel = null;
        for (ShenjfxwtbajzggzqkbWithBLOBs item : dataList) {
            auditProjectTableModel = new AuditProjectTableModel();
            BeanUtils.copyProperties(item, auditProjectTableModel);
            result.add(auditProjectTableModel);
        }
        Collections.sort(result, new Comparator<AuditProjectTableModel>() {
            @Override
            public int compare(AuditProjectTableModel o1, AuditProjectTableModel o2) {
                BigDecimal oldValue = o1.getWentje();
                BigDecimal newValue = o2.getWentje();
                if (oldValue == null) {
                    oldValue = new BigDecimal(0);
                }
                if (newValue == null) {
                    newValue = new BigDecimal(0);
                }
                return newValue.compareTo(oldValue);
            }
        });
        return result;
    }

    // 根据审计项目类型统计数量
    @Override
    public List<SumModel> getSumCountByAuditProjectType() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<ShenjfxwtbajzggzqkbWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumMap = new HashMap<>();
        for (ShenjfxwtbajzggzqkbWithBLOBs item : dataList) {
            String projectType = item.getShenjxmlx();
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

    // 根据问题类型统计数量
    @Override
    public List<SumModel> getSumCountByIssueType() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<ShenjfxwtbajzggzqkbWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumMap = new HashMap<>();
        for (ShenjfxwtbajzggzqkbWithBLOBs item : dataList) {
            String issueType = item.getWentlx();
            if (StringUtils.isEmpty(issueType)) {
                issueType = "其他";
            }
            if (sumMap.containsKey(issueType)) {
                sumMap.put(issueType, sumMap.get(issueType) + 1);
            } else {
                sumMap.put(issueType, 1);
            }
        }

        return assembleSumModelList(sumMap);
    }

    // 根据问题重要程度统计金额
    @Override
    public List<SumModel> getSumAmountByIssueImportance() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<ShenjfxwtbajzggzqkbWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, BigDecimal> sumMap = new HashMap<>();
        for (ShenjfxwtbajzggzqkbWithBLOBs item : dataList) {
            String issueImportance = item.getZhongycd();
            if (StringUtils.isEmpty(issueImportance)) {
                issueImportance = "其他";
            }
            // 有可能金额是null的，这里进行判断
            BigDecimal amount = item.getWentje();
            if (null == amount) {
                amount = new BigDecimal(0);
            }

            if (sumMap.containsKey(issueImportance)) {
                sumMap.put(issueImportance, sumMap.get(issueImportance).add(amount));
            } else {
                sumMap.put(issueImportance, amount);
            }
        }

        return assembleSumModelList(sumMap);
    }

    @Override
    public List<SumModel> getSumCountByAudit(String sort) {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<ShenjfxwtbajzggzqkbWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumMap = new HashMap<>();
        for (ShenjfxwtbajzggzqkbWithBLOBs item : dataList) {
            String auditUnit = item.getShenjdw();
            // 有可能是null的，这里进行判断
            if (StringUtils.isEmpty(auditUnit)) {
                auditUnit = "其他";
            }

            if (sumMap.containsKey(auditUnit)) {
                sumMap.put(auditUnit, sumMap.get(auditUnit) + 1);
            } else {
                sumMap.put(auditUnit, 1);
            }
        }

        List<SumModel> result = assembleSumModelList(sumMap);
        Collections.sort(result, new SumModelIntegerComparator(sort));
        return result;
    }

    @Override
    public List<SumModel> getSumCountBuAudited(String sort) {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<ShenjfxwtbajzggzqkbWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumMap = new HashMap<>();
        for (ShenjfxwtbajzggzqkbWithBLOBs item : dataList) {
            String auditedUnit = item.getBeisjdw();
            // 有可能是null的，这里进行判断
            if (StringUtils.isEmpty(auditedUnit)) {
                auditedUnit = "其他";
            }

            if (sumMap.containsKey(auditedUnit)) {
                sumMap.put(auditedUnit, sumMap.get(auditedUnit) + 1);
            } else {
                sumMap.put(auditedUnit, 1);
            }
        }

        List<SumModel> result = assembleSumModelList(sumMap);
        Collections.sort(result, new SumModelIntegerComparator(sort));
        return result;
    }

    // 按照每月进行累计，累计审计数量和金额，key是二级单位名称
    @Override
    public List<LineChartModel> getSumCountAndAmountBarChart(Date startDate, Date endDate) {
        List<ShenjfxwtbajzggzqkbWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumCountMap = new TreeMap<>();
        Map<String, BigDecimal> sumAmountMap = new TreeMap<>();
        for (ShenjfxwtbajzggzqkbWithBLOBs item : dataList) {
            // 二级单位名称
            String unitName = item.getSusejdw();
            BigDecimal amount = item.getWentje();
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
        amountLineChartModel.setData(amountList);
        amountLineChartModel.setName(nameList);
        amountLineChartModel.setTitle("审计事件金额");
        countLineChartModel.setTitle("审计事件数量");
        countLineChartModel.setData(countList);
        countLineChartModel.setName(nameList);
        List<LineChartModel> result = new ArrayList<>();
        result.add(countLineChartModel);
        result.add(amountLineChartModel);
        return result;
    }

    @Override
    public List<LineChartModel> getSumCountAndAmountLineChart() {
        Date endMonth = ZXFKUtils.nowMonth();
        Date startMonth = ZXFKUtils.calcDateByNowDate(endMonth, 0, -10, 0);
        List<ShenjfxwtbajzggzqkbWithBLOBs> dataList = findByDate(startMonth, endMonth);
        Map<String, Integer> sumCountMap = new TreeMap<>();
        Map<String, BigDecimal> sumAmountMap = new TreeMap<>();
        for (ShenjfxwtbajzggzqkbWithBLOBs item : dataList) {
            SimpleDateFormat sdf = new SimpleDateFormat(ZXFKUtils.MONTH_PATTERN);
            String key = "";
            try {
                key = sdf.format(item.getWentfssj());
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
            // 计算金额
            BigDecimal amount = item.getWentje();
            if (null == amount) {
                amount = new BigDecimal(0);
            }
            if (sumAmountMap.containsKey(key)) {
                sumAmountMap.put(key, sumAmountMap.get(key).add(amount));
            } else {
                sumAmountMap.put(key, amount);
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
        amountLineChartModel.setData(amountList);
        amountLineChartModel.setName(nameList);
        amountLineChartModel.setTitle("审计问题金额");
        countLineChartModel.setTitle("审计问题数量");
        countLineChartModel.setData(countList);
        countLineChartModel.setName(nameList);
        List<LineChartModel> result = new ArrayList<>();
        result.add(countLineChartModel);
        result.add(amountLineChartModel);
        return result;
    }

    @Override
    public List<LineChartModel> getSumCountByStateForIndex() {
        Date endMonth = ZXFKUtils.nowMonth();
        Date startMonth = ZXFKUtils.calcDateByNowDate(endMonth, 0, -10, 0);
        List<ShenjfxwtbajzggzqkbWithBLOBs> dataList = findByDate(startMonth, endMonth);
        // 整改已完成
        Map<String, Integer> sumFinishCountMap = new TreeMap<>();
        // 整改进行中
        Map<String, Integer> sumIngCountMap = new TreeMap<>();
        // 整改已延期
        Map<String, Integer> sumDelayCountMap = new TreeMap<>();

        for (ShenjfxwtbajzggzqkbWithBLOBs item : dataList) {
            SimpleDateFormat sdf = new SimpleDateFormat(ZXFKUtils.MONTH_PATTERN);
            String key = "";
            try {
                key = sdf.format(item.getWentfssj());
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            // 初始化
            fillZeroToMapValue(key, sumFinishCountMap);
            fillZeroToMapValue(key, sumDelayCountMap);
            fillZeroToMapValue(key, sumIngCountMap);

            Date dateFromDB = item.getJihwcsj();
            if (null == dateFromDB) {
                continue;
            }

            Date nowDate = new Date();
            String sZhenggzt = item.getZhenggzt();
            if (!StringUtils.isEmpty(sZhenggzt) && (sZhenggzt.contains("已整改") || sZhenggzt.contains("已完成"))) {
                // 整改已完成
                sumFinishCountMap.put(key, sumFinishCountMap.get(key) + 1);
            } else if (nowDate.getTime() <= dateFromDB.getTime()) {
                // 整改进行中
                sumIngCountMap.put(key, sumIngCountMap.get(key) + 1);
            } else if (nowDate.getTime() > dateFromDB.getTime()) {
                // 整改已延期
                sumDelayCountMap.put(key, sumDelayCountMap.get(key) + 1);
            }
        }

        Iterator<String> it = sumFinishCountMap.keySet().iterator();
        LineChartModel<Integer> countFinishLineChartModel = new LineChartModel<>();
        LineChartModel<Integer> countIngLineChartModel = new LineChartModel<>();
        LineChartModel<Integer> countDelayChartModel = new LineChartModel<>();
        List<String> nameList = new ArrayList<>();
        List<Integer> countFinishList = new ArrayList<>();
        List<Integer> countIngList = new ArrayList<>();
        List<Integer> countDelayList = new ArrayList<>();
        while (it.hasNext()) {
            String key = it.next();
            nameList.add(key);
            countDelayList.add(sumDelayCountMap.get(key));
            countFinishList.add(sumFinishCountMap.get(key));
            countIngList.add(sumIngCountMap.get(key));
        }
        countFinishLineChartModel.setData(countFinishList);
        countFinishLineChartModel.setName(nameList);
        countFinishLineChartModel.setTitle("整改已完成");
        countIngLineChartModel.setData(countIngList);
        countIngLineChartModel.setName(nameList);
        countIngLineChartModel.setTitle("整改进行中");
        countDelayChartModel.setData(countDelayList);
        countDelayChartModel.setName(nameList);
        countDelayChartModel.setTitle("整改已延期");
        List<LineChartModel> result = new ArrayList<>();
        result.add(countIngLineChartModel);
        result.add(countFinishLineChartModel);
        result.add(countDelayChartModel);
        return result;
    }

    @Override
    public List<SumModel> getSumCountByZhenggjlForIndex(Date startDate, Date endDate) {
        List<ShenjfxwtbajzggzqkbWithBLOBs> dataList = findByDate(startDate, endDate);
        // 整改进行中
        Integer sumIngCount = 0;
        // 整改将到期
        Integer sumWillFinishCount = 0;
        // 整改已延期
        Integer sumDelayCount = 0;
        // 整改已完成
        Integer sumFinishCount = 0;

        Date nowDate = new ZXFKUtils().nowDate();
        for (ShenjfxwtbajzggzqkbWithBLOBs item : dataList) {
            // 计划完成时间
            Date oJhwcDate = item.getJihwcsj();
            if (null == oJhwcDate) {
                continue;
            }

            String sZhenggzt = item.getZhenggzt();
            long days = (oJhwcDate.getTime() - nowDate.getTime()) / (1000 * 3600 * 24);
            if (!StringUtils.isEmpty(sZhenggzt) && (sZhenggzt.contains("已整改") || sZhenggzt.contains("已完成"))) {
                // 整改已完成
                sumFinishCount++;
            } else if (days > 0 && days <= 30) {
                // 整改将到期
                sumWillFinishCount++;
            } else if (days > 7) {
                // 整改进行中
                sumIngCount++;
            } else if (days < 0) {
                // 整改已延期
                sumDelayCount++;
            }
        }

        List<SumModel> result = new ArrayList<>();
        result.add(new SumModel("整改进行中", sumIngCount));
        result.add(new SumModel("整改将到期", sumWillFinishCount));
        result.add(new SumModel("整改已延期", sumDelayCount));
        result.add(new SumModel("整改已完成", sumFinishCount));
        return result;
    }

    // 不一定有这个类别的，所以给初始值为0
    private void fillZeroToMapValue(String key, Map<String, Integer> sumFinishCountMap) {
        if (!sumFinishCountMap.containsKey(key)) {
            sumFinishCountMap.put(key, 0);
        }
    }


    /**
     * 权限验证
     */
    public void authority(List<ShenjfxwtbajzggzqkbWithBLOBs> dataList) {
        // 预留空方法 TODO

    }


}
