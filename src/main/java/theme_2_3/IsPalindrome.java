package theme_2_3;

public class IsPalindrome {

    /**
     * Checks if given <code>text</code> is a palindrome.
     *
     * @param text any string
     * @return <code>true</code> when <code>text</code> is a palindrome, <code>false</code> otherwise
     */
    public static boolean isPalindrome(String text) {
        String regexPattern = "[^a-zA-Z0-9]";
        text = text.replaceAll(regexPattern, "").toLowerCase();

        return text.equals(new StringBuilder(text).reverse().toString());
    }
}