package tools;

import org.junit.jupiter.api.Assertions;

public class CustomAssertions {

    private CustomAssertions() {
    }

    public static void assertContains(String fullText, String subText) {
        Assertions.assertTrue(fullText.contains(subText), String.format(
                """
                        Full text: %s
                        Sub  text: %s
                        doesn't contain'
                        """, fullText, subText));
    }

    public static void assertNotContains(String fullText, String subText) {
        Assertions.assertFalse(fullText.contains(subText), String.format(
                """
                        Full text: %s
                        Sub  text: %s
                        contains'
                        """, fullText, subText));
    }
}