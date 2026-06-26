package Helper;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Config {

    public static WebDriver driver;

    // Maximiser la fenêtre (utile en mode non-headless)
    public static void maximizeWindow() {
        driver.manage().window().maximize();
    }

    // Configuration Chrome pour exécution locale + CI (Jenkins)
    public static void confChrome() {

        ChromeOptions options = new ChromeOptions();

        // Mode headless (obligatoire pour Jenkins / serveurs sans interface graphique)
        options.addArguments("--headless=new");

        // Sécurité et compatibilité Linux / Docker
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");

        // Taille fixe de la fenêtre (important en mode headless)
        options.addArguments("--window-size=1920,1080");

        // Améliore la compatibilité avec certaines versions de ChromeDriver
        options.addArguments("--remote-allow-origins=*");

        // Désactivation des éléments inutiles du navigateur
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");

        // Création du driver Chrome avec options
        driver = new ChromeDriver(options);

        // Timeout global pour les recherches d’éléments
        // (simple et suffisant pour début, WebDriverWait est recommandé plus tard)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // Méthode pour changer dynamiquement l’attente implicite
    public static void attente(int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }
}
