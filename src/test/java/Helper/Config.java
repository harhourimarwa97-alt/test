package Helper;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Config {

    public static WebDriver driver;

    // Ouvrir le navigateur en plein écran
    public static void maximizeWindow() {
        if (driver != null) {
            driver.manage().window().maximize();
        }
    }

    // Configuration du navigateur Chrome
    public static void confChrome() {

        ChromeOptions options = new ChromeOptions();

        // Mode headless (important pour Jenkins / CI)
        options.addArguments("--headless=new");

        // Options pour Linux / Jenkins
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");

        // Taille de la fenêtre en mode headless
        options.addArguments("--window-size=1920,1080");

        // Initialisation du driver Chrome
        driver = new ChromeDriver(options);

        // Attente globale pour trouver les éléments
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // Modifier le temps d'attente
    public static void attente(int seconds) {
        if (driver != null) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
        }
    }
}
