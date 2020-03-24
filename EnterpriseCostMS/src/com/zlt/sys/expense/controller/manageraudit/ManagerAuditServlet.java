package com.zlt.sys.expense.controller.manageraudit;

import com.my.web.servlet.RequestBeanUtils;
import com.zlt.pojo.AuditRecord;
import com.zlt.pojo.Expense;
import com.zlt.sys.expense.service.IExpenseService;
import com.zlt.sys.expense.service.IManagerAuditService;
import com.zlt.sys.expense.service.imp.ExpenseServiceImp;
import com.zlt.sys.expense.service.imp.ManagerAuditServiceImp;
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


@WebServlet("/expense/ManagerAuditServlet")
public class ManagerAuditServlet extends HttpServlet {

    IExpenseService iExpenseService = new ExpenseServiceImp();

    /*
    * 获取所有符合条件的报销单并跳转至经理审批界面
    * */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //格式化时间
        ConvertUtils.register(new ExpenseDateFormat(), Date.class);
        //获取前端数据
        Expense expense = RequestBeanUtils.requestToSimpleBean(request,Expense.class);

        List<Expense> expenseList = iExpenseService.selectExpenseList(expense);
        request.setAttribute("expense",expense);
        request.setAttribute("expenseList",expenseList);

        request.getRequestDispatcher("/view/expense/managerAudit/expense_managerlist.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }
}
