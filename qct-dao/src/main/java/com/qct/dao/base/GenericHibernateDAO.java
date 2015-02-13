/**
 * *
 */
package com.qct.dao.base;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * GenericHibernateDao 继承 HibernateDao，简单封装 HibernateTemplate 各项功能，
 * 简化基于Hibernate Dao 的编写。
 *
 * Created by qct on 2015/2/12.
 */
public abstract class GenericHibernateDAO<T, ID extends Serializable>
        extends HibernateDaoSupport implements GenericDAO<T, ID> {

    private Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public GenericHibernateDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * 为父类HibernateDaoSupport注入SessionFactory
     *
     * @param sessionFactory
     */
    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }


    // ==================== 基本检索、增加、修改、删除操作 ====================

    @Override
    public T get(ID id) {
        return (T) getHibernateTemplate().get(persistentClass, id);
    }

    @Override
    public T getWithLock(ID id, LockMode lock) {
        T t = (T) getHibernateTemplate().get(persistentClass, id, lock);
        if (t != null) {
            this.flush();   // 立即刷新，否则锁不会生效。
        }
        return t;
    }

    @Override
    public T load(ID id) {
        return (T) getHibernateTemplate().load(persistentClass, id);
    }

    @Override
    public T loadWithLock(ID id, LockMode lock) {
        T t = (T) getHibernateTemplate().load(persistentClass, id, lock);
        if (t != null) {
            this.flush();   // 立即刷新，否则锁不会生效。
        }
        return t;
    }

    @Override
    public List<T> loadAll() {
        return (List<T>) getHibernateTemplate().loadAll(persistentClass);
    }

    @Override
    public T findById(ID id) {
        return load(id);
    }

    @Override
    public T findById(ID id, boolean lock) {
        T entity;
        if (lock)
            entity = (T) getHibernateTemplate().load(getPersistentClass(), id,
                    LockMode.PESSIMISTIC_WRITE);
        else
            entity = findById(id);

        return entity;
    }

    @Override
    public void update(T entity) {
        getHibernateTemplate().update(entity);
    }

    @Override
    public void updateWithLock(T entity, LockMode lock) {
        getHibernateTemplate().update(entity, lock);
        this.flush();   // 立即刷新，否则锁不会生效。
    }

    @Override
    public void save(T entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public void saveOrUpdate(T entity) {
        makePersistent(entity);
    }

    @Override
    public void delete(T entity) {
        this.makeTransient(entity);
    }

    @Override
    public void deleteWithLock(T entity, LockMode lock) {
        getHibernateTemplate().delete(entity, lock);
        this.flush();   // 立即刷新，否则锁不会生效。
    }

    @Override
    public void deleteByPrimaryKey(ID id) {
        this.delete(this.load(id));
    }

    @Override
    public void deleteByPrimaryKeyWithLock(ID id, LockMode lock) {
        this.deleteWithLock(this.load(id), lock);
    }

    @Override
    public void deleteAll(Collection<T> entities) {
        getHibernateTemplate().deleteAll(entities);
    }

    @Override
    public List<T> findAll() {
        return findByCriteria();
    }

    @Override
    public List<T> findByExample(T exampleInstance) {
        DetachedCriteria detachedCrit = DetachedCriteria
                .forClass(getPersistentClass());
        Example example = Example.create(exampleInstance);
        detachedCrit.add(example);
        return (List<T>) getHibernateTemplate().findByCriteria(detachedCrit);
    }

    @Override
    public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
        DetachedCriteria detachedCrit = DetachedCriteria
                .forClass(getPersistentClass());
        Example example = Example.create(exampleInstance);
        for (String exclude : excludeProperty) {
            example.excludeProperty(exclude);
        }
        detachedCrit.add(example);
        return (List<T>) getHibernateTemplate().findByCriteria(detachedCrit);
    }

    @Override
    public T makePersistent(T entity) {
        getHibernateTemplate().saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void makeTransient(T entity) {
        getHibernateTemplate().delete(entity);
    }

    @Override
    public List<T> findByCriteria(Criterion... criterion) {
        DetachedCriteria detachedCrit = DetachedCriteria
                .forClass(getPersistentClass());
        for (Criterion c : criterion) {
            detachedCrit.add(c);
        }
        return (List<T>) getHibernateTemplate().findByCriteria(detachedCrit);
    }

    @Deprecated
    @Override
    public void saveOrUpdateAll(Collection<T> entities) {
        for(T entity : entities) {
            saveOrUpdate(entity);
        }
    }

    @Override
    public List<T> findByCriteria(Order order, Criterion... criterion) {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        for (Criterion c : criterion) {
            crit.add(c);
        }
        if (order != null)
            crit.addOrder(order);
        return crit.list();
    }

    @Override
    public List<T> findByCriteria(Order order, int firstResult, int maxResults, Criterion... criterion) {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        for (Criterion c : criterion) {
            crit.add(c);
        }
        if (order != null)
            crit.addOrder(order);
        crit.setFirstResult(firstResult);
        crit.setMaxResults(maxResults);
        return crit.list();
    }


    // ==================== HSQL ==============================================

    @Override
    public int bulkUpdate(String queryString) {
        return getHibernateTemplate().bulkUpdate(queryString);
    }

    @Override
    public int bulkUpdate(String queryString, Object[] values) {
        return getHibernateTemplate().bulkUpdate(queryString, values);
    }

    @Override
    public List find(String queryString) {
        return getHibernateTemplate().find(queryString);
    }

    @Override
    public List find(String queryString, Object[] values) {
        return getHibernateTemplate().find(queryString, values);
    }

    @Override
    public List findByNamedParam(String queryString, String[] paramNames, Object[] values) {
        return getHibernateTemplate().findByNamedParam(queryString, paramNames, values);
    }

    @Override
    public List findByNamedQuery(String queryName) {
        return getHibernateTemplate().findByNamedQuery(queryName);
    }

    @Override
    public List findByNamedQuery(String queryName, Object[] values) {
        return getHibernateTemplate().findByNamedQuery(queryName, values);
    }

    @Override
    public List findByNamedQueryAndNamedParam(String queryName, String[] paramNames, Object[] values) {
        return getHibernateTemplate().findByNamedQueryAndNamedParam(queryName, paramNames, values);
    }

    @Override
    public Iterator iterate(String queryString) {
        return getHibernateTemplate().iterate(queryString);
    }

    @Override
    public Iterator iterate(String queryString, Object[] values) {
        return getHibernateTemplate().iterate(queryString, values);
    }

    @Override
    public void closeIterator(Iterator it) {
        getHibernateTemplate().closeIterator(it);
    }


    // ================================ Criteria ==============================

    @Override
    public DetachedCriteria createDetachedCriteria() {
        return DetachedCriteria.forClass(this.persistentClass);
    }

    @Override
    public Criteria createCriteria() {
        return this.createDetachedCriteria().getExecutableCriteria(this.getSession());
    }

    @Override
    public List findByCriteria(DetachedCriteria criteria) {
        return getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List findByCriteria(DetachedCriteria criteria, int firstResult, int maxResults) {
        return getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
    }

    @Override
    public List<T> findEqualByEntity(T entity, String[] propertyNames) {
        Criteria criteria = this.createCriteria();
        Example exam = Example.create(entity);
        exam.excludeZeroes();
        String[] defPropertys = getSessionFactory().getClassMetadata(persistentClass).getPropertyNames();
        for (String defProperty : defPropertys) {
            int ii = 0;
            for (ii = 0; ii < propertyNames.length; ++ii) {
                if (defProperty.equals(propertyNames[ii])) {
                    criteria.addOrder(Order.asc(defProperty));
                    break;
                }
            }
            if (ii == propertyNames.length) {
                exam.excludeProperty(defProperty);
            }
        }
        criteria.add(exam);
        return (List<T>) criteria.list();
    }

    @Override
    public List<T> findLikeByEntity(T entity, String[] propertyNames) {
        Criteria criteria = this.createCriteria();
        for (String property : propertyNames) {
            try {
                Object value = PropertyUtils.getProperty(entity, property);
                if (value instanceof String) {
                    criteria.add(Restrictions.like(property, (String) value, MatchMode.ANYWHERE));
                    criteria.addOrder(Order.asc(property));
                } else {
                    criteria.add(Restrictions.eq(property, value));
                    criteria.addOrder(Order.asc(property));
                }
            } catch (Exception ex) {
                // 忽略无效的检索参考数据。
            }
        }
        return (List<T>) criteria.list();
    }

    @Override
    public Integer getRowCount(DetachedCriteria criteria) {
        criteria.setProjection(Projections.rowCount());
        List list = this.findByCriteria(criteria, 0, 1);
        return (Integer) list.get(0);
    }

    @Override
    public Object getStatValue(DetachedCriteria criteria, String propertyName, String StatName) {
        if (StatName.toLowerCase().equals("max"))
            criteria.setProjection(Projections.max(propertyName));
        else if (StatName.toLowerCase().equals("min"))
            criteria.setProjection(Projections.min(propertyName));
        else if (StatName.toLowerCase().equals("avg"))
            criteria.setProjection(Projections.avg(propertyName));
        else if (StatName.toLowerCase().equals("sum"))
            criteria.setProjection(Projections.sum(propertyName));
        else return null;
        List list = this.findByCriteria(criteria, 0, 1);
        return list.get(0);
    }


    // ================================ Others ================================

    @Override
    public void lock(T entity, LockMode lockMode) {
        getHibernateTemplate().lock(entity, lockMode);
    }

    @Override
    public void initialize(Object proxy) {
        getHibernateTemplate().initialize(proxy);
    }

    @Override
    public void flush() {
        getHibernateTemplate().flush();
    }

    @Override
    public void merge(T entity) {
        this.getSession().merge(entity);
    }
}
