package theme_5_4;

import java.io.Serializable;
import java.util.Objects;

public class Human implements Serializable {
    private final String name;
    private final String surname;

    public Human(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Human) {
            return Objects.equals(name, ((Human) obj).name) && surname.equals(((Human) obj).surname);
        }
        return false;
    }

    @Override
    public String toString() {
        return name;
    }
}