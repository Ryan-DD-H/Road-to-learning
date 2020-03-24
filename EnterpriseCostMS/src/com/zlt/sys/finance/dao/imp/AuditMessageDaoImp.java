package com.zlt.sys.finance.dao.imp;

import com.zlt.sys.finance.dao.IAuditMessageDao;
import com.zlt.utiles.C3p0Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuditMessageDaoImp implements IAuditMessageDao {


    @Override
    public int getAuditMessage(String expenseState) {
        Connection conn = null;
        int num = 0;
        String sql= "SELECT COUNT(expenseState) num from t_expense WHERE expenseState = ? GROUP BY expenseState";
        try {
            conn = C3p0Util.getConn();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1,expenseState);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                //获取数据
                num = rs.getInt("num");
            }
            return num;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }
}
