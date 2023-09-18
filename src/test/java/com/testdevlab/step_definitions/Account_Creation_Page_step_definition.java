package com.testdevlab.step_definitions;

import com.testdevlab.pages.Account_Creation_Page;
import com.testdevlab.utilities.BrowserUtils;
import com.testdevlab.utilities.ConfigurationReader;
import com.testdevlab.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class Account_Creation_Page_step_definition {
    Account_Creation_Page account_creation_page = new Account_Creation_Page();

    @Given("I am in a Sign Up page")
    public void I_am_in_a_Sign_Up_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        if (BrowserUtils.isElementVisible(account_creation_page.cancelButton, 5)) {
            account_creation_page.cancelButton.click();
        }
        account_creation_page.register.click();
    }

    //The method below is for clicking a web element
    @Then("click on {string} button")
    public void user_click_on_button(String webElement) {
        switch (webElement.toLowerCase()) {

            case "continue_with_email":
                account_creation_page.continueWithEmail.click();
                break;
            case "create_account":
                account_creation_page.createAccount.click();
                break;
            default:
                throw new IllegalArgumentException("Unknown element: " + webElement);
        }
    }

    @Then("I enter valid user email")
    public void I_enter_valid_user_email() {
        account_creation_page.emailAddress.sendKeys(ConfigurationReader.getProperty("email"));
    }

    @Then("I enter valid password")
    public void I_enter_valid_password() {
        if (BrowserUtils.isElementVisible(account_creation_page.password, 1)) {
            account_creation_page.password.sendKeys(ConfigurationReader.getProperty("password"));
        } else {
            account_creation_page.newPassword.sendKeys(ConfigurationReader.getProperty("password"));
            account_creation_page.passwordConfirm.sendKeys(ConfigurationReader.getProperty("password"));
        }
    }


    @And("main page is opened")
    public void mainPageIsOpened() {
        Assert.assertEquals("Booking.com",Driver.getDriver().getTitle());

    }


    @And("I click on {string} button under account menu")
    public void iClickOnButtonUnderAccountMenu(String arg0) {
    }

    @And("I click on {string} button under manage accounts section")
    public void iClickOnButtonUnderManageAccountsSection(String arg0) {

    }


    @Then("{string} page is opened And correct value is prefilled in email verification placeholder")
    public void pageIsOpenedAndCorrectValueIsPrefilledInEmailVerificationPlaceholder(String arg0) {
    }
}
