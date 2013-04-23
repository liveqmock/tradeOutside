package com.github.cruiser.tradeoutside.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 持久化DCC交易明细
 * 
 * @author 顾启明
 * 
 */
@Entity
@Table(name = "TRADEDCC", uniqueConstraints = {})
public class TradeDcc implements Serializable, Model {

    private static final long serialVersionUID = 48L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dcc_id")
    private long id;/* 数据库主键 */

    @Basic
    @Column(length = 6)
    private String txnTim;/* trans_time, char (6) 交易时间，12域 */

    @Basic
    @Column(length = 4)
    private String txnCod;/*
                           * tran_code char (4) 联机类：PURC 消费 PRPU 预授权完成 OFFL 离线
                           * 调整类：目前我行调整类交易是手工处理，没有定义， 建议根据实际情况补充新定义
                           */

    @Basic
    @Column(length = 12)
    private String seqNum;/*
                           * retr_num char (12) 检索参考号，37域 nnnnnn(batch no) +
                           * nnnnnn (POSP seq number)
                           */

    @Basic
    @Column(length = 8)
    private String termId;/* terminal_no char (8) 终端号，41域 */

    @Basic
    @Column(length = 40)
    private String acpAdr;/* merchant_name char (40) 商户名称地址，43域 */

    @Basic
    @Column(length = 19)
    private String actNo;/* acct_num char (19) 账号，2域 */

    @Basic
    @Column(length = 4)
    private String valDat;/* exp_date char (4) 卡有效期，14域（空格） */

    @Basic
    private BigDecimal txnAmt;/* tran_amt char (12) 交易金额，4域 */

    @Basic
    @Column(length = 12)
    private String tips;/* tips char (12) 值为空格 */

    @Basic
    @Column(length = 6)
    private String aRspCd;/* auth_no char (6) 授权码，38域 */

    @Basic
    @Column(length = 8)
    private String txnDat;/* trans_date char (8) 交易日期，13域 */

    @Basic
    @Column(length = 15)
    private String busiNo;/* merchant_no char (15) 商户号，42域 */

    @Basic
    @Column(length = 1)
    private String crdTyp;/* card_flag char (1) V/M/D/J/A */

    @Basic
    @Column(length = 8)
    private String actDat;/* settlement_date char (8) 清算日期 */

    @Basic
    @Column(length = 8)
    private String filDat;/* 文件日期 */

    public String getFilDat() {
        return filDat;
    }

    public void setFilDat(String filDat) {
        this.filDat = filDat;
    }

    public BigDecimal getRealTxnAmt() {
        return txnAmt.divide(new BigDecimal("100.0"));
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getTxnTim() {
        return txnTim;
    }

    public void setTxnTim(String txnTim) {
        this.txnTim = txnTim;
    }

    public String getTxnCod() {
        return txnCod;
    }

    public void setTxnCod(String txnCod) {
        this.txnCod = txnCod;
    }

    public String getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(String seqNum) {
        this.seqNum = seqNum;
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public String getAcpAdr() {
        return acpAdr;
    }

    public void setAcpAdr(String acpAdr) {
        this.acpAdr = acpAdr;
    }

    public String getActNo() {
        return actNo;
    }

    public void setActNo(String actNo) {
        this.actNo = actNo;
    }

    public String getValDat() {
        return valDat;
    }

    public void setValDat(String valDat) {
        this.valDat = valDat;
    }

    public BigDecimal getTxnAmt() {
        return txnAmt;
    }

    public void setTxnAmt(BigDecimal txnAmt) {
        this.txnAmt = txnAmt;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getaRspCd() {
        return aRspCd;
    }

    public void setaRspCd(String aRspCd) {
        this.aRspCd = aRspCd;
    }

    public String getTxnDat() {
        return txnDat;
    }

    public void setTxnDat(String txnDat) {
        this.txnDat = txnDat;
    }

    public String getBusiNo() {
        return busiNo;
    }

    public void setBusiNo(String busiNo) {
        this.busiNo = busiNo;
    }

    public String getCrdTyp() {
        return crdTyp;
    }

    public void setCrdTyp(String crdTyp) {
        this.crdTyp = crdTyp;
    }

    public String getActDat() {
        return actDat;
    }

    public void setActDat(String actDat) {
        this.actDat = actDat;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("\ntxnTim: ").append(txnTim).append("\ntxnCod: ")
                .append(txnCod).append("\nseqNum: ").append(seqNum)
                .append("\ntermId: ").append(termId).append("\nacpAdr: ")
                .append(acpAdr).append("\nactNo: ").append(actNo)
                .append("\nvalDat: ").append(valDat).append("\ntxnAmt: ")
                .append(getRealTxnAmt()).append("\ntips: ").append(tips)
                .append("\naRspCd: ").append(aRspCd).append("\ntxnDat: ")
                .append(txnDat).append("\nbusiNo: ").append(busiNo)
                .append("\ncrdTyp: ").append(crdTyp).append("\nactDat: ")
                .append(actDat);
        return sb.toString();

    }
}
