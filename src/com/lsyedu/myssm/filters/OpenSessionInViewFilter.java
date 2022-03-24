package com.lsyedu.myssm.filters;

import com.lsyedu.myssm.trans.TransactionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author lsy
 * @version 1.0
 */
@WebFilter("*.do")
public class OpenSessionInViewFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            TransactionManager.beginTrans();
            filterChain.doFilter(servletRequest, servletResponse);
            TransactionManager.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                TransactionManager.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {

    }
}
