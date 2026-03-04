package AutomationExercise.Utilities;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static AutomationExercise.DriverManagerLayer.DriverManager.getDriver;
import static AutomationExercise.Utilities.DataUtils.getConfigValue;
import java.time.Duration;
import java.util.Objects;


public class WaitUtils {

    //TODO: implicit Wait method
    public static void implicitlyWait() {
        getDriver().
                manage().
                timeouts().
                implicitlyWait
                        (Duration.ofSeconds
                                (Integer.parseInt
                                        (getConfigValue
                                                ("config","WAIT_IMPLICIT")
                                        )
                                )
                        );
    }

    //TODO:  Explicit Wait
    public static WebDriverWait generalExplicitWait(WebDriver driver, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    //TODO:  Explicit Wait for clickability method
    public static void explicitlyWaitForClickability(WebDriver driver, By locator) {
        new WebDriverWait
                (driver,
                        Duration.ofSeconds
                                (Integer.parseInt
                                        (getConfigValue
                                                ("config","WAIT_EXPLICIT")
                                        )
                                )
                )
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    //TODO: explicit wait for visibility
    public static void explicitlyWaitForVisibility(WebDriver driver, By locator) {
        new WebDriverWait
                (driver, Duration.ofSeconds
                        (Integer.parseInt(getConfigValue
                                ("config","WAIT_EXPLICIT")
                        )
                        )
                )
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    //TODO: wait for element that has attribute
    public static boolean waitForElementHasAttribute(By by, String attributeName) {
        try {
            return  generalExplicitWait
                    (getDriver(),Integer.parseInt
                            (getConfigValue
                                    ("config", "WAIT_EXPLICIT")
                            )
                    )
                    .until(ExpectedConditions.attributeToBeNotEmpty(Objects.requireNonNull(waitForElementPresent(by)), attributeName));
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
        return false;
    }
    //TODO:  wait for the element to be Presented
    public static WebElement  waitForElementPresent(By by) {
        try {
            return generalExplicitWait
                    (getDriver(),Integer.parseInt
                            (getConfigValue
                                    ("config", "WAIT_EXPLICIT")
                            )
                    )
                    .until(ExpectedConditions.presenceOfElementLocated(by));
        }catch (Throwable e){
            e.printStackTrace();
        }
        return null;
    }




}
