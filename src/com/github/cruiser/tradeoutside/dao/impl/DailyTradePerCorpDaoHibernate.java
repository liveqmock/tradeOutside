package com.github.cruiser.tradeoutside.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.github.cruiser.tradeoutside.dao.DailyTradePerCorpDao;
import com.github.cruiser.tradeoutside.model.Corp;
import com.github.cruiser.tradeoutside.model.DailyTradePerCorp;

public class DailyTradePerCorpDaoHibernate extends HibernateDaoSupport
        implements DailyTradePerCorpDao {

    @Override
    public DailyTradePerCorp get(Long id) {

        return getHibernateTemplate().get(DailyTradePerCorp.class, id);
    }

    @Override
    public Long save(DailyTradePerCorp object) {

        return (Long) getHibernateTemplate().save(object);
    }

    @Override
    public void saveOrUpdate(DailyTradePerCorp object) {
        getHibernateTemplate().saveOrUpdate(object);

    }

    @Override
    public void update(DailyTradePerCorp object) {
        getHibernateTemplate().update(object);

    }

    @Override
    public void delete(DailyTradePerCorp object) {
        getHibernateTemplate().delete(object);

    }

    @Override
    public void delete(Long id) {
        delete(get(id));

    }

    public DailyTradePerCorp load(Long id) {
        return getHibernateTemplate().load(DailyTradePerCorp.class, id);

    }

    @Override
    public void flush() {
        getHibernateTemplate().flush();

    }

    @Override
    public List<DailyTradePerCorp> findAll() {
        return (List<DailyTradePerCorp>) getHibernateTemplate().find(
                "from DailyTradePerCorp");

    }

    @Override
    public List<DailyTradePerCorp> findByReqDat(String reqDat) {
        return getHibernateTemplate().find(
                "from DailyTradePerCorp as d where d.reqDat=?",
                reqDat
                );

    }

    @Override
    public List<DailyTradePerCorp> findByReqDat(String startdate, String endate) {
        return getHibernateTemplate().find(
                "from DailyTradePerCorp as d" +
                " where d.reqDat>=? and d.reqDat<=?",
                startdate, endate);

    }

    @Override
    public List<DailyTradePerCorp> findByCorpReqDat(Corp corp, String reqDat) {
        return (List<DailyTradePerCorp>) getHibernateTemplate()
                .find("from DailyTradePerCorp as d " + "where d.corp=?"
                        + " and d.reqDat=?", corp, reqDat);

    }

    @Override
    public List<DailyTradePerCorp> findByCorpReqDat(Corp corp,
            String startdate, String endate) {
        return getHibernateTemplate().find(
                "from DailyTradePerCorp as d where d.Corp=?"
                        + " and d.reqDat>=? and d.reqDat<=?", corp,
                startdate, endate);

    }

}