package com.github.cruiser.tradeoutside.model;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "DAILY_TRADE",
        uniqueConstraints = { @UniqueConstraint(
                columnNames = {"corp_id_fk", "reqDat" }) })
public class DailyTradePerCorp implements Serializable, Model {

    private static final long serialVersionUID = 48L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "daily_trade_id")
    private long id;

    /** 商户 */
    @ManyToOne
    @JoinColumn(name = "corp_id_fk")
    private Corp corp;

    /** 会计日期 */
    @Basic
    @Column(length = 8)
    private String reqDat;

    /** dcc手续费汇总 */
    @Basic
    @Column(columnDefinition = "numeric(19,6) default 0.0")
    private BigDecimal totalDccRate;

    /** edc手续费汇总 */
    @Basic
    @Column(columnDefinition = "numeric(19,6) default 0.0")
    private BigDecimal totalEdcRate;

    /** 交易总金额 */
    @Basic
    @Column(columnDefinition = "numeric(19,6) default 0.0")
    private BigDecimal totalTxnAmt;

    /** dcc手续费按终端汇总 */
    @ElementCollection
    @CollectionTable(name = "TERM_DCC_RATES", joinColumns
        = @JoinColumn(name = "daily_trade_id"))
    @MapKeyColumn(name = "termNo", nullable = false)
    @Column(name = "dccRate", columnDefinition = "numeric(19,6) default 0.0")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Map<String, BigDecimal> termDccRates;

    /** edc手续费按终端汇总 */
    @ElementCollection
    @CollectionTable(name = "TERM_EDC_RATES", joinColumns
        = @JoinColumn(name = "daily_trade_id"))
    @MapKeyColumn(name = "termNo", nullable = false)
    @Column(name = "edcRate", columnDefinition = "numeric(19,6) default 0.0")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Map<String, BigDecimal> termEdcRates;

    /** 终端金额汇总 */
    @ElementCollection
    @CollectionTable(name = "TERM_TXNAMTS", joinColumns
        = @JoinColumn(name = "daily_trade_id"))
    @MapKeyColumn(name = "termNo", nullable = false)
    @Column(name = "txnAmt", columnDefinition = "numeric(19,6) default 0.0")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Map<String, BigDecimal> termTxnAmts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Corp getCorp() {
        return corp;
    }

    public void setCorp(Corp corp) {
        this.corp = corp;
    }

    public String getReqDat() {
        return reqDat;
    }

    public void setReqDat(String reqDat) {
        this.reqDat = reqDat;
    }

    public BigDecimal getTotalDccRate() {
        return totalDccRate;
    }

    public void setTotalDccRate(BigDecimal totalDccRate) {
        this.totalDccRate = totalDccRate;
    }

    public BigDecimal getTotalEdcRate() {
        return totalEdcRate;
    }

    public void setTotalEdcRate(BigDecimal totalEdcRate) {
        this.totalEdcRate = totalEdcRate;
    }

    public BigDecimal getTotalTxnAmt() {
        return totalTxnAmt;
    }

    public void setTotalTxnAmt(BigDecimal totalTxnAmt) {
        this.totalTxnAmt = totalTxnAmt;
    }

    public Map<String, BigDecimal> getTermDccRates() {
        return termDccRates;
    }

    public void setTermDccRates(Map<String, BigDecimal> termDccRates) {
        this.termDccRates = termDccRates;
    }

    public Map<String, BigDecimal> getTermEdcRates() {
        return termEdcRates;
    }

    public void setTermEdcRates(Map<String, BigDecimal> termEdcRates) {
        this.termEdcRates = termEdcRates;
    }

    public Map<String, BigDecimal> getTermTxnAmts() {
        return termTxnAmts;
    }

    public void setTermTxnAmts(Map<String, BigDecimal> termTxnAmts) {
        this.termTxnAmts = termTxnAmts;
    }

    public String toString() {
        StringBuffer output = new StringBuffer();
        output.append("商户每天交易统计：（")
            .append("\n商户号: ").append(corp.getBusiNo())
            .append("\n会计日期: ").append(reqDat)
            .append("\ndcc手续费汇总: ").append(totalDccRate)
            .append("\nedc手续费汇总: ").append(totalEdcRate)
            .append("\n交易总金额: ").append(totalTxnAmt);

        Set<String> keySet = termDccRates.keySet();
        Iterator<String> commonIt = keySet.iterator();
        while (commonIt.hasNext()) {
            String termNo = commonIt.next();
            output.append("\ndcc终端").append(termNo).append("手续费汇总: ")
                    .append(termDccRates.get(termNo));
        }

        keySet = termEdcRates.keySet();
        commonIt = keySet.iterator();
        while (commonIt.hasNext()) {
            String termNo = commonIt.next();
            output.append("\nedc终端").append(termNo).append("手续费汇总: ")
                    .append(termEdcRates.get(termNo));
        }

        keySet = termTxnAmts.keySet();
        commonIt = keySet.iterator();
        while (commonIt.hasNext()) {
            String termNo = commonIt.next();
            output.append("\nedc终端").append(termNo).append("手续费汇总: ")
                    .append(termTxnAmts.get(termNo));
        }

        return output.toString();

    }
}
