package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Helper.Config;

public class RechercheSalaire {

    @FindBy(xpath = "//input[@placeholder='Recherche...']")
    WebElement champRecherche;

    private static final By LIGNES_TABLEAU =
        By.xpath("//table//tbody/tr");

    private static final By BTN_SUIVANT =
        By.xpath("//li[contains(@class,'page-item')]/a[text()='>']");

    private WebDriverWait wait;

    public RechercheSalaire() {
        PageFactory.initElements(Config.driver, this);
        wait = new WebDriverWait(Config.driver, Duration.ofSeconds(15));
    }

    // ── Tape le texte ET attend que le tableau se rafraîchisse ──────
    public void rechercherSalaire(String texte) {
        wait.until(ExpectedConditions.visibilityOf(champRecherche));
        champRecherche.clear();
        champRecherche.sendKeys(texte);

        // Attendre que le tableau se mette à jour après la frappe
        try {
            wait.until(ExpectedConditions
                .refreshed(ExpectedConditions
                    .presenceOfAllElementsLocatedBy(LIGNES_TABLEAU)));
        } catch (Exception e) {
            // Si pas de résultats du tout → tableau peut disparaître
            try { Thread.sleep(1500); } catch (Exception ex) {}
        }
    }

    // ── Parcourt toutes les pages et cherche le nom ─────────────────
    public boolean verifierSalaireExisteDansListe(String nom) {

        int page = 1;

        while (page <= 10) {
            System.out.println("--- Page " + page + " ---");

            // Attendre le chargement des lignes
            try {
                wait.until(ExpectedConditions
                    .presenceOfAllElementsLocatedBy(LIGNES_TABLEAU));
            } catch (Exception e) {
                System.out.println("Aucun résultat sur la page " + page);
                return false;
            }

            List<WebElement> lignes =
                Config.driver.findElements(LIGNES_TABLEAU);

            // ── Si 0 lignes → nom inexistant ────────────────────────
            if (lignes.isEmpty()) {
                System.out.println("Tableau vide → nom introuvable.");
                return false;
            }

            for (WebElement ligne : lignes) {
                try {
                    String texte = ligne.getText().trim();
                    System.out.println("Ligne : " + texte);

                    if (texte.toLowerCase().contains(nom.toLowerCase())) {
                        System.out.println("✅ Trouvé page " + page);
                        return true;
                    }
                } catch (StaleElementReferenceException e) {
                    // La page a bougé entre temps → on relance la page
                    System.out.println("Élément périmé, on relance la page " + page);
                    break;
                }
            }

            // ── Chercher le bouton suivant ───────────────────────────
            List<WebElement> btnSuivant =
                Config.driver.findElements(BTN_SUIVANT);

            if (btnSuivant.isEmpty()) {
                System.out.println("Dernière page atteinte.");
                break;
            }

            String classBouton = btnSuivant.get(0)
                .findElement(By.xpath(".."))
                .getAttribute("class");

            if (classBouton != null && classBouton.contains("disabled")) {
                System.out.println("Bouton suivant désactivé → dernière page.");
                break;
            }

            btnSuivant.get(0).click();
            try { Thread.sleep(1000); } catch (Exception e) {}
            page++;
        }

        System.out.println("❌ Nom non trouvé dans la liste.");
        return false;
    }
}