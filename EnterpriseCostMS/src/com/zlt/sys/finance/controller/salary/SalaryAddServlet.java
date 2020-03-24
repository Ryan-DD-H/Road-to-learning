package com.zlt.sys.finance.controller.salary;

import com.my.web.servlet.RequestBeanUtils;
import com.zlt.pojo.SalaryRecord;
import com.zlt.pojo.Users;
import com.zlt.sys.finance.service.ISalaryService;
import com.zlt.sys.finance.service.imp.SalaryServiceImp;
import com.zlt.sys.system.service.IUsersService;
import com.zlt.sys.system.service.imp.UsersServiceImp;
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

@WebServlet("/finance/SalaryAddServlet")
public class SalaryAddServlet extends HttpServlet {


    IUsersService iUsersService = new UsersServiceImp();
    ISalaryService iSalaryService = new SalaryServiceImp();
    /*
    * 发放薪资
    * */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            //格式化时间
            ConvertUtils.register(new ExpenseDateFormat("yyyy-MM"), Date.class);
            //查询所有的用户的信息
            List<Users> usersList = iUsersService.selectUsersByName();
            request.setAttribute("usersList", usersList);
            //获取前端数据
            SalaryRecord salaryRecord = RequestBeanUtils.requestToSimpleBean(request, SalaryRecord.class);
            iSalaryService.salaryAdd(salaryRecord);
            request.setAttribute("tips", "薪资发放成功");
            request.getRequestDispatcher("/view/finance/salary/salarypayment_add.jsp").forward(request, response);
        }catch (RuntimeException e){
            request.setAttribute("tips", e.getMessage());
            request.getRequestDispatcher("/view/finance/salary/salarypayment_add.jsp").forward(request, response);
        }
    }


    /*
    * 跳转至发放薪资的界面
    * */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询所有的用户的信息
        List<Users> usersList = iUsersService.selectUsersByName();
        request.setAttribute("usersList",usersList);
        request.getRequestDispatcher("/view/finance/salary/salarypayment_add.jsp").forward(request,response);
    }
}
