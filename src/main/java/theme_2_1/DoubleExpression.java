package theme_2_1;

public class DoubleExpression {
    public static boolean doubleExpression(double a, double b, double c) {
        double e = 0.1e-4;
        return Math.abs(a + b - c) < e;
    }
}