package com.github.cruiser.tradeoutside.trade;

import java.math.BigDecimal;

public class TradeDcc extends Trade {
	
	private long id;

	private String txnTim;/*trans_time, char (6) 交易时间，12域*/
	private String txnCod;/*tran_code char (4) 联机类：PURC  消费 		PRPU  预授权完成 		OFFL  离线   调整类：目前我行调整类交易是手工处理，没有定义， 建议根据实际情况补充新定义*/
	private String seqNum;/*retr_num char (12) 检索参考号，37域 nnnnnn(batch no) + nnnnnn (POSP seq number)*/
	private String termId;/*terminal_no char (8) 终端号，41域*/
	private String acpAdr;/*merchant_name char (40) 商户名称地址，43域*/
	private String actNo;/* acct_num char (19) 账号，2域*/
	private String valDat;/*exp_date char (4) 卡有效期，14域（空格）*/
	private BigDecimal txnAmt;/*tran_amt char (12) 交易金额，4域*/
	private String tips;/*tips char (12) 值为空格*/
	private String aRspCd;/*auth_no char (6) 授权码，38域*/
	private String txnDat;/*trans_date char (8) 交易日期，13域*/
	private String busiNo;/*merchant_no char (15) 商户号，42域*/
	private String crdTyp;/*card_flag char (1) V/M/D/J/A*/
	private String actDat;/*settlement_date char (8) 清算日期 */

	/*public BigDecimal getTxnAmtInDecimal(){
		return (txnAmt==null?new BigDecimal("0.0"):new BigDecimal(txnAmt)).divide(new BigDecimal("100.0"));
	}*/

	public BigDecimal getRealTxnAmt(){
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
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("\ntxnTim: ").append(txnTim)
		.append("\ntxnCod: ").append(txnCod)
		.append("\nseqNum: ").append(seqNum)
		.append("\ntermId: ").append(termId)
		.append("\nacpAdr: ").append(acpAdr)
		.append("\nactNo: ").append(actNo)
		.append("\nvalDat: ").append(valDat)
		.append("\ntxnAmt: ").append(getRealTxnAmt())
		.append("\ntips: ").append(tips)
		.append("\naRspCd: ").append(aRspCd)
		.append("\ntxnDat: ").append(txnDat)
		.append("\nbusiNo: ").append(busiNo)
		.append("\ncrdTyp: ").append(crdTyp)
		.append("\nactDat: ").append(actDat);
		return sb.toString();

	}
}
