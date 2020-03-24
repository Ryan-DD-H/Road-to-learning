package com.zlt.pojo;

import java.util.Arrays;
import java.util.Date;

/*
* 报销单
* */
public class Expense {

    //报销单id
    private Integer expenseId;

    //用户id
    private Integer usersId;

    //报销原因
    private String expenseName;

    //报销详情
    private String expenseDesc;

    //报销总金额
    private Float expenseMoney;

    //报销单状态码
    private String expenseState;

    //报销单状态码的html显示（通过Service层字符串拼接来完成）（与本表无关，仅用于方便发送数据）
    private String expenseStateHTML;

    //报销单填写日期
    private Date expenseDate;

    //费用id的集合（与本表无关，仅用于方便接收数据）
    private String costIds[];

    //报销明细表的具体金额的数组（与本表无关，仅用于方便接收数据）
    private Float listMoneys[];

    //报销明细表的费用说明的数组（与本表无关，仅用于方便接收数据）
    private String listNames[];

    //开始的时间（与本表无关，仅用于方便接收数据）
    private Date startDate;

    //结束的时间（与本表无关，仅用于方便接收数据）
    private Date endDate;

    //用户的名字（与本表无关，仅用于方便接收数据）
    private String usersName;

    //报销单审批的地址（通过Service层字符串拼接来完成）（与本表无关，仅用于方便发送数据）
    private String expenseAuditUrl;

    //修改我的报销单的地址（通过Service层字符串拼接来完成）（与本表无关，仅用于方便发送数据）
    private String expenseUpdateUrl;



    public String getExpenseUpdateUrl() {
        return expenseUpdateUrl;
    }

    public void setExpenseUpdateUrl(String expenseUpdateUrl) {
        this.expenseUpdateUrl = expenseUpdateUrl;
    }

    public String getExpenseAuditUrl() {
        return expenseAuditUrl;
    }

    public void setExpenseAuditUrl(String expenseAuditUrl) {
        this.expenseAuditUrl = expenseAuditUrl;
    }

    public Integer getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Integer expenseId) {
        this.expenseId = expenseId;
    }

    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public String getExpenseDesc() {
        return expenseDesc;
    }

    public void setExpenseDesc(String expenseDesc) {
        this.expenseDesc = expenseDesc;
    }

    public Float getExpenseMoney() {
        return expenseMoney;
    }

    public void setExpenseMoney(Float expenseMoney) {
        this.expenseMoney = expenseMoney;
    }

    public String getExpenseState() {
        return expenseState;
    }

    public void setExpenseState(String expenseState) {
        this.expenseState = expenseState;
    }

    public String getExpenseStateHTML() {
        return expenseStateHTML;
    }

    public void setExpenseStateHTML(String expenseStateHTML) {
        this.expenseStateHTML = expenseStateHTML;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String[] getCostIds() {
        return costIds;
    }

    public void setCostIds(String[] costIds) {
        this.costIds = costIds;
    }

    public Float[] getListMoneys() {
        return listMoneys;
    }

    public void setListMoneys(Float[] listMoneys) {
        this.listMoneys = listMoneys;
    }

    public String[] getListNames() {
        return listNames;
    }

    public void setListNames(String[] listNames) {
        this.listNames = listNames;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getUsersName() {
        return usersName;
    }

    public void setUsersName(String usersName) {
        this.usersName = usersName;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expenseId=" + expenseId +
                ", usersId=" + usersId +
                ", expenseName='" + expenseName + '\'' +
                ", expenseDesc='" + expenseDesc + '\'' +
                ", expenseMoney=" + expenseMoney +
                ", expenseState='" + expenseState + '\'' +
                ", expenseStateHTML='" + expenseStateHTML + '\'' +
                ", expenseDate=" + expenseDate +
                ", costIds=" + Arrays.toString(costIds) +
                ", listMoneys=" + Arrays.toString(listMoneys) +
                ", listNames=" + Arrays.toString(listNames) +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", usersName='" + usersName + '\'' +
                ", expenseAuditUrl='" + expenseAuditUrl + '\'' +
                '}';
    }
}
