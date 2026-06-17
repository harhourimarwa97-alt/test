package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Helper.Config;

public class SalairePage {

    @FindBy(xpath = "//button[text()='+Ajouter un Salaire']")
    WebElement btnAjouter;

    @FindBy(xpath = "//input[@placeholder='Nom & Prénom']")
    WebElement inputNom;

    @FindBy(xpath = "(//*[@data-testid='ArrowDropDownIcon']/..)[2]")
    WebElement btnArrowStatut;

    private static final By XPATH_OPTIONS_STATUT =
        By.xpath("//ul[@role='listbox']/li");

    @FindBy(xpath = "//input[@name='dateEmbauche']")
    WebElement Date;

    @FindBy(xpath = "//input[@placeholder='Salaire brute']")
    WebElement Salaire;

    @FindBy(xpath = "//input[@placeholder='en DT']")
    WebElement Frais;

    @FindBy(xpath = "//button[text()='Ajouter']")
    WebElement btnAjouterPopup;

    private static final By XPATH_OPTIONS_NOM =
        By.xpath("//ul[@role='listbox']//li[contains(@class,'MuiAutocomplete-option')]");

    // ✅ CORRECTION : locator du message de succès
    private static final By SUCCESS_MSG =
        By.xpath("//*[contains(.,'succès') or contains(.,'ajouté') or contains(.,'success')]");

    private WebDriverWait wait;
    private Actions actions;

    public SalairePage() {
        PageFactory.initElements(Config.driver, this);
        wait = new WebDriverWait(Config.driver, Duration.ofSeconds(15));
        actions = new Actions(Config.driver);
    }

    public void cliquerbtnAjouter() {
        wait.until(ExpectedConditions.elementToBeClickable(btnAjouter));
        actions.moveToElement(btnAjouter).click().perform();
    }

    public void choisirNom(String nom) {
        wait.until(ExpectedConditions.elementToBeClickable(inputNom));

        inputNom.click();
        inputNom.clear();
        inputNom.sendKeys(nom);

        wait.until(ExpectedConditions.attributeToBe(inputNom, "aria-expanded", "true"));

        List<WebElement> options = wait.until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(XPATH_OPTIONS_NOM)
        );

        for (WebElement option : options) {
            String optionText = option.getText().trim()
                .replaceAll("[\\n\\r]+", " ")
                .replaceAll("\\s+", " ");

            String nomCherche = nom.trim().replaceAll("\\s+", " ");

            if (optionText.equalsIgnoreCase(nomCherche)) {
                option.click();
                return;
            }
        }

        throw new RuntimeException("Nom non trouvé : " + nom);
    }

    public void choisirStatut(String statut) {
        wait.until(ExpectedConditions.elementToBeClickable(btnArrowStatut));
        btnArrowStatut.click();

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(XPATH_OPTIONS_STATUT, 0));

        List<WebElement> options = Config.driver.findElements(XPATH_OPTIONS_STATUT);

        for (WebElement option : options) {
            String optionText = option.getText().trim().replaceAll("\\s+", " ");
            String statutCherche = statut.trim().replaceAll("\\s+", " ");

            if (optionText.equalsIgnoreCase(statutCherche)) {
                option.click();
                return;
            }
        }

        if (!options.isEmpty()) {
            options.get(0).click();
            return;
        }

        throw new RuntimeException("Statut non trouvé : " + statut);
    }

    public void saisirDate(String date) {
        wait.until(ExpectedConditions.visibilityOf(Date));
        Date.sendKeys(date);
    }

    public void saisirSalaire(String salaire) {
        wait.until(ExpectedConditions.visibilityOf(Salaire));
        Salaire.sendKeys(salaire);
    }

    public void saisirFrais(String frais) {
        wait.until(ExpectedConditions.visibilityOf(Frais));
        Frais.sendKeys(frais);
    }

    public void cliquerbtnAjouterPopup() {
        wait.until(ExpectedConditions.elementToBeClickable(btnAjouterPopup));
        actions.moveToElement(btnAjouterPopup).click().perform();
    }

    // ❌ ANCIENNE VERSION (ne garantit pas le succès métier)
    /*
    public boolean salaireAjouteAvecSucces() {
        wait.until(ExpectedConditions.invisibilityOf(btnAjouterPopup));
        return true;
    }
    */

    // ✅ VERSION CORRIGÉE : attend un vrai message de succès dans l'UI
    public boolean salaireAjouteAvecSucces() {
        WebElement msg = wait.until(
            ExpectedConditions.visibilityOfElementLocated(SUCCESS_MSG)
        );
        return msg.isDisplayed();
    }
}
