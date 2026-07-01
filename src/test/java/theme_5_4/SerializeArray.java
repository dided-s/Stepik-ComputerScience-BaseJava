package theme_5_4;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializeArray {

    public static byte[] serialize(Serializable... array) {

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {


            for (Serializable serializable : array) {
                if (serializable instanceof Integer) {
                    objectOutputStream.writeInt((Integer) serializable);
                } else if (serializable instanceof Long) {
                    objectOutputStream.writeLong((Long) serializable);
                } else if (serializable instanceof Boolean) {
                    objectOutputStream.writeBoolean((Boolean) serializable);
                } else if (serializable instanceof Character) {
                    objectOutputStream.writeChar((Character) serializable);
                } else if (serializable instanceof Double) {
                    objectOutputStream.writeDouble((Double) serializable);
                } else if (serializable instanceof Float) {
                    objectOutputStream.writeFloat((Float) serializable);
                } else if (serializable instanceof String) {
                    objectOutputStream.writeUTF((String) serializable);
                } else {
                    objectOutputStream.writeObject(serializable);
                }
            }

            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}