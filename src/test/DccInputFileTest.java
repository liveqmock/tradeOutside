package test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.github.cruiser.tradeoutside.model.TradeDcc;
import com.github.cruiser.tradeoutside.process.DccFileImport;

public class DccInputFileTest {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {

        /*System.out.println("tmp/BOCOM_310999_PIF.20231231".indexOf("."));
        String[] tmp = "tmp/BOCOM_310999_PIF.20231231".split("\\.");
        for(String one:tmp){
            System.out.println(one);
        }*/
        DccFileImport test = new DccFileImport("tmp/BOCOM_310999_PIF.20231231");
        test.importFileProcess();


        /*Configuration conf = new Configuration().configure();
        SessionFactory sf = conf.buildSessionFactory();
        Session sess = sf.openSession();
        Transaction tx = sess.beginTransaction();

        List<Trade> trades = new DccInputFile()
                .getTradeList("tmp/BOCOM_PIF_20120524.txt");

        Iterator it = trades.iterator();
        while (it.hasNext()) {
            sess.save(it.next());
        }

        tx.commit();
        sess.close();*/

    }

}
