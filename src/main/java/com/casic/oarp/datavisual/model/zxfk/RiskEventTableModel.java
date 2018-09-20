package com.casic.oarp.datavisual.model.zxfk;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RiskEventTableModel implements Serializable {

    // 二级单位名称
    private String nameTwo;

    // 三级单位名称
    private String nameThree;

    // 事件类型
    private String eventsType;

    // 风险事件最新进展
    private String riskEventsProgress;

    // 是否进入诉讼程序
    private String lawsuitProcedure;

    // 期初金额
    private BigDecimal beginMoney;

    // 期末金额
    private BigDecimal endMoney;

    // 事件是否终结
    private String eventsEnd;

    // 主管领导
    private String leader;

    // 责任部门
    private String relevantDepartment;

    // 责任人
    private String relevantPerson;

    // 创建时间
    private Date zCreatetime;

    public String getNameTwo() {
        return nameTwo;
    }

    public void setNameTwo(String nameTwo) {
        this.nameTwo = nameTwo;
    }

    public String getNameThree() {
        return nameThree;
    }

    public void setNameThree(String nameThree) {
        this.nameThree = nameThree;
    }

    public String getEventsType() {
        return eventsType;
    }

    public void setEventsType(String eventsType) {
        this.eventsType = eventsType;
    }

    public String getRiskEventsProgress() {
        return riskEventsProgress;
    }

    public void setRiskEventsProgress(String riskEventsProgress) {
        this.riskEventsProgress = riskEventsProgress;
    }

    public String getLawsuitProcedure() {
        return lawsuitProcedure;
    }

    public void setLawsuitProcedure(String lawsuitProcedure) {
        this.lawsuitProcedure = lawsuitProcedure;
    }

    public BigDecimal getBeginMoney() {
        return beginMoney;
    }

    public void setBeginMoney(BigDecimal beginMoney) {
        this.beginMoney = beginMoney;
    }

    public BigDecimal getEndMoney() {
        return endMoney;
    }

    public void setEndMoney(BigDecimal endMoney) {
        this.endMoney = endMoney;
    }

    public String getEventsEnd() {
        return eventsEnd;
    }

    public void setEventsEnd(String eventsEnd) {
        this.eventsEnd = eventsEnd;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getRelevantDepartment() {
        return relevantDepartment;
    }

    public void setRelevantDepartment(String relevantDepartment) {
        this.relevantDepartment = relevantDepartment;
    }

    public String getRelevantPerson() {
        return relevantPerson;
    }

    public void setRelevantPerson(String relevantPerson) {
        this.relevantPerson = relevantPerson;
    }

    public Date getzCreatetime() {
        return zCreatetime;
    }

    public void setzCreatetime(Date zCreatetime) {
        this.zCreatetime = zCreatetime;
    }

    public RiskEventTableModel() {
    }

    @Override
    public String toString() {
        return "RiskEventTableModel{" +
                "nameTwo='" + nameTwo + '\'' +
                ", nameThree='" + nameThree + '\'' +
                ", eventsType='" + eventsType + '\'' +
                ", riskEventsProgress='" + riskEventsProgress + '\'' +
                ", lawsuitProcedure='" + lawsuitProcedure + '\'' +
                ", beginMoney=" + beginMoney +
                ", endMoney=" + endMoney +
                ", eventsEnd='" + eventsEnd + '\'' +
                ", leader='" + leader + '\'' +
                ", relevantDepartment='" + relevantDepartment + '\'' +
                ", relevantPerson='" + relevantPerson + '\'' +
                ", zCreatetime=" + zCreatetime +
                '}';
    }
}
