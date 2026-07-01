package theme_5_4;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class DeserializeAnimalArray {

    public static Animal[] deserializeAnimalArray(byte[] data) {
        try (InputStream is = new ByteArrayInputStream(data);
             ObjectInputStream objectInputStream = new ObjectInputStream(is)) {
            int size = objectInputStream.readInt();
            Animal[] animals = new Animal[size];

            for (int i = 0; i < size; i++) {
                animals[i] = (Animal) objectInputStream.readObject();
            }

            return animals;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(e);
        }
    }
}