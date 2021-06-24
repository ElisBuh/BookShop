package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final Logger log = LoggerFactory.getLogger(Config.class);
    private static File file = new File("src/main/resources/config,properties");
    private static Properties properties = new Properties();

    public static int countMonth() {
        try {
            properties.load(new FileReader(file));
            return Integer.parseInt(properties.getProperty("storageService.month"));
        } catch (IOException e) {
            log.error(e.toString());
            return 0;
        }
    }
    public static boolean permissionChangeStatusRequest(){
        try {
            properties.load(new FileReader(file));
            return Boolean.parseBoolean(properties.getProperty("storageService.permissionChangeStatusRequest"));
        } catch (IOException e) {
            log.error(e.toString());
            return true;
        }
    }

}
