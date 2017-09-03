package org.onez.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;

/**
 * Created by joshanashakya on 8/9/17.
 */
public class DataHandler {

    /**
     * Loads data from resource file
     *
     * @param source
     * @return String
     */
    public static String loadData(String source) {
        BufferedReader reader = new BufferedReader (new InputStreamReader (new DataHandler ().getClass ().getClassLoader ().
                getResourceAsStream (source)));
        try {
            StringBuilder builder = new StringBuilder ();
            String line;
            while ((line = reader.readLine ()) != null) {
                builder.append (line + "::");
            }
            return builder.toString ();
        } catch (Exception ex) {
            // TODO: handle this stacktrace
            ex.printStackTrace ();
        }
        return "";
    }
}
