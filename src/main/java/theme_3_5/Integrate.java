package theme_3_5;

import java.util.function.DoubleUnaryOperator;

public class Integrate {

    public static double EPRSILON = 1e-6;

    public static double integrate(DoubleUnaryOperator f, double a, double b) {
        double step = 1, n = 2;
        double current = integrate(f, a, b, step);
        step /= n;
        double previous = integrate(f, a, b, step);

        while (Math.abs(current - previous) >= EPRSILON) {
            previous = current;
            step /= n;
            current = integrate(f, a, b, step);
        }

        System.out.println(step);
        return current;
    }

    private static double integrate(DoubleUnaryOperator f, double a, double b, double step) {
        double answer = 0;
        double x = a;
        while (x < b) {
            answer += f.applyAsDouble(x);
            x += step;
        }
        return answer * step;
    }
}