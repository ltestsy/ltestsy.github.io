package com.lsyedu.myssm.myspringmvc;

import com.lsyedu.myssm.ioc.BeanFactory;
import com.lsyedu.myssm.util.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author lsy
 * @version 1.0
 */
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {
    private BeanFactory beanFactory;

    public void init() throws ServletException {
        super.init();
        //beanFactory = new ClassPathXmlApplicationContext();
        ServletContext application = getServletContext();
        Object beanFactoryObj = application.getAttribute("beanFactory");
        if(beanFactoryObj != null) {
            beanFactory = (BeanFactory)beanFactoryObj;
        } else {
            throw new RuntimeException("IOC容器获取失败");
        }
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        servletPath = servletPath.substring(1);
        int lastDotIndex = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(0, lastDotIndex);
        Object controllerBeanObj = beanFactory.getBean(servletPath);
        String operate = req.getParameter("operate");
        if(StringUtil.isEmpty(operate)) {
            operate = "index";
        }
        try {
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if(operate.equals(method.getName())) {
                    Parameter[] parameters = method.getParameters();
                    Object[] parameterValues = new Object[parameters.length];
                    for (int i = 0; i < parameterValues.length; i++) {
                        Parameter parameter = parameters[i];
                        String parameterName = parameter.getName();
                        if("req".equals(parameterName)) {
                            parameterValues[i] = req;
                        } else if("resp".equals(parameterName)) {
                            parameterValues[i] = resp;
                        } else if("session".equals(parameterName)) {
                            parameterValues[i] = req.getSession();
                        } else {
                            String parameterValue = req.getParameter(parameterName);
                            String typeName = parameter.getType().getName();
                            Object parameterObj = parameterValue;
                            if(parameterObj != null) {
                                if ("java.lang.Integer".equals(typeName)) {
                                    parameterObj = Integer.parseInt(parameterValue);
                                }
                            }
                            parameterValues[i] = parameterObj;
                        }
                    }
                    method.setAccessible(true);
                    Object returnObj = method.invoke(controllerBeanObj, parameterValues);
                    String methodReturnStr = (String)returnObj;
                    if(methodReturnStr.startsWith("redirect:")) {
                        String redirectStr = methodReturnStr.substring("redirect:".length());
                        resp.sendRedirect(redirectStr);
                    } else {
                        super.processTemplate(methodReturnStr, req, resp);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DispatcherServletException("DispatcherServlet 出错了");
        }
    }
}
