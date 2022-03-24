package com.lsyedu.myssm.trans;

import com.lsyedu.myssm.basedao.ConnUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author lsy
 * @version 1.0
 */
public class TransactionManager {
    public static void beginTrans() throws SQLException {
        ConnUtil.getConn().setAutoCommit(false);
    }

    public static void commit() throws SQLException {
        Connection connection = ConnUtil.getConn();
        connection.commit();
        ConnUtil.closeConn();
    }

    public static void rollback() throws SQLException {
        Connection connection = ConnUtil.getConn();
        connection.rollback();
        ConnUtil.closeConn();
    }
}
