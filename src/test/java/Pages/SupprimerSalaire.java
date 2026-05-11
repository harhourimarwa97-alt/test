package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Helper.Config;

public class SupprimerSalaire {

    WebDriverWait wait;

    // ✅ Lignes du tableau
    private static final By XPATH_LIGNES =
        By.xpath("//tbody[@class='MuiTableBody-root css-1xnox0e']//tr");

    // ✅ Bouton 1 — "Oui, supprimer"
    @FindBy(xpath = "//button[@class='swal2-confirm button button-success']")
    WebElement boutonOuiSupprimer;

    // ✅ Bouton 2 — "OK"
    @FindBy(xpath = "//button[@class='swal2-confirm swal2-styled']")
    WebElement boutonOk;
    
    //bouton annuler
    @FindBy(xpath = "//button[@class='swal2-cancel button button-error']")
    WebElement boutonAnnuler;
    

    public SupprimerSalaire() {
        PageFactory.initElements(Config.driver, this);
        wait = new WebDriverWait(Config.driver, Duration.ofSeconds(15));
    }

    // ✅ Méthode 1 : Parcourir la liste et cliquer supprimer
    public void cliquerSupprimerSalaire(String nom) {

        List<WebElement> lignes = Config.driver.findElements(XPATH_LIGNES);
        System.out.println("=== LIGNES : " + lignes.size() + " ===");

        for (WebElement ligne : lignes) {
            String nomLigne = ligne.findElement(
                By.xpath(".//td[1]//div")
            ).getText().trim();

            System.out.println("Nom : '" + nomLigne + "'");

            if (nomLigne.equalsIgnoreCase(nom)) {
                WebElement boutonSupprimer = ligne.findElement(
                    By.xpath(".//button[@aria-label='Supprimer']")
                );
                boutonSupprimer.click();
                System.out.println("✅ Suppression cliquée pour : " + nom);
                return;
            }
        }

        throw new RuntimeException("❌ Nom non trouvé : " + nom);
    }

    // ✅ Méthode 2 : Vérifier la popup de confirmation
    public boolean popupConfirmationAffichee(String message) {
        WebElement popup = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'" + message + "')]")
            )
        );
        System.out.println("✅ Popup affichée : " + message);
        return popup.isDisplayed();
    }

    // ✅ Méthode 3 : Cliquer "Oui, supprimer"
    public void confirmerSuppression() {
        wait.until(ExpectedConditions.elementToBeClickable(boutonOuiSupprimer));
        boutonOuiSupprimer.click();
        System.out.println("✅ Oui supprimer cliqué !");
        try { Thread.sleep(1000); } catch (Exception e) {}
    }

    // ✅ Méthode 4 : Cliquer "OK" popup succès
    public void clickerOkConfirmation() {
        wait.until(ExpectedConditions.elementToBeClickable(boutonOk));
        boutonOk.click();
        System.out.println("✅ OK cliqué !");
        try { Thread.sleep(1500); } catch (Exception e) {}
        clickerOkConfirmation();
    }

    // ✅ Méthode 5 : Vérifier que le salaire n'existe plus
    public boolean salaireExistePas(String nom) {
        try { Thread.sleep(1500); } catch (Exception e) {}

        List<WebElement> lignes = Config.driver.findElements(XPATH_LIGNES);
        System.out.println("=== VÉRIFICATION APRÈS SUPPRESSION ===");
        System.out.println("Nombre de lignes : " + lignes.size());

        for (WebElement ligne : lignes) {
            String nomLigne = ligne.findElement(
                By.xpath(".//td[1]//div")
            ).getText().trim();

            System.out.println("Ligne : '" + nomLigne + "'");

            if (nomLigne.equalsIgnoreCase(nom)) {
                System.out.println("❌ Existe encore : " + nom);
                return false;
            }
        }

        System.out.println("✅ Plus dans la liste !");
        return true;
    }
    public boolean salaireExiste(String nom) {
        return !Config.driver.findElements(
            By.xpath("//td[contains(text(),'" + nom + "')]")
        ).isEmpty();
    }
    
    public void CliquerAnnulerSuppression( String nom) {
		// Méthode pour annuler la suppression (si besoin)
    	
    	List<WebElement> lignes = Config.driver.findElements(XPATH_LIGNES);
		for (WebElement ligne : lignes) {
			String nomLigne = ligne.findElement(
				By.xpath(".//td[1]//div")
			).getText().trim();

			if (nomLigne.equalsIgnoreCase(nom)) {
				WebElement boutonSupprimer = ligne.findElement(
					By.xpath(".//button[@aria-label='Supprimer']")
				);
				boutonSupprimer.click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[contains(text(),'Vous ne pourrez pas revenir en arrière !')]")
				));
				WebElement boutonAnnuler = Config.driver.findElement(
					By.xpath("//button[@class='swal2-cancel button button-error']")
				);
				boutonAnnuler.click();
				System.out.println("✅ Suppression annulée pour : " + nom);
				return;
			}
		}

		throw new RuntimeException("❌ Nom non trouvé pour annulation : " + nom);
	}
    
}