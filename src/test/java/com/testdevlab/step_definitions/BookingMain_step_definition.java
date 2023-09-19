package com.testdevlab.step_definitions;

import com.testdevlab.pages.Account_Creation_Page;
import com.testdevlab.pages.BookingMain_Page;
import com.testdevlab.utilities.BrowserUtils;
import com.testdevlab.utilities.ConfigurationReader;
import com.testdevlab.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class BookingMain_step_definition {
    Duration timeout = Duration.ofSeconds(6);
    String currentWindowHandle = "";
    String currentWindow1 = "";
    Account_Creation_Page account_creation_page = new Account_Creation_Page();
    BookingMain_Page bookingMain_page = new BookingMain_Page();

    String destinationAsText = "";
    String datesAsText = "";
    String adultsAndChildrenAsText = "";

    @Given("I have account created")
    public void i_have_account_created() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        if (BrowserUtils.isElementVisible(account_creation_page.cancelButton, timeout)) {
            account_creation_page.cancelButton.click();
        }
        /*
        //Storing windows handle for later use
        String currentWindowHandle = Driver.getDriver().getWindowHandle();
        String currentWindow1 = "";

        if (BrowserUtils.isElementVisible(account_creation_page.cancelButton, timeout)) {
            account_creation_page.cancelButton.click();
            account_creation_page.register.click();
            bookingMain_page.signInByGoogle.click();
            currentWindow1 = Driver.getDriver().getWindowHandle();

        } else if (BrowserUtils.isElementVisible(bookingMain_page.continueOnFirefox, timeout)) {
            bookingMain_page.continueOnFirefox.click();                                                                 //Issue: I cannot click this web element
            currentWindow1 = Driver.getDriver().getWindowHandle();
        }

        BrowserUtils.switchToWindowByTitle(Driver.getDriver(), currentWindow1);
        bookingMain_page.emailOrPhone.sendKeys(ConfigurationReader.getProperty("email"));
        bookingMain_page.next.click();
        BrowserUtils.isElementVisible(bookingMain_page.password, timeout);
        bookingMain_page.password.sendKeys(ConfigurationReader.getProperty("password"));
        BrowserUtils.scrollToElement(Driver.getDriver(), bookingMain_page.next);
        bookingMain_page.next.click();

        Driver.getDriver().switchTo().window(currentWindowHandle);

         */

    }

    @And("I am in {string} page")
    public void iAmInPage(String icon) {
        BrowserUtils.isElementVisible(bookingMain_page.staysIcon, timeout);
        Assert.assertTrue(bookingMain_page.staysIcon.isDisplayed());
/*
        switch (icon.toLowerCase()) {

            case "home":
                BrowserUtils.isElementVisible(bookingMain_page.staysIcon, timeout);
                Assert.assertTrue(bookingMain_page.staysIcon.isSelected());
                break;
            case "flights":
                bookingMain_page.flightsIcon.click();
                break;
            case "car rental":
                bookingMain_page.carsCarRentalsIcon.click();
                break;
            case "attractions":
                bookingMain_page.attractionsIcon.click();
                break;
            case "airport taxis":
                bookingMain_page.airportTaxisIcon.click();
                break;
            default:
                throw new IllegalArgumentException("Unknown element: " + icon);
        }

 */
    }

    @When("I set up destination as {string}")
    public void iSetUpDestinationAs(String destination) {

        if (BrowserUtils.isElementVisible(bookingMain_page.destination, timeout)) {
            bookingMain_page.destination.click();
            bookingMain_page.destination.sendKeys(destination);
            if (bookingMain_page.selectDestinationFromDropDown.getText().equals(destination)) {
                bookingMain_page.selectDestinationFromDropDown.click();
            }
            destinationAsText = bookingMain_page.destination.getAttribute("value");
        }

    }

    @And("I set dates {string} - {string}")
    public void iSetDates(String startingDate, String endingDate) {

        bookingMain_page.dateReservation.click();
        String start = BrowserUtils.modifyAndReturn(startingDate);
        String end = BrowserUtils.modifyAndReturn(endingDate);

        WebElement startDate = Driver.getDriver().findElement(By.xpath(start));
        BrowserUtils.scrollToElement(Driver.getDriver(), startDate);
        startDate.click();

        WebElement endDate = Driver.getDriver().findElement(By.xpath(end));

        endDate.click();

        datesAsText = BrowserUtils.removeExtraSpaces(bookingMain_page.dateReservation.getText());

    }

    @And("I select {int} adults and {int} children")
    public void iSelectAdultsAndChildren(int adults, int children) {
        bookingMain_page.adultsAndChildren.click();
        int numberOfClicking = adults - 2;
        if (numberOfClicking > 0) {
            for (int i = 0; i < numberOfClicking; i++) {
                bookingMain_page.adultsPlusButton.click();
            }
        } else if (numberOfClicking < 0) {
            for (int i = 0; i < Math.abs(numberOfClicking); i++) {
                bookingMain_page.adultsMinusButton.click();
            }
        }

        if (children > 0) {
            for (int i = 0, j = 3; i < children; i++, j++) {
                bookingMain_page.childrenPlusButton.click();
                Select select = new Select(bookingMain_page.selectAgeForChildren);
                select.selectByIndex(j);
            }
        }
        int people = adults + children;
        int loop = people / 2 - 1;
        bookingMain_page.adultsAndChildren.click();
        if (people > 3) {
            for (int i = 0; i < loop; i++) {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) Driver.getDriver();
                jsExecutor.executeScript("arguments[0].click();", bookingMain_page.roomPlusButton);
            }
        }
        adultsAndChildrenAsText = bookingMain_page.adultsAndChildren.getText();


    }

    @And("I click on {string} button")
    public void iClickOnButton(String search) {
        switch (search.toLowerCase()) {

            case "search":
                BrowserUtils.isElementVisible(bookingMain_page.search, timeout);
                bookingMain_page.search.click();
                break;
            case "reserve":
                bookingMain_page.reserve.click();
                BrowserUtils.isElementVisible(bookingMain_page.iWillReserve, timeout);
                break;
            case "i'll reserve":
                if (BrowserUtils.isElementVisible(bookingMain_page.removeFinishYourBooking, timeout)) {
                    bookingMain_page.removeFinishYourBooking.click();
                }
                Select select = new Select(bookingMain_page.iWillReserveAmount);
                select.selectByIndex(1);
                bookingMain_page.iWillReserve.click();
                break;
            case "next: final details":
                if (BrowserUtils.isElementVisible(bookingMain_page.removeFinishYourBooking, timeout)) {
                    bookingMain_page.removeFinishYourBooking.click();
                }
                bookingMain_page.finalDetails.click();
                break;

            default:
                throw new IllegalArgumentException("Unknown element: " + search);
        }

    }

    @And("I click on {string} for fist hotel in the list")
    public void iClickOnForFistHotelInTheList(String seeAvailability) {
        currentWindowHandle = Driver.getDriver().getWindowHandle();
        bookingMain_page.seeAvailability.click();
    }

    @And("{string} page is opened for selected hotel")
    public void pageIsOpenedForSelectedHotel(String hotelDetails) {
        currentWindow1 = Driver.getDriver().getCurrentUrl();
        BrowserUtils.switchToWindowByTitle(Driver.getDriver(), currentWindow1);
        //Asserting that we are on the page of hotel
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("hotel"));
        Assert.assertTrue(bookingMain_page.hotelName.isDisplayed());

        //Asserting the value of destination
        Assert.assertTrue(bookingMain_page.destination.getAttribute("value").contains(destinationAsText));

        //Asserting the date of reservation
        String actualDate = BrowserUtils.formatDateString(bookingMain_page.gettingStartingDate.getText().substring(3));
        actualDate = actualDate.substring(0, actualDate.length() - 5);
        Assert.assertTrue(datesAsText.contains(actualDate));

        String actual1 = BrowserUtils.formatDateString(bookingMain_page.gettingEndingDate.getText().substring(3));
        actual1 = actual1.substring(0, actual1.length() - 5);
        Assert.assertTrue(datesAsText.contains(actual1));

        //Asserting the adult and child reservation
        String adultAndChild = bookingMain_page.gettingAdultsAndChild.getText();
        Assert.assertEquals("Added adults and children number does not match with expected one", adultsAndChildrenAsText, adultAndChild);

        //Asserting rating
        try {
            Assert.assertTrue(bookingMain_page.ratting.isDisplayed());
        } catch (org.openqa.selenium.TimeoutException e) {
            // Handle the exception or print an error message
            System.out.println("Exception: Element not displayed within the timeout.");
            // You can also fail the test here if needed
            Assert.fail("The rating WebElement is not displayed.");
        }

    }


    @Then("{string} page is displayed")
    public void pageIsDisplayed(String element) {

        switch (element.toLowerCase()) {

            case "checkout":
                BrowserUtils.scrollToElement(Driver.getDriver(), bookingMain_page.checkOutTotal);
                Assert.assertTrue(bookingMain_page.checkOutTotal.isDisplayed());
                break;
            case "final details":

                break;

            default:
                throw new IllegalArgumentException("Unknown element: " + element);
        }

    }


    @And("I enter valid booking information {string}, {string}, {string}, {string}")
    public void iEnterValidBookingInformation(String firstName, String lastName, String email, String description) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) Driver.getDriver();
        jsExecutor.executeScript("window.scrollTo(0, 0);");

        //bookingMain_page.checkOutIAmTheMainGuest.click();
        bookingMain_page.checkOutFirstName.sendKeys(firstName);
        bookingMain_page.checkOutLastName.sendKeys(lastName);
        bookingMain_page.checkOutEmail.sendKeys(email);
        bookingMain_page.checkOutEmailFirstOption.click();
        BrowserUtils.scrollToElement(Driver.getDriver(), bookingMain_page.checkOutDescription);
        bookingMain_page.checkOutYesRadioButton.click();
        bookingMain_page.checkOutDescription.sendKeys(description);

    }
}
