package com.github.cruiser.tradeoutside.dao;

import java.util.List;
import com.github.cruiser.tradeoutside.model.TradeDcc;

public interface TradeDccDao extends CommonDao<TradeDcc> {

    /**
     * 根据会计日期查询对应TradeDcc实例
     * 
     * @param actdat
     *            会计日期
     * @return 对应的TradeDcc实例
     */
    List<TradeDcc> findByActdat(String actDat);

    /**
     * 根据导入文件日期查询对应TradeDcc实例
     * 
     * @param reqDat
     *            文件导入日期
     * @return 对应的TradeDcc实例
     */
    List<TradeDcc> findByReqDat(String reqDat);

    /**
     * 根据商户编号,文件导入日期查询对应TradeDcc实例
     * 
     * @param busiNo
     *            需要查询的商户编号
     * @param reqDat
     *            文件导入日期
     * @return 对应的TradeDcc实例
     */
    List<TradeDcc> findByBusiNoReqDat(String busiNo, String reqDat);

    /**
     * 根据商户编号查询对应TradeDcc实例
     * 
     * @param busiNo
     *            需要查询的商户编号
     * @param termNo
     *            需要查询的终端号
     * @param reqDat
     *            文件导入日期
     * @return 对应的TradeDcc实例
     */
    List<TradeDcc> findByBusiNoTermReqDat(String busiNo, String termId,
            String reqDat);

}
