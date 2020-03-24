package com.zlt.sys.system.controller.users;

import com.my.web.servlet.RequestBeanUtils;
import com.zlt.pojo.SalaryRecord;
import com.zlt.pojo.Users;
import com.zlt.sys.finance.service.ISalaryService;
import com.zlt.sys.finance.service.imp.SalaryServiceImp;
import com.zlt.utiles.ExpenseDateFormat;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/system/UsersSalaryServlet")
public class UsersSalaryServlet extends HttpServlet {


    /*
    * 用户查询自己的薪水
    * */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ISalaryService iSalaryService = new SalaryServiceImp();
        //格式化时间
        ConvertUtils.register(new ExpenseDateFormat("yyyy-MM"), Date.class);
        //获取前端数据
        SalaryRecord salaryRecord = RequestBeanUtils.requestToSimpleBean(request,SalaryRecord.class);
        //设置用户的id，使其只允许查自己的薪水
        Users users = (Users) request.getSession().getAttribute("users");
        salaryRecord.setUsersId(users.getUsersId());

        List<SalaryRecord> salaryRecordList = iSalaryService.selectSalaryRecord(salaryRecord);
        request.setAttribute("salaryRecord",salaryRecord);
        request.setAttribute("salaryRecordList",salaryRecordList);
        request.getRequestDispatcher("/view/system/user/salarypayment_mylist.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
