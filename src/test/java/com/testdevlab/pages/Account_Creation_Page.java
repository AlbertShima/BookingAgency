package com.testdevlab.pages;

import com.testdevlab.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Account_Creation_Page {
    public Account_Creation_Page() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    //Here will be listed all the locators
    @FindBy(xpath = "//div[@class=\"dd5dccd82f\"]//button[@type=\"button\"]")
    public WebElement cancelButton; //Pop up dialog box which appears at the beginning
    @FindBy(xpath = "//a[@aria-label=\"Create an account\"]")
    public WebElement register;

    @FindBy(id = "username")
    public WebElement emailAddress;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    public WebElement continueWithEmail;

    @FindBy(id = "new_password")
    public WebElement newPassword;      //New password for creating new account

    @FindBy(id = "confirmed_password")
    public WebElement passwordConfirm;  //Confirm password for new account

    @FindBy(xpath = "//button[@type=\"submit\"]")
    public WebElement createAccount;    //Create account for new user

    @FindBy(id = "password")
    public WebElement password;         //Password for an already created account

    @FindBy(xpath = "//span[text()=\"Sign in with a verification link\"]/..")
    public WebElement signInWithAVerificationLink;

    @FindBy(xpath = "//div[@class=\"aca0ade214 a5f1aae5b2 c2931f4182 e7d9f93f4d\"]")
    public WebElement profileMenuTriggerTitleYour; //Profile Menu at the right top

    @FindBy(xpath = "//*[text()=\"Manage account\"]")
    public WebElement manageAccount;

    @FindBy(xpath = "//*[text()=\"Personal details\"]")
    public WebElement personalDetails;

    @FindBy(xpath = "//div[@class=\"HkzQyP3VFWJ3FPcwZbMw\"]")
    public WebElement getEmailFromPersonalDetails;

    @FindBy(xpath = "//span[text()=\"Press and hold this button to pass the security check\"]")
    public WebElement pressAndHold;

}
