package tools;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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

    /**
     * Посчитать количество вхождений подстроки в строку. (Не пересекающиеся вхождения)
     */
    public static int textNonCrossedContainsCount(String text, String sub) {
        if (text == null || sub == null || sub.isEmpty()) return 0;

        int count = 0;
        int from = 0;

        while ((from = text.indexOf(sub, from)) != -1) {
            count++;
            from += sub.length();
        }

        return count;
    }

    public static String executeAndGetSystemErrAsString(Runnable runnable) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream oldOut = System.err;

        try {
            System.setErr(new PrintStream(baos));
            runnable.run();
        } finally {
            System.setErr(oldOut);
        }

        return baos.toString();
    }
}