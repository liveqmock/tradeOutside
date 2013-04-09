package test;
import org.hibernate.*;
import java.util.*;
import org.hibernate.cfg.*;

import com.github.cruiser.tradeoutside.input.DccInputFile;
import com.github.cruiser.tradeoutside.model.TradeDcc;
import com.github.cruiser.tradeoutside.trade.*;

public class TradeDccTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		//TradeDcc one = new TradeDcc();
		List<Trade> trades = new DccInputFile().getTradeList("tmp/BOCOM_PIF_20120524.txt");
		//one.setFirst(1);
		//one.setLast(2);
		Iterator it = trades.iterator();
		while(it.hasNext()){
			sess.save(it.next());
		}
		tx.commit();
		sess.close();
	}

}
