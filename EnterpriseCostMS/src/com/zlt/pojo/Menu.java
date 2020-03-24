package com.zlt.pojo;


/*
* 权限表
* */
public class Menu {

    //权限id
    private Integer menuId;

    //权限名称
    private String menuName;

    //权限的父权限id
    private Integer pMenuId;

    //权限所对应的地址
    private String menuUrl;



    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getpMenuId() {
        return pMenuId;
    }

    public void setpMenuId(Integer pMenuId) {
        this.pMenuId = pMenuId;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }
}
