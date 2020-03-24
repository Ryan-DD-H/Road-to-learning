package com.zlt.test;



import com.zlt.utiles.C3p0Util;

import java.sql.Connection;
import java.sql.SQLException;

public class TestC3p0 {

    /**
     *
     */
    public static  void test(){

        try {
            Connection conn = C3p0Util.getConn();
            System.out.println(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        test();


    }


}
