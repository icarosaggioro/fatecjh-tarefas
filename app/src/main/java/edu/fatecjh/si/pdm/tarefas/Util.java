package edu.fatecjh.si.pdm.tarefas;

import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Icaro on 01/06/2015.
 */
public class Util {

    public static final long getCurrentTimestamp() {
        return new Date().getTime();
    }

    public static final long getTimestampFromDate(int d, int m, int y) {
        Calendar c = new GregorianCalendar(y, m, d);
        return c.getTimeInMillis();
    }

    public static String getFriendlyDate(long dataCumprimento) {
        CharSequence s = DateFormat.format("dd/MM/yyyy", dataCumprimento);
        return s.toString();
    }
}
