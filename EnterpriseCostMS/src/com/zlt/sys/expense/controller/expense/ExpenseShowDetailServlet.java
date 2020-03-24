package com.zlt.sys.expense.controller.expense;

import com.my.web.servlet.RequestBeanUtils;
import com.zlt.pojo.AuditRecord;
import com.zlt.pojo.Expense;
import com.zlt.pojo.ExpenseList;
import com.zlt.sys.expense.service.IExpenseService;
import com.zlt.sys.expense.service.IManagerAuditService;
import com.zlt.sys.expense.service.imp.ExpenseServiceImp;
import com.zlt.sys.expense.service.imp.ManagerAuditServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/expense/ExpenseShowDetailServlet")
public class ExpenseShowDetailServlet extends HttpServlet {

    IExpenseService iExpenseService = new ExpenseServiceImp();
    IManagerAuditService iManagerAuditService = new ManagerAuditServiceImp();
    //查询报销单详情
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Expense expense = RequestBeanUtils.requestToBean(request,Expense.class);
        List<Expense> expenseList = iExpenseService.selectExpenseList(expense);
        expense = expenseList.get(0);
        request.setAttribute("expense",expense);
        List<ExpenseList> expenseListList= iExpenseService.selectExpenseListList(expense.getExpenseId());
        request.setAttribute("expenseListList",expenseListList);

        //获取审核记录
        List<AuditRecord> auditRecordList = iManagerAuditService.selectAuditRecord(expense.getExpenseId());
        request.setAttribute("auditRecordList",auditRecordList);

        request.getRequestDispatcher("/view/expense/expense/expense_show.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
