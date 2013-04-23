package com.github.cruiser.tradeoutside.dao;

import java.util.List;
import com.github.cruiser.tradeoutside.model.DailyTradePerCorp;
import com.github.cruiser.tradeoutside.model.Corp;

public interface DailyTradePerCorpDao extends CommonDao<DailyTradePerCorp> {

    /**
     * 根据会计日期查询对应DailyTradePerCorp实例
     * @param actdat
     * @return
     */
    List<DailyTradePerCorp> findByActdat(String actdat);

    /**
     * 查询一段时间跨度的DailyTradePerCorp实例
     * @param actdat
     * @return
     */
    List<DailyTradePerCorp> findByActdat(String startdate, String endate);

    /**
     * 根据商户会计日期查询对应DailyTradePerCorp实例
     * 
     * @param corp
     *            需要查询的商户
     * @param actdat
     *            需要查询的会计日期
     * @return 对应的TradeDcc实例
     */
    List<DailyTradePerCorp> findByCorpActdat(Corp corp, String actdat);

    /**
     * 根据商户查询一段时间跨度的DailyTradePerCorp实例
     * @param corp 需要查询的商户
     * @param startdate 时间开始
     * @param endate 时间结束
     * @return
     */
    List<DailyTradePerCorp> findByCorpTimeparm(Corp corp, String startdate,
            String endate);

}
