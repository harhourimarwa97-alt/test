package Pages;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import Helper.Config;

public class LoginPage {

    WebDriverWait wait;

    @FindBy(id="sign-in-email-input")
    WebElement email;

    @FindBy(id="sign-in-password-input")
    WebElement password;

    @FindBy(id="sign-in-button")
    WebElement loginButton;
    
    @FindBy(xpath = "/html/body/div[2]/div[3]/div/div/div/div[2]/div/div/div/div/div/div/div[3]/div/h6")
    List <WebElement> options;

    @FindBy(xpath = "/html/body/div/div[1]/div/div[2]/div/div[2]/p")
    WebElement dashboardText;
    
    public LoginPage() {
        PageFactory.initElements(Config.driver, this);
        //wait = new WebDriverWait(Config.driver, Duration.ofSeconds(15));
    }

    public void connect(String user , String pass) {
       Config.attente(10);
      //  wait.until(ExpectedConditions.visibilityOf(email));
       // email.clear();
        email.sendKeys(user);

       // wait.until(ExpectedConditions.visibilityOf(password));
       // password.clear();
        password.sendKeys(pass);
        
        loginButton.click();
    }

  
    public void cliqueroption(String nomOption) {
    	Config.attente(10);
    	
    	for(WebElement option:options ) {
    		
    		if(option.getText().trim().equalsIgnoreCase(nomOption.trim())) {
    			
    			option.click();
    		}
    	}
    }

  

    public void verifierDashboard(String verifMsg) {
    	
    	String actualText = dashboardText.getText();
    	
    	Assert.assertEquals(verifMsg ,actualText);

        
    }
}