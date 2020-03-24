package com.zlt.sys.finance.dao.imp;

import com.zlt.pojo.SalaryRecord;
import com.zlt.sys.finance.dao.ISalaryDao;
import com.zlt.utiles.C3p0Util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.zlt.utiles.MarkCode.AVAILABLE_CODE;


public class SalaryDaoImp implements ISalaryDao {

    /**
     * 发放薪资
     * @author 黄国旺
     * @param salaryRecord 薪资记录表的信息
     * @return 无异常则返回更新的条数，否则返回0
     * */
    @Override
    public int salaryAdd(SalaryRecord salaryRecord) {
        int i = 0;
        String sql = "INSERT INTO t_salary_record VALUES (?,?,?,now(),?,?)";
        try {
            i = C3p0Util.update(sql,salaryRecord.getSalaryId(),salaryRecord.getUsersId(),salaryRecord.getSalaryMonth(),salaryRecord.getSalaryComm(),salaryRecord.getSalaryBasic());
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 查询符合条件的薪资的集合
     * @author 黄国旺
     * @param salaryRecord 薪资记录表的信息
     * @return 无异常则返回符合条件的薪资的集合，否则返回0
     * */
    @Override
    public List<SalaryRecord> selectSalaryRecord(SalaryRecord salaryRecord) {

        StringBuffer stringBuffer = new StringBuffer("SELECT u.usersName,sr.* FROM t_salary_record sr INNER JOIN t_users u ON sr.usersId = u.usersId AND u.usersMark = ?");
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(AVAILABLE_CODE);
        if(salaryRecord.getUsersName() != null && salaryRecord.getUsersName().trim().length() > 0){
            stringBuffer.append(" AND u.usersName LIKE ?");
            arrayList.add("%"+salaryRecord.getUsersName()+"%");
        }

        if(salaryRecord.getSalaryMonth() != null && salaryRecord.getSalaryMonth().toString().trim().length() > 0){
            stringBuffer.append(" AND sr.salaryMonth = ?");
            arrayList.add(salaryRecord.getSalaryMonth());
        }

        if(salaryRecord.getUsersId() != null && salaryRecord.getUsersId() > 0){
            stringBuffer.append(" AND u.usersId = ?");
            arrayList.add(salaryRecord.getUsersId());
        }

        try {
                return C3p0Util.queryList(stringBuffer.toString(),SalaryRecord.class,arrayList.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
