package com.zlt.pojo;



/*
* 费用表
* */
public class Cost {

    //费用id
    private Integer costId;

    //费用名称
    private String costName;

    //费用描述
    private String costDesc;

    //费用的状态码（用于表示是否已经删除）
    private String costMark;

    //用于拼接多选框按钮（与本表无关，仅用于方便发送数据）
    private String ck;

    //费用的id数组（与本表无关，仅用于方便发送数据）
    private Integer[] ids;

    public void setCk(String ck) {
        this.ck = ck;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public Integer getCostId() {
        return costId;
    }

    public void setCostId(Integer costId) {
        this.costId = costId;
    }

    public String getCostName() {
        return costName;
    }

    public void setCostName(String costName) {
        this.costName = costName;
    }

    public String getCostDesc() {
        return costDesc;
    }

    public void setCostDesc(String costDesc) {
        this.costDesc = costDesc;
    }

    public String getCostMark() {
        return costMark;
    }

    public void setCostMark(String costMark) {
        this.costMark = costMark;
    }

    //手动拼接多选框按钮
    public String getCk() {
        ck = "<INPUT TYPE='checkbox' value='" + getCostId() + "' name='ids' />";
        return ck;
    }

}
