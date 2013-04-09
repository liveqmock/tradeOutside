package com.github.cruiser.tradeoutside.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;

public class DailyTradePerCorp implements Serializable {
	
	private static final long serialVersionUID = 48L;

	private long id;

	private Corp corp;/* 商户 */
	private String actDat;/* 会计日期 */
	private BigDecimal totalDccRate;/* dcc手续费汇总 */
	private BigDecimal totalEdcRate;/* edc手续费汇总 */
	private BigDecimal totalTxnAmt;/* 交易总金额 */
	private Map<String, BigDecimal> termDccRates;/* dcc手续费按终端汇总 */
	private Map<String, BigDecimal> termEdcRates;/* edc手续费按终端汇总 */
	private Map<String, BigDecimal> termTxnAmts;/* 终端金额汇总 */


	public String toString(){
		StringBuffer output = new StringBuffer();
		output.append("商户每天交易统计：（")
		.append("\n商户号: ").append(corp.getBusiNo())
		.append("\n会计日期: ").append(dccRate)
		.append("\nedc手续费率: ").append(edcRate);

		Iterator<String> commonIt = dccTerminals.iterator();
		while(commonIt.hasNext()){
			output.append("\ndcc终端编号: ").append(commonIt.next());
		}

		commonIt = edcTerminals.iterator();
		while(commonIt.hasNext()){
			output.append("\nedc终端编号: ").append(commonIt.next());
		}

		output.append("）");
		return output.toString();

	}
}
