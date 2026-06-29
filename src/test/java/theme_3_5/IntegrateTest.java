package theme_3_5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.DoubleUnaryOperator;
import java.util.stream.Stream;

public class IntegrateTest {

    @ParameterizedTest
    @MethodSource("arguments")
    void testArguments(DoubleUnaryOperator f, double a, double b, double expected) {
        var actual = Integrate.integrate(f, a, b);

        Assertions.assertTrue(Math.abs(expected - actual) <= Integrate.EPRSILON,
                String.format("""
                        \nExpected : %f
                        Actual   : %f
                        """, expected, actual));
    }


    private static Stream<Arguments> arguments() {
        DoubleUnaryOperator f1 = x -> 1;
        DoubleUnaryOperator f2 = x -> x + 2;
        DoubleUnaryOperator f3 = x -> Math.sin(x) / x;
        return Stream.of(
                Arguments.of(f1, 0, 10, 10.0),
                Arguments.of(f2, 0, 10, 70.0),
                Arguments.of(f3, 1, 5, 0.603849)
        );
    }
}