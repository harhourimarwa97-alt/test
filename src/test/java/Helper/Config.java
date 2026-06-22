package Helper;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Config {

    public static WebDriver driver;

    public static void maximizewindow() {
        driver.manage().window().maximize();
    }

    public static void confChrome() {

        ChromeOptions options = new ChromeOptions();

        // ✅ Mode Headless pour accélérer l'exécution sur Jenkins
        options.addArguments("--headless=new");

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");

        // ✅ Taille fixe de la fenêtre pour éviter les problèmes d'affichage
        options.addArguments("--window-size=1920,1080");

        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-software-rasterizer");

        // ✅ Désactivation des notifications Chrome
        options.addArguments("--disable-notifications");

        // ✅ Désactivation du blocage des popups
        options.addArguments("--disable-popup-blocking");

        // ✅ Désactivation des infobulles Chrome
        options.addArguments("--disable-infobars");

        // Création du driver avec toutes les options définies
        driver = new ChromeDriver(options);

        // ✅ Réduction de l'attente implicite de 10 s à 5 s pour améliorer les performances
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // ✅ Maximisation automatique de la fenêtre
        driver.manage().window().maximize();
    }

    // ⚠️ Il est préférable d'utiliser WebDriverWait au lieu de modifier
    // l'implicitWait plusieurs fois durant l'exécution
    public static void attente(int s) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(s));
    }
}
