package StepDefErudaxis;

import static org.junit.Assert.assertTrue;

import Pages.RechercheSalaire;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RechercherSalaireStepDef {
	RechercheSalaire rechercheSalaire = new RechercheSalaire();
	
	
	@When("utilisateur saisit {string} dans le champ recherche")
	public void utilisateur_saisit_dans_le_champ_recherche(String nom) {
		rechercheSalaire.rechercherSalaire(nom);
	
	}
	
	// ✅ CORRECT — vérifie vraiment le résultat
	@Then("les résultats affichés contiennent {string}")
	public void les_résultats_affichés_contiennent(String nom) {
	    boolean trouve = rechercheSalaire.verifierSalaireExisteDansListe(nom);
	    assertTrue("Le nom '" + nom + "' n'a pas été trouvé dans la liste.", trouve);
	}
	/*@Then("les résultats affichés contiennent {string}")
	public void les_résultats_affichés_contiennent(String text) {
		
		rechercheSalaire.verifierSalaireExiste(text);   */
		
		
	    
	}


