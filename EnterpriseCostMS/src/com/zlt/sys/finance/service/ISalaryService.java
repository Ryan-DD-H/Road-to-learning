package com.zlt.sys.finance.service;

import com.zlt.pojo.SalaryRecord;

import java.util.List;

public interface ISalaryService {
    void salaryAdd(SalaryRecord salaryRecord);

    List<SalaryRecord> selectSalaryRecord(SalaryRecord salaryRecord);
}
