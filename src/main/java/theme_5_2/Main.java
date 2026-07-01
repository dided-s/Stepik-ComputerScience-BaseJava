package theme_5_2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Main {
    public static void main(String[] args) throws IOException {
        ConvertWindowsToUnixStringEnd.convertWindowsToUnixStringEnd(System.in, System.out);
    }
}

class ConvertWindowsToUnixStringEnd {

    public static void convertWindowsToUnixStringEnd(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte SLASH_R = 13;
        byte SLASH_N = 10;

        byte current = (byte) inputStream.read();
        byte previous;

        while (current != -1) {
            previous = current;
            current = (byte) inputStream.read();

            if (!(previous == SLASH_R && current == SLASH_N)) {
                outputStream.write(previous);
            }
        }

        outputStream.flush();
    }
}