package App;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// this class to use date and use the Format dd/MM/yyyy
public class DateFormat {
    // this method converts String to date
    public static Date stringToDate(String dateAsString) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            return df.parse(dateAsString);//Parse to convert Date to Date
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // this method converts String to date

    public static String dateToString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(date);

    }
    public static String getYearAndMonth(Date date){
        SimpleDateFormat df = new SimpleDateFormat("yyyy,MM");
        return df.format(date);
    }
    public static Integer getYear(Date date){
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        return new Integer(df.format(date));
    }
}
