package com.github.cruiser.tradeoutside.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.github.cruiser.tradeoutside.dao.CorpDao;
import com.github.cruiser.tradeoutside.model.Corp;

public class CorpDaoHibernate
	extends HibernateDaoSupport
	implements CorpDao{

	@Override
	public Corp get(Long id) {

		return getHibernateTemplate().get(Corp.class, id);
	}

	@Override
	public Long save(Corp object) {

		return (Long)getHibernateTemplate().save(object);
	}

	@Override
	public void update(Corp object) {
		getHibernateTemplate().update(object);

	}

	@Override
	public void delete(Corp object) {
		getHibernateTemplate().delete(object);

	}

	@Override
	public void delete(Long id) {
		delete(get(id));

	}

	public Corp load(Long id) {
		return getHibernateTemplate().load(Corp.class, id);

	}

	@Override
	public List<Corp> findAll() {
		return (List<Corp>)getHibernateTemplate()
				.find("from Corp");

	}

	@Override
	public List<Corp> findByBusiNo(String busiNo) {
		return (List<Corp>)getHibernateTemplate()
				.find("from Corp as a where a.busiNo=?", busiNo);
	}

	@Override
	public void flush() {
		getHibernateTemplate().flush();
		
	}

}
