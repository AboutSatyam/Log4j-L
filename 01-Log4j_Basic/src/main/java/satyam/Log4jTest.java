package satyam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jTest {

    private static final Logger Logger =
            LogManager.getLogger(Log4jTest.class);

    public static void main(String[] args) {
        process();
    }

    public static void process() {

        // Service logic

        Logger.trace("FROM THE TRACE METHOD");
        Logger.debug("FROM THE DEBUG METHOD");
        Logger.info("FROM THE INFO METHOD");
        Logger.warn("FROM THE WARN METHOD");
        Logger.error("FROM THE ERROR METHOD");
        Logger.fatal("FROM THE FATAL METHOD");
    }
}
