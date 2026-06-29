package theme_3_5.analyzer;

public abstract class KeywordAnalyzer implements TextAnalyzer {

    protected String[] keywords;

    protected KeywordAnalyzer(String... keywords) {
        this.keywords = keywords;
    }

    protected String[] getKeywords() {
        return this.keywords;
    }

    protected abstract Label getLabel();

    @Override
    public Label processText(String text) {
        for (String keyword : getKeywords()) {
            if (text.contains(keyword)) {
                return getLabel();
            }
        }
        return Label.OK;
    }
}