package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Helper.Config;

public class Modifier {

    // ================= TABLE =================

    @FindBy(xpath = "//table//tbody//tr")
    List<WebElement> lignesTableau;

    // ================= MODALE =================

    @FindBy(xpath = "//h2[contains(text(),'Modifier le salaire')]")
    WebElement titreModale;

    @FindBy(xpath = "//label[contains(text(),'salaire brute')]//following-sibling::div//input")
    WebElement champSalaire;

    @FindBy(xpath = "//button[normalize-space()='ANNULER']")
    WebElement boutonAnnuler;

    @FindBy(xpath = "//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeSmall MuiButton-containedSizeSmall MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeSmall MuiButton-containedSizeSmall css-1lclcs2']")
    WebElement boutonModifier;
    
    // //div[@id='swal2-html-container']
    // //*[contains(text(),'Salaire modifié')]
    
    @FindBy(xpath = "//div[contains(@role,'dialog')]//*[contains(text(),'Salaire modifié')]")
    WebElement messageSucces;
    
    @FindBy(xpath = "//button[@class='swal2-confirm swal2-styled']")
    WebElement boutonOk;

    public Modifier() {
        PageFactory.initElements(Config.driver, this);
    }

    // ================= UTIL =================

    private String normalize(String value) {
        return value == null ? "" :
                value.replace("\u00A0", " ")
                     .trim()
                     .toLowerCase();
    }

    private double parseAmount(String value) {
        return Double.parseDouble(value.replaceAll("[^0-9.]", ""));
    }

    private void waitTable() {
        WebDriverWait wait = new WebDriverWait(Config.driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(lignesTableau));
    }

    // ================= LOGIQUE TABLE =================

    public boolean chercherLigne(String type, String statut) {

        waitTable();

        for (WebElement ligne : lignesTableau) {

            String typeCell = ligne.findElement(By.xpath("./td[2]")).getText();
            String statutCell = ligne.findElement(By.xpath("./td[3]")).getText();

            if (normalize(typeCell).equals(normalize(type)) &&
                normalize(statutCell).equals(normalize(statut))) {

                System.out.println("✅ Ligne trouvée → TYPE=" + type + " | STATUT=" + statut);
                return true;
            }
        }

        System.out.println("❌ Ligne introuvable → TYPE=" + type + " | STATUT=" + statut);
        return false;
    }

    public void cliquerIconeModification(String type, String statut) {

        waitTable();

        for (WebElement ligne : lignesTableau) {

            String typeCell = ligne.findElement(By.xpath("./td[2]")).getText();
            String statutCell = ligne.findElement(By.xpath("./td[3]")).getText();

            if (normalize(typeCell).equals(normalize(type)) &&
                normalize(statutCell).equals(normalize(statut))) {

                ligne.findElement(By.xpath("./td[5]//button[1]")).click();

                System.out.println("✅ Icône modification cliquée");
                return;
            }
        }

        throw new RuntimeException("❌ Aucune ligne trouvée pour modification");
    }
    
    public void saisirNouveauMontant(String montant) {

        WebDriverWait wait = new WebDriverWait(Config.driver, Duration.ofSeconds(10));

        WebElement input = wait.until(
            ExpectedConditions.elementToBeClickable(champSalaire)
        );

        input.click();

        // Sélectionner tout le texte
        input.sendKeys(Keys.CONTROL + "a");

        // Supprimer ancienne valeur
        input.sendKeys(Keys.DELETE);

        // Saisir nouveau montant
        input.sendKeys(montant);

        System.out.println("✅ Nouveau montant saisi : " + montant);
    }

/*
    public void saisirNouveauMontant(String montant) {
        champSalaire.clear();
        champSalaire.sendKeys(montant);
        System.out.println("✅ Montant saisi : " + montant);
    }
*/
    public void soumettreFormulaire() {

        WebDriverWait wait = new WebDriverWait(Config.driver, Duration.ofSeconds(10));

        WebElement bouton = wait.until(
            ExpectedConditions.elementToBeClickable(boutonModifier)
        );

        bouton.click();

        System.out.println("✅ Bouton MODIFIER cliqué");
    } 
    /*
    public void soumettreFormulaire() {
    	Config.attente(10);
        boutonModifier.click();
        System.out.println("✅ Bouton MODIFIER cliqué");
    }
    */
    
    public boolean messageSuccesAffiche() {

        WebDriverWait wait = new WebDriverWait(Config.driver, Duration.ofSeconds(10));

        WebElement msg = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@role,'dialog')]//*[contains(text(),'Salaire modifié')]")
            )
        );

        return msg.isDisplayed();
    }

  /*  public boolean messageSuccesAffiche() {
        return messageSucces.isDisplayed();
    }
*/
    // ================= VERIFICATION =================
/*
    public boolean verifierMontantMisAJour(String type, String statut, String montantAttendu) {

        waitTable();

        for (WebElement ligne : lignesTableau) {

            String typeCell = ligne.findElement(By.xpath("./td[2]")).getText();
            String statutCell = ligne.findElement(By.xpath("./td[3]")).getText();

            if (normalize(typeCell).equals(normalize(type)) &&
                normalize(statutCell).equals(normalize(statut))) {

                String montantAffiche = ligne.findElement(By.xpath("./td[4]")).getText();

                double actual = parseAmount(montantAffiche);
                double expected = parseAmount(montantAttendu);

                System.out.println("Montant UI=" + actual + " | attendu=" + expected);

                return actual == expected;
            }
        }

        return false;
    }
*/
    // ================= MODALE CHECK =================

    public boolean verifierModaleOuverte() {
        return titreModale.isDisplayed()
            && champSalaire.isDisplayed()
            && boutonModifier.isDisplayed()
            && boutonAnnuler.isDisplayed();
    }
    
    public void fermerPopupSucces() {
        boutonOk.click();
        System.out.println("✅ Popup fermé (OK cliqué)");
    }
}