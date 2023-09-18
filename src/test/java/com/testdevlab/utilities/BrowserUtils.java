package com.testdevlab.utilities;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrowserUtils {
    //Here I will put all the methods tht I am going to use
    //I will not use .sleep() method.
    /**
     * This method will accept int (in seconds) and execute Thread.sleep()
     * for given duration
     *
     * @param second
     */
    public static void sleep(int second) {
        second *= 1000;
        try {
            Thread.sleep(second);
        } catch (InterruptedException e) {

        }
    }

    // Method to check if a web element is visible
    public static boolean isElementVisible(WebElement element, Duration timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
            wait.until(ExpectedConditions.visibilityOf(element));
            return true; // Element is visible
        } catch (org.openqa.selenium.TimeoutException e) {
            return false; // Element is not visible within the specified duration
        }
    }

    public static void clickAndHoldForDuration(WebDriver driver, long durationInMilliseconds) {
        // Get the middle of the page
        int centerX = driver.manage().window().getSize().width / 2;
        int centerY = driver.manage().window().getSize().height / 2;

        Actions actions = new Actions(driver);
        actions.moveToElement(getElementAtCoordinates(driver, centerX, centerY))
                .clickAndHold()
                .pause(durationInMilliseconds)
                .release()
                .perform();
    }
    private static WebElement getElementAtCoordinates(WebDriver driver, int x, int y) {
        return driver.findElement(By.xpath("//html"));
    }

    public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

}
