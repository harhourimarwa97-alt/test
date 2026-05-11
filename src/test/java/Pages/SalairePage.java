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
 
 // 1. Bouton flèche → pour OUVRIR la liste
    @FindBy(xpath = "(//*[@data-testid='ArrowDropDownIcon']/..)[2]")
    WebElement btnArrowStatut;

    // 2. Options → pour TROUVER les options après ouverture
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

    
 /*   private static final By XPATH_OPTIONS_NOM =
    	    By.xpath("//ul[@role='listbox']/li[@role='option']");
    
    */
    
    private static final By XPATH_OPTIONS_NOM =
    	    By.xpath("//ul[@role='listbox']//li[contains(@class,'MuiAutocomplete-option')]");
    

  
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
        
        // 1. Cliquer pour focus
        inputNom.click();
        
        // 2. Vider et taper le nom
        inputNom.clear();
        inputNom.sendKeys(nom);

        // 3. Attendre que la dropdown soit VRAIMENT ouverte
        wait.until(ExpectedConditions.attributeToBe(inputNom, "aria-expanded", "true"));

        // 4. Chercher les options
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
        //  Clique sur le bouton flèche pour ouvrir la liste
        wait.until(ExpectedConditions.elementToBeClickable(btnArrowStatut));
        btnArrowStatut.click();
        
        
        
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(XPATH_OPTIONS_STATUT, 0));

        //  Cherche les options
        List<WebElement> options = Config.driver.findElements(XPATH_OPTIONS_STATUT);

        System.out.println("=== STATUT OPTIONS ===");
        System.out.println("Nombre : " + options.size());
        for (WebElement o : options) {
            System.out.println("Option : '" + o.getText().trim() + "'");
        }

        // Clique sur le bon statut
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
    	
    	WebDriverWait wait = new WebDriverWait(Config.driver, Duration.ofSeconds(15));
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

    public boolean salaireAjouteAvecSucces() {
        wait.until(ExpectedConditions.invisibilityOf(btnAjouterPopup));
        return true;
    }
}