package com.testdevlab.utilities;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;
import java.util.regex.Pattern;

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


    public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void switchToWindowByTitle(WebDriver driver, String targetWindowTitle) {
        Set<String> windowHandles = driver.getWindowHandles();

        for (String windowHandle : windowHandles) {
            driver.switchTo().window(windowHandle);
            String windowTitle = driver.getTitle();

            if (windowTitle.equals(targetWindowTitle)) {
                return; // Found the target window, no need to continue searching
            }
        }
    }
    public static String modifyAndReturn(String input) {
        // Make changes to the input string
        String dayStart = input.substring(0, 2);
        String monthStart = input.substring(3, 5);
        String yearStart = input.substring(6);

        // Create the modified string
        String modifiedString = "//span[@data-date=\"" + yearStart + "-" + monthStart + "-" + dayStart + "\"]";

        // Return the modified string
        return modifiedString;
    }

    public static void changeAttributeValue(WebDriver driver, WebElement element, String attributeName, String newValue) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String script = "arguments[0].setAttribute(arguments[1], arguments[2]);";
        jsExecutor.executeScript(script, element, attributeName, newValue);
    }

    public static String removeExtraSpaces(String input) {
        // Define a regular expression pattern to match multiple spaces
        Pattern pattern = Pattern.compile("\\s+");

        // Use the pattern to split the input string into words
        String[] words = pattern.split(input);

        // Join the words back together with a single space between them
        String result = String.join(" ", words);

        return result;
    }

    public static String formatDateString(String input) {
        // Split the input string by spaces
        String[] words = input.split(" ");

        if (words.length >= 3) {
            // Extract the first three letters from the first word
            String firstWord = words[0].substring(0, Math.min(words[0].length(), 3));

            // Extract the first three letters from the third word
            String thirdWord = words[2].substring(0, Math.min(words[2].length(), 3));

            // Build the formatted result
            String result = firstWord + " " + words[1] + " " + thirdWord + " " + words[3];

            return result;
        } else {
            // If there are not enough words in the input, return the original string
            return input;
        }
    }


}
