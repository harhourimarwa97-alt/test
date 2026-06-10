package StepDefErudaxis;

import org.junit.Assert;
import Helper.Config;
import Pages.SalairePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SalaireStepDef {

    
    SalairePage salairePage = null;

    @When("utilisateur clique sur le bouton {string}")
    public void utilisateur_clique_sur_le_bouton(String bouton) {
        if (bouton.equalsIgnoreCase("Ajouter un salaire")) {
            // ✅ Crée une première instance pour cliquer sur le bouton
            salairePage = new SalairePage();
            salairePage.cliquerbtnAjouter();
        
        } else if (bouton.equalsIgnoreCase("Ajouter")) {
            salairePage.cliquerbtnAjouterPopup();
        }
    }

    @And("utilisateur choisit le nom {string}")
    public void utilisateur_choisit_le_nom(String nom) {
        salairePage.choisirNom(nom);
     // ✅ Réinitialise après que les champs apparaissent
        salairePage = new SalairePage();
    }

    @And("utilisateur choisit le statut {string}")
    public void utilisateur_choisit_le_statut(String statut) {
        salairePage.choisirStatut(statut);
    }

    @And("utilisateur saisit la date {string}")
    public void utilisateur_saisit_la_date(String date) {
        salairePage.saisirDate(date);
    }

    @And("utilisateur saisit le salaire {string}")
    public void utilisateur_saisit_le_salaire(String salaire) {
        salairePage.saisirSalaire(salaire);
    }

    @And("utilisateur saisit les frais {string}")
    public void utilisateur_saisit_les_frais(String frais) {
        salairePage.saisirFrais(frais);
    }

    // @Then("le salaire est ajouté avec succès")
    // public void le_salaire_est_ajoute_avec_succes() {
    //     Assert.assertTrue(salairePage.salaireAjouteAvecSucces());
    //     Config.driver.quit();
    // }
    @Then("le salaire est ajouté avec succès")
    public void le_salaire_est_ajoute_avec_succes() {

      boolean success =
            Config.driver.getPageSource().contains("Salaire ajouté")
            || Config.driver.getPageSource().contains("Succès Salaire ajouté");

      Assert.assertTrue("Message de succès non affiché", success);
 
      Config.driver.quit();
       }
}
