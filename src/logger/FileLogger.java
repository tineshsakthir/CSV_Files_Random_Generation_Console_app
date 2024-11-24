package logger;

import entity.MyDateTime;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class FileLogger {
    private static final int CURRENT_CSV_FILE_LIMIT = 10000;
    private static final int EMPTY_THE_STRING_BUFFER_BY_SETTING_ZERO = 0 ;
    public static int logToFile( String newDirectoryPath, String txtFileName, String csvFileName, MyDateTime startTime,MyDateTime endTimeAfterFiveMinutes){

        String txtFilePath = newDirectoryPath + "\\" + txtFileName;

        int currentFileTotalRowsCountWhenReceivedIsSplitted = 0 ;

        try (FileWriter writer = new FileWriter(txtFilePath)) {
            // Write headers
            for (int i = 0; i < FileLoggerStatics.csvHeaders.length; i++) {
                writer.append(FileLoggerStatics.csvHeaders[i]);
                if (i < FileLoggerStatics.csvHeaders.length - 1) writer.append(",");
            }
            writer.append("\n");

            //Writes the other liens
            Random random = new Random() ;
            for(int lineNumber = 1 ; lineNumber<=CURRENT_CSV_FILE_LIMIT ; lineNumber++){

                //Header 1 content
                writer.append(FileLoggerUtil.getRandomSender(random)) ;
                writer.append(',') ;

                StringBuilder receiversString = new StringBuilder() ;
                final int noOfReceivers = FileLoggerUtil.getRandomNumberOfReceivers(random) ;
                currentFileTotalRowsCountWhenReceivedIsSplitted += noOfReceivers ;
                for(int receiver = 1 ; receiver <= noOfReceivers ; receiver++){
                    receiversString.append(FileLoggerUtil.getRandomReceiver(random)) ;
                    if(receiver != noOfReceivers) receiversString.append(';') ;
                }

                //Header 2 content
                writer.append(receiversString.toString()) ;
                writer.append(',') ;

                //Header 3 content
                writer.append(FileLoggerUtil.getRandomAlphaNumericId(random)) ;
                writer.append(',') ;

                //Header 4 content
                writer.append(FileLoggerUtil.getRandomSubject(random)) ;
                writer.append(',') ;

                //Header 5 content
                writer.append(FileLoggerUtil.getRandomClientIpAddress(random)) ;
                writer.append(',') ;

                //Header 6 content
                writer.append(FileLoggerUtil.getRandomServerIpAddress(random)) ;
                writer.append(',') ;

                //Header 7 content
                writer.append(FileLoggerUtil.getRandomMessageSize(random)) ;
                writer.append(',') ;

                receiversString.setLength(EMPTY_THE_STRING_BUFFER_BY_SETTING_ZERO);
                for(int receiver = 1 ; receiver <= noOfReceivers ; receiver++){
                    receiversString.append(FileLoggerUtil.getRandomRecipientType(random)) ;
                    if(receiver != noOfReceivers) receiversString.append(';') ;
                }

                //Header 8 content
                writer.append(receiversString.toString()) ;
                writer.append(',') ;

                //Header 9 content
                writer.append(FileLoggerUtil.getRandomDateTimeBetween(random, startTime, endTimeAfterFiveMinutes)) ;
                //Not append comma(,) , append new line
                writer.append("\n");
            }

        } catch (IOException e) {
            System.out.println("An error occurred while creating the CSV file.");
            e.printStackTrace();
        }

        // Attempting to rename the file after closing the FileWriter
        File originalFile = new File(txtFilePath);
        String newFilePathWithCsvExtension = newDirectoryPath + File.separator +csvFileName;
        File renamedFile = new File(newFilePathWithCsvExtension);

        boolean success = originalFile.renameTo(renamedFile);

        if (!success) {
            System.out.println("File renaming failed due to unknown reasons. Check permissions and path validity.");
        }

        System.out.println("CSV file created successfully at: " + newFilePathWithCsvExtension);
        return currentFileTotalRowsCountWhenReceivedIsSplitted ;
    }
}
