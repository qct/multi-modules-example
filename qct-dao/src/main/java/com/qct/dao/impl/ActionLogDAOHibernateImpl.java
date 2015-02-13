package com.qct.dao.impl;

import com.qct.dao.ActionLogDAO;
import com.qct.dao.base.GenericHibernateDAO;
import com.qct.dao.pojo.ActionLog;
import org.springframework.stereotype.Repository;

/**
 * Created by qct on 2015/2/13.
 */
@Repository("actionLogDAO")
public class ActionLogDAOHibernateImpl extends GenericHibernateDAO<ActionLog, Long> implements ActionLogDAO {
}
