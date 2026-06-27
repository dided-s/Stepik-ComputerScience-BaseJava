package theme_3_4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class ComplexNumberTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/theme_3_4/ComplexNumber/data.csv", delimiter = ',')
    void testCsv(double re1, double im1, double re2, double im2, boolean expected) {
        var complexNumber1 = new ComplexNumber(re1, im1);
        var complexNumber2 = new ComplexNumber(re2, im2);

        Assertions.assertEquals(complexNumber1, complexNumber1,
                String.format("ComplexNumber: re=%f, im=%f doesn't equal to itself", re1, im1));
        Assertions.assertEquals(complexNumber1.hashCode(), complexNumber1.hashCode(),
                String.format("ComplexNumber hashCode: re=%f, im=%f doesn't equal to itself", re1, im1));
        Assertions.assertEquals(complexNumber2, complexNumber2,
                String.format("ComplexNumber: re=%f, im=%f doesn't equal to itself", re2, im2));
        Assertions.assertEquals(complexNumber2.hashCode(), complexNumber2.hashCode(),
                String.format("ComplexNumber hashCode: re=%f, im=%f doesn't equal to itself", re2, im2));

        if (expected) {
            Assertions.assertEquals(complexNumber1, complexNumber2);
            Assertions.assertEquals(complexNumber1.hashCode(), complexNumber2.hashCode());
        } else {
            Assertions.assertNotEquals(complexNumber1, complexNumber2);
        }
    }
}