package Helper;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Config {

    public static WebDriver driver;

    // Maximiser la fenêtre du navigateur
    public static void maximizeWindow() {
        driver.manage().window().maximize();
    }

    // Configuration Chrome (compatible Jenkins + local)
    public static void confChrome() {

        ChromeOptions options = new ChromeOptions();

        // Mode headless pour exécution sur serveur / Jenkins
        options.addArguments("--headless=new");

        // Sécurité et compatibilité Linux / Docker / CI
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");

        // Taille fixe de la fenêtre en mode headless
        options.addArguments("--window-size=1920,1080");

        // Compatibilité ChromeDriver / Chrome
        options.addArguments("--remote-allow-origins=*");

        // Désactivation des éléments inutiles du navigateur
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");

        // Initialisation du driver Chrome
        driver = new ChromeDriver(options);

        // Attente implicite globale (simple mais suffisante pour début)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // Modifier dynamiquement l'attente implicite
    public static void attente(int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }
}
