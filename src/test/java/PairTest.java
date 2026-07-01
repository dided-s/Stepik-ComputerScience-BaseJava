import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import theme_6_1.Pair;

public class PairTest {

    @Test
    void test() {
        Pair<Integer, String> pair = Pair.of(1, "hello");
        Integer i = pair.getFirst();
        Assertions.assertEquals(1, i);

        String s = pair.getSecond();
        Assertions.assertEquals("hello", s);

        Pair<Integer, String> pair2 = Pair.of(1, "hello");

        Assertions.assertEquals(pair, pair2);

        Assertions.assertEquals(pair.hashCode(), pair2.hashCode());
    }
}