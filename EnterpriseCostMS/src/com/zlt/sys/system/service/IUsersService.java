package com.zlt.sys.system.service;

import com.zlt.pojo.Menu;
import com.zlt.pojo.SalaryRecord;
import com.zlt.pojo.Users;

import java.util.List;

public interface IUsersService {


    /*
    * 用于用户登录的判断
    * */
    Users usersLogin(Users users);


    /*
    * 用于获取用户的工资报表信息
    * */
    List<SalaryRecord> selectImg(Integer usersId);

    /*
    * 用于获取用户的权限信息
    * */
    List<Menu> selectMenus(Integer roleId);

    void updateUsersInfo(Users users);

    /*
    * 用于添加新的用户
    * */
    void usersAdd(Users users);

    List<Users> selectListUsers(Users users);


    /*
    * 删除用户信息
    * */
    void usersDelete(Users users);

    /*
    * 更新用户信息
    * */
    void usersUpdate(Users user);

    /*
    * 按照
    * */
    List<Users> selectUsersByName();
}
