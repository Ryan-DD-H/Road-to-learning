package com.zlt.sys.system.dao;

import com.zlt.pojo.Menu;
import com.zlt.pojo.SalaryRecord;
import com.zlt.pojo.Users;

import java.util.List;

public interface IUsersDao {

    /*
    * 查询用户的登录信息
    * */
    Users loginDao(Users users);

    /*
    * 查询用户的工资信息
    * */
    List<SalaryRecord> selectImgDao(Integer usersId);

    /*
    * 查询用户所拥有的权限
    * */
    List<Menu> selectMenus(Integer roleId);


    /*
    * 更新的用户的所有信息
    * */
    int updateUsersInfo(Users users);

    /*
    * 添加新用户
    * */
    int usersAdd(Users users);

    /*
    * 查询所有在职用户
    * */
    List<Users> selectListUsers(Users users);

    /*
    * 删除用户
    * */
    int usersDelete(Users users);

    /*
    * 更新用户的信息
    * */
    int usersUpdate(Users user);

    List<Users> selectUsersByName();
}
