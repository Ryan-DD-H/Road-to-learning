package com.zlt.sys.finance.service;

import com.zlt.pojo.Expense;

import java.util.List;

public interface IAuditService {
    List<Expense> selectExpenseList(Expense expense);

    int getAuditMessage(int roleId);
}
