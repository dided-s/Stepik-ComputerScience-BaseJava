package theme_5_4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.EOFException;
import java.util.stream.Stream;

public class DeserializeAnimalArrayTest {

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("argumentsGood")
    void testArgumentsGood(byte[] bytes, Animal[] expected) {
        Animal[] actual = DeserializeAnimalArray.deserializeAnimalArray(bytes);
        Assertions.assertArrayEquals(expected, actual);
    }

    private static Stream<Arguments> argumentsGood() {

        Animal wolf = new Animal("Волк");
        Animal rabbit = new Animal("Заяц");
        Animal fox = new Animal("Лиса");
        Animal cow = new Animal("Корова");
        Animal horse = new Animal("Лошадь");
        Animal dog = new Animal("Собака");
        Animal cat = new Animal("Кошка");

        Animal[] wild = new Animal[]{wolf, rabbit, fox};
        Animal[] domestic = new Animal[]{cow, horse, dog, cat};

        return Stream.of(
                Arguments.of(
                        SerializeArray.serialize(wild.length, wolf, rabbit, fox),
                        wild),
                Arguments.of(
                        SerializeArray.serialize(domestic.length, cow, horse, dog, cat),
                        domestic)
        );
    }

    @ParameterizedTest(name = "{index} {2}: {1}")
    @MethodSource("argumentsException")
    void testArgumentsException(byte[] bytes, Throwable expected, String description) {
        var exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> DeserializeAnimalArray.deserializeAnimalArray(bytes));

        System.out.println(exception.getCause().getClass());
        Assertions.assertEquals(expected.getClass(), exception.getCause().getClass());
    }

    private static Stream<Arguments> argumentsException() {

        Animal wolf = new Animal("Волк");
        Animal rabbit = new Animal("Заяц");
        Animal fox = new Animal("Лиса");
        Animal cow = new Animal("Корова");
        Animal horse = new Animal("Лошадь");
        Animal dog = new Animal("Собака");
        Animal cat = new Animal("Кошка");

        Human alex = new Human("Alex", "Pushkin");

        Animal[] wild = new Animal[]{wolf, rabbit, fox};
        Animal[] domestic = new Animal[]{cow, horse, dog, cat};

        return Stream.of(
                Arguments.of(SerializeArray.serialize(wild.length, alex, rabbit, fox), new ClassCastException(),
                        "Ошибка когда не все объекты Animal"),
                Arguments.of(SerializeArray.serialize(wolf, rabbit, fox), new EOFException(),
                        "Если нет size"),
                Arguments.of(SerializeArray.serialize(domestic.length, cow, horse, dog), new EOFException(),
                        "Если size неверное")
        );
    }
}