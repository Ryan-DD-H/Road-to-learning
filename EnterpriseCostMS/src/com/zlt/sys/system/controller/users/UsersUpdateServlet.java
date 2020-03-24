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

@WebServlet("/system/UsersUpdateServlet")
public class UsersUpdateServlet extends HttpServlet {

    IUsersService iUsersService = new UsersServiceImp();
    /*
    * 修改用户的信息
    * */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Users user = RequestBeanUtils.requestToBean(request,Users.class);
            request.setAttribute("user",user);
            iUsersService.usersUpdate(user);
            request.setAttribute("tips","用户信息修改成功");
            request.getRequestDispatcher("/view/system/user/userinfo_update.jsp").forward(request,response);
        }catch (RuntimeException e){
            request.setAttribute("tips",e.getMessage());
            request.getRequestDispatcher("/view/system/user/userinfo_update.jsp").forward(request,response);
        }



    }

    /*
    * 显示用户修改的界面
    * */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Users user = RequestBeanUtils.requestToBean(request,Users.class);
        List<Users> usersList = iUsersService.selectListUsers(user);
        user = usersList.get(0);
        request.setAttribute("user",user);
        request.getRequestDispatcher("/view/system/user/userinfo_update.jsp").forward(request,response);

    }
}
