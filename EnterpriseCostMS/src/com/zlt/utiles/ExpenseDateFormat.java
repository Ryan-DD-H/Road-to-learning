package com.zlt.utiles;

import org.apache.commons.beanutils.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * 设置时间样式
 * */
public class ExpenseDateFormat implements Converter {

    //设置默认时间样式

    private String date = "yyyy-MM-dd";


    public ExpenseDateFormat() {
        super();
    }


    /**
     * 通过有参构造器来自定义时间样式
     * */
    public ExpenseDateFormat(String date) {
        super();
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public Object convert(Class aClass, Object o) {

        if(o == null || o.toString().trim().length() == 0){
            return null;
        }

        if(aClass == Date.class){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(date);
            try {
                return simpleDateFormat.parse(String.valueOf(o));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }


        return null;
    }

}
