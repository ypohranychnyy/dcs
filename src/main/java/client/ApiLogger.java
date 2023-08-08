package client;

import com.dkorobtsov.logging.LogFormatter;
import com.dkorobtsov.logging.LogWriter;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApiLogger implements LogWriter {
    private static final Logger logger = Logger.getLogger("DefaultLogger");

    ApiLogger(LogFormatter logFormatter) {
        logger.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(logFormatter.formatter);
        logger.addHandler(handler);
    }

    public void log(String msg) {
        logger.log(Level.INFO, msg);
    }
}
