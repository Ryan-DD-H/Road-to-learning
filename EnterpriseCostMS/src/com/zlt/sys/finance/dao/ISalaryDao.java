package com.zlt.sys.finance.dao;

import com.zlt.pojo.SalaryRecord;

import java.util.List;

public interface ISalaryDao {
    int salaryAdd(SalaryRecord salaryRecord);

    List<SalaryRecord> selectSalaryRecord(SalaryRecord salaryRecord);
}
