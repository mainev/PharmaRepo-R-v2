/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class DateFormatter {

    static String pattern = "MM/dd/yyyy";
    static SimpleDateFormat sdf = new SimpleDateFormat(pattern);

    /**
     * Returns a string format of date with pattern "MM/dd/yyyy".
     *
     * @param date
     * @return
     */
    public static String convertToString(Date date) {
        return sdf.format(date);
    }

    /**
     * Returns a date format of a string with pattern "MM/dd/yyyy".
     *
     * @param dateInput
     * @return
     * @throws ParseException
     */
    public static Date convertToDate(String dateInput) throws ParseException {
        return sdf.parse(dateInput);
    }

    public static Date formatDate(Date dateInput) throws ParseException {
        return sdf.parse(sdf.format(dateInput));
    }
}
