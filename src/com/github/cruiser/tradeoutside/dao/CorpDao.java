package com.github.cruiser.tradeoutside.dao;

import java.util.List;

import com.github.cruiser.tradeoutside.model.Corp;

public interface CorpDao {

	/**
	 * 根据商户编号查询对应Corp实例
	 * @param busiNo 需要查询的商户编号
	 * @return 对应的Corp实例
	 */
	List<Corp> findByBusiNo(String busiNo);

}
