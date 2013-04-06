import org.hibernate.*;
import org.hibernate.cfg.*;

import com.github.cruiser.tradeoutside.trade.TradeDcc;

public class TradeDccTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		TradeDcc one = new TradeDcc();
		//one.setFirst(1);
		//one.setLast(2);
		sess.save(one);
		tx.commit();
		sess.close();
	}

}
