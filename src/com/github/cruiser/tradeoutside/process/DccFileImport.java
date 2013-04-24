package com.github.cruiser.tradeoutside.process;

import java.io.*;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.math.*;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.cruiser.tradeoutside.dao.CorpDao;
import com.github.cruiser.tradeoutside.dao.DailyTradePerCorpDao;
import com.github.cruiser.tradeoutside.dao.TradeDccDao;
import com.github.cruiser.tradeoutside.dao.impl.*;
import com.github.cruiser.tradeoutside.model.*;

public class DccFileImport {

    /** 请款文件日期 */
    private String reqDat;
    private String fileName;
    private ClassPathXmlApplicationContext context;
    private TradeDccDao tradeDccDao;
    private CorpDao corpDao;
    private DailyTradePerCorpDao dailyTradePerCorpDao;

    @SuppressWarnings("unused")
    private DccFileImport(){}

    /**
     * DccFileImport构建函数，在实例化同时初始化导入文件变量和请求日期变量
     * @param fileName
     */
    public DccFileImport(String fileName){

        this.fileName = fileName;

        if(-1==fileName.indexOf(".")){
            this.fileName = null;
            this.reqDat = null;
            return;
        }else{
            this.reqDat = fileName.split("\\.")[1];
            
        }
        
        this.context = new ClassPathXmlApplicationContext(
            "config/dao-config.xml", "config/beans-config.xml");

        this.tradeDccDao = context.getBean("tradeDcc",
                TradeDccDaoHibernate.class);

        this.corpDao = context.getBean("corp",
                CorpDaoHibernate.class);

        this.dailyTradePerCorpDao
                = context.getBean("dailyTradePerCorp",
                        DailyTradePerCorpDaoHibernate.class);

    }

