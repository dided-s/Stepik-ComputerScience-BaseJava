package theme_4_1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class GetCallerClassAndMethodNameTest {

    @ParameterizedTest
    @MethodSource("arguments")
    void testArguments(String actual, String expected) {
        Assertions.assertEquals(expected, actual);
    }


    private static Stream<Arguments> arguments() {
        A aObject = new A();
        B bObject = new B();
        return Stream.of(
                Arguments.of(aObject.call(), "theme_4_1.A#call"),
                Arguments.of(A.callStatic(), "theme_4_1.A#callStatic"),
                Arguments.of(aObject.a3(), "theme_4_1.A#a3"),
                Arguments.of(bObject.call(), "theme_4_1.B#call"),
                Arguments.of(B.callStatic(), "theme_4_1.B#callStatic")
        );
    }
}

class A {

    public String call() {
        return a1();
    }

    public static String callStatic() {
        return a2();
    }

    public String a1() {
        return GetCallerClassAndMethodName.getCallerClassAndMethodName();
    }

    public static String a2() {
        return GetCallerClassAndMethodName.getCallerClassAndMethodName();
    }

    public String a3() {
        return B.bStatic();
    }
}

class B {

    public String call() {
        return b();
    }

    public static String callStatic() {
        return bStatic();
    }

    public String b() {
        return GetCallerClassAndMethodName.getCallerClassAndMethodName();
    }

    public static String bStatic() {
        return GetCallerClassAndMethodName.getCallerClassAndMethodName();
    }
}