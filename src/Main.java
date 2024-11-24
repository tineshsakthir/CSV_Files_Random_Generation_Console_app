import entity.MyDateTime;
import logger.FileLogger;
import utilities.MyDateTimeUtils;
import enums.MyDateFormatType;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        String dateTime1 = "20241012T0000" ;
        String dateTime2 = "20241013T0000" ;

        int totalRowsCountWhenReceivedIsSplitted = 0 ;

        MyDateTime myDateTime1 = new MyDateTime(dateTime1) ;
        MyDateTime myDateTime2 = new MyDateTime(dateTime2) ;

        MyDateTime prevDateTime = new MyDateTime(myDateTime1) ;
        MyDateTimeUtils.incrementFiveMinutes(myDateTime1);

        String baseDirectoryPath = "D:\\LogFolderForCSV";
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String newDirectoryPath = baseDirectoryPath + "\\" + timeStamp;

        // Always this folder for simplicity
        String myFolderName = "ServerListeningFolder" ;
        String newDirectoryPath = baseDirectoryPath + "\\" + myFolderName;
        File newDirectory = new File(newDirectoryPath);

        if (newDirectory.mkdirs()) {
            System.out.println("Directory created: " + newDirectoryPath);
        } else {
            System.out.println("Failed to create directory or it already exists.");
        }

        while(utilities.MyDateTimeUtils.isDateTime1LessThanOrEqualToDateTime2(myDateTime1, myDateTime2)){

            int currentFileTotalRowsCountWhenReceivedIsSplitted = FileLogger.logToFile(newDirectoryPath, myDateTime1.toString(MyDateFormatType.TextFileName),myDateTime1.toString(MyDateFormatType.CsvFileName) , prevDateTime, myDateTime1);
            prevDateTime = new MyDateTime(myDateTime1) ;
            MyDateTimeUtils.incrementFiveMinutes(myDateTime1);
            totalRowsCountWhenReceivedIsSplitted += currentFileTotalRowsCountWhenReceivedIsSplitted ;
        }

        System.out.println("Total Lines : " +totalRowsCountWhenReceivedIsSplitted);
    }
}