    /**
     * 整个文件导入的主控
     * @throws FileNotFoundException
     */
    public void importFileProcess() throws FileNotFoundException{

        //清除已存在的数据
        cleanExistDataAction();

        //遍历文件每行进行导入操作
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        //每行的buffer
        String oneline = null;
        TradeDcc tradeDcc = null;

        try {
            while (null != (oneline = br.readLine())) {
                tradeDcc = fillerByFixedLength(oneline);

                //持久化TradeDcc类
                importTradeDccAction(tradeDcc);
                
                //持久化Corp类
                importCorpAction(tradeDcc);

                //持久化DailyTradePerCorp类
                importDailyTradePerCorp(tradeDcc);

            }
        }catch(Exception e){
            e.printStackTrace();
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

    /**
     * 将用于清除原数据表数据
     */
    public void cleanExistDataAction(){
        //先清除原有数据
        //清除tradedcc数据表数据
        cleanExistTradeDccAction();
        //清除dailytrade数据表数据
        cleanExistDailyTradeAction();
    }

    /**
     * 清除TraccDcc数据表数据
     */
    private void cleanExistTradeDccAction(){
        List<TradeDcc> tradeDccs = tradeDccDao.findByReqDat(reqDat);
        Iterator<TradeDcc> itTradeDccs = tradeDccs.iterator();
        while(itTradeDccs.hasNext()){
            tradeDccDao.delete(itTradeDccs.next());
        }

    }

    /**
     * 清除DailyTrade数据
     */
    private void cleanExistDailyTradeAction() {
        List<DailyTradePerCorp> dailyTradePerCorps = dailyTradePerCorpDao.findByReqDat(reqDat);
        Iterator<DailyTradePerCorp> itDailyTradePerCorp = dailyTradePerCorps.iterator();
        while(itDailyTradePerCorp.hasNext()){
            dailyTradePerCorpDao.delete(itDailyTradePerCorp.next());
        }
        
    }

    /**
     * 计算流水汇总，并持久化
     * @param tradeDcc
     */
    private void importDailyTradePerCorp(TradeDcc tradeDcc) {

        List<Corp> corps = corpDao.findByBusiNo(tradeDcc.getBusiNo());
        Corp corp = corps.get(0);
        BigDecimal realReBate = tradeDcc.getReBate().divide(new BigDecimal("100.0"));

        List<DailyTradePerCorp> dailyTradePerCorps
            = dailyTradePerCorpDao.findByCorpReqDat(corp, tradeDcc.getReqDat());

        DailyTradePerCorp dailyTradePerCorp = null;

        if(dailyTradePerCorps.size()==1){//已经存在记录
            dailyTradePerCorp = dailyTradePerCorps.get(0);

            //更新总金额，总dcc费率
            dailyTradePerCorp.setTotalTxnAmt(dailyTradePerCorp.getTotalTxnAmt()
                    .add(tradeDcc.getRealTxnAmt()));
            dailyTradePerCorp.setTotalDccRate(dailyTradePerCorp.getTotalDccRate()
                    .add(tradeDcc.getRealTxnAmt().multiply(realReBate)));
            
            //更新终端金额
            Map<String, BigDecimal> termTxnAmts = dailyTradePerCorp.getTermTxnAmts();
            if(termTxnAmts.containsKey(tradeDcc.getTermId())){
                BigDecimal termTxnAmt = termTxnAmts.get(tradeDcc.getTermId());
                termTxnAmt = termTxnAmt.add(tradeDcc.getRealTxnAmt());
                termTxnAmts.remove(tradeDcc.getTermId());
                termTxnAmts.put(tradeDcc.getTermId(), termTxnAmt);
                
            }else{
                termTxnAmts.put(tradeDcc.getTermId(), tradeDcc.getRealTxnAmt());
            }
            dailyTradePerCorp.setTermTxnAmts(termTxnAmts);

            //更新终端dcc费率
            Map<String, BigDecimal> termDccRates = dailyTradePerCorp.getTermDccRates();
            if(termDccRates.containsKey(tradeDcc.getTermId())){
                BigDecimal termDccRate = termDccRates.get(tradeDcc.getTermId());
                termDccRate = termDccRate
                        .add(tradeDcc.getRealTxnAmt()
                                .multiply(realReBate));
                termDccRates.remove(tradeDcc.getTermId());
                termDccRates.put(tradeDcc.getTermId(), termDccRate);
                
            }else{
                termDccRates.put(tradeDcc.getTermId(),
                        tradeDcc.getRealTxnAmt().multiply(realReBate));
            }
            dailyTradePerCorp.setTermDccRates(termDccRates);

        }else if(dailyTradePerCorps.size()==0){//没有记录
            dailyTradePerCorp = new DailyTradePerCorp();

            dailyTradePerCorp.setCorp(corp);

            //更新总金额，总dcc费率
            dailyTradePerCorp.setTotalTxnAmt(tradeDcc.getRealTxnAmt());
            dailyTradePerCorp.setTotalDccRate(tradeDcc.getRealTxnAmt()
                    .multiply(realReBate));
            
            //更新终端金额
            Map<String, BigDecimal> termTxnAmts = new HashMap<String, BigDecimal>();
            termTxnAmts.put(tradeDcc.getTermId(), tradeDcc.getRealTxnAmt());
            dailyTradePerCorp.setTermTxnAmts(termTxnAmts);

            //更新终端dcc费率
            Map<String, BigDecimal> termDccRates = new HashMap<String, BigDecimal>();
            termDccRates.put(tradeDcc.getTermId(),
                    tradeDcc.getRealTxnAmt().multiply(realReBate));
            dailyTradePerCorp.setTermDccRates(termDccRates);

        }else{//同一商户号有多条，异常
            throw new IllegalArgumentException("DailyTradePerCorps.size > 1");
        }
        dailyTradePerCorp.setReqDat(reqDat);
        dailyTradePerCorpDao.saveOrUpdate(dailyTradePerCorp);
        dailyTradePerCorpDao.flush();

    }

    /**
     * 持久化交易明细
     * @param tradeDcc
     */
    private void importTradeDccAction(TradeDcc tradeDcc) {

        tradeDccDao.save(tradeDcc);

    }

    /**
     * 持久化Corp
     * 首先检查是否已经有该商户记录，如否就创建商户，最后一起更新相关终端列表
     * @param tradeDcc
     */
    private void importCorpAction(TradeDcc tradeDcc) {

        List<Corp> corps = corpDao.findByBusiNo(tradeDcc.getBusiNo());

        Corp corp = null;
        Set<String> dccTerminals = null;

        if(corps.size()==1){//已经存在记录
            corp = corps.get(0);

            dccTerminals = corp.getDccTerminals();
            if(!dccTerminals.contains(tradeDcc.getTermId())){
                dccTerminals.add(tradeDcc.getTermId());
            }
            
        }else if(corps.size()==0){//没有记录
            corp = new Corp();
            corp.setBusiNo(tradeDcc.getBusiNo());
            dccTerminals = new HashSet<String>();
            dccTerminals.add(tradeDcc.getTermId());

        }else{//同一商户号有多条，异常
            throw new IllegalArgumentException("corps.size > 1");
        }
        corp.setDccRate(tradeDcc.getReBate()
                .divide(new BigDecimal("100.0")));

        corp.setDccTerminals(dccTerminals);
        corpDao.saveOrUpdate(corp);
        corpDao.flush();

    }

    /**
     * 通过遍历文件得到TradeDcc列表
     * @param fileName 明细文件路径
     * @return TradeDcc对象列表
     * @throws FileNotFoundException
     * @Deprecated
     */
    public List<TradeDcc> getTradeList(String fileName) throws FileNotFoundException{

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String oneline = null;

        List<TradeDcc> trades = new ArrayList<TradeDcc>();

        try {
            while (null != (oneline = br.readLine())) {
                trades.add(fillerByFixedLength(oneline));
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

    /**
     * 由文件中的一行数据得到TradeDcc对象
     * @param oneline
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public TradeDcc fillerByFixedLength(String oneline)
            throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, SecurityException, NoSuchMethodException,
            IllegalArgumentException, InvocationTargetException {

        int fillerLength = 1;// 分隔长度

        String[] fieldName = { "TxnTim", "TxnCod", "SeqNum", "TermId",
                "AcpAdr", "ActNo", "ValDat", "TxnAmt",
                "Tips", "aRspCd", "TxnDat", "BusiNo",
                "CrdTyp", "tActDt", "ReBate" };// 各字段set方法名称

        int[] fieldFixedLength = { 6, 4, 12, 8,
                40, 19, 4, 12,
                12, 6, 8, 15,
                1, 8, 8 };// 各字段长度

        Class<?>[] fieldType
            = { String.class, String.class, String.class, String.class,
                String.class, String.class, String.class, BigDecimal.class,
                String.class, String.class, String.class, String.class,
                String.class, String.class, BigDecimal.class };// set方法对应的参数类型

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
                    fieldType[indexOfField]);

            _reflectMethod.invoke(reflectClass, parmValue);

        }

        reflectClass.setReqDat(reqDat);
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
