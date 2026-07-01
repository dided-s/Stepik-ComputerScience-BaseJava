package theme_6_1;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<FIRST, SECOND> {

    public FIRST first;
    public SECOND second;

    private Pair(FIRST first, SECOND second) {
        this.first = first;
        this.second = second;
    }

    public void ifPresent(BiConsumer<? super FIRST, ? super SECOND> action) {
        action.accept(first, second);
    }

    public FIRST getFirst() {
        return first;
    }

    public SECOND getSecond() {
        return second;
    }

    public static <FIRST, SECOND> Pair<FIRST, SECOND> of(FIRST first, SECOND second) {
        return new Pair<>(first, second);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Pair<?, ?>)) {
            return false;
        }

        Pair<?, ?> pair = (Pair<?, ?>) object;

        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}