package entity;

import enums.MyDateFormatType;

import java.util.Calendar;

public class MyDateTime {
    private int year ;
    private int month ;
    private int day ;
    private int hour ;
    private int minutes ;
    private static final int yearStartIndex = 0 ;
    private static final int yearEndIndex = 4 ;
    private static final int monthStartIndex = 4 ;
    private static final int monthEndIndex = 6 ;
    private static final int dayStartIndex = 6 ;
    private static final int dayEndIndex = 8 ;
    private static final int hourStartIndex = 9 ;
    private static final int hourEndIndex = 11 ;
    private static final int minutesStartIndex = 11 ;
    private static final int minutesEndIndex = 13 ;
    private static final int TEN = 10 ;
    private static final int ZERO = 0 ;
    private static final String ZERO_STRING = "0" ;
    private static final int EMPTY_THE_STRING_BUFFER_BY_SETTING_ZERO = 0 ;
    private static final int STRING_BUFFER_INITIAL_CAPACITTY = 30 ;


    //Initializing Constructor
    public MyDateTime(String dateTime) {
        if (dateTime == null || dateTime.length() != 13) {
            throw new IllegalArgumentException("Invalid dateTime format. Expected format: yyyyMMdd-HHmm.");
        }
        // I am creating the below to avoid magic numbers in the program
       setYear(Integer.parseInt(dateTime.substring(yearStartIndex, yearEndIndex)));
       setMonth(Integer.parseInt(dateTime.substring(monthStartIndex, monthEndIndex)));
       setDay(Integer.parseInt(dateTime.substring(dayStartIndex, dayEndIndex)));
       setHour(Integer.parseInt(dateTime.substring(hourStartIndex, hourEndIndex)));
       setMinutes(Integer.parseInt(dateTime.substring(minutesStartIndex, minutesEndIndex)));
    }

    //Copy Constructor
    public MyDateTime(MyDateTime myDateTime) {
        this.day = myDateTime.getDay() ;
        this.month = myDateTime.getMonth() ;
        this.year = myDateTime.getYear() ;
        this.hour = myDateTime.getHour() ;
        this.minutes = myDateTime.getMinutes() ;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int min) {
        this.minutes = min;
    }

    private String twoLengthMaker(int num){
        if(num/TEN != ZERO) return String.valueOf(num) ;
        return ZERO_STRING + num;
    }

    /**
     * 2024-11-12T12:30:00Z() -> size(21)
     * messagetrace20241112-1230.csv -> size(29)
     let me give default size of 35
     **/
    private final StringBuffer sb = new StringBuffer(STRING_BUFFER_INITIAL_CAPACITTY) ;
    private static final char HYPHEN = '-' ;
    private static final char DATE_TIME_SPLITTER = 'T' ;
    private static final char COLON = ':' ;
    private static final String TXT_EXTENSION = ".txt";
    private static final String CSV_EXTENSION = ".csv";
    private static final String ZULU_EXTENSION = "00Z()" ;
    private static final String FILE_NAME_START_PAD = "messagetrace" ;
    public String toString(MyDateFormatType myDateFormatType){
        sb.setLength(EMPTY_THE_STRING_BUFFER_BY_SETTING_ZERO);
        if(myDateFormatType == MyDateFormatType.MessageUTC){
            sb.append(year) ;
            sb.append(HYPHEN) ;
            sb.append(twoLengthMaker(month)) ;
            sb.append(HYPHEN) ;
            sb.append(twoLengthMaker(day)) ;
            sb.append(DATE_TIME_SPLITTER) ;
            sb.append(twoLengthMaker(hour)) ;
            sb.append(COLON) ;
            sb.append(twoLengthMaker(minutes)) ;
            sb.append(COLON) ;
            sb.append(ZULU_EXTENSION) ;
            return sb.toString() ;
        }else if(myDateFormatType == MyDateFormatType.TextFileName){
            return getFileNameString(TXT_EXTENSION);
        }else if(myDateFormatType == MyDateFormatType.CsvFileName){
            return getFileNameString(CSV_EXTENSION);
        }
        return "Currently toString() is not written for this format" ;
    }

    private String getFileNameString(String extension) {
        sb.append(FILE_NAME_START_PAD) ;
        sb.append(year) ;
        sb.append(twoLengthMaker(month)) ;
        sb.append(twoLengthMaker(day)) ;
        sb.append(HYPHEN) ;
        sb.append(twoLengthMaker(hour)) ;
        sb.append(twoLengthMaker(minutes)) ;
        sb.append(extension) ;
        return sb.toString() ;
    }

    /**
     * Converts the current date-time object to milliseconds from the epoch.
     * @return The milliseconds from epoch time.
     */
    public long toMillis() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, hour, minutes, 0);
        return calendar.getTimeInMillis();
    }

}
