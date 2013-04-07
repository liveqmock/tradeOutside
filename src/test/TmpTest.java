package test;
import org.hibernate.*;
import org.hibernate.cfg.*;

import test.*;

public class TmpTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		Temp_value one = new Temp_value();
		one.setFirst(1);
		one.setLast(2);
		sess.save(one);
		tx.commit();
		sess.close();
	}

}
