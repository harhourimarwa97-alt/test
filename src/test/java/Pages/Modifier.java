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

    @FindBy(xpath = "//table//tbody//tr")
    List<WebElement> lignesTableau;

    @FindBy(xpath = "//h2[contains(text(),'Modifier le salaire')]")
    WebElement titreModale;

    @FindBy(xpath = "//label[contains(text(),'salaire brute')]//following-sibling::div//input")
    WebElement champSalaire;

    @FindBy(xpath = "//button[normalize-space()='ANNULER']")
    WebElement boutonAnnuler;

    @FindBy(xpath = "//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeSmall MuiButton-containedSizeSmall MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeSmall MuiButton-containedSizeSmall css-1lclcs2']")
    WebElement boutonModifier;

    @FindBy(xpath = "//div[contains(@role,'dialog')]//*[contains(text(),'Salaire modifié')]")
    WebElement messageSucces;

    @FindBy(xpath = "//button[@class='swal2-confirm swal2-styled']")
    WebElement boutonOk;

    @FindBy(xpath="//button[@class='MuiButtonBase-root MuiButton-root MuiButton-outlined MuiButton-outlinedPrimary MuiButton-sizeSmall MuiButton-outlinedSizeSmall MuiButton-root MuiButton-outlined MuiButton-outlinedPrimary MuiButton-sizeSmall MuiButton-outlinedSizeSmall css-1d9alwg']")
    WebElement bouton_ANNULER;

    // =================  SCENARIO 2 : message erreur =================

    @FindBy(xpath = "//div[contains(@role,'dialog')]//*[contains(text(),'Montant trop élevé')]")
    WebElement messageErreur;

    @FindBy(xpath = "//button[@class='swal2-confirm swal2-styled']")
    WebElement boutonOkErreur;


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

            String typeCell   = ligne.findElement(By.xpath("./td[2]")).getText();
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

            String typeCell   = ligne.findElement(By.xpath("./td[2]")).getText();
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
        input.sendKeys(Keys.CONTROL + "a");
        input.sendKeys(Keys.DELETE);
        input.sendKeys(montant);

        System.out.println("✅ Nouveau montant saisi : " + montant);
    }

    public void soumettreFormulaire() {

        WebDriverWait wait = new WebDriverWait(Config.driver, Duration.ofSeconds(10));

        WebElement bouton = wait.until(
            ExpectedConditions.elementToBeClickable(boutonModifier)
        );

        bouton.click();

        System.out.println("✅ Bouton MODIFIER cliqué");
    }

    public void annulerFormulaire() {

        WebDriverWait wait = new WebDriverWait(Config.driver, Duration.ofSeconds(10));

        WebElement bouton = wait.until(ExpectedConditions.elementToBeClickable(bouton_ANNULER));
        bouton.click();

        System.out.println("✅ Bouton ANNULER cliqué");
    }

    public boolean messageSuccesAffiche() {

        WebDriverWait wait = new WebDriverWait(Config.driver, Duration.ofSeconds(10));

        WebElement msg = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@role,'dialog')]//*[contains(text(),'Salaire modifié')]")
            )
        );

        return msg.isDisplayed();
    }

    public void fermerPopupSucces() {
        boutonOk.click();
        System.out.println("✅ Popup succès fermé (OK cliqué)");
    }

    // ================= AJOUT SCENARIO 2 : montant trop élevé =================

    /**
     * Vérifie que le message "Montant trop élevé" est affiché.
     */
    public boolean messageErreurAffiche() {

        WebDriverWait wait = new WebDriverWait(Config.driver, Duration.ofSeconds(10));

        WebElement msg = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@role,'dialog')]//*[contains(text(),'Montant trop élevé')]")
            )
        );

        System.out.println("✅ Message d'erreur affiché : Montant trop élevé");
        return msg.isDisplayed();
    }

    /**
     * Ferme le popup d'erreur en cliquant sur OK.
     */
    public void fermerPopupErreur() {

        WebDriverWait wait = new WebDriverWait(Config.driver, Duration.ofSeconds(10));

        WebElement bouton = wait.until(
            ExpectedConditions.elementToBeClickable(boutonOkErreur)
        );
        bouton.click();

        System.out.println("✅ Popup erreur fermé (OK cliqué)");
    }

    // ================= AJOUT SCENARIO 3 : bouton Annuler =================

    /**
     * Récupère le montant actuel affiché dans la ligne selon type et statut.
     * Utilisé pour comparer avant/après dans le scénario Annuler.
     */
    public String getMontantActuel(String type, String statut) {

        waitTable();

        for (WebElement ligne : lignesTableau) {

            String typeCell   = ligne.findElement(By.xpath("./td[2]")).getText();
            String statutCell = ligne.findElement(By.xpath("./td[3]")).getText();

            if (normalize(typeCell).equals(normalize(type)) &&
                normalize(statutCell).equals(normalize(statut))) {

                String montant = ligne.findElement(By.xpath("./td[4]")).getText();
                System.out.println("✅ Montant actuel récupéré : " + montant);
                return montant.trim();
            }
        }

        System.out.println("❌ Montant introuvable pour TYPE=" + type + " | STATUT=" + statut);
        return "";
    }

    /**
     * Vérifie que la liste des salaires est bien affichée.
     * Utilisé après un clic sur Annuler pour confirmer le retour à la liste.
     */
    public boolean listeDesSalairesAffichee() {

        WebDriverWait wait = new WebDriverWait(Config.driver, Duration.ofSeconds(10));

        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(lignesTableau));
            System.out.println("✅ Liste des salaires affichée");
            return true;

        } catch (Exception e) {
            System.out.println("❌ Liste des salaires non affichée");
            return false;
        }
    }

    // ================= MODALE CHECK =================

    public boolean verifierModaleOuverte() {
        return titreModale.isDisplayed()
            && champSalaire.isDisplayed()
            && boutonModifier.isDisplayed()
            && boutonAnnuler.isDisplayed();
    }
}