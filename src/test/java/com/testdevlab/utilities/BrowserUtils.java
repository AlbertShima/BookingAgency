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
    public static boolean isElementVisible(WebElement element, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeoutInSeconds);
            wait.until(ExpectedConditions.visibilityOf(element));
            return true; // Element is visible
        } catch (Exception e) {
            return false; // Element is not visible
        }
    }

    // Method to long click a web element for a specified duration in seconds
    public static void longClick(WebElement element, int durationInSeconds) {
        Actions actions = new Actions(Driver.getDriver());

        // Click and hold the element
        actions.clickAndHold(element).build().perform();

        // Use WebDriverWait to wait for the specified duration
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), durationInSeconds);

        // Use a loop to repeatedly check for the element until the duration is reached
        for (int i = 0; i < durationInSeconds; i++) {
            wait.until(ExpectedConditions.visibilityOf(element)); // Wait for the element to be visible
        }

        // Release the mouse button to complete the long click
        actions.release(element).build().perform();
    }

    // Method to switch to an iframe by its index
    public static void switchToIframeByIndex(int index) {
        WebDriver driver = Driver.getDriver(); // Assuming you have a Driver class to manage your WebDriver instance
        driver.switchTo().frame(index);
    }

    // Method to switch back to the default content
    public static void switchToDefaultContent() {
        WebDriver driver = Driver.getDriver(); // Assuming you have a Driver class to manage your WebDriver instance
        driver.switchTo().defaultContent();
    }

    public static void safeClick(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10); // Adjust the timeout as needed

        try {
            // Wait for the element to be clickable
            wait.until(ExpectedConditions.elementToBeClickable(element));

            // Scroll to the element (if needed)
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();

            // Click the element
            element.click();
        } catch (Exception e) {
            // Handle any exceptions or log the error
            e.printStackTrace();
        }
    }

    public static void disableAllFrames(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        // Find all iframes on the page
        java.util.List<WebElement> iframes = driver.findElements(By.tagName("iframe"));

        // Disable each iframe by setting the src attribute to an empty string
        for (WebElement iframe : iframes) {
            jsExecutor.executeScript("arguments[0].setAttribute('src', '')", iframe);
        }
    }


}
