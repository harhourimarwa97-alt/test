package StepDefErudaxis;

import org.openqa.selenium.chrome.ChromeDriver;

import Helper.Config;
import Helper.Utils;
import Pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageStepDef {
    @Given("utilisateur est sur la page de connexion")
    public void utilisateur_est_sur_la_page_de_connexion() throws Exception{
    	//Config.driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

Config.driver = new ChromeDriver(options);
        Config.driver.manage().window().maximize();

        Config.driver.get(Utils.getProperty("Erudaxis_link"));

    }
    @When("il saisit un email valide {string} et un mot de passe valide {string}")
    public void il_saisit_un_email_valide_et_un_mot_de_passe_valide(String username, String pws) {
    	LoginPage loginPage = new LoginPage();
    	Config.attente(10);
    	loginPage.connect(username, pws);
    }
    @When("il clique sur option {string}")
    public void il_clique_sur_option(String role) {
    	 LoginPage loginPage = new LoginPage();
        Config.attente(10);
        loginPage.cliqueroption(role);
    }
    @Then("il est redirigé vers le tableau de bord qui contient l option {string}")
    public void il_est_redirigé_vers_le_tableau_de_bord_qui_contient_l_option(String verif) throws  Exception {
    	 LoginPage loginPage = new LoginPage();
        Thread.sleep(5000);
        loginPage.verifierDashboard(verif);
        Config.driver.quit();
    }

}
