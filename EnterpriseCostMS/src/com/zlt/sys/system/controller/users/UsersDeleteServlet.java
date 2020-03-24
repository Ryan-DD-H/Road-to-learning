package com.zlt.sys.system.controller.users;

import com.my.web.servlet.RequestBeanUtils;
import com.zlt.pojo.Users;
import com.zlt.sys.system.service.IUsersService;
import com.zlt.sys.system.service.imp.UsersServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/system/UsersDeleteServlet")
public class UsersDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IUsersService iUsersService = new UsersServiceImp();
        try {
            Users users = RequestBeanUtils.requestToBean(request, Users.class);
            iUsersService.usersDelete(users);
            request.setAttribute("tips", "用户删除成功");
            request.getRequestDispatcher("/system/UsersListQueryServlet").forward(request, response);
        }catch (RuntimeException e){
            request.setAttribute("tips", e.getMessage());
            request.getRequestDispatcher("/system/UsersListQueryServlet").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
