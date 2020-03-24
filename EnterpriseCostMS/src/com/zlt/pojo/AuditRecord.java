package com.zlt.pojo;

import java.util.Date;

/*
* 审批记录表
* */
public class AuditRecord {

    //审批记录表id
    private Integer auditId;

    //报销单id
    private Integer expenseId;

    //用户id（与本表无关，仅用于方便接收数据）
    private Integer usersId;

    //用户的名字（与本表无关，仅用于方便接收数据）
    private String usersName;

    //审核的状态码
    private String auditState;

    //审核状态码的html显示（通过Service层字符串拼接来完成）（与本表无关，仅用于方便发送数据）
    private String auditStateHTML;

    //审批描述
    private String auditSuggest;

    //审批的时间
    private Date auditDate;



    public String getAuditStateHTML() {
        return auditStateHTML;
    }

    public void setAuditStateHTML(String auditStateHTML) {
        this.auditStateHTML = auditStateHTML;
    }

    public String getUsersName() {
        return usersName;
    }

    public void setUsersName(String usersName) {
        this.usersName = usersName;
    }

    public Integer getAuditId() {
        return auditId;
    }

    public void setAuditId(Integer auditId) {
        this.auditId = auditId;
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

    public String getAuditState() {
        return auditState;
    }

    public void setAuditState(String auditState) {
        this.auditState = auditState;
    }

    public String getAuditSuggest() {
        return auditSuggest;
    }

    public void setAuditSuggest(String auditSuggest) {
        this.auditSuggest = auditSuggest;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }
}
