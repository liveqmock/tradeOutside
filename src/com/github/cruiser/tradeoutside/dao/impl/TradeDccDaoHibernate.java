package com.github.cruiser.tradeoutside.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.github.cruiser.tradeoutside.dao.TradeDccDao;
import com.github.cruiser.tradeoutside.dao.CommonDao;
import com.github.cruiser.tradeoutside.model.TradeDcc;

public class TradeDccDaoHibernate
	extends HibernateDaoSupport
	implements TradeDccDao {

	@Override
	public TradeDcc get(Long id) {

		return getHibernateTemplate().get(TradeDcc.class, id);
	}

	@Override
	public Long save(TradeDcc object) {

		return (Long)getHibernateTemplate().save(object);
	}

	@Override
	public void update(TradeDcc object) {
		getHibernateTemplate().update(object);

	}

	@Override
	public void delete(TradeDcc object) {
		getHibernateTemplate().delete(object);

	}

	@Override
	public void delete(Long id) {
		delete(get(id));

	}

	public TradeDcc load(Long id) {
		return getHibernateTemplate().load(TradeDcc.class, id);

	}

	@Override
	public List<TradeDcc> findAll() {
		return (List<TradeDcc>)getHibernateTemplate()
				.find("from TradeDcc");

	}

	@Override
	public List<TradeDcc> findByBusiNoActdat(final String busiNo, final String actdat) {
		
		return (List<TradeDcc>)getHibernateTemplate()
				.executeFind(
						new HibernateCallback(){

							@Override
							public Object doInHibernate(Session session)
									throws HibernateException, SQLException {
								return session.createQuery("select t from TradeDcc t " +
										"where t.busiNo = :busiNo" +
										" and t.actDat = :actDat")
										.setString("busiNo", busiNo)
										.setString("actDat", actdat)
										.list();
							}
							
						}
						);
	}

	@Override
	public List<TradeDcc> findByBusiNoTermActdat(
			final String busiNo,
			final String termId,
			final String actdat) {
		
		return (List<TradeDcc>)getHibernateTemplate()
				.executeFind(
						new HibernateCallback(){

							@Override
							public Object doInHibernate(Session session)
									throws HibernateException, SQLException {
								return session.createQuery("select t from TradeDcc t " +
										"where t.busiNo = :busiNo" +
										" and t.actDat = :actDat" +
										" and t.termId = :termId")
										.setString("busiNo", busiNo)
										.setString("actDat", actdat)
										.setString("termId", termId)
										.list();
							}
							
						}
						);
	}

	@Override
	public void flush() {
		getHibernateTemplate().flush();
		
	}

}
