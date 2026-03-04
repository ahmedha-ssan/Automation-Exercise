package AutomationExercise.Utilities;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

import static AutomationExercise.DriverManagerLayer.DriverManager.getDriver;
import static AutomationExercise.Utilities.DataUtils.getConfigValue;
import static AutomationExercise.Utilities.WaitUtils.*;


public class Utility {

    //TODO TIMING
    public static String getTimestamp() {
        // Example: 04-09-2025 17:45:30
        return new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss", Locale.ENGLISH).format(new Date());
    }

    public static String getSimpleTimestamp() {
        // Example: 5-45-PM
        return new SimpleDateFormat("h-m-ssa", Locale.ENGLISH).format(new Date());
    }

    //TODO Convert Locator to Web Element
    public static WebElement findWebElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }
    //TODO Find multiple elements with the locator By object
    public static List<WebElement> findWebElements(WebDriver driver, By by) {
        return driver.findElements(by);
    }
    //TODO: click on element after visibility
    public static void clicking(WebDriver driver, By locator) {
        explicitlyWaitForClickability(driver, locator);
        findWebElement(driver, locator).click();
    }
    //TODO Send data to element after checking visibility
    public static void sendData(WebDriver driver, By locator, String data) {
        explicitlyWaitForVisibility(driver, locator);
        findWebElement(driver, locator).sendKeys(data);
    }
    //TODO: get text from element after checking visibility
    public static String getText(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return findWebElement(driver, locator).getText();
    }
    //TODO: reload the current page
    public static void reloadPage() {
        getDriver().navigate().refresh();
    }
    //TODO: navigate to a specific URL
    public static void openWebsite(String URL) {
        getDriver().get(URL);
    }
    //TODO: Maximize the window
    public static void maximizeWindow() {
        getDriver().manage().window().maximize();
    }
    //TODO: Minimize the window
    public static void minimizeWindow() {
        getDriver().manage().window().minimize();
    }

    //TODO: Generate random number from 1 to upperBound value say it is 5
    public static int generateRandomNumber(int upperBound) {
        return new Random().nextInt(upperBound) + 1;
    }

    //TODO: generate a unique number each guarantee uniqueness
    public static Set<Integer> generateUniqueNumber(int numberNeeded, int totalNumbers) {
        if (numberNeeded > totalNumbers) {
            throw new IllegalArgumentException(
                    "You cannot generate more unique numbers than the total range!");
        }
        // Set does not allow duplicates
        Set<Integer> generatedNumbers = new HashSet<>();
        Random random = new Random();

        while (generatedNumbers.size() < numberNeeded) {
            int randomNumber = random.nextInt(totalNumbers) + 1;
            generatedNumbers.add(randomNumber);
        }
        return generatedNumbers;
    }

    public static boolean verifyEquals(By locator, String expectedText) {
        return getText(getDriver(), locator).equals(expectedText);
    }

    //TODO: execute object from JS
    public static JavascriptExecutor getJsExecutor() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        return js;
    }
    //TODO:  Scroll to The center element
    public static void scrollToElement(WebDriver driver, By locator) {
        ((JavascriptExecutor) (driver)).executeScript(
                "arguments[0].scrollIntoView({block: 'center', inline: 'center'});"
                , findWebElement(driver, locator));
    }
    public static void scrollToElementAtTop(WebDriver driver, By locator) {
        ((JavascriptExecutor) (driver)).executeScript(
                //true → align element’s top
                "arguments[0].scrollIntoView(true);"
                , findWebElement(driver, locator));
    }
    public static void scrollToElementAtBottom(WebDriver driver, By locator) {
        ((JavascriptExecutor) (driver)).executeScript(
                //false → align element’s bottom
                "arguments[0].scrollIntoView(false);"
                , findWebElement(driver, locator));
    }
    public static void scrollToPosition(WebDriver driver, int x, int y) {
        ((JavascriptExecutor) (driver)).executeScript(
                "window.scrollTo(" + x + ", " + y + ");");
    }

    // TODO Method to zoom out using JavaScript
    public static void zoomOut(WebDriver driver, int zoomFactor) {
        ((JavascriptExecutor) driver).executeScript(
                "document.body.style.zoom = '" + zoomFactor + "%'");
    }
    //TODo Verify if the given web element is visible.
    public static boolean verifyElementVisible(By by) {
        try {
            explicitlyWaitForVisibility(getDriver(), by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //TODO: Function for getting first opt selected from drop down
    public static WebElement getSelectedOptionFromDropDown(WebDriver driver, By locator) {
        return new Select(
                Utility
                        .findWebElement(driver, locator))
                .getFirstSelectedOption();
    }

    //TODO: Function for selecting from drop down
    public static void selectFromDropDown(WebDriver driver, By locator, String option) {
        new Select(
                Utility
                        .findWebElement(driver, locator))
                .selectByVisibleText(option);
    }
    //IMPORTANT NEEEEK
    public static boolean VerifyURL(WebDriver driver, String expectedURL) {
        try {
            generalExplicitWait(driver, Integer
                    .parseInt(getConfigValue("config", "WAIT_EXPLICIT")
                    )
            )
                    .until(ExpectedConditions.urlToBe(expectedURL));
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    public static Set<Cookie> getAllCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public static void restoreSession(WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie : cookies)
            driver.manage().addCookie(cookie);
    }

    public static File getLatestFile(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        assert files != null;
        if (files.length == 0)
            return null;
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
        return files[0];
    }

}
