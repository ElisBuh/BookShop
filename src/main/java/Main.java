import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/main/resources/config,properties");
        Properties properties = new Properties();

        try {
            properties.load(new FileReader(file));
            int month = Integer.parseInt(properties.getProperty("month"));
            System.out.println(month);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
