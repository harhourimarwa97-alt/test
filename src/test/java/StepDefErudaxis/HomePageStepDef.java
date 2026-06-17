package StepDefErudaxis;

import org.junit.Assert;
import Helper.Config;
import Pages.HomePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomePageStepDef {

    // ✅ UNE SEULE INSTANCE HOME PAGE
    HomePage homePage = new HomePage();

    @When("utilisateur clique sur le menu {string}")
    public void utilisateur_clique_sur_le_menu(String menu) {
        homePage.clickOnMenu(menu);
    }

    @When("utilisateur clique sur le sous-menu {string}")
    public void utilisateur_clique_sur_le_sous_menu(String submenu) {
        homePage.clickOnSubMenu(submenu);
    }

    @When("utilisateur clique sur le sous-sous-menu {string}")
    public void utilisateur_clique_sur_le_sous_sous_menu(String subsubmenu) {
        homePage.clickOnSubSubMenu(subsubmenu);
    }

    @When("utilisateur clique sur le sous-sous-sous-menu {string}")
    public void utilisateur_clique_sur_le_sous_sous_sous_menu(String subsubsubmenu) {
        homePage.clickOnSubSubSubMenu(subsubsubmenu);
    }

    @Then("le titre de la page est {string}")
    public void le_titre_de_la_page_est(String title) {

        String actualTitle = homePage.getPageTitle();

        Assert.assertEquals(title, actualTitle);
    }
}
