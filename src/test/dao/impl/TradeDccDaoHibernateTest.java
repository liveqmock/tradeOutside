package test.dao.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.cruiser.tradeoutside.dao.impl.TradeDccDaoHibernate;
import com.github.cruiser.tradeoutside.model.TradeDcc;

public class TradeDccDaoHibernateTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

        TradeDcc modelObject = new TradeDcc();
        modelObject.setAcpAdr("Sai Ma Can Yin Shanghai Chn 126686       ");
        modelObject.setReqDat("20120503");
        modelObject.setActNo("5209530362504767         ");
        modelObject.setaRspCd("020008");
        modelObject.setBusiNo("301310058126686");
        modelObject.setCrdTyp("M");
        modelObject.setSeqNum("212421752146");
        modelObject.setTermId("26686004");
        modelObject.setTips("000000000000");
        modelObject.setTxnAmt(new BigDecimal("000000088000"));
        modelObject.setTxnCod("PURC");
        modelObject.setTxnDat("20120503");
        modelObject.setTxnTim("152733");
        modelObject.setValDat("     ");

        System.out.println(modelObject);

        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "config/dao-config.xml", "config/beans-config.xml");
        TradeDccDaoHibernate modelObjectDao = ctx.getBean("tradeDcc",
                TradeDccDaoHibernate.class);

        /*
         * List<TradeDcc> modelObjects = modelObjectDao.findAll();
         * Iterator<TradeDcc> it = modelObjects.iterator();
         * 
         * while(it.hasNext()){ TradeDcc oldTradeDcc = it.next();
         * modelObjectDao.delete(oldTradeDcc);
         * 
         * }
         * 
         * modelObjectDao.save(modelObject);
         */

        List<TradeDcc> modelObjects = modelObjectDao.findByBusiNoTermReqDat(
                "301310058126686", "26686004", "20120503");
        // List<TradeDcc> modelObjects =
        // modelObjectDao.findByBusiNoActdat("301310058126686", "20120503");
        // List<TradeDcc> modelObjects =
        // modelObjectDao.findByBusiNoActdat("xxx", "20120503");
        // modelObjects = modelObjectDao.findAll();
        Iterator<TradeDcc> it = modelObjects.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }

    }

}
