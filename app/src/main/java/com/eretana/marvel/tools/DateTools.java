package com.eretana.marvel.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTools {

    public static String formatDate(long milliseconds) {
        Date date = new Date(milliseconds);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        return dateFormat.format(date);
    }

}
