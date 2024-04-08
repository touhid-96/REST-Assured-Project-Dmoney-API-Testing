package Utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.util.Random;

public class Utils {
    public static void setEnvVariable(String key, String value) throws ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration("./src/test/resources/config.properties");
        config.setProperty(key, value);
        config.save();
    }

    public static int generateRandomDigits(int length) {
        Random random = new Random();

        StringBuilder zeros = new StringBuilder();
        for (int i=0; i<length-1; i++) {
            zeros.append("0");
        }

        //ex. random.nextInt(900000)+100000)
        int highBound = Integer.parseInt("9" + zeros);
        int lowBound = Integer.parseInt("1" + zeros);

        return random.nextInt(highBound)+lowBound;
    }
}
