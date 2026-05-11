package Pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Helper.Config;

public class HomePage {

    // ===================== LES XPATH =====================

    @FindBy(xpath = "/html/body/div/div[1]/div/ul/a/li/div/div[2]/span")
    List<WebElement> menus;

    @FindBy(xpath = "/html/body/div/div[1]/div/ul/a[3]/div/div/div/ul/li/div/div/div/span")
    List<WebElement> submenus;

    @FindBy(xpath = "/html/body/div/div[1]/div/ul/a[3]/div/div/div/ul[1]/div/div/div/ul/li/div/div/div/span")
    List<WebElement> subsubmenus;

    @FindBy(xpath = "/html/body/div/div[1]/div/ul/a[3]/div/div/div/ul[1]/div/div/div/ul[1]/div/div/div/a/li/div/div/div/span")
    List<WebElement> subsubsubmenus;

    @FindBy(tagName = "h5")
    WebElement title;

    // ===================== CONSTRUCTEUR =====================

    private WebDriverWait wait;
    private Actions actions;

    public HomePage() {
        PageFactory.initElements(Config.driver, this);
        wait = new WebDriverWait(Config.driver, Duration.ofSeconds(15));
        actions = new Actions(Config.driver);
    }

    // ===================== METHODES =====================

    private void clickInList(List<WebElement> elements, String name) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));

        for (WebElement el : elements) {
            if (el.getText().trim().equals(name)) {
                // ✅ Actions : déplace la souris vers l'élément puis clique
                actions.moveToElement(el).click().perform();
                return;
            }
        }
        throw new RuntimeException("Élément non trouvé : " + name);
    }

    public void clickOnMenu(String menuName) {
        clickInList(menus, menuName);
    }

    public void clickOnSubMenu(String submenuName) {
        clickInList(submenus, submenuName);
    }

    public void clickOnSubSubMenu(String subsubmenuName) {
        clickInList(subsubmenus, subsubmenuName);
    }

    public void clickOnSubSubSubMenu(String subsubsubmenuName) {
        clickInList(subsubsubmenus, subsubsubmenuName);
    }

    public String getPageTitle() {
        wait.until(ExpectedConditions.visibilityOf(title));
        return title.getText().trim();
    }
}