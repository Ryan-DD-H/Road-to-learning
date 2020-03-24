package com.zlt.sys.system.dao.imp;

import com.zlt.pojo.Menu;
import com.zlt.pojo.SalaryRecord;
import com.zlt.pojo.Users;
import com.zlt.sys.system.dao.IUsersDao;
import com.zlt.utiles.C3p0Util;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.zlt.utiles.MarkCode.*;

public class UsersDaoImp implements IUsersDao {



    /**
     * @author 黄国旺
     * @param users 用户的用户名和密码
     * @return 用户的用户名和密码正确则返回用户的信息，否则就返回null
     * */
    @Override
    public Users loginDao(Users users) {
        String sql = "SELECT t.*,r.roleName FROM t_users t INNER JOIN t_role r ON t.roleId = r.roleId WHERE usersAccount = ? AND usersPwd = ? AND usersMark = ?";
        try {
            return C3p0Util.queryOne(sql,Users.class,users.getUsersAccount(),users.getUsersPwd(),AVAILABLE_CODE);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @author 黄国旺
     * @param usersId 用户的id
     * @return 返回用户最近1年的薪资的集合
     * */
    @Override
    public List<SalaryRecord> selectImgDao(Integer usersId) {
        String sql="SELECT tsr.salaryMonth,SUM(tsr.salaryBasic) salaryBasic,SUM(tsr.salaryComm) salaryComm FROM t_salary_record tsr WHERE 1=1 AND tsr.usersId = ? GROUP BY tsr.salaryMonth ORDER BY salaryMonth DESC LIMIT 12";
        try {
            return C3p0Util.queryList(sql,SalaryRecord.class,usersId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * @author 黄国旺
     * @param roleId 用户的权限编号
     * @return 返回用户编号所对应的权限的集合
     * */
    @Override
    public List<Menu> selectMenus(Integer roleId) {
        String sql = "SELECT m.* FROM role_menu rm INNER JOIN menu m ON rm.menuId = m.menuId AND rm.roleId = ?";
        try {
            return C3p0Util.queryList(sql,Menu.class,roleId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @author 黄国旺
     * @param users 用户的信息
     * @return 无异常则返回更新条数信息，否则返回0
     * */
    @Override
    public int updateUsersInfo(Users users) {
        StringBuffer sql = new StringBuffer("UPDATE t_users u SET u.usersName = ?,u.usersAge = ?,u.usersSex = ?, u.usersPhone = ?,u.usersPwd = ?");
        ArrayList arrayList = new ArrayList();
        arrayList.add(users.getUsersName());
        arrayList.add(users.getUsersAge());
        arrayList.add(users.getUsersSex());
        arrayList.add(users.getUsersPhone());
        arrayList.add(users.getUsersPwd());

        if(users.getProfileUrl() != null){
            sql.append(",u.profileUrl = ?");
            arrayList.add(users.getProfileUrl());
        }

        sql.append(" WHERE u.usersId = ?");
        arrayList.add(users.getUsersId());


        try {
            return C3p0Util.update(sql.toString(),arrayList.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @author 黄国旺
     * @param users 用户的信息
     * @return 无异常则返回更新条数信息，否则返回0
     * */
    @Override
    public int usersAdd(Users users) {
        String sql = "INSERT INTO t_users VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            return C3p0Util.update(sql,users.getUsersId(),users.getRoleId(),users.getUsersName(),users.getUsersSex(),users.getUsersAge(),users.getUsersPhone(),users.getUsersSalary(),users.getUsersAccount(),users.getUsersPwd(),AVAILABLE_CODE,users.getProfileUrl());
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @author 黄国旺
     * @param users 用户的信息
     * @return 无异常则返回所有符合条件的用户的集合，否则返回null
     * */
    @Override
    public List<Users> selectListUsers(Users users) {
        StringBuffer sql = new StringBuffer("SELECT u.*,r.roleName FROM t_users u INNER JOIN t_role r ON u.roleId = r.roleId AND u.usersMark = ? ");
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(AVAILABLE_CODE);

        //条件查询判断
        if(users.getUsersId() != null && users.getUsersId() != 0){
            sql.append(" AND u.usersId = ?");
            arrayList.add(users.getUsersId());
        }

        if(users.getUsersName() != null && users.getUsersName().trim().length() > 0){
            sql.append(" AND u.usersName LIKE ?");
            arrayList.add("%"+users.getUsersName()+"%");
        }

        try {
            return C3p0Util.queryList(sql.toString(),Users.class,arrayList.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @author 黄国旺
     * @param users 用户的信息
     * @return 无异常则返回更新条数信息，否则返回0
     * */
    @Override
    public int usersDelete(Users users) {
        Connection conn = null;
        int i = 0;
        try{

            conn = C3p0Util.getConn();
            QueryRunner queryRunner = new QueryRunner();
            conn.setAutoCommit(false);

            String sql = "UPDATE t_users u SET u.usersMark = ? WHERE u.usersId = ?";

            Integer[] ids = users.getIds();
            for (int j = 0;j < ids.length;j++){
                i += queryRunner.update(conn,sql,UNAVAILABLE_CODE,ids[j]);
            }

            conn.commit();
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
     * @param user 用户的信息
     * @return 无异常则返回更新条数信息，否则返回0
     * */
    @Override
    public int usersUpdate(Users user) {
        String sql = "UPDATE t_users u SET u.usersName = ?,u.usersAge = ?,u.usersSex = ?, u.usersPhone = ?,u.usersPwd = ?,u.roleId = ?,u.usersSalary = ? WHERE u.usersId = ?";
        try {
            return C3p0Util.update(sql,user.getUsersName(),user.getUsersAge(),user.getUsersSex(),user.getUsersPhone(),user.getUsersPwd(),user.getRoleId(),user.getUsersSalary(),user.getUsersId());
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @author 黄国旺
     * @return 返回所有的在职的用户信息
     * */
    @Override
    public List<Users> selectUsersByName() {

        String sql = "SELECT u.usersId,u.usersName FROM t_users u WHERE u.usersMark = ?";
        try {
            return C3p0Util.queryList(sql,Users.class,AVAILABLE_CODE);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
