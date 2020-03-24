package com.zlt.sys.expense.dao;

import com.zlt.pojo.Cost;
import com.zlt.pojo.Expense;
import com.zlt.pojo.ExpenseList;

import java.util.List;

public interface IExpenseDao {
    List<Cost> selectCostList();

    int ExpenseAdd(Expense expense);

    List<Expense> selectExpenseList(Expense expense);

    List<ExpenseList> selectExpenseListList(Integer expenseId);

    int ExpenseUpdate(Expense expense);
}
