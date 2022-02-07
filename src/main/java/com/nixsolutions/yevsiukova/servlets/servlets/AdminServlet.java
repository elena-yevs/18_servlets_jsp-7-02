package com.nixsolutions.yevsiukova.servlets.servlets;

import com.nixsolutions.yevsiukova.servlets.dto.UserEntityDTO;
import com.nixsolutions.yevsiukova.servlets.jdbc.entity.RoleEntity;
import com.nixsolutions.yevsiukova.servlets.service.RoleService;
import com.nixsolutions.yevsiukova.servlets.service.UserService;

import javax.management.relation.Role;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "AdminServlet", value = "/welcomeAdmin")
public class AdminServlet extends HttpServlet {
    String login = "admin";
    //  String roleName = "admin";
    UserService userService;
    RoleService roleService;
    UserEntityDTO userEntityDTO = new UserEntityDTO();
    RoleEntity roleEntity = new RoleEntity();


    @Override
    public void init() {
        userService = (UserService) getServletContext().getAttribute("userService");
        roleService = (RoleService) getServletContext().getAttribute("roleService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("usersList", userService.findAll());
        session.setAttribute("lastName", userService.findByLogin(login).getLastName());
       // session.setAttribute("roleName", roleService.findByName(userEntityDTO.getRoleName()).getRoleName());
          request.getSession().setAttribute("roleName", roleEntity.getRoleName());
        //request.getSession().setAttribute("roleName", userEntityDTO.getRoleEntity().getRoleName());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/admin-page.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}