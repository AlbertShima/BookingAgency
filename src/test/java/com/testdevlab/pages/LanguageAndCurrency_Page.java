package com.testdevlab.pages;

import com.testdevlab.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LanguageAndCurrency_Page {
    public LanguageAndCurrency_Page() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//button[@data-testid=\"header-currency-picker-trigger\"]")
    public WebElement currency;
    @FindBy(xpath = "//button[@data-testid=\"header-language-picker-trigger\"]")
    public WebElement language;

    // Method to create XPath locator string with text containing a specific value
    public String createXPathLocatorWithText(String text) {
        return "(//*[contains(text(), '" + text + "')])[2]";
    }
}
