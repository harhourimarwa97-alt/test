package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Helper.Config;

public class Consultation {

    @FindBy(xpath = "//tbody[contains(@class,'MuiTableBody-root')]//tr[contains(@class,'MuiTableRow-root')]")
    List<WebElement> lignes;

    @FindBy(xpath = "//h5[contains(@class,'MuiTypography-h5')]")
    WebElement verifConsultation;

    public Consultation() {
        PageFactory.initElements(Config.driver, this);
    }

    public void consulter(String nom) {

        Config.attente(5);

        while (true) {

            //  noms de la page courante
            List<WebElement> nameCells = Config.driver.findElements(
                By.xpath("//tbody[contains(@class,'MuiTableBody-root')]//tr[contains(@class,'MuiTableRow-root')]//td[1]//div[contains(@class,'css-16w71rb')]")
            );

            //  les boutons détail de la page courante
            List<WebElement> detailBtns = Config.driver.findElements(
                By.xpath("//tbody[contains(@class,'MuiTableBody-root')]//tr[contains(@class,'MuiTableRow-root')]//button[@aria-label='Voir Plus']")
            );

            //  Chercher le nom dans la page courante
            for (int i = 0; i < nameCells.size(); i++) {

                String cellText = nameCells.get(i).getAttribute("textContent").trim();

                System.out.println("Row " + i + ": '" + cellText + "'");

                if (cellText.equalsIgnoreCase(nom.trim())) {

                    System.out.println("✅ Found: '" + nom + "' at row " + i);

                    WebElement btn = detailBtns.get(i);

                    new Actions(Config.driver)
                        .moveToElement(btn)
                        .click()
                        .perform();

                    System.out.println("✅ Clicked 'Voir Plus' for: '" + nom + "'");

                    return;
                }
            }

            //  Chercher le bouton suivant de la pagination
            List<WebElement> nextBtns = Config.driver.findElements(
                By.xpath("//span[contains(@class,'MuiIcon-root') and text()='chevron_right']/parent::button")
            );

            //  Bouton absent → page unique
            if (nextBtns.isEmpty()) {

                throw new RuntimeException("❌ Ligne introuvable pour : " + nom);
            }

            WebElement nextBtn = nextBtns.get(0);

            // Bouton disabled → dernière page atteinte
            String isDisabled = nextBtn.getAttribute("disabled");

            if (isDisabled != null) {

                System.out.println("⛔ Bouton suivant désactivé — dernière page atteinte.");

                throw new RuntimeException("❌ Ligne introuvable pour : " + nom);
            }

            // ── Sauvegarder le premier nom avant pagination
            String firstRowBefore = nameCells.isEmpty()
                ? ""
                : nameCells.get(0).getAttribute("textContent").trim();

            //  Cliquer sur le bouton suivant
            ((JavascriptExecutor) Config.driver)
                .executeScript("arguments[0].click();", nextBtn);

            Config.attente(3);

            // Vérifier si la page a changé
            List<WebElement> rowsAfter = Config.driver.findElements(
                By.xpath("//tbody[contains(@class,'MuiTableBody-root')]//tr[contains(@class,'MuiTableRow-root')]//td[1]//div[contains(@class,'css-16w71rb')]")
            );

            String firstRowAfter = rowsAfter.isEmpty()
                ? ""
                : rowsAfter.get(0).getAttribute("textContent").trim();

            System.out.println("First row before: '" + firstRowBefore + "'");
            System.out.println("First row after:  '" + firstRowAfter + "'");

            System.out.println("➡️ Navigated to next page...");
        }
    }

    public boolean verificationConsultation() {

        Config.attente(10);

        return verifConsultation.isDisplayed();
    }
}