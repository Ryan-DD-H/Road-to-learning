package com.zlt.utiles;


/**
 * @author 黄国旺
 * 状态码描述
 * */
public class MarkCode {

    /**
     * @author 黄国旺
     *
     *  AVAILABLE_CODE        可用标识（如：在职，未删除等）
     * UNAVAILABLE_CODE      不可用标识（如：离职，已删除等）
     * */
    public static final String AVAILABLE_CODE = "0",UNAVAILABLE_CODE = "1";


    /**
     * @author 黄国旺
     * ENTER_NO         保存但是没有提交的
     * ENTER_YES        保存并提交了的
     * FINANCE_NO       财务审核未通过的
     * FINANCE_YES      财务审核通过了的
     * MANAGER_NO       经理审核未通过的
     * MANAGER_YSE      经理审核通过了的
     * */
    public static final String ENTER_NO = "0",ENTER_YES = "1",FINANCE_NO = "-2",FINANCE_YES = "2",MANAGER_NO = "-4",MANAGER_YES = "4";



}
