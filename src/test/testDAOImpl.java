package test;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.hibernate.SessionFactory;
import java.util.*;

import com.github.cruiser.tradeoutside.trade.TradeDcc;

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

		return (List<TradeDcc>)getHtm().find("from TRADEDCC t where t.id like ?", Long.valueOf(id));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<TradeDcc> test = new testDAOImpl().find(1);
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
