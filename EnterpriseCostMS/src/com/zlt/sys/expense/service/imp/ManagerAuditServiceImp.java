package com.zlt.sys.expense.service.imp;

import com.zlt.pojo.AuditRecord;
import com.zlt.pojo.Expense;
import com.zlt.sys.expense.dao.IManagerAuditDao;
import com.zlt.sys.expense.dao.imp.ManagerAuditServiceDao;
import com.zlt.sys.expense.service.IManagerAuditService;

import java.util.ArrayList;
import java.util.List;

import static com.zlt.utiles.MarkCode.*;
import static com.zlt.utiles.NullAssertion.isNotNull;

public class ManagerAuditServiceImp implements IManagerAuditService {

    IManagerAuditDao iManagerAuditDao = new ManagerAuditServiceDao();
    @Override
    public void addAudit(AuditRecord auditRecord) {
        isNotNull("报销编号获取失败，请刷新重试",auditRecord.getExpenseId());
        isNotNull("请添加审批描述",auditRecord.getAuditSuggest());
        int i = iManagerAuditDao.addAudit(auditRecord);
        isNotNull("审核失败",i);
    }

    @Override
    public List<AuditRecord> selectAuditRecord(Integer expenseId) {
        isNotNull("报销编号获取失败，请刷新重试",expenseId);

        List<AuditRecord> auditRecordList = iManagerAuditDao.selectAuditRecord(expenseId);
        ArrayList<AuditRecord> arrayList = new ArrayList<>();
        for(AuditRecord auditRecord : auditRecordList){
            if(auditRecord.getAuditState().equals(ENTER_NO)){
                auditRecord.setAuditStateHTML("<button type='button' class='btn btn-info'>保存未提交</button>");
            }else if(auditRecord.getAuditState().equals(ENTER_YES)){
                auditRecord.setAuditStateHTML("<button type='button' class='btn btn-info'>保存已提交</button>");
            }else if(auditRecord.getAuditState().equals(FINANCE_NO)){
                auditRecord.setAuditStateHTML("<button type='button' class='btn btn-danger'>财务审核未通过</button>");
            }else if(auditRecord.getAuditState().equals(FINANCE_YES)){
                auditRecord.setAuditStateHTML("<button type='button' class='btn btn-success'>财务审核通过</button>");
            }else if(auditRecord.getAuditState().equals(MANAGER_NO)){
                auditRecord.setAuditStateHTML("<button type='button' class='btn btn-danger'>经理审核未通过</button>");
            }else if(auditRecord.getAuditState().equals(MANAGER_YES)){
                auditRecord.setAuditStateHTML("<button type='button' class='btn btn-success'>经理审核通过</button>");
            }
            arrayList.add(auditRecord);
        }

        return arrayList;
    }
}
