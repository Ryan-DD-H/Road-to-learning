package com.zlt.sys.system.dao.imp;

import com.zlt.pojo.Cost;
import com.zlt.sys.system.dao.ICostDao;
import com.zlt.utiles.C3p0Util;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.zlt.utiles.MarkCode.AVAILABLE_CODE;
import static com.zlt.utiles.MarkCode.UNAVAILABLE_CODE;

public class CostDaoImp implements ICostDao {

    /**
     * @author 黄国旺
     * @param cost 费用的信息
     * @return 无异常则返回符合条件的费用的集合，否则返回null
     * */
    @Override
    public List<Cost> selectListCost(Cost cost) {
        StringBuffer sql = new StringBuffer("SELECT * FROM t_cost c WHERE c.costMark = ?");
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(AVAILABLE_CODE);

        if(cost.getCostId() != null && cost.getCostId() != 0){
            sql.append(" AND c.costId = ?");
            arrayList.add(cost.getCostId());
        }

        if(cost.getCostName() != null && cost.getCostName().trim().length() > 0){
            sql.append(" AND c.costName LIKE ?");
            arrayList.add("%"+cost.getCostName()+"%");
        }

        try {
            return C3p0Util.queryList(sql.toString(),Cost.class,arrayList.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @author 黄国旺
     * @param cost 费用的信息
     * @return 无异常则返回增加的条数，否则返回0
     * */
    @Override
    public int costAdd(Cost cost) {
        String sql = "INSERT INTO t_cost VALUES (?,?,?,?)";
        try {
            return C3p0Util.update(sql,cost.getCostId(),cost.getCostName(),cost.getCostDesc(),AVAILABLE_CODE);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @author 黄国旺
     * @param cost 费用的信息
     * @return 无异常则返回更新的条数，否则返回0
     * */
    @Override
    public int costDelete(Cost cost) {
        Connection conn = null;
        int i = 0;
        try{

            conn = C3p0Util.getConn();
            QueryRunner queryRunner = new QueryRunner();
            //关闭自动提交
            conn.setAutoCommit(false);

            String sql = "UPDATE t_cost c SET c.costMark = ? WHERE c.costId = ?";

            Integer[] ids = cost.getIds();
            for (int j = 0;j < ids.length;j++){
                i += queryRunner.update(conn,sql,UNAVAILABLE_CODE,ids[j]);
            }

            //手动提交
            conn.commit();
            //开启自动提交
            conn.setAutoCommit(true);
            return i;
        }catch (Exception e){
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.getStackTrace();
            return 0;
        }
    }

    /**
     * @author 黄国旺
     * @param cost 费用的信息
     * @return 无异常则返回更新的条数，否则返回0
     * */
    @Override
    public int costUpdate(Cost cost) {
        String sql = "UPDATE t_cost c SET c.costName = ?,c.costDesc = ? WHERE c.costId = ?";
        try {
            return C3p0Util.update(sql,cost.getCostName(),cost.getCostDesc(),cost.getCostId());
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
