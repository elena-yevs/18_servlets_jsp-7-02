package com.nixsolutions.yevsiukova.servlets.context;

import com.nixsolutions.yevsiukova.servlets.jdbc.dao.dml.JdbcRoleDao;
import com.nixsolutions.yevsiukova.servlets.jdbc.dao.dml.JdbcUserDao;
import com.nixsolutions.yevsiukova.servlets.service.RoleService;
import com.nixsolutions.yevsiukova.servlets.service.UserService;

import javax.servlet.ServletContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ClassContext implements ServletContextListener {
    @Override
    public void contextInitialized(javax.servlet.ServletContextEvent sce) {

        JdbcUserDao userDao = new JdbcUserDao();
        JdbcRoleDao roleDao = new JdbcRoleDao();

        UserService userService = new UserService(userDao);
        RoleService roleService = new RoleService(roleDao);
        final ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("userService", userService);
        servletContext.setAttribute("roleService", roleService);
    }


    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}
