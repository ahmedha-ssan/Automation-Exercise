package AutomationExercise.TCs;

import AutomationExercise.DriverManagerLayer.DriverManager;
import AutomationExercise.Pages.P1RegistrationPage;
import AutomationExercise.Utilities.Utility;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static AutomationExercise.DriverManagerLayer.DriverFactory.createInstance;
import static AutomationExercise.DriverManagerLayer.DriverManager.getDriver;
import static AutomationExercise.Utilities.DataUtils.getEnvironmentProperty;
import static AutomationExercise.Utilities.DataUtils.getJsonData;
import static AutomationExercise.Utilities.Utility.VerifyURL;

public class TC05_RegisterWithExistingEmailTest {
    private final String NAME = getJsonData("ExistedRegistrationData", "name");
    private final String EMAIL = getJsonData("ExistedRegistrationData", "email");
    private final String SIGNUP_MSG = getJsonData("ExistedRegistrationData", "message");
    private final String ERROR_MSG = getJsonData("ExistedRegistrationData", "error message");

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        createInstance(getEnvironmentProperty("BROWSER"));
        Utility.openWebsite(getEnvironmentProperty("BASE_URL"));
    }


    @Test
    public void RegisterWithExistingEmailTest(){

        new P1RegistrationPage(getDriver())
                    .clickOnSignupLoginButton();
        Assert.assertTrue(new P1RegistrationPage(getDriver())
                .verifyThatNewUserSignupAppears(SIGNUP_MSG));


        new P1RegistrationPage(getDriver())
                .enterUserDataPAGEOne(NAME, EMAIL)
                .clickSignupButton();
        Assert.assertTrue(new P1RegistrationPage(getDriver())
                .verifyThatErrorMessageAppears(ERROR_MSG));


    }


    @AfterMethod
    public void quit() {
        DriverManager.quit();
    }
}
