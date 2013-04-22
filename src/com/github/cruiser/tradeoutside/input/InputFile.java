package com.github.cruiser.tradeoutside.input;

import java.math.BigDecimal;
import java.util.List;

//import com.github.cruiser.tradeoutside.trade.Trade;

/**
 * 定义输入文件的父类
 * 
 * @author qm
 * 
 */
public abstract class InputFile {

    // public abstract List<Trade> getTradeList(String fileName) throws
    // Exception;

    /**
     * 
     * @param fieldValue
     * @param fieldType
     * @return
     */
    public static Object[] getFieldArray(String fieldValue, Class<?> fieldType) {
        if (BigDecimal.class.equals(fieldType)) {
            return new Object[] { new BigDecimal(fieldValue) };
        } else {
            return new Object[] { fieldValue };
        }
    }
}
