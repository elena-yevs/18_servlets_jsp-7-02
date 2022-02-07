package com.nixsolutions.yevsiukova.servlets.servlets;

import com.nixsolutions.yevsiukova.servlets.dto.UserEntityDTO;
import com.nixsolutions.yevsiukova.servlets.jdbc.entity.UserEntity;
import com.nixsolutions.yevsiukova.servlets.service.RoleService;
import com.nixsolutions.yevsiukova.servlets.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "EditServlet", value = "/edit")
public class EditServlet extends HttpServlet {
    UserEntity userEntity = null;
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/edit-user.jsp");
        //request.setAttribute("userEntity", userEntity);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        userEntity = userService.findByLogin(request.getParameter("login"));

        request.getSession().setAttribute("userEntity", userEntity);
        if (request.getParameter("role").equals("admin")) {
            userEntity.setRoleId(1L);
        } else {
            userEntity.setRoleId(2L);
        }
        userEntity.setPassword(request.getParameter("password"));
        userEntity.setEmail(request.getParameter("email"));
        userEntity.setFirstName(request.getParameter("firstname"));
        userEntity.setLastName(request.getParameter("lastname"));
        userEntity.setBirthday(java.sql.Date.valueOf(request.getParameter("birthday")));
        userService.update(userEntityDTO);
        response.sendRedirect("/welcomeAdmin");
    }
}




