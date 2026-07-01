package theme_5_2;

import java.io.IOException;
import java.io.InputStream;

public class CheckSumOfStream {

    public static int checkSumOfStream(InputStream inputStream) throws IOException {
        int checkSum = 0;
        int b;

        while ((b = inputStream.read()) != -1) {
            checkSum = Integer.rotateLeft(checkSum, 1) ^ b;
        }

        return checkSum;
    }
}