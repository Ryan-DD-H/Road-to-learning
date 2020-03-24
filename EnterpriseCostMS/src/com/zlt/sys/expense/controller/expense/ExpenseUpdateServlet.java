package com.zlt.sys.expense.controller.expense;

import com.my.web.servlet.RequestBeanUtils;
import com.mysql.cj.xdevapi.Session;
import com.zlt.pojo.*;
import com.zlt.sys.expense.service.IExpenseService;
import com.zlt.sys.expense.service.IManagerAuditService;
import com.zlt.sys.expense.service.imp.ExpenseServiceImp;
import com.zlt.sys.expense.service.imp.ManagerAuditServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/expense/ExpenseUpdateServlet")
public class ExpenseUpdateServlet extends HttpServlet {

    IExpenseService iExpenseService = new ExpenseServiceImp();
    IManagerAuditService iManagerAuditService = new ManagerAuditServiceImp();
    //修改我的报销单
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            //查询费用明细
            List<Cost> costList = iExpenseService.selectCostList();
            //放入费用表
            request.setAttribute("costList", costList);
            Expense expense = RequestBeanUtils.requestToBean(request, Expense.class);
            //放入自己输入的更新的信息
            request.setAttribute("expense", expense);
            //获取指定的报销明细表的信息
            List<ExpenseList> expenseListList = iExpenseService.selectExpenseListList(expense.getExpenseId());
            request.setAttribute("expenseListList", expenseListList);
            //放入用户id
            Users users = (Users)request.getSession().getAttribute("users");
            expense.setUsersId(users.getUsersId());
            //更新报销单
            iExpenseService.expenseUpdate(expense);
            request.setAttribute("tips", "报销单修改成功");
            request.getRequestDispatcher("/view/expense/expense/expense_update.jsp").forward(request, response);
        }catch (RuntimeException e){
            request.setAttribute("tips",e.getMessage());
            request.getRequestDispatcher("/view/expense/expense/expense_update.jsp").forward(request, response);
        }
    }

    //跳转至修改我的报销单界面及加载其所需信息
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Expense expense = RequestBeanUtils.requestToBean(request,Expense.class);
        //获取对应的报销单的信息
        List<Expense> expenseList = iExpenseService.selectExpenseList(expense);
        expense = expenseList.get(0);
        request.setAttribute("expense",expense);
        //获取指定的报销明细表的信息
        List<ExpenseList> expenseListList= iExpenseService.selectExpenseListList(expense.getExpenseId());
        request.setAttribute("expenseListList",expenseListList);

        //获取审核记录
        List<AuditRecord> auditRecordList = iManagerAuditService.selectAuditRecord(expense.getExpenseId());
        request.setAttribute("auditRecordList",auditRecordList);

        //查询费用明细
        List<Cost> costList = iExpenseService.selectCostList();
        request.setAttribute("costList",costList);

        request.getRequestDispatcher("/view/expense/expense/expense_update.jsp").forward(request,response);
    }
}
