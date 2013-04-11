package test.dao.impl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.cruiser.tradeoutside.dao.impl.CorpDaoHibernate;
import com.github.cruiser.tradeoutside.model.Corp;

public class CorpDaoHibernateTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		

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
		
		ApplicationContext ctx
			= new ClassPathXmlApplicationContext(
					"config/dao-config.xml",
					"config/beans-config.xml");
		CorpDaoHibernate test
			= ctx.getBean("corp", CorpDaoHibernate.class);
		//System.out.println(test.findByBusiNo(corp.getBusiNo()));
		/*if(corp.equals(test.findByBusiNo(corp.getBusiNo()))){
			System.out.println("corp已存在");
			test.delete(corp);
		}*/

		test.delete(corp);
		//test.save(corp);
		
		List<Corp> corps = test.findAll();
		Iterator<Corp> it = corps.iterator();
		
		while(it.hasNext()){
			System.out.println(it.next());
		}

	}

}
