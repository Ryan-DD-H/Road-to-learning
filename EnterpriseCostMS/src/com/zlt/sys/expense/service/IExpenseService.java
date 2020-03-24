package com.zlt.sys.expense.service;

import com.zlt.pojo.Cost;
import com.zlt.pojo.Expense;
import com.zlt.pojo.ExpenseList;

import java.util.List;

public interface IExpenseService {


    //查找所有报销明细数据
    List<Cost> selectCostList();

    void expenseAdd(Expense expense);

    List<Expense> selectExpenseList(Expense expense);


    List<ExpenseList> selectExpenseListList(Integer expenseId);

    void expenseUpdate(Expense expense);
}
