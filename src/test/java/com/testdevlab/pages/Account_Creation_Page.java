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
    public WebElement cancelButton;
    @FindBy(xpath = "//a[@aria-label=\"Create an account\"]")
    public WebElement register;

    @FindBy(id = "username")
    public WebElement emailAddress;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    public WebElement continueWithEmail;

    @FindBy(id = "new_password")
    public WebElement newPassword;

    @FindBy(id = "password")
    public WebElement password;

    @FindBy(id = "confirmed_password")
    public WebElement passwordConfirm;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    public WebElement createAccount;

    @FindBy(xpath = "//span[text()=\"Sign in with a verification link\"]/..")
    public WebElement signInWithAVerificationLink;

    @FindBy(xpath = "//button[@data-testid=\"header-language-picker-trigger\"]")
    public WebElement backToSignIn;

    @FindBy(xpath = "//input[@type=\"email\"]")
    public WebElement emailOnGmail;

    @FindBy(xpath = "//input[@type=\"password\"]")
    public WebElement passwordOnGmail;

    @FindBy(xpath = "//*[text()=\"Next\"]")
    public WebElement nextOnGmail;

    @FindBy(xpath = "(//*[text()=\"Your verification link\"])[2]")
    public WebElement verificationLinkOnGmail;

    @FindBy(xpath = "")
    public WebElement verifyMeOnGmail;

    @FindBy(xpath = "//*[@data-tooltip=\"Show trimmed content\"]")
    public WebElement threeDotsOnGmail;
}
