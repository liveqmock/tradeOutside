package com.github.cruiser.tradeoutside.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import java.util.Iterator;

/**
 * 持久化商户
 * @author 顾启明
 *
 */
public class Corp implements Serializable, Model {
	
	private static final long serialVersionUID = 48L;

	private long id;

	private String busiNo;/* merchant_no char (15) 商户号，42域 */
	private BigDecimal dccRate;/* dcc手续费率 */
	private BigDecimal edcRate;/* edc手续费率 */
	private Set<String> dccTerminals;/* dcc终端编号 */
	private Set<String> edcTerminals;/* edc终端编号 */

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getBusiNo() {
		return busiNo;
	}


	public void setBusiNo(String busiNo) {
		this.busiNo = busiNo;
	}


	public BigDecimal getDccRate() {
		return dccRate;
	}


	public void setDccRate(BigDecimal dccRate) {
		this.dccRate = dccRate;
	}


	public BigDecimal getEdcRate() {
		return edcRate;
	}


	public void setEdcRate(BigDecimal edcRate) {
		this.edcRate = edcRate;
	}


	public Set<String> getDccTerminals() {
		return dccTerminals;
	}


	public void setDccTerminals(Set<String> dccTerminals) {
		this.dccTerminals = dccTerminals;
	}


	public Set<String> getEdcTerminals() {
		return edcTerminals;
	}


	public void setEdcTerminals(Set<String> edcTerminals) {
		this.edcTerminals = edcTerminals;
	}

	public boolean equals(Object obj){
		if(this==obj){
			return true;
		}
		if(obj!=null &&
				obj.getClass()==Corp.class){
			Corp corp = (Corp)obj;
			return this.getBusiNo().equals(corp.getBusiNo());
		}
		return false;
	}

	public int hashCode(){
		return busiNo.hashCode();
	}

	public String toString(){
		StringBuffer output = new StringBuffer();
		output.append("商户：（")
		.append("\n商户号: ").append(busiNo)
		.append("\ndcc手续费率: ").append(dccRate)
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
