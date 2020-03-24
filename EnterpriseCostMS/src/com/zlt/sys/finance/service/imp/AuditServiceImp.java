package com.zlt.sys.finance.service.imp;

import com.zlt.pojo.Expense;
import com.zlt.sys.expense.dao.IExpenseDao;
import com.zlt.sys.expense.dao.imp.ExpenseDaoImp;
import com.zlt.sys.finance.dao.IAuditMessageDao;
import com.zlt.sys.finance.dao.imp.AuditMessageDaoImp;
import com.zlt.sys.finance.service.IAuditService;

import java.util.ArrayList;
import java.util.List;

import static com.zlt.utiles.MarkCode.*;

public class AuditServiceImp implements IAuditService {

    IExpenseDao iExpenseDao = new ExpenseDaoImp();

    /**
     * 给对应的报销单添加参数
     * @author 黄国旺
     * @param expense 报销单的信息
     * @return 返回添加对应信息的报销单信息
     * */
    @Override
    public List<Expense> selectExpenseList(Expense expense) {

        List<Expense> expenseList = iExpenseDao.selectExpenseList(expense);
        ArrayList<Expense> expenseArrayList = new ArrayList<>();
        for(Expense expense1 : expenseList){
            if(expense1.getExpenseState().equals(ENTER_NO)){
                expense1.setExpenseStateHTML("<button type='button' class='btn btn-info'>保存未提交</button>");
            }else if(expense1.getExpenseState().equals(ENTER_YES)){
                expense1.setExpenseStateHTML("<button type='button' class='btn btn-info'>保存已提交</button>");
            }else if(expense1.getExpenseState().equals(FINANCE_NO)){
                expense1.setExpenseStateHTML("<button type='button' class='btn btn-danger'>财务审核未通过</button>");
                expense1.setExpenseAuditUrl("<a class='btn btn-default' href='finance/AuditServlet?expenseId="+expense1.getExpenseId()+"' role='button'>审核</a>");
            }else if(expense1.getExpenseState().equals(FINANCE_YES)){
                expense1.setExpenseStateHTML("<button type='button' class='btn btn-success'>财务审核通过</button>");
            }else if(expense1.getExpenseState().equals(MANAGER_NO)){
                expense1.setExpenseStateHTML("<button type='button' class='btn btn-danger'>经理审核未通过</button>");
            }else if(expense1.getExpenseState().equals(MANAGER_YES)){
                expense1.setExpenseStateHTML("<button type='button' class='btn btn-success'>经理审核通过</button>");
                expense1.setExpenseAuditUrl("<a class='btn btn-default' href='finance/AuditServlet?expenseId="+expense1.getExpenseId()+"' role='button'>审核</a>");
            }
            expenseArrayList.add(expense1);
        }
        return expenseArrayList;
    }


    /**
     * 获取用户的审核消息
     * @author 黄国旺
     * @param roleId 用户权限的Id
     * @return 返回待审核的报销单条数
     * */
    @Override
    public int getAuditMessage(int roleId) {

        IAuditMessageDao iAuditMessageDao = new AuditMessageDaoImp();
        int num = 0;
        if(roleId == 1){
            num = iAuditMessageDao.getAuditMessage("1");
            num += iAuditMessageDao.getAuditMessage("4");
            return num;
        }else if(roleId == 2){
            num = iAuditMessageDao.getAuditMessage("1");
            return num;
        }else if(roleId == 3){
            num = iAuditMessageDao.getAuditMessage("4");
            return num;
        }else {
            return num;
        }
    }
}
