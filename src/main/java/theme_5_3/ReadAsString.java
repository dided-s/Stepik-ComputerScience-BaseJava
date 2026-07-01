package theme_5_3;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class ReadAsString {

    public static String readAsString(InputStream inputStream, Charset charset) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
        StringBuilder builder = new StringBuilder();

        int b;
        while ((b = inputStreamReader.read()) != -1) {
            builder.append((char) b);
        }

        return builder.toString();
    }
}