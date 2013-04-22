package test.dao.impl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.cruiser.tradeoutside.dao.impl.CorpDaoHibernate;
import com.github.cruiser.tradeoutside.model.Corp;

public class CorpDaoHibernateTest {

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

        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "config/dao-config.xml", "config/beans-config.xml");
        CorpDaoHibernate corpDao = ctx.getBean("corp", CorpDaoHibernate.class);

        List<Corp> corps = corpDao.findAll();
        Iterator<Corp> it = corps.iterator();

        /*
         * while(it.hasNext()){ Corp oldCorp = it.next();
         * System.out.println("删除"+oldCorp);
         * oldCorp.getDccTerminals().add("000000"); corpDao.update(oldCorp);
         * //corpDao.delete(oldCorp);
         * 
         * }
         */

        corpDao.save(corp);

        corps = corpDao.findAll();
        it = corps.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }

    }

}
