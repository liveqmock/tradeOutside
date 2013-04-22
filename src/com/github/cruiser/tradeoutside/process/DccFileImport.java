package com.github.cruiser.tradeoutside.process;

import java.io.*;
import java.lang.reflect.*;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.math.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.cruiser.tradeoutside.dao.impl.TradeDccDaoHibernate;
import com.github.cruiser.tradeoutside.model.TradeDcc;

public class DccFileImport {

    public void excute(String fileName) throws FileNotFoundException{
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "config/dao-config.xml", "config/beans-config.xml");
        TradeDccDaoHibernate modelObjectDao = ctx.getBean("tradeDcc",
                TradeDccDaoHibernate.class);

        List<TradeDcc> trades = getTradeList(fileName);
        for(Iterator<TradeDcc> itTrades = trades.iterator();
            itTrades.hasNext();){

            modelObjectDao.save(itTrades.next());

        }

    }

    public List<TradeDcc> getTradeList(String fileName) throws FileNotFoundException{

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String oneline = null;

        List<TradeDcc> trades = new ArrayList<TradeDcc>();

        try {
            while (null != (oneline = br.readLine())) {
                trades.add(DccFileImport.fillerByFixedLength(oneline));
            }
            return trades;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static TradeDcc fillerByFixedLength(String oneline)
            throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, SecurityException, NoSuchMethodException,
            IllegalArgumentException, InvocationTargetException {

        int fillerLength = 1;// 分隔长度

        String[] fieldName = { "TxnTim", "TxnCod", "SeqNum", "TermId",
                "AcpAdr", "ActNo", "ValDat", "TxnAmt",
                "Tips", "aRspCd", "TxnDat", "BusiNo",
                "CrdTyp", "ActDat" };// 各字段set方法名称

        int[] fieldFixedLength = { 6, 4, 12, 8,
                40, 19, 4, 12,
                12, 6, 8, 15,
                1, 8 };// 各字段长度

        Class<?>[] fieldType
            = { String.class, String.class, String.class, String.class,
                String.class, String.class, String.class, BigDecimal.class,
                String.class, String.class, String.class, String.class,
                String.class, String.class };// set方法对应的参数类型

        Class<?> _reflectClass = Class
                .forName("com.github.cruiser.tradeoutside.model.TradeDcc");
        TradeDcc reflectClass = (TradeDcc) _reflectClass.newInstance();

        int fieldStartPosition = 0;// 用于标识各字段开头位置

        /*
         * 使用反射对各字段进行赋值
         */
        for (int indexOfField = 0; indexOfField < fieldFixedLength.length; indexOfField++) {

            StringBuffer methodName = new StringBuffer();
            methodName.append("set").append(fieldName[indexOfField]);

            Method _reflectMethod = _reflectClass.getMethod(
                    methodName.toString(), fieldType[indexOfField]);

            StringBuffer fieldValue = new StringBuffer();
            fieldValue.append(oneline.substring(fieldStartPosition,
                    fieldStartPosition + fieldFixedLength[indexOfField]));

            fieldStartPosition += fieldFixedLength[indexOfField] + fillerLength;
            Object[] parmValue = getFieldArray(fieldValue.toString(),
                    fieldType[indexOfField]);// = {fieldValue.toString()};

            _reflectMethod.invoke(reflectClass, parmValue);

        }

        return reflectClass;
    }

    private static Object[] getFieldArray(String fieldValue, Class<?> fieldType) {
        if (BigDecimal.class.equals(fieldType)) {
            return new Object[] { new BigDecimal(fieldValue) };
        } else {
            return new Object[] { fieldValue };
        }
    }
}
