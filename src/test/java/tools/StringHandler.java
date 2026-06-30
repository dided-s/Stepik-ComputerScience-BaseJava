package tools;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class StringHandler extends Handler {

    private StringBuilder log = new StringBuilder();

    @Override
    public void publish(LogRecord record) {
        if (!isLoggable(record)) {
            return;
        }
        log.append(getFormatter().format(record));
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() {
    }

    public String getLog() {
        return log.toString();
    }

    public void clear() {
        log.setLength(0);
    }
}