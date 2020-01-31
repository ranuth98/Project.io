package com.cognizant.dao;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author 833397
 *
 */

/**
 * DateUtil class has a convertToDate() method.
 *
 */
public class DateUtil {

    /**
     * convertToDate() convert date entered in a form(dd/MM/yyyy) into a
     * java.util.Date type
     */
    public static Date convertToDate(String date) {
        try {
            SimpleDateFormat simpleDateFormatObj = new SimpleDateFormat("dd/MM/yyyy");
            simpleDateFormatObj.setLenient(false);
            Date dateType = simpleDateFormatObj.parse(date);
            return dateType;
        } catch (ParseException e) {
            System.out.println(e);
        }
        return null;

    }
}
