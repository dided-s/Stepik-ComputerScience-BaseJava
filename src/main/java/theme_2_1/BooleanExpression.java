package theme_2_1;

public class BooleanExpression {
    public static boolean booleanExpression(boolean a, boolean b, boolean c, boolean d) {
        return ((a ^ b) && (c ^ d)) || ((a ^ c) && (b ^ d));
    }
}