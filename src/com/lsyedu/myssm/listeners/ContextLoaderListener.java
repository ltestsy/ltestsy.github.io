package com.lsyedu.myssm.listeners;

import com.lsyedu.myssm.ioc.BeanFactory;
import com.lsyedu.myssm.ioc.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author lsy
 * @version 1.0
 */
@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext application = servletContextEvent.getServletContext();
        String path = application.getInitParameter("contextConfigLocation");
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(path);

        application.setAttribute("beanFactory", beanFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
