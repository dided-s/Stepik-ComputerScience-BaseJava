package theme_2_1;

public class LeapYearCount {
    public static int leapYearCount(int year) {
        return ((year / 4) - (year / 100)) + (year / 400);
    }
}