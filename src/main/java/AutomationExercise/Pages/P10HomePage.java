package AutomationExercise.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import static AutomationExercise.Utilities.DataUtils.getEnvironmentProperty;
import static AutomationExercise.Utilities.Utility.*;

public class P10HomePage {

    //a[contains(text(),'Home')]

    private final By HomePageButton = By.xpath("//a[contains(text(),'Home')]");
    private final By HomePageButtonText = By.xpath("//i[@class=\"fa fa-home\"]");

    private final By EnterEmailAddressFiled = By.id("susbscribe_email");
    private final By PressEmailAddressFiledButton = By.id("subscribe");
    private final By successMsg = By.xpath("//div[@class='alert-success alert' and contains(text(),'You have been successfully subscribed!')]");
    private final By SubscriptionText = By.xpath("//h2[contains(text(),'Subscription')]");
    private final By HeaderText = By.cssSelector("div[class='item active'] div[class='col-sm-6'] h2");
    //  Test Case 25: Verify Scroll Up using 'Arrow' button and Scroll Down functionality
    private final By ScrollUpButton = By.xpath("//a[@id=\"scrollUp\"]");

    private final WebDriver driver;
    public P10HomePage(WebDriver driver) {
        this.driver =driver;
    }

    public void navigateToHomePage() {
        openWebsite(getEnvironmentProperty("BASE_URL"));
    }

    public P10HomePage clickOnHomePageButton() {
        clicking(driver, HomePageButton);
        return this;
    }

    //✅ Validations
    public boolean verifyThatHomePageButtonAppears(String expectedText) {
        System.out.println(HomePageButtonText);
        System.out.println(expectedText);
        return verifyEquals(HomePageButtonText, expectedText);
    }

    //This method is used to scroll to the bottom of the home page
    public P10HomePage ScrollToFooter() {
        scrollToElementAtBottom(driver, EnterEmailAddressFiled);
        return this;
    }
    //verify scrolling down
    public boolean verifySubscriptionText(String expectedText) {
        verifyElementVisible(SubscriptionText);
        return verifyEquals(SubscriptionText, expectedText);
    }


    public P10HomePage addEmailAddress(String emailAddress) {
        sendData(driver, this.EnterEmailAddressFiled, emailAddress);
        return this;
    }
    public P10HomePage ClickOnEnterEmailAddressFiled() {
        clicking(driver, PressEmailAddressFiledButton);
        return this;
    }
    //Validations
    public P10HomePage VerifySuccessMessage(String expectedText) {
        verifyEquals(successMsg, expectedText);
        return this;
    }

    public P10HomePage pressScrollUpButton() {
        clicking(driver, ScrollUpButton);
        return this;
    }

    //verify scrolling up
    public boolean verifyScrollingUp(String expectedText) {
        verifyElementVisible(SubscriptionText);
        return verifyEquals(SubscriptionText, expectedText);
    }

    public P10HomePage scrollToUpWithOutArrowButton() {
        scrollToElementAtTop(driver, HeaderText);
        return this;

    }
}

