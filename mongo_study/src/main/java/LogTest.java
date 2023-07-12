import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {
    public static final Logger LOGGER = LoggerFactory.getLogger(LogTest.class);
    public static void main(String[] args) {
        LOGGER.error("error");
        LOGGER.warn("wring");
        LOGGER.info("info");
        LOGGER.debug("debug");// 默认级别
        LOGGER.trace("trace");
    }

}
