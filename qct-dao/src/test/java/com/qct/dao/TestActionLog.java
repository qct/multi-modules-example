package com.qct.dao;

import com.qct.dao.pojo.ActionLog;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by qct on 2015/2/13.
 */
public class TestActionLog extends TestBase {

    @Autowired
    ActionLogDAO actionLogDAO;

    @Test
    public void testFind() {
        ActionLog al = actionLogDAO.findById(4063l);
        logger.debug("**************" + al.getUid());
    }
}
