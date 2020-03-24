package com.zlt.sys.expense.service.imp;

import com.zlt.pojo.Cost;
import com.zlt.pojo.Expense;
import com.zlt.pojo.ExpenseList;
import com.zlt.sys.expense.dao.IExpenseDao;
import com.zlt.sys.expense.dao.imp.ExpenseDaoImp;
import com.zlt.sys.expense.service.IExpenseService;
import static com.zlt.utiles.NullAssertion.isNotNull;
import static com.zlt.utiles.MarkCode.*;

import java.util.ArrayList;
import java.util.List;

public class ExpenseServiceImp implements IExpenseService {

    IExpenseDao iExpenseDao = new ExpenseDaoImp();

    @Override
    public List<Cost> selectCostList() {
        return iExpenseDao.selectCostList();
    }

    @Override
    public void expenseAdd(Expense expense) {
        isNotNull("报销用户id获取错误",expense.getUsersId());
        isNotNull("报销原因不能为空",expense.getExpenseName());
        isNotNull("报销详情不能为空",expense.getExpenseDesc());
        isNotNull("报销金额有误",expense.getExpenseMoney());
        isNotNull("请选择费用明细",expense.getCostIds());
        isNotNull("报销具体金额有误",expense.getListMoneys());
        isNotNull("报销费用说明有误",expense.getListNames());
        int i = iExpenseDao.ExpenseAdd(expense);
        isNotNull("报销单添加失败",i);
    }

    /*
    * 对获取到的报销单添加特定属性
    * */
    @Override
    public List<Expense> selectExpenseList(Expense expense) {

        List<Expense> expenseList = iExpenseDao.selectExpenseList(expense);
        //添加不同类型的带地址的按钮
        ArrayList<Expense> expenseArrayList = new ArrayList<>();
        for(Expense expense1 : expenseList){
            if(expense1.getExpenseState().equals(ENTER_NO)){
                expense1.setExpenseStateHTML("<button type='button' class='btn btn-info'>保存未提交</button>");
                expense1.setExpenseUpdateUrl("<a class='btn btn-primary' href='expense/ExpenseUpdateServlet?expenseId="+expense1.getExpenseId()+"' role='button'>更新报销单</a>");
            }else if(expense1.getExpenseState().equals(ENTER_YES)){
                expense1.setExpenseStateHTML("<button type='button' class='btn btn-info'>保存已提交</button>");
                expense1.setExpenseAuditUrl("<a class='btn btn-default' href='expense/AuditServlet?expenseId="+expense1.getExpenseId()+"' role='button'>审核</a>");
                expense1.setExpenseUpdateUrl("<a class='btn btn-primary' href='expense/ExpenseUpdateServlet?expenseId="+expense1.getExpenseId()+"' role='button'>更新报销单</a>");
            }else if(expense1.getExpenseState().equals(FINANCE_NO)){
                expense1.setExpenseStateHTML("<button type='button' class='btn btn-danger'>财务审核未通过</button>");
                expense1.setExpenseUpdateUrl("<a class='btn btn-primary' href='expense/ExpenseUpdateServlet?expenseId="+expense1.getExpenseId()+"' role='button'>更新报销单</a>");
            }else if(expense1.getExpenseState().equals(FINANCE_YES)){
                expense1.setExpenseStateHTML("<button type='button' class='btn btn-success'>财务审核通过</button>");
            }else if(expense1.getExpenseState().equals(MANAGER_NO)){
                expense1.setExpenseStateHTML("<button type='button' class='btn btn-danger'>经理审核未通过</button>");
                expense1.setExpenseAuditUrl("<a class='btn btn-default' href='expense/AuditServlet?expenseId="+expense1.getExpenseId()+"' role='button'>审核</a>");
                expense1.setExpenseUpdateUrl("<a class='btn btn-primary' href='expense/ExpenseUpdateServlet?expenseId="+expense1.getExpenseId()+"' role='button'>更新报销单</a>");
            }else if(expense1.getExpenseState().equals(MANAGER_YES)){
                expense1.setExpenseStateHTML("<button type='button' class='btn btn-success'>经理审核通过</button>");
                expense1.setExpenseUpdateUrl("<a class='btn btn-primary' href='expense/ExpenseUpdateServlet?expenseId="+expense1.getExpenseId()+"' role='button'>更新报销单</a>");
            }
            expenseArrayList.add(expense1);
        }
        return expenseArrayList;
    }

    @Override
    public List<ExpenseList> selectExpenseListList(Integer expenseId) {
        return iExpenseDao.selectExpenseListList(expenseId);
    }

    @Override
    public void expenseUpdate(Expense expense) {
        isNotNull("报销用户id获取错误",expense.getUsersId());
        isNotNull("报销原因不能为空",expense.getExpenseName());
        isNotNull("报销详情不能为空",expense.getExpenseDesc());
        isNotNull("报销金额有误",expense.getExpenseMoney());
        isNotNull("请选择费用明细",expense.getCostIds());
        isNotNull("报销具体金额有误",expense.getListMoneys());
        isNotNull("报销费用说明有误",expense.getListNames());
        int i = iExpenseDao.ExpenseUpdate(expense);
        isNotNull("报销单修改失败",i);
    }


}
