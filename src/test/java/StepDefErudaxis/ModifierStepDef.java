package StepDefErudaxis;

import static org.junit.Assert.assertTrue;

import Pages.Modifier;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ModifierStepDef {

    Modifier modifierPage = new Modifier();

    String currentNom;
    String currentType;
    String currentStatut;

  
    @Given("un salaire de nom {string} de type {string} et de statut {string} est visible dans la liste")
    public void un_salaire_de_nom_de_type_et_de_statut_est_visible_dans_la_liste(
            String nom, String type, String statut) {

        currentNom = nom;
        currentType = type;
        currentStatut = statut;

        boolean trouve = modifierPage.chercherLigne(type, statut);

        assertTrue(
            "❌ Aucun salaire trouvé avec TYPE='" + type + "' et STATUT='" + statut + "'",
            trouve
        );
    }

  

    @When("l'utilisateur clique sur l'icône de modification de ce salaire")
    public void l_utilisateur_clique_sur_l_icône_de_modification_de_ce_salaire() {

        modifierPage.cliquerIconeModification(currentType, currentStatut);
    }

    @When("l'utilisateur efface le montant actuel et saisit {string}")
    public void l_utilisateur_efface_le_montant_actuel_et_saisit(String montant) {

        modifierPage.saisirNouveauMontant(montant);
    }

    @When("l'utilisateur clique sur le bouton {string}")
    public void l_utilisateur_clique_sur_le_bouton(String bouton) {

        modifierPage.soumettreFormulaire();
    }


    @Then("un message de succès {string} est affiché")
    public void un_message_de_succès_est_affiché(String message) {

        assertTrue(
            "❌ Message de succès non affiché : " + message,
            modifierPage.messageSuccesAffiche()
        );
    }
    
    @When("l'utilisateur ferme le popup de succès")
    public void utilisateur_ferme_popup_succes() {
        modifierPage.fermerPopupSucces();
    }    
/*
   @Then("la liste affiche le montant {string} pour le salaire {string} de type {string} et de statut {string}")
    public void la_liste_affiche_le_montant_pour_le_salaire_de_type_et_de_statut(
            String montant, String nom, String type, String statut) {

        boolean ok = modifierPage.verifierMontantMisAJour(type, statut, montant);

        assertTrue(
            "❌ Montant non mis à jour pour TYPE='" + type + "' | STATUT='" + statut + "'",
            ok
        );
    }
   
   */
}