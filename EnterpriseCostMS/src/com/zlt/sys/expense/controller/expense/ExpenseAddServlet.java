package com.zlt.sys.expense.controller.expense;

import com.my.web.servlet.RequestBeanUtils;
import com.zlt.pojo.Cost;
import com.zlt.pojo.Expense;
import com.zlt.sys.expense.service.IExpenseService;
import com.zlt.sys.expense.service.imp.ExpenseServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/expense/ExpenseAddServlet")
public class ExpenseAddServlet extends HttpServlet {

    IExpenseService iExpenseService = new ExpenseServiceImp();

    /*
    * 添加报销单
    * */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            //查询费用明细
            List<Cost> costList = iExpenseService.selectCostList();
            request.setAttribute("costList",costList);
            //获取前端发送来的数据
            Expense expense = RequestBeanUtils.requestToBean(request, Expense.class);
            iExpenseService.expenseAdd(expense);
            request.setAttribute("tips","报销单添加成功");
            request.getRequestDispatcher("/view/expense/expense/expense_add.jsp").forward(request, response);
        }catch (RuntimeException e){
            request.setAttribute("tips",e.getMessage());
            request.getRequestDispatcher("/view/expense/expense/expense_add.jsp").forward(request, response);
        }
    }

    /*
    * 跳转至添加报销单界面及记载其所需的信息
    * */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //查询费用明细
        List<Cost> costList = iExpenseService.selectCostList();
        request.setAttribute("costList",costList);


        request.getRequestDispatcher("/view/expense/expense/expense_add.jsp").forward(request,response);
    }
}
