package com.casic.oarp.datavisual.model.zxfk;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SsajqkbgbModel implements Serializable {

    // 二级单位
    private String susejdw;
    // 我方涉案单位
    private String bqysadw;
    // 对方涉案单位
    private String dfsadw;
    // 诉讼角色
    private String litigationArbitration;
    // 案件类型
    private String caseType1;
    // 案件类别
    private String caseType2;
    // 审判阶段
    private String scjd;
    // 立案时间
    private Date prosecutionDate;
    // 撤销案件时间
    private Date closingTime;
    // 标的金额
    private BigDecimal bdje;
    // 案件状态
    private String byxzByjaZzbl;
    // 更新时间
    private Date updateTime;
    // 涉及两金金额
    private BigDecimal sjljje;
    // 挽回经济损失金额
    private BigDecimal whjjssje;
    // 化解两金金额
    private BigDecimal hjljje;
    // 结案方式
    private String jafs;

    public SsajqkbgbModel() {
    }

    public String getSusejdw() {
        return susejdw;
    }

    public void setSusejdw(String susejdw) {
        this.susejdw = susejdw;
    }

    public String getBqysadw() {
        return bqysadw;
    }

    public void setBqysadw(String bqysadw) {
        this.bqysadw = bqysadw;
    }

    public String getDfsadw() {
        return dfsadw;
    }

    public void setDfsadw(String dfsadw) {
        this.dfsadw = dfsadw;
    }

    public String getLitigationArbitration() {
        return litigationArbitration;
    }

    public void setLitigationArbitration(String litigationArbitration) {
        this.litigationArbitration = litigationArbitration;
    }

    public String getCaseType1() {
        return caseType1;
    }

    public void setCaseType1(String caseType1) {
        this.caseType1 = caseType1;
    }

    public String getCaseType2() {
        return caseType2;
    }

    public void setCaseType2(String caseType2) {
        this.caseType2 = caseType2;
    }

    public String getScjd() {
        return scjd;
    }

    public void setScjd(String scjd) {
        this.scjd = scjd;
    }

    public Date getProsecutionDate() {
        return prosecutionDate;
    }

    public void setProsecutionDate(Date prosecutionDate) {
        this.prosecutionDate = prosecutionDate;
    }

    public Date getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(Date closingTime) {
        this.closingTime = closingTime;
    }

    public BigDecimal getBdje() {
        return bdje;
    }

    public void setBdje(BigDecimal bdje) {
        this.bdje = bdje;
    }

    public String getByxzByjaZzbl() {
        return byxzByjaZzbl;
    }

    public void setByxzByjaZzbl(String byxzByjaZzbl) {
        this.byxzByjaZzbl = byxzByjaZzbl;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public BigDecimal getSjljje() {
        return sjljje;
    }

    public void setSjljje(BigDecimal sjljje) {
        this.sjljje = sjljje;
    }

    public BigDecimal getWhjjssje() {
        return whjjssje;
    }

    public void setWhjjssje(BigDecimal whjjssje) {
        this.whjjssje = whjjssje;
    }

    public BigDecimal getHjljje() {
        return hjljje;
    }

    public void setHjljje(BigDecimal hjljje) {
        this.hjljje = hjljje;
    }

    public String getJafs() {
        return jafs;
    }

    public void setJafs(String jafs) {
        this.jafs = jafs;
    }

    @Override
    public String toString() {
        return "SsajqkbgbModel{" +
                "susejdw='" + susejdw + '\'' +
                ", bqysadw='" + bqysadw + '\'' +
                ", dfsadw='" + dfsadw + '\'' +
                ", litigationArbitration='" + litigationArbitration + '\'' +
                ", caseType1='" + caseType1 + '\'' +
                ", caseType2='" + caseType2 + '\'' +
                ", scjd='" + scjd + '\'' +
                ", prosecutionDate=" + prosecutionDate +
                ", closingTime=" + closingTime +
                ", bdje=" + bdje +
                ", byxzByjaZzbl='" + byxzByjaZzbl + '\'' +
                ", updateTime=" + updateTime +
                ", sjljje=" + sjljje +
                ", whjjssje=" + whjjssje +
                ", hjljje=" + hjljje +
                ", jafs='" + jafs + '\'' +
                '}';
    }
}
