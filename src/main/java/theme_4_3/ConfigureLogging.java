package theme_4_3;

import java.util.logging.*;

public class ConfigureLogging {

    public static void configureLogging() {

        Logger classALogger = Logger.getLogger("org.stepic.java.logging.ClassA");
        Logger classBLogger = Logger.getLogger("org.stepic.java.logging.ClassB");
        Logger orgStepicJavaLogger = Logger.getLogger("org.stepic.java");

        classALogger.setLevel(Level.ALL);
        classBLogger.setLevel(Level.WARNING);
        orgStepicJavaLogger.setLevel(Level.ALL);

        Handler xmlConsoleHandler = new ConsoleHandler();
        xmlConsoleHandler.setLevel(Level.ALL);
        xmlConsoleHandler.setFormatter(new XMLFormatter());

        orgStepicJavaLogger.addHandler(xmlConsoleHandler);
        orgStepicJavaLogger.setUseParentHandlers(false);
    }
}