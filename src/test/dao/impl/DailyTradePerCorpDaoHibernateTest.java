package test.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.cruiser.tradeoutside.dao.*;
import com.github.cruiser.tradeoutside.dao.impl.*;
import com.github.cruiser.tradeoutside.model.*;

public class DailyTradePerCorpDaoHibernateTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Corp corp = new Corp();
        corp.setBusiNo("1231231234");
        corp.setDccRate(new BigDecimal("0.5"));
        corp.setEdcRate(new BigDecimal("0.4"));
        Set<String> dccTerms = new HashSet<String>();
        dccTerms.add("134679");
        corp.setDccTerminals(dccTerms);
        Set<String> edcTerms = new HashSet<String>();
        edcTerms.add("123456");
        corp.setEdcTerminals(edcTerms);

        System.out.println(corp);

        DailyTradePerCorp dailyTrade = new DailyTradePerCorp();
        dailyTrade.setReqDat("20130419");
        dailyTrade.setCorp(corp);
        dailyTrade.setTotalDccRate(new BigDecimal("1.0"));
        dailyTrade.setTotalEdcRate(new BigDecimal("2.0"));
        dailyTrade.setTotalTxnAmt(new BigDecimal("3.0"));
        dailyTrade.setTermDccRates(new HashMap());
        dailyTrade.setTermEdcRates(new HashMap());
        dailyTrade.setTermTxnAmts(new HashMap());

        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "config/dao-config.xml", "config/beans-config.xml");
        CorpDao corpDao = ctx.getBean("corp", CorpDao.class);

        dailyTrade.setCorp(corpDao.get(1L));

        DailyTradePerCorpDao dailyTradeDao = ctx.getBean("dailyTradePerCorp",
                DailyTradePerCorpDaoHibernate.class);

        /*
         * List corps = commonDao.findAll(); Iterator it = corps.iterator();
         * 
         * while(it.hasNext()){ Object oldCorp = it.next();
         * System.out.println("删除"+oldCorp);
         * //oldCorp.getDccTerminals().add("000000");
         * //commonDao.update(oldCorp); //corpDao.delete(oldCorp);
         * 
         * }
         */

        dailyTradeDao.save(dailyTrade);

        /*
         * corps = commonDao.findAll(); it = corps.iterator();
         * 
         * while(it.hasNext()){ System.out.println(it.next()); }
         */

    }

}
