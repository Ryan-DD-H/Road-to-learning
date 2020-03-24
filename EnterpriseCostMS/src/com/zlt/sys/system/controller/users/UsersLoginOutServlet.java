package com.zlt.sys.system.controller.users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



@WebServlet("/system/UsersLoginOutServlet")
public class UsersLoginOutServlet extends HttpServlet {

    /*
    * 用于用户退出登录使用
    * */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //删除session域中所有的信息,并跳转至登录界面
        HttpSession session = request.getSession();
        session.removeAttribute("users");
        session.removeAttribute("menus");
        session.removeAttribute("salaryRecords");
        response.sendRedirect("/view/login.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
