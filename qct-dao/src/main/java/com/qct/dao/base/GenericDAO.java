package com.qct.dao.base;


import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by qct on 2015/2/12.
 */
public interface GenericDAO<T, ID extends Serializable> {

    // ==================== 基本检索、增加、修改、删除操作 ====================

    /**
     * 根据主键获取实体。如果没有相应的实体，返回 null。
     *
     * @param id
     * @return
     */
    T get(ID id);


    /**
     * 根据主键获取实体并加锁。如果没有相应的实体，返回 null。
     *
     * @param id
     * @param lock
     * @return
     */
    T getWithLock(ID id, LockMode lock);

    /**
     * 根据主键获取实体。如果没有相应的实体，抛出异常。
     *
     * @param id
     * @return
     */
    T load(ID id);

    /**
     * 根据主键获取实体并加锁。如果没有相应的实体，抛出异常。
     *
     * @param id
     * @param lock
     * @return
     */
    T loadWithLock(ID id, LockMode lock);

    /**
     * 通过ID检索对应的实体对象
     *
     * @param id
     * @param lock
     * @return
     */
    T findById(ID id, boolean lock);

    /**
     * 通过ID检索对应的实体对象*
     *
     * @param id
     * @return
     */
    T findById(ID id);

    /**
     * 列出所有的实体对象*
     *
     * @return
     */
    List<T> findAll();

    /**
     * 获取全部实体。
     *
     * @return
     */
    List<T> loadAll();

    /**
     * 更新实体
     *
     * @param entity
     */
    void update(T entity);

    /**
     * 更新实体并加锁
     *
     * @param entity
     * @param lock
     */
    void updateWithLock(T entity, LockMode lock);

    /**
     * *
     * 存储实体到数据库
     *
     * @param entity
     */
    void save(T entity);


    /**
     * 增加或更新实体
     *
     * @param entity
     */
    void saveOrUpdate(T entity);


    /**
     *
     * 增加或更新集合中的全部实体
     * 这个方法最好不要用，要用最好根据自己需求覆写
     *
     * @param entities
     */
    void saveOrUpdateAll(Collection<T> entities);

    /**
     * 删除指定的实体
     *
     * @param entity
     */
    void delete(T entity);

    /**
     * 加锁并删除指定的实体
     *
     * @param entity
     * @param lock
     */
    void deleteWithLock(T entity, LockMode lock);

    /**
     * 通过Example方法检索实体对象*
     *
     * @param exampleInstance
     * @return
     */
    List<T> findByExample(T exampleInstance);

    /**
     * * 通过Example方法检索实体对象
     *
     * @param exampleInstance
     * @param excludeProperty
     * @return
     */
    @SuppressWarnings("unchecked")
    List<T> findByExample(T exampleInstance, String[] excludeProperty);


    /**
     * 产生持久化一个实体对象
     *
     * @param entity
     * @return
     */
    T makePersistent(T entity);

    /**
     * 产生一个游离对象
     *
     * @param entity
     */
    void makeTransient(T entity);

    /**
     * 根据主键删除指定实体
     *
     * @param id
     */
    void deleteByPrimaryKey(ID id);

    /**
     * 根据主键加锁并删除指定的实体
     *
     * @param id
     * @param lock
     */
    void deleteByPrimaryKeyWithLock(ID id, LockMode lock);

    /**
     * 删除集合中的全部实体
     *
     * @param entities
     */
    void deleteAll(Collection<T> entities);

    /**
     * *
     *
     * @param criterion
     * @return
     */
    @SuppressWarnings("unchecked")
    List<T> findByCriteria(Criterion... criterion);

    /**
     * * 增加了排序的功能。
     *
     * @param order
     * @param criterion
     * @return
     */
    @SuppressWarnings("unchecked")
    List<T> findByCriteria(Order order, Criterion... criterion);

    /**
     * * 增加了翻页功能
     *
     * @param order
     * @param firstResult
     * @param maxResults
     * @param criterion
     * @return
     */
    @SuppressWarnings("unchecked")
    List<T> findByCriteria(Order order, int firstResult, int maxResults, Criterion... criterion);


