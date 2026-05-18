package StepDefErudaxis;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Helper.Config;
import Pages.LoginPage;
import io.cucumber.java.en.Given;

public class CommunStepDef {

    @Given("utilisateur est sur la page d'accueil")
    public void utilisateur_est_sur_la_page_d_accueil() {
        Config.driver = new ChromeDriver();
        Config.maximizewindow();
        Config.driver.get("https://staging.erudaxis.com/login");
    }

    @Given("utilisateur est connecté avec {string} et {string}")
    public void utilisateur_est_connecte(String email, String password) {
    	
        LoginPage loginPage = new LoginPage();
        loginPage.connect(email, password);
        
    }
    
    
    /*
    @Given("utilisateur choisit l option  {string}")
    public void utilisateur_choisit_l_option(String option) {

        LoginPage loginPage = new LoginPage();

        // Cliquer sur l'option
        loginPage.cliqueroption(option);

        // Wait intelligent
        WebDriverWait wait = new WebDriverWait(Config.driver, Duration.ofSeconds(30));

        // Attendre que le menu Administration soit visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//*[contains(text(),'Administration')]")
        ));

        // Debug utile
        System.out.println("✅ Redirection réussie");
        System.out.println("URL actuelle : " + Config.driver.getCurrentUrl());
    }
    
    */
    
    @Given("utilisateur choisit l option  {string}")
    public void utilisateur_choisit_l_option(String option) {

        LoginPage loginPage = new LoginPage();

        // Cliquer sur l'option
        loginPage.cliqueroption(option);

        // Wait intelligent
        WebDriverWait wait = new WebDriverWait(Config.driver, Duration.ofSeconds(30));

        // Attendre qu'un élément du dashboard apparaisse
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//*[contains(text(),'Administration')]")
        ));

        System.out.println("✅ Option sélectionnée : " + option);
        System.out.println("✅ URL actuelle : " + Config.driver.getCurrentUrl());
    }
  /*
 
    // ✅  utilisateur choisit l'option
    
    @Given("utilisateur choisit l option  {string}")
    public void utilisateur_choisit_l_option(String option) {
    	 LoginPage loginPage = new LoginPage();
         loginPage.cliqueroption(option);
      
       
     // ✅ Attente de redirection vers le dashboard
        
            WebDriverWait wait = new WebDriverWait(Config.driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.urlContains("dashboards"));

            // ✅ Pause supplémentaire pour que les menus se chargent
            try { Thread.sleep(5000); } catch (Exception e) {}
        }
        
       */ 
    }
