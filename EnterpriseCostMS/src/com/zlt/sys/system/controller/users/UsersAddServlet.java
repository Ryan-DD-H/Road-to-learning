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

@WebServlet("/system/UsersAddServlet")
public class UsersAddServlet extends HttpServlet {

    /*
    * 执行添加用户的请求
    * */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            IUsersService iUsersService = new UsersServiceImp();
            //获取前端传来的所需添加用户的数据
            Users users = RequestBeanUtils.requestToBean(request, Users.class);
            //调用Service层的方法添加用户
            iUsersService.usersAdd(users);
            //提示信息
            request.setAttribute("tips", "用户添加成功！");
            request.getRequestDispatcher("/view/system/user/userinfo_add.jsp").forward(request, response);
        }catch (RuntimeException e){
            request.setAttribute("tips", e.getMessage());
            request.getRequestDispatcher("/view/system/user/userinfo_add.jsp").forward(request, response);
        }
    }

    /*
    * 跳转至添加用户界面
    * */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/system/user/userinfo_add.jsp").forward(request,response);
    }
}
