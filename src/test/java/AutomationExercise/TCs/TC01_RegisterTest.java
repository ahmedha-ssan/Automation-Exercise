package AutomationExercise.TCs;

import AutomationExercise.Pages.P1RegistrationPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static AutomationExercise.DriverManagerLayer.DriverFactory.createInstance;
import static AutomationExercise.DriverManagerLayer.DriverManager.getDriver;
import static AutomationExercise.Utilities.DataUtils.*;
import static AutomationExercise.Utilities.Utility.getTimestamp;

public class TC01_RegisterTest {

    private final String emailTimestamp
            = getJsonValue("RegisterNewUser", "userData.emailTimestamp")
            + getTimestamp() + "@gmail.com";
    private final String fullNameRegister
            = getJsonValue("RegisterNewUser", "userData.fullNameRegister");

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        createInstance(getEnvironmentProperty("BROWSER"));
        new P1RegistrationPage(getDriver()).navigateToRegisterPage();
    }



    public void registeringUser(){
        new P1RegistrationPage(getDriver())
                .clickOnSignupLoginButton()
                .enterUserDataPAGEOne(fullNameRegister, emailTimestamp)
                .clickSignupButton()
                .enterUserDataTwo(
                        getJsonValue("RegisterNewUser","userData.password"),
                        getJsonValue("RegisterNewUser","userData.daySelect"),
                        getJsonValue("RegisterNewUser","userData.monthSelect"),
                        getJsonValue("RegisterNewUser","userData.yearSelect"),
                        getJsonValue("RegisterNewUser","userData.firstName"),
                        getJsonValue("RegisterNewUser","userData.lastName"),
                        getJsonValue("RegisterNewUser","userData.companyName"),
                        getJsonValue("RegisterNewUser","userData.addressOne"),
                        getJsonValue("RegisterNewUser","userData.addressTwo"),
                        getJsonValue("RegisterNewUser","userData.country"),
                        getJsonValue("RegisterNewUser", "userData.state"),
                        getJsonValue("RegisterNewUser", "userData.city"),
                        getJsonValue("RegisterNewUser", "userData.zipcode"),
                        getJsonValue("RegisterNewUser", "userData.mobileNumber"))
                .createAccount();
        Assert.assertTrue(new P1RegistrationPage(getDriver()).checkSuccessMessageAccountCreated("ACCOUNT CREATED!"));
    }

}



