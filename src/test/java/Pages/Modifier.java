package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Helper.Config;

public class Modifier {

    // =========================================================
    //   XPATH — tous les sélecteurs regroupés ici
    // =========================================================

    @FindBy(xpath = "//tbody[contains(@class,'MuiTableBody-root')]//tr[contains(@class,'MuiTableRow-root')]")
    List<WebElement> lignes;

    @FindBy(xpath = "//tbody[contains(@class,'MuiTableBody-root')]//tr[contains(@class,'MuiTableRow-root')]//button[@aria-label='Modifier']")
    List<WebElement> boutonsModifier;

    @FindBy(xpath = "//div[contains(@class,'MuiDialog-paper')]")
    WebElement popUpModifier;

    @FindBy(xpath = "//div[contains(@class,'MuiDialog-paper')]//*[contains(normalize-space(),'Modifier le salaire')]")
    WebElement titrepopUpModifier;
    
    @FindBy(xpath="//div//div//div[@class='MuiBox-root css-13ktxfw']")
    WebElement champNomETPrenom;
    
    /*
    @FindBy(xpath = "//div[contains(@class,'MuiDialog-paper')]//select[contains(@name,'nom')] | //div[contains(@class,'MuiDialog-paper')]//*[contains(@id,'nom')]")
    WebElement champNom;*/

    @FindBy(xpath = "//div[contains(@class,'MuiDialog-paper')]//select[contains(@name,'statut')] | //div[contains(@class,'MuiDialog-paper')]//*[contains(@id,'statut')]")
    WebElement champStatut;

    @FindBy(xpath = "//div[contains(@class,'MuiDialog-paper')]//select[contains(@name,'type')] | //div[contains(@class,'MuiDialog-paper')]//*[contains(@id,'type')]")
    WebElement champType;

    @FindBy(xpath = "//div[contains(@class,'MuiDialog-paper')]//input[@type='date']")
    WebElement champDate;

    @FindBy(xpath = "//div[contains(@class,'MuiDialog-paper')]//*[contains(@name,'salaire') or contains(@id,'salaire')]")
    WebElement champSalaire;

    @FindBy(xpath = "//div[contains(@class,'MuiDialog-paper')]//*[contains(@name,'frais') or contains(@id,'frais')]")
    WebElement champFrais;

    @FindBy(xpath = "//div[contains(@class,'MuiDialog-paper')]//button[contains(normalize-space(),'ANNULER') or contains(normalize-space(),'Annuler')]")
    WebElement boutonAnnuler;

    @FindBy(xpath = "//div[contains(@class,'MuiDialog-paper')]//button[contains(normalize-space(),'MODIFIER') or contains(normalize-space(),'Modifier')]")
    WebElement boutonModifier;

  
    public Modifier() {
        PageFactory.initElements(Config.driver, this);
    }

    /**
     * Parcourt les lignes du tableau et cherche la première ligne
     * dont TYPE = {type} ET STATUT = {statut}.
     * Utilisé par le step : "je cherche une ligne avec le type "<type>" et le statut "<statut>""
     */
    public boolean chercherLigne(String type, String statut) {

        Config.attente(5);

        // Récupérer toutes les lignes du tableau
        List<WebElement> toutesLesLignes = Config.driver.findElements(
            By.xpath("//tbody[contains(@class,'MuiTableBody-root')]//tr[contains(@class,'MuiTableRow-root')]")
        );

        // Parcourir chaque ligne
        for (int i = 0; i < toutesLesLignes.size(); i++) {

            WebElement ligne = toutesLesLignes.get(i);

            // Lire la colonne TYPE (col 2) et STATUT (col 3)
            String typeCell   = ligne.findElement(By.xpath("./td[2]")).getText().trim();
            String statutCell = ligne.findElement(By.xpath("./td[3]")).getText().trim();

            System.out.println("Ligne " + i + " → TYPE: '" + typeCell + "' | STATUT: '" + statutCell + "'");

            // Vérifier si la ligne correspond aux paramètres du Scenario Outline
            if (typeCell.equalsIgnoreCase(type) && statutCell.equalsIgnoreCase(statut)) {

                System.out.println("✅ Ligne trouvée à l'index " + i
                    + " → TYPE='" + type + "' | STATUT='" + statut + "'");

                return true; // ligne trouvée
            }
        }

        System.out.println("❌ Aucune ligne avec TYPE='" + type + "' et STATUT='" + statut + "' trouvée.");
        return false; // ligne non trouvée
    }

   
    public boolean iconeModificationVisible(String type, String statut) {

        Config.attente(3);

        List<WebElement> toutesLesLignes = Config.driver.findElements(
            By.xpath("//tbody[contains(@class,'MuiTableBody-root')]//tr[contains(@class,'MuiTableRow-root')]")
        );

        List<WebElement> boutons = Config.driver.findElements(
            By.xpath("//tbody[contains(@class,'MuiTableBody-root')]//tr[contains(@class,'MuiTableRow-root')]//button[@aria-label='Modifier']")
        );

        for (int i = 0; i < toutesLesLignes.size(); i++) {

            WebElement ligne      = toutesLesLignes.get(i);
            String typeCell       = ligne.findElement(By.xpath("./td[2]")).getText().trim();
            String statutCell     = ligne.findElement(By.xpath("./td[3]")).getText().trim();

            if (typeCell.equalsIgnoreCase(type) && statutCell.equalsIgnoreCase(statut)) {

                return boutons.get(i).isDisplayed();
            }
        }

        return false;
    }

   
     // Clique icône de modification de ligne  avec TYPE = {type} ET STATUT = {statut}.
    
    public void cliquerIconeModification(String type, String statut) {

        Config.attente(3);

        List<WebElement> toutesLesLignes = Config.driver.findElements(
            By.xpath("//tbody[contains(@class,'MuiTableBody-root')]//tr[contains(@class,'MuiTableRow-root')]")
        );

        List<WebElement> boutons = Config.driver.findElements(
            By.xpath("//tbody[contains(@class,'MuiTableBody-root')]//tr[contains(@class,'MuiTableRow-root')]//button[@aria-label='Modifier']")
        );

        for (int i = 0; i < toutesLesLignes.size(); i++) {

            WebElement ligne  = toutesLesLignes.get(i);
            String typeCell   = ligne.findElement(By.xpath("./td[2]")).getText().trim();
            String statutCell = ligne.findElement(By.xpath("./td[3]")).getText().trim();

            if (typeCell.equalsIgnoreCase(type) && statutCell.equalsIgnoreCase(statut)) {

                boutons.get(i).click();

                System.out.println("✅ Cliqué sur l'icône de modification → TYPE='"
                    + type + "' | STATUT='" + statut + "'");
                return;
            }
        }

        throw new RuntimeException(
            "❌ Impossible de cliquer : aucune ligne avec TYPE='"
            + type + "' et STATUT='" + statut + "' trouvée."
        );
    }

  
    public boolean verifier() {

        Config.attente(5);

        return titrepopUpModifier.isDisplayed()
            && champNomETPrenom.isDisplayed()
            && champStatut.isDisplayed()
            && champType.isDisplayed()
            && champDate.isDisplayed()
            && champSalaire.isDisplayed()
            && champFrais.isDisplayed()
            && boutonAnnuler.isDisplayed()
            && boutonModifier.isDisplayed();
    }
}