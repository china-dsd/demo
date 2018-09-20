package com.casic.oarp.datavisual.model.zxfk;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ZhongdjcfxpgtjModel implements Serializable {

    // 二级单位名称
    private String erjdwmc;

    // 三级或四级单位名称
    private String sanjhsjdwmc;

    // 审议事项名称
    private String shenysxmc;

    // 事项类型
    private String shixlx;

    // 最终审议单位级次
    private String zuizsydwjc;

    // 审议机构（字符类型）
    private String shenyjg;

    // 主责部门
    private String zhuzbm;

    // 事项风险程度
    private String shixfxcd;

    // 提交时间
    private Date zCreatetime;

    // 审计金额（万元）（可能部分没有金额）
    private BigDecimal shejje;

    public ZhongdjcfxpgtjModel() {
    }

    public String getErjdwmc() {
        return erjdwmc;
    }

    public void setErjdwmc(String erjdwmc) {
        this.erjdwmc = erjdwmc;
    }

    public String getSanjhsjdwmc() {
        return sanjhsjdwmc;
    }

    public void setSanjhsjdwmc(String sanjhsjdwmc) {
        this.sanjhsjdwmc = sanjhsjdwmc;
    }

    public String getShenysxmc() {
        return shenysxmc;
    }

    public void setShenysxmc(String shenysxmc) {
        this.shenysxmc = shenysxmc;
    }

    public String getShixlx() {
        return shixlx;
    }

    public void setShixlx(String shixlx) {
        this.shixlx = shixlx;
    }

    public String getZuizsydwjc() {
        return zuizsydwjc;
    }

    public void setZuizsydwjc(String zuizsydwjc) {
        this.zuizsydwjc = zuizsydwjc;
    }

    public String getShenyjg() {
        return shenyjg;
    }

    public void setShenyjg(String shenyjg) {
        this.shenyjg = shenyjg;
    }

    public String getZhuzbm() {
        return zhuzbm;
    }

    public void setZhuzbm(String zhuzbm) {
        this.zhuzbm = zhuzbm;
    }

    public String getShixfxcd() {
        return shixfxcd;
    }

    public void setShixfxcd(String shixfxcd) {
        this.shixfxcd = shixfxcd;
    }

    public Date getzCreatetime() {
        return zCreatetime;
    }

    public void setzCreatetime(Date zCreatetime) {
        this.zCreatetime = zCreatetime;
    }

    public BigDecimal getShejje() {
        return shejje;
    }

    public void setShejje(BigDecimal shejje) {
        this.shejje = shejje;
    }

    @Override
    public String toString() {
        return "ZhongdjcfxpgtjModel{" +
                "erjdwmc='" + erjdwmc + '\'' +
                ", sanjhsjdwmc='" + sanjhsjdwmc + '\'' +
                ", shenysxmc='" + shenysxmc + '\'' +
                ", shixlx='" + shixlx + '\'' +
                ", zuizsydwjc='" + zuizsydwjc + '\'' +
                ", shenyjg='" + shenyjg + '\'' +
                ", zhuzbm='" + zhuzbm + '\'' +
                ", shixfxcd='" + shixfxcd + '\'' +
                ", zCreatetime=" + zCreatetime +
                ", shejje=" + shejje +
                '}';
    }
}
