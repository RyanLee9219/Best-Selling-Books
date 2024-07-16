package system.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
// done
public class SystemUtil {

    public static String[] lineReader(String line) {
        final int NUMCOLS = 6;
        String[] str = new String[NUMCOLS];
        int index = 0;
        final char chComma = ',';
        final char chQuotes = '"';
        int start = 0;
        int end = line.indexOf(chComma);
        String value;
        while (start < end) {
            if (line.charAt(start) == chQuotes) {
                start++;
                end = line.indexOf(chQuotes, start + 1);
            }
            value = line.substring(start, end);
            value = value.trim();
            str[index++] = value;
            if (line.charAt(end) == chQuotes)
                start = end + 2;
            else
                start = end + 1;
            end = line.indexOf(chComma, start + 1);
        }
        if (start < line.length()) {
            value = line.substring(start);
            str[index++] = value;
        }
        return str;
    }

    public static boolean isValid(String str) {
        return str != null && !str.trim().isEmpty();
    }
}

