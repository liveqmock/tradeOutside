package test;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.impl.SessionFactoryImpl;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

import com.github.cruiser.tradeoutside.model.*;

public class CreateTables {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*ApplicationContext ctx
		= new ClassPathXmlApplicationContext(
				"config/dao-config.xml",
				"config/beans-config.xml");
		SessionFactoryImpl tst
			= (SessionFactoryImpl)ctx.getBean("sessionFactory");
		*/
		AnnotationConfiguration test = new AnnotationConfiguration();
		test.addAnnotatedClass(Corp.class);
		test.addAnnotatedClass(TradeDcc.class);
		test.addAnnotatedClass(DailyTradePerCorp.class);
		test.setProperty("hibernate.connection.driver_class", "net.sourceforge.jtds.jdbc.Driver");
		test.setProperty("hibernate.connection.url", "jdbc:jtds:sybase://182.53.15.211:6600/middb");
		test.setProperty("hibernate.connection.username", "miduser");
		test.setProperty("hibernate.connection.password", "miduser");
		test.setProperty("hibernate.dialect", "org.hibernate.dialect.SybaseAnywhereDialect");
		test.setProperty("hibernate.hbm2dll.auto", "create");
		test.setProperty("hibernate.show_sql", "true");
		test.setProperty("hibernate.format_sql", "true");
		SchemaExport schemaExport = new SchemaExport(test);
		schemaExport.setOutputFile("sql.txt");
		schemaExport.create(true, true);
	}

}
