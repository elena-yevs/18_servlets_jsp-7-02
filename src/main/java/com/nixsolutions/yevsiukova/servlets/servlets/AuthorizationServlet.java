package com.nixsolutions.yevsiukova.servlets.servlets;

import com.nixsolutions.yevsiukova.servlets.jdbc.entity.UserEntity;
import com.nixsolutions.yevsiukova.servlets.service.RoleService;
import com.nixsolutions.yevsiukova.servlets.service.UserService;
import javax.servlet.http.HttpSession;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.Objects;


@WebServlet(name = "AuthorizationServlet", value = "/welcome")
public class AuthorizationServlet extends HttpServlet {
    UserService userService;
    RoleService roleService;

    @Override
    public void init() {
        userService = (UserService) getServletContext().getAttribute("userService");
        roleService = (RoleService) getServletContext().getAttribute("roleService");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/authorization.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adminPassword = "admin";
        String userPassword = "user";
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        UserEntity currentUser = userService.findByLogin(login);
        boolean isAuthorized = currentUser != null;

        HttpSession httpSession = request.getSession();

        if (isAuthorized && Objects.equals(password, adminPassword)) {
            httpSession.setAttribute("currentUser", currentUser);
            request.getSession().setAttribute(login.trim(), password.trim());
            response.sendRedirect("/welcomeAdmin");
        } else if (isAuthorized && Objects.equals(password, userPassword)) {
            httpSession.setAttribute("currentUser", currentUser);
            request.getSession().setAttribute(login.trim(), password.trim());
            response.sendRedirect("/welcomeUser");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/authorization.jsp");
            dispatcher.forward(request, response);
        }
    }
}

