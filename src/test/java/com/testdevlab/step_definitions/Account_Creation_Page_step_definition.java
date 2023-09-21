package com.testdevlab.step_definitions;

import com.testdevlab.pages.Account_Creation_Page;
import com.testdevlab.utilities.BrowserUtils;
import com.testdevlab.utilities.ConfigurationReader;
import com.testdevlab.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;


public class Account_Creation_Page_step_definition {
    Account_Creation_Page account_creation_page = new Account_Creation_Page();
    Duration timeout = Duration.ofSeconds(5);
    Actions actions = new Actions(Driver.getDriver());

    @Given("I am in a Sign Up page")
    public void I_am_in_a_Sign_Up_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));

        //If we have the pop-up we will cancel that
        if (BrowserUtils.isElementVisible(account_creation_page.cancelButton, timeout)) {
            account_creation_page.cancelButton.click();
        } else if (BrowserUtils.isElementVisible(account_creation_page.cancelButton, timeout)) {
            account_creation_page.cancelButton.click();
        }
        account_creation_page.register.click();
    }

    @Then("I enter valid user email")
    public void I_enter_valid_user_email() {
        account_creation_page.emailAddress.sendKeys(ConfigurationReader.getProperty("newEmail"));
    }

    //The method below is for clicking a web element
    @Then("click on {string} button")
    public void user_click_on_button(String webElement) throws InterruptedException {

        switch (webElement.toLowerCase()) {

            case "continue with email":
                account_creation_page.continueWithEmail.click();
                break;
            case "create account":
                //I am skipping this step because of the secure of the web
                //account_creation_page.createAccount.click();
                Driver.getDriver().navigate().back();
                Driver.getDriver().navigate().back();
                Thread.sleep(10);

                break;
            case "signin with verification link":
                account_creation_page.signInWithAVerificationLink.click();
                break;
            case "manage accounts":
                BrowserUtils.isElementVisible(account_creation_page.profileMenuTriggerTitleYour, timeout);
                account_creation_page.profileMenuTriggerTitleYour.click();
                account_creation_page.manageAccount.click();
                break;
            case "personal details":
                account_creation_page.personalDetails.click();
                break;
            default:
                throw new IllegalArgumentException("Unknown element: " + webElement);
        }
    }

    @Then("I enter valid password")
    public void I_enter_valid_password() {
        //Here I am checking if the email is already registered than we have one
        //box for password otherwise we have two, newPassword and confirmPassword
        if (BrowserUtils.isElementVisible(account_creation_page.password, timeout)) {
            account_creation_page.password.sendKeys(ConfigurationReader.getProperty("password"));
        } else {
            account_creation_page.newPassword.sendKeys(ConfigurationReader.getProperty("password"));
            account_creation_page.passwordConfirm.sendKeys(ConfigurationReader.getProperty("password"));
        }
    }

    @And("main page is opened")
    public void mainPageIsOpened() {
        BrowserUtils.isElementVisible(account_creation_page.profileMenuTriggerTitleYour, timeout);
        //actions.clickAndHold(account_creation_page.pressAndHold).perform();
        //I am proofing that the logo for the account is displayed after log-in
        Assert.assertTrue(account_creation_page.profileMenuTriggerTitleYour.isDisplayed());
    }

    @Then("{string} page is opened")
    public void pageIsOpened(String personalDetailPage) {
        //Storing as an array of strings each word
        String[] words = personalDetailPage.toLowerCase().split("\\s+");
        //Getting the url
        String actualURL = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(actualURL.contains(words[0]));

    }

    @And("correct value is prefilled in email verification placeholder")
    public void correctValueIsPrefilledInEmailVerificationPlaceholder() {
        String expectedEmail = ConfigurationReader.getProperty("email");
        String actualEmail = account_creation_page.getEmailFromPersonalDetails.getText();
        Assert.assertEquals(expectedEmail, actualEmail);
    }


}
