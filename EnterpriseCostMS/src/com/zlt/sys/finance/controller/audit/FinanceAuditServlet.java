package com.zlt.sys.finance.controller.audit;

import com.my.web.servlet.RequestBeanUtils;
import com.zlt.pojo.Expense;
import com.zlt.sys.expense.service.IExpenseService;
import com.zlt.sys.expense.service.imp.ExpenseServiceImp;
import com.zlt.sys.finance.service.IAuditService;
import com.zlt.sys.finance.service.imp.AuditServiceImp;
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

@WebServlet("/finance/FinanceAuditServlet")
public class FinanceAuditServlet extends HttpServlet {


    IAuditService iAuditService = new AuditServiceImp();

    /*
    *跳转至财务审核的界面
    * */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //格式化时间
        ConvertUtils.register(new ExpenseDateFormat(), Date.class);
        //获取前端数据
        Expense expense = RequestBeanUtils.requestToSimpleBean(request,Expense.class);

        List<Expense> expenseList = iAuditService.selectExpenseList(expense);
        request.setAttribute("expense",expense);
        request.setAttribute("expenseList",expenseList);
        request.getRequestDispatcher("/view/finance/financAaudit/financeaudit_list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
