package com.zlt.sys.expense.dao.imp;

import com.zlt.pojo.Cost;
import com.zlt.pojo.Expense;
import com.zlt.pojo.ExpenseList;
import com.zlt.sys.expense.dao.IExpenseDao;
import com.zlt.utiles.C3p0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.zlt.utiles.MarkCode.AVAILABLE_CODE;


public class ExpenseDaoImp implements IExpenseDao {

    /**
     * 查询所有的费用
     * @author 黄国旺
     * @return 返回费用的集合
     * */
    @Override
    public List<Cost> selectCostList() {
        String sql = "SELECT * FROM t_cost c WHERE c.costMark = ?";
        try {
            return C3p0Util.queryList(sql,Cost.class,AVAILABLE_CODE);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @author 黄国旺
     * @param expense 报销单的信息
     * @return 无异常则返回更新的条数，否则返回0
     * */
    @Override
    public int ExpenseAdd(Expense expense) {
        Connection conn = null;
        int i = 0;
        try {
            conn = C3p0Util.getConn();
            conn.setAutoCommit(false);
            QueryRunner queryRunner = new QueryRunner();
            //添加新的报销单
            String sql1 = "INSERT INTO t_expense VALUES (null,?,?,?,?,?,now())";
            i += queryRunner.update(conn,sql1,expense.getUsersId(),expense.getExpenseName(),expense.getExpenseDesc(),expense.getExpenseMoney(),expense.getExpenseState());

            //获取添加报销单的编号
            String sql2 = "SELECT LAST_INSERT_ID()";
            int expenseId = queryRunner.query(conn,sql2,new ScalarHandler<BigInteger>()).intValue();

            //添加报销单的明细信息
            String[] costIds = expense.getCostIds();
            String[] listNames = expense.getListNames();
            Float[] listMoneys = expense.getListMoneys();
            String sql3 = "INSERT INTO t_expense_list VALUES (?,?,null,?,?)";
            for(int j = 0; j < costIds.length;j++ ){
                i += queryRunner.update(conn,sql3,expenseId,costIds[j],listNames[j],listMoneys[j]);
            }

            conn.commit();
            conn.setAutoCommit(true);
            return i;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 查询符合条件的报销单
     * @author 黄国旺
     * @param expense 报销单的信息
     * @return 无异常则返回符合条件的报销单的集合，否则返回null
     * */
    @Override
    public List<Expense> selectExpenseList(Expense expense) {

        StringBuffer stringBuffer = new StringBuffer("SELECT u.usersName,e.* FROM t_expense e INNER JOIN t_users u ON u.usersId = e.usersId");
        ArrayList<Object> arrayList = new ArrayList<>();
        //条件搜索的拼接
        if(expense.getUsersName() != null && expense.getUsersName().trim().length() > 0){
            stringBuffer.append(" AND u.usersName LIKE ?");
            arrayList.add("%"+expense.getUsersName()+"%");
        }

        if(expense.getStartDate() != null && expense.getStartDate().toString().trim().length() > 0){
                stringBuffer.append(" AND e.expenseDate > ?");
                arrayList.add(expense.getStartDate());
        }

        if(expense.getEndDate() != null && expense.getEndDate().toString().trim().length() > 0){
            stringBuffer.append(" AND e.expenseDate < ?");
            arrayList.add(expense.getEndDate());
        }

        if(expense.getExpenseName() != null && expense.getExpenseName().trim().length() > 0){
            stringBuffer.append(" AND e.expenseName LIKE ?");
            arrayList.add("%"+expense.getExpenseName()+"%");
        }

        if(expense.getExpenseId() != null && expense.getExpenseId() > 0){
            stringBuffer.append(" AND e.expenseId = ?");
            arrayList.add(expense.getExpenseId());
        }

        if(expense.getUsersId() != null && expense.getUsersId() > 0){
            stringBuffer.append(" AND u.usersId = ?");
            arrayList.add(expense.getUsersId());
        }

        if(expense.getExpenseState() != null && expense.getExpenseState().trim().length() > 0){
            stringBuffer.append(" AND e.expenseState = ?");
            arrayList.add(expense.getExpenseState());
        }

        try {

            return C3p0Util.queryList(stringBuffer.toString(),Expense.class,arrayList.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @author 黄国旺
     * @param expenseId 报销单的id
     * @return 返回符合条件的报销明细表的信息
     * */
    @Override
    public List<ExpenseList> selectExpenseListList(Integer expenseId) {

        String sql = "SELECT el.*,c.costName FROM t_expense_list el INNER JOIN t_cost c ON el.costId = c.costId AND el.expenseId = ?";
        try {
            return C3p0Util.queryList(sql,ExpenseList.class,expenseId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 用于修改我的报销单
     * @author 黄国旺
     * @param expense 报销单的信息
     * @return 无异常则返回更新的条数，否则返回0
     * */
    @Override
    public int ExpenseUpdate(Expense expense) {
        Connection conn =null;
        int i = 0;
        try {
            conn = C3p0Util.getConn();
            conn.setAutoCommit(false);
            QueryRunner queryRunner = new QueryRunner();

            //更新报销单的信息
            String sql1 = "UPDATE t_expense te SET te.expenseName = ?,te.expenseMoney = ?,te.expenseDesc = ?,te.expenseState = ? WHERE te.expenseId = ?";
            i += queryRunner.update(conn,sql1,expense.getExpenseName(),expense.getExpenseMoney(),expense.getExpenseDesc(),expense.getExpenseState(),expense.getExpenseId());
            //删除对应报销编号的报销明细表的信息
            String sql2 = "DELETE FROM t_expense_list WHERE expenseId = ?";
            i += queryRunner.update(conn,sql2,expense.getExpenseId());
            //添加新的报销明细表
            String[] costIds = expense.getCostIds();
            String[] listNames = expense.getListNames();
            Float[] listMoneys = expense.getListMoneys();
            String sql3 = "INSERT INTO t_expense_list VALUES (?,?,null,?,?)";
            for(int j = 0; j < costIds.length;j++ ){
                i += queryRunner.update(conn,sql3,expense.getExpenseId(),costIds[j],listNames[j],listMoneys[j]);
            }

            conn.commit();
            conn.setAutoCommit(true);
            return i;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return 0;
        }
    }
}
