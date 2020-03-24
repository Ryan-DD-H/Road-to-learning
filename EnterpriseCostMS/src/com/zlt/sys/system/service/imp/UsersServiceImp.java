package com.zlt.sys.system.service.imp;

import com.zlt.pojo.Menu;
import com.zlt.pojo.SalaryRecord;
import com.zlt.pojo.Users;
import com.zlt.sys.system.dao.IUsersDao;
import com.zlt.sys.system.dao.imp.UsersDaoImp;
import com.zlt.sys.system.service.IUsersService;

import java.util.List;

import static com.zlt.utiles.NullAssertion.*;

public class UsersServiceImp implements IUsersService {

    IUsersDao iUsersDao = new UsersDaoImp();



    @Override
    public Users usersLogin(Users users) {
        //断言
        isNotNull("用户名不能为空",users.getUsersAccount());
        isNotNull("密码不能为空",users.getUsersPwd());
        //调用Dao层方法查询该用户是否存在
        users = iUsersDao.loginDao(users);
        isNotNull("用户名或密码错误",users);
        //将用户的密码置空
        users.setUsersPwd(null);
        return users;
    }

    @Override
    public List<SalaryRecord> selectImg(Integer usersId) {
        return iUsersDao.selectImgDao(usersId);
    }

    @Override
    public List<Menu> selectMenus(Integer roleId) {
        return iUsersDao.selectMenus(roleId);
    }

    @Override
    public void updateUsersInfo(Users users) {
        //断言
        isNotNull("未获取到用户编号",users.getUsersId());
        isNotNull("用户名不能为空",users.getUsersName());
        isNotNull("用户年龄有误",users.getUsersAge());
        isNotNull("用户性别不能为空",users.getUsersSex());
        isNotNull("用户电话不能为空",users.getUsersPhone());
        isNotNull("用户密码不能为空",users.getUsersPwd());
        int i =iUsersDao.updateUsersInfo(users);
        isNotNull("用户信息更新失败",i);
    }

    @Override
    public void usersAdd(Users users) {
        //断言
        isNotNull("用户权限不能为空",users.getRoleId());
        isNotNull("用户名不能为空",users.getUsersName());
        isNotNull("用户性别不能为空",users.getUsersSex());
        isNotNull("用户年龄有误",users.getUsersAge());
        isNotNull("用户电话不能为空",users.getUsersPhone());
        isNotNull("用户薪水有误",users.getUsersSalary());
        isNotNull("用户账户不能为空",users.getUsersAccount());
        isNotNull("用户密码不能为空",users.getUsersPwd());
        int i = iUsersDao.usersAdd(users);
        isNotNull("用户信息添加失败",i);

    }

    @Override
    public List<Users> selectListUsers(Users users) {
        return iUsersDao.selectListUsers(users);
    }

    @Override
    public void usersDelete(Users users) {
        int i = iUsersDao.usersDelete(users);
        isNotNull("用户删除失败",i);
    }

    @Override
    public void usersUpdate(Users user) {
        isNotNull("未获取到用户编号",user.getUsersId());
        isNotNull("用户名不能为空",user.getUsersName());
        isNotNull("用户的权限不能为空",user.getRoleId());
        isNotNull("用户年龄有误",user.getUsersAge());
        isNotNull("用户薪水有误",user.getUsersSalary());
        isNotNull("用户性别不能为空",user.getUsersSex());
        isNotNull("用户电话不能为空",user.getUsersPhone());
        isNotNull("用户密码不能为空",user.getUsersPwd());
        int i =iUsersDao.usersUpdate(user);
        isNotNull("用户信息更新失败",i);
    }

    @Override
    public List<Users> selectUsersByName() {
        return iUsersDao.selectUsersByName();
    }
}
