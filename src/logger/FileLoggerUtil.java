package logger;

import entity.MyDateTime;

import java.util.Calendar;
import java.util.Random;

public class FileLoggerUtil {
    private final static int MAX_NO_RECEIVERS = 4 ;
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMERIC = "0123456789";



    public static String getRandomSender(Random random) {
        return FileLoggerStatics.emailAddresses[random.nextInt(FileLoggerStatics.emailAddresses.length)];
    }

    // maximum no of receivers
    public static int getRandomNumberOfReceivers(Random random) {
        return random.nextInt(1, MAX_NO_RECEIVERS+1) ;
    }

    public static String getRandomReceiver(Random random) {
        return FileLoggerStatics.emailAddresses[random.nextInt(FileLoggerStatics.emailAddresses.length)];
    }

    public static String getRandomAlphaNumericId(Random random) {
        StringBuilder alphas = new StringBuilder(8);
        StringBuilder numerics = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(ALPHABET.length());
            alphas.append(ALPHABET.charAt(index));
        }
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(NUMERIC.length());
            numerics.append(NUMERIC.charAt(index));
        }
        return alphas.toString() + numerics.toString();
    }

    public static String getRandomSubject(Random random) {
        return FileLoggerStatics.subjects[random.nextInt(FileLoggerStatics.subjects.length)] ;
    }

    public static String getRandomClientIpAddress(Random random) {
        return getRandomIpAddress(random) ;

    }

    public static String getRandomServerIpAddress(Random random) {
        return getRandomIpAddress(random) ;
    }

    private static String getRandomIpAddress(Random random){
        return FileLoggerStatics.ipAddresses[random.nextInt(FileLoggerStatics.ipAddresses.length)];
    }


    public static String getRandomMessageSize(Random random) {
        return String.valueOf(random.nextInt(1000, 10000)) ;
    }


    public static String getRandomRecipientType(Random random) {
        return FileLoggerStatics.recipientTypes[random.nextInt(FileLoggerStatics.recipientTypes.length)];
    }

    public static String getRandomDateTimeBetween(Random random, MyDateTime startDateTime, MyDateTime endDateTime) {
        // Convert both dates to milliseconds
        long startMillis = startDateTime.toMillis();
        long endMillis = endDateTime.toMillis();

        // Generate a random time between startMillis and endMillis
        long randomMillis = startMillis + (long) (random.nextDouble() * (endMillis - startMillis));

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(randomMillis);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Calendar months are 0-based
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);

        // Return the new Entity.MyDateTime object
        return String.format("%04d-%02d-%02dT%02d:%02d:%02dZ", year, month, day, hour, minutes, getRandomSeconds(random));
    }


    private static int getRandomSeconds(Random random){
        return random.nextInt(60) ;
    }
}
