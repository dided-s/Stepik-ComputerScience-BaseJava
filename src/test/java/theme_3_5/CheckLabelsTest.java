package theme_3_5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import theme_3_5.analyzer.*;

import java.util.stream.Stream;

public class CheckLabelsTest {

    @ParameterizedTest
    @MethodSource("arguments")
    void testArguments(TextAnalyzer[] analyzers, Label expected, String text) {
        Label actual = new CheckLabels().checkLabels(analyzers, text);

        Assertions.assertEquals(expected, actual);
    }


    private static Stream<Arguments> arguments() {
        String[] spamKeywords = {"spam", "bad"};
        int commentMaxLength = 40;

        TextAnalyzer[] analyzer_S_N_T = {
                new SpamAnalyzer(spamKeywords),
                new NegativeTextAnalyzer(),
                new TooLongTextAnalyzer(commentMaxLength)
        };
        TextAnalyzer[] analyzer_S_T_N = {
                new SpamAnalyzer(spamKeywords),
                new TooLongTextAnalyzer(commentMaxLength),
                new NegativeTextAnalyzer()
        };
        TextAnalyzer[] analyzer_T_S_N = {
                new TooLongTextAnalyzer(commentMaxLength),
                new SpamAnalyzer(spamKeywords),
                new NegativeTextAnalyzer()
        };
        TextAnalyzer[] analyzer_T_N_S = {
                new TooLongTextAnalyzer(commentMaxLength),
                new NegativeTextAnalyzer(),
                new SpamAnalyzer(spamKeywords)
        };
        TextAnalyzer[] analyzer_N_S_T = {
                new NegativeTextAnalyzer(),
                new SpamAnalyzer(spamKeywords),
                new TooLongTextAnalyzer(commentMaxLength)
        };
        TextAnalyzer[] analyzer_N_T_S = {
                new NegativeTextAnalyzer(),
                new TooLongTextAnalyzer(commentMaxLength),
                new SpamAnalyzer(spamKeywords)
        };
        return Stream.of(
                Arguments.of(analyzer_S_N_T, Label.OK,
                        "This comment is so good."),
                Arguments.of(analyzer_S_T_N, Label.TOO_LONG,
                        "This comment is so Loooooooooooooooooooooooooooong."),
                Arguments.of(analyzer_T_S_N, Label.NEGATIVE_TEXT,
                        "Very negative comment !!!!=(!!!!;"),
                Arguments.of(analyzer_T_N_S, Label.TOO_LONG,
                        "Very BAAAAAAAAAAAAAAAAAAAAAAAAD comment with :|;"),
                Arguments.of(analyzer_N_S_T, Label.SPAM,
                        "The comment is a spam, maybeeeeeeeeeeeeeeeeeeeeee!"),
                Arguments.of(analyzer_N_T_S, Label.NEGATIVE_TEXT,
                        "Very bad, very neg =(, very ..................")
        );
    }
}