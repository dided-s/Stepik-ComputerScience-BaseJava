package tools;

import java.util.Arrays;

public class Utils {

    public static int[] parseIntArray(String text, String delimiter) {
        return Arrays.stream(text.split(delimiter))
                .map(String::trim)
                .mapToInt(Integer::valueOf)
                .toArray();
    }

    public static int[] parseIntArray(String text) {
        return parseIntArray(text, ",");
    }
}