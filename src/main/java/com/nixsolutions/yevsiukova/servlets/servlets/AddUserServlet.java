package com.nixsolutions.yevsiukova.servlets.servlets;

import com.nixsolutions.yevsiukova.servlets.dto.UserEntityDTO;
import com.nixsolutions.yevsiukova.servlets.jdbc.entity.RoleEntity;
import com.nixsolutions.yevsiukova.servlets.jdbc.entity.UserEntity;
import com.nixsolutions.yevsiukova.servlets.service.RoleService;
import com.nixsolutions.yevsiukova.servlets.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "AddUserServlet", value = "/addUser")
public class AddUserServlet extends HttpServlet {
    UserService userService;
    RoleService roleService;
    UserEntityDTO userEntityDTO = new UserEntityDTO();

    @Override
    public void init() {
        userService = (UserService) getServletContext().getAttribute("userService");
        roleService = (RoleService) getServletContext().getAttribute("roleService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/add-user.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RoleEntity roleEntity;
        if (request.getParameter("role").equals("admin")) {
            roleEntity = new RoleEntity(1L, "admin");
        } else {
            roleEntity = new RoleEntity(2L, "user");
        }
        userEntityDTO.setRoleEntity(roleEntity);
        userEntityDTO.setLogin(request.getParameter("login"));
        userEntityDTO.setPassword(request.getParameter("password"));
        userEntityDTO.setEmail(request.getParameter("email"));
        userEntityDTO.setFirstName(request.getParameter("firstname"));
        userEntityDTO.setLastName(request.getParameter("lastname"));
        userEntityDTO.setBirthday(java.sql.Date.valueOf(request.getParameter("birthday")));

        userService.create(userEntityDTO);
        response.sendRedirect("/welcomeAdmin");
    }


}