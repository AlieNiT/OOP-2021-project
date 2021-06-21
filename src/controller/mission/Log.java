package controller.mission;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
    public static Logger logger;

    public static void setLogger(String file_name) throws IOException {
        File f = new File(file_name);
        if (!f.exists()) f.createNewFile();
        FileHandler fh = new FileHandler(file_name, true);
        logger = Logger.getAnonymousLogger();
        logger.setUseParentHandlers(false);
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
    }

}
