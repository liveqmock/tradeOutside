package com.github.cruiser.tradeoutside.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.github.cruiser.tradeoutside.dao.TradeDccDao;
import com.github.cruiser.tradeoutside.model.TradeDcc;

public class TradeDccDaoHibernate extends HibernateDaoSupport implements
        TradeDccDao {

    @Override
    public TradeDcc get(Long id) {

        return getHibernateTemplate().get(TradeDcc.class, id);
    }

    @Override
    public Long save(TradeDcc object) {

        return (Long) getHibernateTemplate().save(object);
    }

    @Override
    public void saveOrUpdate(TradeDcc object) {

        getHibernateTemplate().saveOrUpdate(object);
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
        return (List<TradeDcc>) getHibernateTemplate().find("from TradeDcc");

    }

    @Override
    public List<TradeDcc> findByBusiNoActdat(final String busiNo,
            final String actDat) {

        return (List<TradeDcc>) getHibernateTemplate().find(
                "from TradeDcc t where t.busiNo = ? and t.actDat = ?",
                busiNo, actDat);
    }

    @Override
    public List<TradeDcc> findByBusiNoTermActdat(final String busiNo,
            final String termId, final String actDat) {

        return (List<TradeDcc>) getHibernateTemplate().find(
                "from TradeDcc t " +
                "where t.busiNo = ?" +
                " and t.actDat = ?" +
                " and t.termId = ?",
                busiNo, actDat, termId);

    }

    @Override
    public void flush() {
        getHibernateTemplate().flush();

    }

    @Override
    public List<TradeDcc> findByActdat(String actDat) {

        return (List<TradeDcc>) getHibernateTemplate().find(
                "from TradeDcc t " +
                "where t.actDat = ?",
                actDat);
    }

}
