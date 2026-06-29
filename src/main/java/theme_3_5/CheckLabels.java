package theme_3_5;

import theme_3_5.analyzer.Label;
import theme_3_5.analyzer.TextAnalyzer;

public class CheckLabels {
    public Label checkLabels(TextAnalyzer[] analyzers, String text) {
        for (TextAnalyzer analyzer : analyzers) {
            Label label = analyzer.processText(text);
            if (label != Label.OK) {
                return label;
            }
        }

        return Label.OK;
    }
}