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
import java.util.List;

@WebServlet("/system/UsersListQueryServlet")
public class UsersListQueryServlet extends HttpServlet {
    IUsersService iUsersService = new UsersServiceImp();

    /*
    *查询指定用户（条件查询所有用户）
    * */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Users queryUser = RequestBeanUtils.requestToBean(request,Users.class);
        List<Users> usersList = iUsersService.selectListUsers(queryUser);
        request.setAttribute("queryUser",queryUser);
        request.setAttribute("usersList",usersList);
        request.getRequestDispatcher("/view/system/user/userinfo_list.jsp").forward(request,response);
    }


    /*
    * 查询所有用户
    * */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Users> usersList = iUsersService.selectListUsers(new Users());
        request.setAttribute("usersList",usersList);
        request.getRequestDispatcher("/view/system/user/userinfo_list.jsp").forward(request,response);
    }
}
