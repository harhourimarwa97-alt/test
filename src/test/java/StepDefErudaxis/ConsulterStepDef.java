package StepDefErudaxis;

import static org.junit.Assert.assertTrue;

import Helper.Config;
import Pages.Consultation;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ConsulterStepDef {

    Consultation page;

    @When("est accede a la page de liste des salaires")
    public void est_accede_a_la_page_de_liste_des_salaires() {
       
        Config.driver.get("https://staging.erudaxis.com/administration/finance/gestionCharge/salaires");
        page = new Consultation();
    }

    @When("il clique sur l'icone de detail d'un salaire {string}")
    public void il_clique_sur_l_icone_de_detail_d_un_salaire(String nom) {
        page.consulter(nom);
    }

    @Then("le detail du salaire est affiche")
    public void le_detail_du_salaire_est_affiche() {
    	Config.attente(10);
        assertTrue("❌ Le detail du salaire n'est pas affiché", page.verificationConsultation());
    }
}