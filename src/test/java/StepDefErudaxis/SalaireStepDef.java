package StepDefErudaxis;

import org.junit.Assert;
import Helper.Config;
import Pages.SalairePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SalaireStepDef {

    // ✅ CORRECTION : une seule instance de Page Object
    // Avant : tu recréais SalairePage plusieurs fois (instable)
    // Maintenant : une seule instance pour tout le scénario
    SalairePage salairePage = new SalairePage();

    @When("utilisateur clique sur le bouton {string}")
    public void utilisateur_clique_sur_le_bouton(String bouton) {

        if (bouton.equalsIgnoreCase("Ajouter un salaire")) {

            // action pour ouvrir le formulaire salaire
            salairePage.cliquerbtnAjouter();
        }

        else if (bouton.equalsIgnoreCase("Ajouter")) {

            // action pour valider le formulaire (popup)
            salairePage.cliquerbtnAjouterPopup();
        }
    }

    @And("utilisateur choisit le nom {string}")
    public void utilisateur_choisit_le_nom(String nom) {

        // sélection du nom dans l'autocomplete
        salairePage.choisirNom(nom);

        // ❌ CORRECTION IMPORTANTE :
        // SUPPRESSION de "salairePage = new SalairePage();"
        // Pourquoi ?
        // → ça cassait le contexte du formulaire ouvert
        // → pouvait provoquer des erreurs Selenium (stale element)
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

    @Then("le salaire est ajouté avec succès")
    public void le_salaire_est_ajoute_avec_succes() {

        // assertion basée sur le message de succès (toast / notification UI)
        Assert.assertTrue(salairePage.salaireAjouteAvecSucces());

        // fermeture du navigateur après exécution du test
        Config.driver.quit();
    }
}
