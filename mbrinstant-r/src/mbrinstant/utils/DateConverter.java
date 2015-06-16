/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class DateConverter {
    
        public DateConverter(){
        }
        
        /***
         * Converts java.util.Date into LocalDate format.
         * @param date
         * @return 
         */
        public static LocalDate convertDateToLocalDate(Date date){

            if(date != null){
                LocalDate localDate = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

                return localDate;
            }
            else return null;
           
        }
        
        public static Date convertLocalDateToDate(LocalDate localDate){
             return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
       
     
}
