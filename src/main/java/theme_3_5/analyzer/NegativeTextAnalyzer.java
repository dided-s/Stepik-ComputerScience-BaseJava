package theme_3_5.analyzer;

public class NegativeTextAnalyzer extends KeywordAnalyzer {

    public NegativeTextAnalyzer() {
        this.keywords = new String[]{":(", "=(", ":|"};
    }

    @Override
    protected Label getLabel() {
        return Label.NEGATIVE_TEXT;
    }
}