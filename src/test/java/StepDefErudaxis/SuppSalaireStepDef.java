package StepDefErudaxis;

import org.junit.Assert;

import Helper.Config;
import Pages.SupprimerSalaire;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SuppSalaireStepDef {

	SupprimerSalaire suppressionPage = new SupprimerSalaire();

    @When("utilisateur parcourt la liste et clique sur supprimer du salaire {string}")
    public void utilisateur_parcourt_et_clique_supprimer(String nom) {
        suppressionPage.cliquerSupprimerSalaire(nom);
    }

    @Then("une popup de confirmation s affiche avec le message {string}")
    public void popup_confirmation_affichee(String message) {
        Assert.assertTrue(
            "Popup non affichée",
            suppressionPage.popupConfirmationAffichee(message)
        );
    }

    @When("utilisateur confirme la suppression")
    public void utilisateur_confirme_suppression() {
        suppressionPage.confirmerSuppression();
    }

    @Then("le salaire de {string} n existe plus dans la liste")
    public void salaire_nexiste_plus(String nom) {
        Assert.assertTrue(
            "Le salaire existe encore : " + nom,
            suppressionPage.salaireExistePas(nom)
        );
        Config.driver.quit();
    }
    
 // ✅ NOUVEAU — Annuler la suppression
    @When("utilisateur annule la suppression")
    public void utilisateur_annule_la_suppression(String nom) {
        suppressionPage.CliquerAnnulerSuppression(nom);
    }

    // ✅ NOUVEAU — Vérifier que le salaire existe toujours
    @Then("le salaire de {string} existe toujours dans la liste")
    public void salaire_existe_toujours(String nom) {
        Assert.assertTrue(
            "Le salaire de " + nom + " devrait exister encore",
            suppressionPage.salaireExiste(nom)
        );
    }
    
    
}