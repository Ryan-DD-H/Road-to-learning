package com.zlt.pojo;

import java.util.Date;


/*
* 薪资记录表
* */
public class SalaryRecord {

    //薪资id
    private Integer salaryId;

    //用户id
    private Integer usersId;

    //用户的名字（与本表无关，仅用于方便发送数据）
    private String usersName;

    //发放薪资的月份（表示的是发放的是几月份的薪资）
    private Date salaryMonth;

    //发放薪资的时间（表示的是哪一天发的工资）
    private Date salaryDate;

    //提成
    private Float salaryComm;

    //底薪
    private Float salaryBasic;




    public Integer getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(Integer salaryId) {
        this.salaryId = salaryId;
    }

    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    public String getUsersName() {
        return usersName;
    }

    public void setUsersName(String usersName) {
        this.usersName = usersName;
    }

    public Date getSalaryMonth() {
        return salaryMonth;
    }

    public void setSalaryMonth(Date salaryMonth) {
        this.salaryMonth = salaryMonth;
    }

    public Date getSalaryDate() {
        return salaryDate;
    }

    public void setSalaryDate(Date salaryDate) {
        this.salaryDate = salaryDate;
    }

    public Float getSalaryComm() {
        return salaryComm;
    }

    public void setSalaryComm(Float salaryComm) {
        this.salaryComm = salaryComm;
    }

    public Float getSalaryBasic() {
        return salaryBasic;
    }

    public void setSalaryBasic(Float salaryBasic) {
        this.salaryBasic = salaryBasic;
    }

    @Override
    public String toString() {
        return "SalaryRecord{" +
                "salaryId=" + salaryId +
                ", usersId=" + usersId +
                ", usersName='" + usersName + '\'' +
                ", salaryMonth=" + salaryMonth +
                ", salaryDate=" + salaryDate +
                ", salaryComm=" + salaryComm +
                ", salaryBasic=" + salaryBasic +
                '}';
    }
}
