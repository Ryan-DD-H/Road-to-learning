package com.zlt.utiles;




/**
 * @author 黄国旺
 * 断言是否为空的类
 * */
public class NullAssertion extends RuntimeException{


    /**
     * @author 黄国旺
     * 获取断言异常的方法
     * @param message 提示信息
     * */
    public static void getAssertionException(final String message) throws AssertionException{
        throw new AssertionException(message);
    }


    /**
     * @author 黄国旺
     * 断言一个字符串不为空
     * @param message 提示信息
     * @param str 需要断言的字符串
     * */
    public static String isNotNull(final String message,String str){
        if (str == null || str.trim().length() == 0 ){
            getAssertionException(message);
        }
        return str;
    }

    /**
     * @author 黄国旺
     * 断言一个数值不为空
     * @param message 提示信息
     * @param integer 需要断言的数值
     * */
    public static Integer isNotNull(final String message,Integer integer){
        if (integer == null || integer <= 0 ){
            getAssertionException(message);
        }
        return integer;
    }


    /**
     * @author 黄国旺
     * 断言一个单精度浮点型不为空
     * @param message 提示信息
     * @param flo 需要断言的数值
     * */
    public static Float isNotNull(final String message,Float flo){
        if (flo == null || flo <= 0 ){
            getAssertionException(message);
        }
        return flo;
    }


    /**
     * @author 黄国旺
     * 断言一个对象不为空
     * @param message 提示信息
     * @param o 需要断言的对象
     * */
    public static Object isNotNull(final String message,Object o){
        if (o == null){
            getAssertionException(message);
        }
        return o;
    }
}
