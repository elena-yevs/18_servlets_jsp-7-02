package com.nixsolutions.yevsiukova.servlets.servlets;

import com.nixsolutions.yevsiukova.servlets.jdbc.entity.UserEntity;
import com.nixsolutions.yevsiukova.servlets.service.RoleService;
import com.nixsolutions.yevsiukova.servlets.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "DeleteServlet", value = "/delete")
public class DeleteServlet extends HttpServlet {
    UserService userService;
    RoleService roleService;


    @Override
    public void init() {
        userService = (UserService) getServletContext().getAttribute("userService");
        roleService = (RoleService) getServletContext().getAttribute("roleService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        UserEntity deletedUser = userService.findByLogin(request.getParameter("login"));
//        userService.remove(deletedUser);
        response.sendRedirect("/welcomeAdmin");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
