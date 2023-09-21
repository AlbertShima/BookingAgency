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

    // Method to check if a web element is visible
    public static boolean isElementVisible(WebElement element, Duration timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
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
                return;
            }
        }
    }
    public static String modifyAndReturn(String input) {
        String dayStart = input.substring(0, 2);
        String monthStart = input.substring(3, 5);
        String yearStart = input.substring(6);

        String modifiedString = "//span[@data-date=\"" + yearStart + "-" + monthStart + "-" + dayStart + "\"]";
        return modifiedString;
    }

    public static void changeAttributeValue(WebDriver driver, WebElement element, String attributeName, String newValue) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String script = "arguments[0].setAttribute(arguments[1], arguments[2]);";
        jsExecutor.executeScript(script, element, attributeName, newValue);
    }

    public static String removeExtraSpaces(String input) {
        Pattern pattern = Pattern.compile("\\s+");
        String[] words = pattern.split(input);
        String result = String.join(" ", words);
        return result;
    }

    public static String formatDateString(String input) {
        String[] words = input.split(" ");
        if (words.length >= 3) {
            String firstWord = words[0].substring(0, Math.min(words[0].length(), 3));
            String thirdWord = words[2].substring(0, Math.min(words[2].length(), 3));
            String result = firstWord + " " + words[1] + " " + thirdWord + " " + words[3];
            return result;
        } else {
            return input;
        }
    }

    public static void switchWindow(int windowIndex) {
        Set<String> windowHandles = Driver.getDriver().getWindowHandles();
        int totalWindows = windowHandles.size();

        if (totalWindows < 2) {
            System.out.println("There are not enough windows to switch.");
            return;
        }

        if (windowIndex == 0) {
            String parentWindowHandle = (String) windowHandles.toArray()[0];
            Driver.getDriver().switchTo().window(parentWindowHandle);
        } else if (windowIndex == 1) {
            String currentWindowHandle = (String) windowHandles.toArray()[totalWindows - 1];
            Driver.getDriver().switchTo().window(currentWindowHandle);
        } else {
            System.out.println("Invalid window index. Use 0 for the parent window or 1 for the current window.");
        }
    }
    public static void clickElementWithJavaScript(WebDriver driver, WebElement element) {
        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
