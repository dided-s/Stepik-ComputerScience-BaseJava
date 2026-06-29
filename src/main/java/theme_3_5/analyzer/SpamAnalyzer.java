package theme_3_5.analyzer;

public class SpamAnalyzer extends KeywordAnalyzer {

    public SpamAnalyzer(String... keywords) {
        super(keywords);
    }

    @Override
    protected Label getLabel() {
        return Label.SPAM;
    }
}