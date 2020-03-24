package com.zlt.sys.system.controller.users;

import com.my.web.servlet.RequestBeanUtils;
import com.zlt.pojo.Menu;
import com.zlt.pojo.SalaryRecord;
import com.zlt.pojo.Users;
import com.zlt.sys.finance.service.IAuditService;
import com.zlt.sys.finance.service.imp.AuditServiceImp;
import com.zlt.sys.system.service.IUsersService;
import com.zlt.sys.system.service.imp.UsersServiceImp;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;



@WebServlet("/system/UsersLoginServlet")
public class UsersLoginServlet extends HttpServlet {

    /*
    * 用于用户登录及其后续界面的加载
    * */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            IUsersService iUsersService = new UsersServiceImp();

            //获取前端传来的数据
            Users users = RequestBeanUtils.requestToBean(request, Users.class);
            //查询所填信息是否与数据库中相匹配
            users = iUsersService.usersLogin(users);

            //如果数据库中存在相应的信息，则进行以下操作(否则会报错，不会执行)

            //将获取到的users数据存入session域中
            HttpSession session = request.getSession();
            session.setAttribute("users", users);

            //权限查询
            List<Menu> menus = iUsersService.selectMenus(users.getRoleId());
            session.setAttribute("menus", menus);

            //获取对应的工资图形报表
            List<SalaryRecord> salaryRecords = iUsersService.selectImg(users.getUsersId());
            session.setAttribute("salaryRecords", salaryRecords);

            //获取对应的待审核消息条数
            IAuditService iAuditService = new AuditServiceImp();
            int messageNum = iAuditService.getAuditMessage(users.getRoleId());
            session.setAttribute("messageNum", messageNum);

            //跳转至系统主界面
            response.sendRedirect("/view/index.jsp");

        }catch (RuntimeException e){
            request.setAttribute("tips", e.getMessage());
            request.getRequestDispatcher("/view/login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
