package AutomationExercise.DriverManagerLayer;

import org.openqa.selenium.WebDriver;

import static AutomationExercise.DriverManagerLayer.DriverManager.setDriver;

public class DriverFactory {

    public static void createInstance(String browser){
        WebDriver driver = BrowserFactory.valueOf(browser.toUpperCase()).createDriver();
        setDriver(driver);
    }
}
