package com.testdevlab.step_definitions;

import com.testdevlab.pages.Account_Creation_Page;
import com.testdevlab.pages.LanguageAndCurrency_Page;
import com.testdevlab.utilities.BrowserUtils;
import com.testdevlab.utilities.ConfigurationReader;
import com.testdevlab.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class LanguageAndCurrency_step_definition {
    LanguageAndCurrency_Page languageAndCurrency_page = new LanguageAndCurrency_Page();
    Duration timeout = Duration.ofSeconds(5);

    @Given("I choose the {string} language")
    public void iChooseTheLanguage(String language) {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));

        Account_Creation_Page account_creation_page = new Account_Creation_Page();

        if (BrowserUtils.isElementVisible(account_creation_page.cancelButton, timeout)) {
            account_creation_page.cancelButton.click();
        }

        languageAndCurrency_page.language.click();

        String lang = languageAndCurrency_page.createXPathLocatorWithText(language);
        WebElement languageLocator = Driver.getDriver().findElement(By.xpath(lang));
        languageLocator.click();

        //Assertion
        String actualLanguage = languageAndCurrency_page.language.getAttribute("aria-label");
        Assert.assertTrue(actualLanguage.contains(language));

    }

    @And("{string} as the currency")
    public void asTheCurrency(String currency) {
        languageAndCurrency_page.currency.click();

        String curr = languageAndCurrency_page.createXPathLocatorWithText(currency);
        WebElement currencyLocator = Driver.getDriver().findElement(By.xpath(curr));
        currencyLocator.click();

        //Assertion
        String actualCurrency = languageAndCurrency_page.currency.getAttribute("aria-label");
        Assert.assertTrue(actualCurrency.contains(currency));

    }
}
