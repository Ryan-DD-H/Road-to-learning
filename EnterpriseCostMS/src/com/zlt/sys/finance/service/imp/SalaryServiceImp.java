package com.zlt.sys.finance.service.imp;

import com.zlt.pojo.SalaryRecord;
import com.zlt.sys.finance.dao.ISalaryDao;
import com.zlt.sys.finance.dao.imp.SalaryDaoImp;
import com.zlt.sys.finance.service.ISalaryService;

import java.util.List;

import static com.zlt.utiles.NullAssertion.isNotNull;


public class SalaryServiceImp implements ISalaryService {

    ISalaryDao iSalaryDao = new SalaryDaoImp();

    @Override
    public void salaryAdd(SalaryRecord salaryRecord) {
        isNotNull("请选择薪资领取人",salaryRecord.getUsersId());
        isNotNull("请选择发放哪个月份薪资",salaryRecord.getSalaryMonth());
        isNotNull("发放提成有误",salaryRecord.getSalaryComm());
        int i = iSalaryDao.salaryAdd(salaryRecord);
        isNotNull("薪资发放失败",i);
    }

    @Override
    public List<SalaryRecord> selectSalaryRecord(SalaryRecord salaryRecord) {
        return iSalaryDao.selectSalaryRecord(salaryRecord);
    }
}
