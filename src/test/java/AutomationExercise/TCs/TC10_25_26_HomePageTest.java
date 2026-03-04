package AutomationExercise.TCs;

import AutomationExercise.DriverManagerLayer.DriverManager;
import AutomationExercise.Pages.P10HomePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static AutomationExercise.DriverManagerLayer.DriverFactory.createInstance;
import static AutomationExercise.DriverManagerLayer.DriverManager.getDriver;
import static AutomationExercise.Utilities.DataUtils.getEnvironmentProperty;
import static AutomationExercise.Utilities.DataUtils.getJsonValue;
import static AutomationExercise.Utilities.Utility.getTimestamp;

public class TC10_25_26_HomePageTest {
    private final String emailTimestamp
            = getJsonValue("RegisterNewUser", "userData.emailTimestamp")
            + getTimestamp() + "@gmail.com";

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        createInstance(getEnvironmentProperty("BROWSER"));
        new P10HomePage(getDriver()).navigateToHomePage();
    }

    @Test
    public void TC00_HomePageVerification() {

        new P10HomePage(getDriver())
                .ScrollToFooter()
                .addEmailAddress(emailTimestamp)
                .ClickOnEnterEmailAddressFiled()
                .VerifySuccessMessage("You have been successfully subscribed!\n")
                .scrollToUpWithOutArrowButton()
                .verifyScrollingUp("Full-Fledged practice website for Automation Engineers\n");
    }

    // TC25_ScrollUsingArrowButtonTest
    @Test(dependsOnMethods = "TC00_HomePageVerification")
    public void TC25_ScrollUsingArrowButtonTest() {
        new P10HomePage(getDriver()).ScrollToFooter();
        new P10HomePage(getDriver()).verifySubscriptionText("Subscription");
        new P10HomePage(getDriver()).ScrollToFooter().
                pressScrollUpButton().verifyScrollingUp("Full-Fledged practice website for Automation Engineers\n");
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quit();
    }
}
