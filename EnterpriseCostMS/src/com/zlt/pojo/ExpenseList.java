package com.zlt.pojo;

/*
* 报销明细表
* */
public class ExpenseList {

    //报销单id
    private Integer expenseId;

    //费用id
    private Integer costId;

    //费用名称（与本表无关，仅用于方便接收数据）
    private  String costName;

    //报销明细表的id
    private Integer listId;

    //费用说明
    private String listName;

    //具体金额
    private Float listMoney;



    public String getCostName() {
        return costName;
    }

    public void setCostName(String costName) {
        this.costName = costName;
    }

    public Integer getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Integer expenseId) {
        this.expenseId = expenseId;
    }

    public Integer getCostId() {
        return costId;
    }

    public void setCostId(Integer costId) {
        this.costId = costId;
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Float getListMoney() {
        return listMoney;
    }

    public void setListMoney(Float listMoney) {
        this.listMoney = listMoney;
    }

}
