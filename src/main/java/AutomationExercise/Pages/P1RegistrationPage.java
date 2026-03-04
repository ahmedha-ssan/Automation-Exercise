package AutomationExercise.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static AutomationExercise.Utilities.DataUtils.getEnvironmentProperty;
import static AutomationExercise.Utilities.Utility.*;

public class P1RegistrationPage {

    //FirstPAGE
    private final By signupLoginButton = By.xpath("//a[contains(text(),'Signup / Login')]");
    private final By signupNameField = By.xpath("//*[@placeholder='Name']");
    private final By signupEmailField = By.xpath("//input[@data-qa=\"signup-email\"]");
    private final By signupButton = By.xpath("//button[@data-qa=\"signup-button\"]");
    private final By errorMsg = By.cssSelector("[action=\"/signup\"] p");
    private final By newUserSignupMessage = By.cssSelector(".signup-form h2");
    //SecPAGE
    private final By genderMrRadioButton = By.id("id_gender1");
    private final By genderMrsRadioButton = By.id("id_gender2");
    //private final By NameInput = By.id("name");
    private final By passwordField = By.id("password");
    private final By daysSelect = By.id("days");
    private final By monthsSelect = By.id("months");
    private final By yearsSelect = By.id("years");
    private final By signUpForNewsletter = By.id("years");
    private final By specialOffersOpt = By.id("optin");

    private final By firstNameInput = By.id("first_name");
    private final By lastNameInput = By.id("last_name");
    private final By companyNameInputField = By.id("company");
    private final By addressOneInput = By.id("address1");
    private final By addressTwoInput = By.id("address2");
    private final By countrySelect = By.id("country");
    private final By stateInput = By.id("state");
    private final By cityInput = By.id("city");
    private final By zipcodeInput = By.id("zipcode");
    private final By mobileNumberInput = By.id("mobile_number");
    private final By createAccountButton = By.xpath("//button[@data-qa='create-account']");
    private final By accountCreated = By.xpath("//h2[@data-qa='account-created']");


    private final WebDriver driver;
    public P1RegistrationPage(WebDriver driver) {
        this.driver =driver;
    }

    public P1RegistrationPage clickOnSignupLoginButton() {
        clicking(driver, signupLoginButton);
        return this;
    }

    public P1RegistrationPage enterUserDataPAGEOne(String name, String email) {
        sendData(driver, this.signupNameField, name);
        sendData(driver, this.signupEmailField, email);
        return this;
    }
    public P1RegistrationPage clickSignupButton() {
        clicking(driver,signupButton);
        return this;
    }

    //Validations
    public boolean verifyThatErrorMessageAppears(String expectedText) {
        return verifyEquals(errorMsg, expectedText);

    }
    public boolean verifyThatNewUserSignupAppears(String expectedText) {
        return verifyEquals(newUserSignupMessage, expectedText);
    }


    public P1RegistrationPage enterUserDataTwo(String pass, String day, String month, String year, String userFirstName, String userLastName,
                                               String company, String userAddressOne, String userAddressTwo, String country,
                                               String state, String city, String zipCode, String userMobileNumber) {

        sendData(driver, passwordField,pass);
        sendData(driver,firstNameInput,userFirstName);
        sendData(driver,lastNameInput,userLastName);
        sendData(driver, zipcodeInput, zipCode);
        sendData(driver, cityInput, city);
        sendData(driver, mobileNumberInput, userMobileNumber);
        sendData(driver, companyNameInputField, company);
        sendData(driver, addressOneInput, userAddressOne);
        sendData(driver, addressTwoInput, userAddressTwo);
        sendData(driver, stateInput, state);

        selectFromDropDown(driver, daysSelect, day);
        selectFromDropDown(driver, monthsSelect, month);
        selectFromDropDown(driver, yearsSelect, year);
        selectFromDropDown(driver, countrySelect, country);

        clicking(driver, genderMrRadioButton);
        clicking(driver, signUpForNewsletter);
        clicking(driver, specialOffersOpt);
        clicking(driver, companyNameInputField);

        return this;
    }

    public void navigateToRegisterPage() {
        openWebsite(getEnvironmentProperty("REGISTER_URL"));
    }

    public void createAccount() {
        scrollToElement(driver, createAccountButton);
        clicking(driver, createAccountButton);
    }


    //Validations
    public boolean checkSuccessMessageAccountCreated(String expectedText) {
        return verifyEquals(accountCreated, expectedText);
    }

}
