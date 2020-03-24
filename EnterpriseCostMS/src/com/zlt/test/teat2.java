package com.zlt.test;



public class teat2 {

    public static void main(String[] args){
        String realPath = "F:\\_知了堂\\企业费用管理系统\\EnterpriseCostMS\\out\\artifacts\\web_war_exploded\\upload";
        String[] path = realPath.split("\\\\");
        String reativePath = "";
        for (int i = 0;i <= path.length-5;i++){
            reativePath += path[i]+"\\";
        }
        reativePath += "web\\resource\\assets\\images";
        System.out.println(reativePath);
    }
}
