package theme_5_3;

import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.printf("%.6f", SumOfStream.sumOfStream(System.in));
    }
}

class SumOfStream {

    public static double sumOfStream(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream).useLocale(Locale.US);
        double sum = 0;

        while (scanner.hasNext()) {
            if (scanner.hasNextDouble()) {
                sum += scanner.nextDouble();
            } else {
                scanner.next();
            }
        }

        return sum;
    }
}