package com.zlt.sys.finance.controller.salary;

import com.my.web.servlet.RequestBeanUtils;
import com.zlt.pojo.Users;
import com.zlt.sys.system.service.IUsersService;
import com.zlt.sys.system.service.imp.UsersServiceImp;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/finance/SalaryQueryServlet")
public class SalaryQueryServlet extends HttpServlet {

    /*
    * AJAX调用查底薪
    * */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //ajax调用的doPost方法
        IUsersService iUsersService = new UsersServiceImp();
        Users users = RequestBeanUtils.requestToBean(request,Users.class);
        List<Users> usersList = iUsersService.selectListUsers(users);
        users = usersList.get(0);
        Float usersSalary = users.getUsersSalary();

        //将获取到的底薪发送的前端
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("usersSalary",usersSalary);
        PrintWriter printWriter = response.getWriter();
        printWriter.print(jsonObject);
        printWriter.flush();
        printWriter.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
