package com.github.cruiser.tradeoutside.dao;

import java.util.List;
import com.github.cruiser.tradeoutside.model.DailyTradePerCorp;
import com.github.cruiser.tradeoutside.model.Corp;

public interface DailyTradePerCorpDao
	extends CommonDao<DailyTradePerCorp> {

	/**
	 * 根据商户编号查询对应DailyTradePerCorp实例
	 * @param busiNo 需要查询的商户编号
	 * @return 对应的TradeDcc实例
	 */
	DailyTradePerCorp findByCorpActdat(Corp corp, String actdat);

	/**
	 * 根据商户编号查询一段时间跨度的DailyTradePerCorp实例
	 * @param busiNo 需要查询的商户编号
	 * @return 对应的TradeDcc实例
	 */
	List<DailyTradePerCorp> findByCorpTimeparm(Corp corp, String startdate, String endate);

}
