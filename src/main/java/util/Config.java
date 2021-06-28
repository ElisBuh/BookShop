package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final Logger log = LoggerFactory.getLogger(Config.class);
    private static final File file = new File("src/main/resources/config.properties");
    private static final Properties properties = new Properties();
    private static FileReader fileReader;

    static {
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            log.error(e.toString());
        }
    }

    public static String configProperties(String value){
        try {
            properties.load(fileReader);
            return properties.getProperty(value);
        } catch (IOException e) {
            log.error(e.toString());
            return null;
        }
    }

}
