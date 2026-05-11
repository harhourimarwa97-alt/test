//package Helper;
//
//import java.time.Duration;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//public class Config {
//	
//	public static WebDriver driver;
//    
//	public static void maximizewindow() {
//		
//		driver.manage().window().maximize();
//	}
//	 public static void confChrome() {
//		 
//	     driver = new ChromeDriver(); 
//
//	        driver.manage().window().maximize();
//
//	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//	    }
//	 
//
//	public static void attente (int s) {
//		
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(s));
//	}
//	
//
//}

package Helper;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Config {

    public static WebDriver driver;

    public static void confChrome() {

        WebDriverManager.chromedriver().setup(); 

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public static void maximizewindow() {
        driver.manage().window().maximize();
    }

    public static void attente(int s) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(s));
    }
}

