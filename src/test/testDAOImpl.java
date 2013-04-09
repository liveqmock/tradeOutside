package test;

import java.util.*;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.cruiser.tradeoutside.model.TradeDcc;

public class testDAOImpl implements testDAO {

	private HibernateTemplate htm = null;
	private SessionFactory sessionFactory;

	private HibernateTemplate getHtm() {
		if(null == htm){
			htm = new HibernateTemplate(sessionFactory);
		}
		return htm;
	}

	@Override
	public List<TradeDcc> find(long id) {

		return (List<TradeDcc>)getHtm().find("from TradeDcc t where t.id = ?", java.lang.Long.valueOf(id));
//		return (List<TradeDcc>)getHtm().find("from TradeDcc");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config/beans-config.xml");
		
		List<TradeDcc> test = ((testDAOImpl)ctx.getBean("testDAO")).find(1);
		Iterator it = test.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}

	}

	/*public SessionFactory getSessionFactory() {
		return sessionFactory;
	}*/

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
