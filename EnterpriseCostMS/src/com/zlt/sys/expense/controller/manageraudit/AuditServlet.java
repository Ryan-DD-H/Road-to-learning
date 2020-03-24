package com.zlt.sys.expense.controller.manageraudit;

import com.my.web.servlet.RequestBeanUtils;
import com.zlt.pojo.AuditRecord;
import com.zlt.pojo.Expense;
import com.zlt.pojo.ExpenseList;
import com.zlt.pojo.Users;
import com.zlt.sys.expense.service.IExpenseService;
import com.zlt.sys.expense.service.IManagerAuditService;
import com.zlt.sys.expense.service.imp.ExpenseServiceImp;
import com.zlt.sys.expense.service.imp.ManagerAuditServiceImp;
import com.zlt.sys.finance.service.IAuditService;
import com.zlt.sys.finance.service.imp.AuditServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/expense/AuditServlet")
public class AuditServlet extends HttpServlet {


    IExpenseService iExpenseService = new ExpenseServiceImp();
    IManagerAuditService iManagerAuditService = new ManagerAuditServiceImp();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            //接收前端传来的数据
            AuditRecord auditRecord = RequestBeanUtils.requestToBean(request, AuditRecord.class);
            //获取对应的报销单信息
            Expense expense = new Expense();
            expense.setExpenseId(auditRecord.getExpenseId());
            List<Expense> expenseList = iExpenseService.selectExpenseList(expense);
            expense = expenseList.get(0);
            request.setAttribute("expense", expense);
            //获取报销明细表
            List<ExpenseList> expenseListList = iExpenseService.selectExpenseListList(expense.getExpenseId());
            request.setAttribute("expenseListList", expenseListList);
            //获取审核记录
            List<AuditRecord> auditRecordList = iManagerAuditService.selectAuditRecord(auditRecord.getExpenseId());
            request.setAttribute("auditRecordList", auditRecordList);
            //审核
            iManagerAuditService.addAudit(auditRecord);
            request.setAttribute("tips", "审核成功");

            //更新对应的待审核消息条数
            HttpSession session = request.getSession();
            Users users = (Users) session.getAttribute("users");
            IAuditService iAuditService = new AuditServiceImp();
            int messageNum = iAuditService.getAuditMessage(users.getRoleId());
            session.setAttribute("messageNum", messageNum);

            request.getRequestDispatcher("/view/expense/managerAudit/expense_audit.jsp").forward(request, response);
        }catch (RuntimeException e){
            request.setAttribute("tips", e.getMessage());
            request.getRequestDispatcher("/view/expense/managerAudit/expense_audit.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Expense expense = RequestBeanUtils.requestToBean(request,Expense.class);
        //获取对应的报销单的信息
        List<Expense> expenseList = iExpenseService.selectExpenseList(expense);
        expense = expenseList.get(0);
        request.setAttribute("expense",expense);
        //获取报销明细表
        List<ExpenseList> expenseListList= iExpenseService.selectExpenseListList(expense.getExpenseId());
        request.setAttribute("expenseListList",expenseListList);
        //获取审核记录
        List<AuditRecord> auditRecordList = iManagerAuditService.selectAuditRecord(expense.getExpenseId());
        request.setAttribute("auditRecordList",auditRecordList);
        request.getRequestDispatcher("/view/expense/managerAudit/expense_audit.jsp").forward(request,response);
    }
}
