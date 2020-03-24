package com.zlt.pojo;


import java.util.Arrays;

/*
* 用户表
* */
public class Users {

    //用户的id
    private Integer usersId;

    //用户身份的id
    private Integer roleId;

    //用户的性别
    private String usersSex;

    //用户的名字
    private String usersName;

    //用户的年龄
    private Integer usersAge;

    //用户的手机号
    private String usersPhone;

    //用户的底薪
    private Float usersSalary;

    //用户的登录账号
    private String usersAccount;

    //用户的登录密码
    private String usersPwd;

    //用户的标识（用于判断该用户是否已经被删除（也就是离职））
    private String usersMark;

    //用户身份的名称
    private String roleName;

    //用于拼接多选框按钮（与本表无关，仅用于方便发送数据）
    private String ck;

    //用户的id的数组（与本表无关，仅用于方便发送数据）
    private Integer[] ids;

    private String profileUrl;

    public void setCk(String ck) {
        this.ck = ck;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getUsersName() {
        return usersName;
    }

    public void setUsersName(String usersName) {
        this.usersName = usersName;
    }

    public Integer getUsersAge() {
        return usersAge;
    }

    public void setUsersAge(Integer usersAge) {
        this.usersAge = usersAge;
    }

    public String getUsersPhone() {
        return usersPhone;
    }

    public void setUsersPhone(String usersPhone) {
        this.usersPhone = usersPhone;
    }

    public Float getUsersSalary() {
        return usersSalary;
    }

    public void setUsersSalary(Float usersSalary) {
        this.usersSalary = usersSalary;
    }

    public String getUsersAccount() {
        return usersAccount;
    }

    public void setUsersAccount(String usersAccount) {
        this.usersAccount = usersAccount;
    }

    public String getUsersPwd() {
        return usersPwd;
    }

    public void setUsersPwd(String usersPwd) {
        this.usersPwd = usersPwd;
    }

    public String getUsersMark() {
        return usersMark;
    }

    public void setUsersMark(String usersMark) {
        this.usersMark = usersMark;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUsersSex() {
        return usersSex;
    }

    public void setUsersSex(String usersSex) {
        this.usersSex = usersSex;
    }

    //手动拼接多选框按钮
    public String getCk(){
        ck="<INPUT TYPE='checkbox' value='"+getUsersId()+"' name='ids' />";
        return ck;
    }

    @Override
    public String toString() {
        return "Users{" +
                "usersId=" + usersId +
                ", roleId=" + roleId +
                ", usersSex='" + usersSex + '\'' +
                ", usersName='" + usersName + '\'' +
                ", usersAge=" + usersAge +
                ", usersPhone='" + usersPhone + '\'' +
                ", usersSalary=" + usersSalary +
                ", usersAccount='" + usersAccount + '\'' +
                ", usersPwd='" + usersPwd + '\'' +
                ", usersMark='" + usersMark + '\'' +
                ", roleName='" + roleName + '\'' +
                ", ck='" + ck + '\'' +
                ", ids=" + Arrays.toString(ids) +
                ", profileUrl='" + profileUrl + '\'' +
                '}';
    }
}
