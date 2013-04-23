package com.github.cruiser.tradeoutside.dao;

import java.util.List;
import com.github.cruiser.tradeoutside.model.TradeDcc;

public interface TradeDccDao extends CommonDao<TradeDcc> {

    /**
     * 根据导入文件日期查询对应TradeDcc实例
     * 
     * @param filDat
     *            导入文件日期
     * @return 对应的TradeDcc实例
     */
    List<TradeDcc> findByFilDat(String filDat);

    /**
     * 根据会计日期查询对应TradeDcc实例
     * 
     * @param actdat
     *            会计日期
     * @return 对应的TradeDcc实例
     */
    List<TradeDcc> findByActdat(String actDat);

    /**
     * 根据商户编号查询对应TradeDcc实例
     * 
     * @param busiNo
     *            需要查询的商户编号
     * @param actdat
     *            会计日期
     * @return 对应的TradeDcc实例
     */
    List<TradeDcc> findByBusiNoActdat(String busiNo, String actDat);

    /**
     * 根据商户编号查询对应TradeDcc实例
     * 
     * @param busiNo
     *            需要查询的商户编号
     * @param termNo
     *            需要查询的终端号
     * @param actdat
     *            会计日期
     * @return 对应的TradeDcc实例
     */
    List<TradeDcc> findByBusiNoTermActdat(String busiNo, String termNo,
            String actDat);
}
