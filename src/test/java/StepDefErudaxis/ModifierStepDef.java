package StepDefErudaxis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import Pages.Modifier;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ModifierStepDef {

    Modifier modifierPage = new Modifier();

    String currentNom;
    String currentType;
    String currentStatut;

    // ================= AJOUT SCENARIO 3 : bouton Annuler =================
    // Mémorise le montant avant modification pour comparer après Annuler
    String montantAvantModification;


    // ================= SCENARIO 1 : existant =================

    @Given("un salaire de nom {string} de type {string} et de statut {string} est visible dans la liste")
    public void un_salaire_de_nom_de_type_et_de_statut_est_visible_dans_la_liste(
            String nom, String type, String statut) {

        currentNom    = nom;
        currentType   = type;
        currentStatut = statut;

        // ================= AJOUT SCENARIO 3 =================
        // On mémorise le montant avant toute modification
        montantAvantModification = modifierPage.getMontantActuel(currentType, currentStatut);

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

        if (bouton.equals("Modifier")) {
            // Scénario 1 et 2
            modifierPage.soumettreFormulaire();

        } else if (bouton.equals("Annuler")) {
            // ================= AJOUT SCENARIO 3 =================
            modifierPage.annulerFormulaire();
        }
    }

    @Then("un message de succès {string} est affiché")
    public void un_message_de_succès_est_affiché(String message) {

        assertTrue(
            "❌ Message de succès non affiché : " + message,
            modifierPage.messageSuccesAffiche()
        );
    }

    @And("l'utilisateur ferme le popup de succès")
    public void utilisateur_ferme_popup_succes() {
        modifierPage.fermerPopupSucces();
    }


    // ================= AJOUT SCENARIO 2 : montant trop élevé =================

    @Then("un message d'erreur {string} est affiché")
    public void un_message_d_erreur_est_affiche(String message) {

        assertTrue(
            "❌ Message d'erreur non affiché : " + message,
            modifierPage.messageErreurAffiche()
        );
    }

    @And("l'utilisateur ferme le popup d'erreur")
    public void l_utilisateur_ferme_le_popup_d_erreur() {

        modifierPage.fermerPopupErreur();
    }


    // ================= AJOUT SCENARIO 3 : bouton Annuler =================

    @Then("le montant du salaire reste inchangé")
    public void le_montant_du_salaire_reste_inchange() {

        String montantApres = modifierPage.getMontantActuel(currentType, currentStatut);

        assertEquals(
            "❌ Le montant a changé alors qu'on a cliqué Annuler !",
            montantAvantModification,
            montantApres
        );
    }

    @And("l'utilisateur est redirigé vers la liste des salaires")
    public void l_utilisateur_est_redirige_vers_la_liste_des_salaires() {

        assertTrue(
            "❌ La liste des salaires n'est pas affichée",
            modifierPage.listeDesSalairesAffichee()
        );
    }
}