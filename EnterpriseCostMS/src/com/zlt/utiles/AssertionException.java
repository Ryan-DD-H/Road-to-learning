package com.zlt.utiles;


/**
 * @author 黄国旺
 * 自定义的用于断言的异常
 * */
public class AssertionException extends RuntimeException{

    public AssertionException(String message) {
        super(message);
    }
}
