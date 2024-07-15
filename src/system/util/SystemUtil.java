package system.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SystemUtil {

    /**
     * Reads the content of a file and converts it into an array of Strings.
     * @param filePath the path to the file to be read
     * @return an ArrayList of Strings, each representing a line in the file
     * @throws IOException if an I/O error occurs
     */
    public static ArrayList<String> lineReader(String filePath) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    /**
     * Checks if a String is valid (i.e., it is not null, not empty, or blank).
     * @param str the String to be validated
     * @return true if the String is valid, false otherwise
     */
    public static boolean isValid(String str) {
        return str != null && !str.trim().isEmpty();
    }
}

