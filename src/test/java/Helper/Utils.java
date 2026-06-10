// package Helper;

// import java.io.FileInputStream;
// import java.io.InputStream;
// import java.util.Properties;

// public class Utils {

//     public static String getProperty(String property) throws Exception {

//         InputStream input = new FileInputStream("src/test/resources/Properties/properties");

//         Properties properties = new Properties();
        
//         properties.load(input);

//         return properties.getProperty(property);
//     }
// }

package Helper;

import java.io.InputStream;
import java.util.Properties;

public class Utils {

    public static String getProperty(String property) throws Exception {

        InputStream input = Utils.class
                .getClassLoader()
                .getResourceAsStream("Properties/properties");

        if (input == null) {
            throw new RuntimeException("Fichier properties introuvable !");
        }

        Properties properties = new Properties();
        properties.load(input);

        return properties.getProperty(property);
    }
}
