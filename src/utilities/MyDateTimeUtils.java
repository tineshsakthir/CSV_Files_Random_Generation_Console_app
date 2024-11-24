package utilities;

import entity.MyDateTime;

public class MyDateTimeUtils {
    private static final int MINUTE_LIMIT = 60 ;
    private static final int HOUR_LIMIT = 24 ;
    private static final int[] MONTH_DAY_LIMIT = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31} ;
    private static final int MONTH_LIMIT_IN_YEAR = 12 ;
    private static final int FEB_MONTH = 1 ;
    private static final int FEB_MONTH_NON_LEAP_YEAR_DAYS = 28 ;
    private static final int FEB_MONTH_LEAP_YEAR_DAYS = 29 ;
    private static final int FIRST_DAY_IN_MONTH = 1 ;
    private static final int FIRST_MONTH_IN_YEAR = 1 ;
    private static final int FIVE_MINUTES = 5 ;
    private static final int ZERO = 0 ;
    private static final int FOUR = 4 ;
    private static final int HUNDRED = 100 ;
    private static final int FOUR_HUNDRED = 400 ;

    public static void incrementFiveMinutes(MyDateTime myDateTime){
        myDateTime.setMinutes(myDateTime.getMinutes()+FIVE_MINUTES);

        // handling minutes
        if(myDateTime.getMinutes()>=MINUTE_LIMIT){
            myDateTime.setMinutes(myDateTime.getMinutes()-MINUTE_LIMIT);
            myDateTime.setHour(myDateTime.getHour()+1);
        }

        if(myDateTime.getHour()>=HOUR_LIMIT){
            myDateTime.setHour(myDateTime.getHour()-HOUR_LIMIT) ;
            myDateTime.setDay(myDateTime.getDay()+1);
        }

        //Every time the days in the febraury month is calculated
        MONTH_DAY_LIMIT[FEB_MONTH] = isLeapYear(myDateTime.getYear()) ? FEB_MONTH_LEAP_YEAR_DAYS : FEB_MONTH_NON_LEAP_YEAR_DAYS ;

        int monthIndex = myDateTime.getMonth()-1 ; // converting from 1-indexed to 0 indexed
        if(myDateTime.getDay() > MONTH_DAY_LIMIT[monthIndex]){
            myDateTime.setDay(FIRST_DAY_IN_MONTH) ;
            myDateTime.setMonth(myDateTime.getMonth()+1);
        }

        if(myDateTime.getMonth() > MONTH_LIMIT_IN_YEAR){
            myDateTime.setMonth(FIRST_MONTH_IN_YEAR);
            myDateTime.setYear(myDateTime.getYear()+1);
        }
    }

    private static boolean isLeapYear(int year) {
        return (year % FOUR_HUNDRED == ZERO) || (year % FOUR == ZERO && year % HUNDRED != ZERO);
    }

    public static boolean isDateTime1LessThanOrEqualToDateTime2(MyDateTime myDateTime1, MyDateTime myDateTime2){
        return myDateTime1.getYear() < myDateTime2.getYear() || myDateTime1.getMonth() < myDateTime2.getMonth()
                || myDateTime1.getDay() < myDateTime2.getDay() || myDateTime1.getHour() < myDateTime2.getHour()
                || myDateTime1.getMinutes() <= myDateTime2.getMinutes();
    }

}