    // ==================== HSQL ==============================================

    /**
     * 使用HSQL语句直接增加、更新、删除实体
     *
     * @param queryString
     * @return
     */
    int bulkUpdate(String queryString);

    /**
     * 使用带参数的HSQL语句增加、更新、删除实体
     *
     * @param queryString
     * @param values
     * @return
     */
    int bulkUpdate(String queryString, Object[] values);

    /**
     * 使用HSQL语句检索数据
     *
     * @param queryString
     * @return
     */
    List find(String queryString);

    /**
     * 使用带参数的HSQL语句检索数据
     *
     * @param queryString
     * @param values
     * @return
     */
    List find(String queryString, Object[] values);

    /**
     * 使用带命名的参数的HSQL语句检索数据
     *
     * @param queryString
     * @param paramNames
     * @param values
     * @return
     */
    List findByNamedParam(String queryString, String[] paramNames, Object[] values);

    /**
     * 使用命名的HSQL语句检索数据
     *
     * @param queryName
     * @return
     */
    List findByNamedQuery(String queryName);

    /**
     * 使用带参数的命名HSQL语句检索数据
     *
     * @param queryName
     * @param values
     * @return
     */
    List findByNamedQuery(String queryName, Object[] values);

    /**
     * 使用带命名参数的命名HSQL语句检索数据
     *
     * @param queryName
     * @param paramNames
     * @param values
     * @return
     */
    List findByNamedQueryAndNamedParam(String queryName, String[] paramNames, Object[] values);

    /**
     * 使用HSQL语句检索数据，返回 Iterator
     *
     * @param queryString
     * @return
     */
    Iterator iterate(String queryString);

    /**
     * 使用带参数HSQL语句检索数据，返回 Iterator
     *
     * @param queryString
     * @param values
     * @return
     */
    Iterator iterate(String queryString, Object[] values);

    /**
     * 关闭检索返回的 Iterator
     *
     * @param it
     */
    void closeIterator(Iterator it);


    // ================================ Criteria ==============================

    /**
     * 创建与会话无关的检索标准对象
     *
     * @return
     */
    DetachedCriteria createDetachedCriteria();

    /**
     * 创建与会话绑定的检索标准对象
     *
     * @return
     */
    Criteria createCriteria();

    /**
     * 使用指定的检索标准检索数据
     *
     * @param criteria
     * @return
     */
    List findByCriteria(DetachedCriteria criteria);

    /**
     * 使用指定的检索标准检索数据，返回部分记录
     *
     * @param criteria
     * @param firstResult
     * @param maxResults
     * @return
     */
    List findByCriteria(DetachedCriteria criteria, int firstResult, int maxResults);

    /**
     * 使用指定的实体及属性检索（满足除主键外属性＝实体值）数据
     *
     * @param entity
     * @param propertyNames
     * @return
     */
    List<T> findEqualByEntity(T entity, String[] propertyNames);

    /**
     * 使用指定的实体及属性(非主键)检索（满足属性 like 串实体值）数据
     *
     * @param entity
     * @param propertyNames
     * @return
     */
    List<T> findLikeByEntity(T entity, String[] propertyNames);

    /**
     * 使用指定的检索标准检索数据，返回指定范围的记录
     *
     * @param criteria
     * @return
     */
    Integer getRowCount(DetachedCriteria criteria);

    /**
     * 使用指定的检索标准检索数据，返回指定统计值
     *
     * @param criteria
     * @param propertyName
     * @param StatName
     * @return
     */
    Object getStatValue(DetachedCriteria criteria, String propertyName, String StatName);


    // ================================ Others ================================

    /**
     * 加锁指定的实体
     *
     * @param entity
     * @param lockMode
     */
    void lock(T entity, LockMode lockMode);

    /**
     * 强制初始化指定的实体
     *
     * @param proxy
     */
    void initialize(Object proxy);

    /**
     * 强制立即更新缓冲数据到数据库（否则仅在事务提交时才更新）
     */
    void flush();

    /**
     * * merge
     *
     * @param entity
     */
    void merge(T entity);
}