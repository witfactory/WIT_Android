package com.cb.witfactory.ui.support;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTimeHelper {

    public static String getCurrentDateTime() {
        // Obtener la fecha y hora actual
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        // Formatear la fecha y hora
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return dateFormat.format(currentDate);
    }
}
