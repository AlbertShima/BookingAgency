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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class BookingMain_step_definition {
    Duration timeout = Duration.ofSeconds(7);
    String currentWindowHandle = "";
    String currentWindow1 = "";
    Account_Creation_Page account_creation_page = new Account_Creation_Page();
    BookingMain_Page bookingMain_page = new BookingMain_Page();

    String destinationAsText = "";
    String datesAsText = "";
    String adultsAndChildrenAsText = "";

    @Given("I have account created")
    public void i_have_account_created() {

        //Storing windows handle for later use
        String currentWindowHandle = Driver.getDriver().getWindowHandle();
        String currentWindow1 = "";

        account_creation_page.register.click();
        bookingMain_page.signInByGoogle.click();
        currentWindow1 = Driver.getDriver().getWindowHandle();

        if (BrowserUtils.isElementVisible(bookingMain_page.continueOnFirefox, timeout)) {
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

    }

    @And("I am in {string} page")
    public void iAmInPage(String icon) {
        BrowserUtils.isElementVisible(bookingMain_page.staysIcon, timeout);
        Assert.assertTrue(bookingMain_page.staysIcon.isDisplayed());

        //Here I made a switch to automate which button we have to click
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
        System.out.println("destination = " + destination);

        BrowserUtils.isElementVisible(bookingMain_page.destination, timeout);
        if (bookingMain_page.destination.getText().isBlank()) {
            bookingMain_page.destination.click();
            bookingMain_page.destination.sendKeys(destination);
        } else {
            //Clear destination text
            bookingMain_page.destination.click();
            bookingMain_page.destination.sendKeys(Keys.CONTROL + "a");
            bookingMain_page.destination.sendKeys(Keys.BACK_SPACE);
            bookingMain_page.destination.sendKeys(destination);
            bookingMain_page.destination.sendKeys(Keys.DOWN, Keys.ENTER);
        }
        destinationAsText = bookingMain_page.destination.getAttribute("value");


    }

    @And("I set dates {string} - {string}")
    public void iSetDates(String startingDate, String endingDate) {
        BrowserUtils.clickElementWithJavaScript(Driver.getDriver(), bookingMain_page.dateReservation);
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
        int numberOfClicking = adults - Integer.parseInt(bookingMain_page.numberOfAdults.getText());
        if (numberOfClicking > 0) {
            for (int i = 0; i < numberOfClicking; i++) {
                bookingMain_page.adultsPlusButton.click();
            }
        } else if (numberOfClicking < 0) {
            for (int i = 0; i < Math.abs(numberOfClicking); i++) {
                bookingMain_page.adultsMinusButton.click();
            }
        }


        int numberOfClickingChildren = children - Integer.parseInt(bookingMain_page.numberOfChildren.getText());
        if (numberOfClickingChildren > 0) {
            for (int i = 0, j = 3; i < numberOfClickingChildren; i++, j++) {
                bookingMain_page.childrenPlusButton.click();
            }
            for (int i = 0; i < numberOfClickingChildren; i++) {
                String xpathForSelectingAgeOfChildren = "(//select[@name=\"age\"])[" + (i + 1) + "]";
                WebElement selectAges = Driver.getDriver().findElement(By.xpath(xpathForSelectingAgeOfChildren));
                selectAges.click();
                Select select = new Select(selectAges);
                select.selectByIndex(i + 3);
            }

        } else if (numberOfClickingChildren < 0) {
            for (int i = 1, j = 3; i < Math.abs(numberOfClickingChildren); i++, j++) {
                bookingMain_page.childrenMinusButton.click();
            }
            for (int i = 0; i < Math.abs(numberOfClickingChildren); i++) {
                String xpathForSelectingAgeOfChildren = "(//select[@name=\"age\"])[" + (i + 1) + "]";
                WebElement selectAges = Driver.getDriver().findElement(By.xpath(xpathForSelectingAgeOfChildren));
                selectAges.click();
                Select select = new Select(selectAges);
                select.selectByIndex(i + 3);
            }
        }

        int people = adults + children;
        int loop = people / 2 - Integer.parseInt(bookingMain_page.numberOfRooms.getText());
        bookingMain_page.adultsAndChildren.click();
        if (people > 3) {
            for (int i = 0; i < loop; i++) {
                BrowserUtils.clickElementWithJavaScript(Driver.getDriver(), bookingMain_page.roomPlusButton);
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
                if (BrowserUtils.isElementVisible(bookingMain_page.iWillReserveAmount, timeout)) {
                    Select select = new Select(bookingMain_page.iWillReserveAmount);
                    select.selectByIndex(1);
                }
                bookingMain_page.iWillReserve.click();
                break;
            case "next: final details":
                if (BrowserUtils.isElementVisible(bookingMain_page.removeFinishYourBooking, timeout)) {
                    bookingMain_page.removeFinishYourBooking.click();
                }
                BrowserUtils.scrollToElement(Driver.getDriver(), bookingMain_page.finalDetails);
                bookingMain_page.finalDetails.click();
                break;

            default:
                throw new IllegalArgumentException("Unknown element: " + search);
        }
    }

    @And("I click on {string} for first hotel in the list")
    public void iClickOnForFirstHotelInTheList(String seeAvailability) {
        currentWindowHandle = Driver.getDriver().getWindowHandle();
        bookingMain_page.seeAvailability.click();
    }

    @And("{string} page is opened for selected hotel")
    public void pageIsOpenedForSelectedHotel(String hotelDetails) {
        BrowserUtils.switchToWindowByTitle(Driver.getDriver(), currentWindow1);

        //Asserting that we are on the page of hotel
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("hotel"));
        Assert.assertTrue(bookingMain_page.hotelName.isDisplayed());

        //Asserting the value of destination
        //Assert.assertTrue(bookingMain_page.destination.getAttribute("value").contains(destinationAsText));

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
        //Assert.assertTrue(bookingMain_page.firstDivContainsRating.isDisplayed());

    }


    @Then("{string} page is displayed")
    public void pageIsDisplayed(String element) {

        switch (element.toLowerCase()) {

            case "checkout":
                BrowserUtils.scrollToElement(Driver.getDriver(), bookingMain_page.checkOutTotal);
                Assert.assertTrue(bookingMain_page.checkOutTotal.isDisplayed());
                break;
            case "final details":
                Assert.assertTrue(bookingMain_page.finalStep.isDisplayed());
                break;

            default:
                throw new IllegalArgumentException("Unknown element: " + element);
        }

    }


    @And("I enter valid booking information {string}, {string}, {string}, {string}, {string}")
    public void iEnterValidBookingInformation(String firstName, String lastName, String email, String description, String phoneNumber) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) Driver.getDriver();
        jsExecutor.executeScript("window.scrollTo(0, 0);");

        bookingMain_page.checkOutFirstName.sendKeys(firstName);
        bookingMain_page.checkOutLastName.sendKeys(lastName);
        bookingMain_page.checkOutEmail.click();
        bookingMain_page.checkOutEmail.sendKeys(Keys.CONTROL + "a");
        bookingMain_page.checkOutEmail.sendKeys(email + Keys.DOWN + Keys.ENTER);
        if (BrowserUtils.isElementVisible(bookingMain_page.phoneNumber, timeout)) {
            bookingMain_page.phoneNumber.sendKeys(phoneNumber);
        }
        bookingMain_page.checkOutYesRadioButton.click();
        bookingMain_page.checkOutDescription.sendKeys(description);


    }

    @And("I click on {string} for the cheapest hotel in the list with a rating above {int} stars")
    public void iClickOnForTheCheapestHotelInTheListWithARatingAboveStars(String seeAvailability, int nrOfStars) {
        // Find all the elements using your XPath expression
        List<WebElement> elements = Driver.getDriver().findElements(By.xpath("//div[@data-testid=\"property-card\"]"));

        int minPrice = Integer.MAX_VALUE;
        WebElement selectedElement = null;

        // Iterate through each WebElement
        for (WebElement element : elements) {
            // Check if the div contains either listRatingStars or listRatingSquares

            boolean containsRating = element.findElements(By.xpath(".//div[@data-testid=\"rating-squares\"] | .//div[@data-testid=\"rating-stars\"]")).size() > 0;

            // Count the number of spans inside listRatingSquares and listRatingStars
            int numberOfSquares = element.findElements(By.xpath(".//div[@data-testid=\"rating-squares\"]/span")).size();
            int numberOfStars = element.findElements(By.xpath(".//div[@data-testid=\"rating-stars\"]/span")).size();

            WebElement listOfPrices = element.findElement(By.xpath("//span[@data-testid=\"price-and-discounted-price\"]"));
            String priceText = listOfPrices.getText().replaceAll("[^0-9]+", "");
            int price = Integer.parseInt(priceText);

            // Check if the div contains at least 3 listRatingSquares or listRatingStars
            if (containsRating && (numberOfSquares >= nrOfStars || numberOfStars >= nrOfStars) && price < minPrice) {
                minPrice = price;
                selectedElement = element;
            }
        }

        if (selectedElement != null) {
            // Click the selected div
            selectedElement.findElement(By.xpath(".//div[@data-testid=\"title\"]")).click();
            System.out.println("Minimum Price: " + minPrice);
        }
    }

    @And("I click on {string} button for the most expensive available room in the hotel")
    public void iClickOnButtonForTheMostExpensiveAvailableRoomInTheHotel(String something) {
        BrowserUtils.switchWindow(0);

        int maxPrice = Integer.MIN_VALUE;
        int indexOfMaxPrice = 0;
        int price = 0;

        List<WebElement> divContainingRatingAndPrice = Driver.getDriver().findElements(By.xpath("//div[@data-testid=\"property-card\"]"));
        for (int i = 1; i < divContainingRatingAndPrice.size(); i++) {
            String xpathListOfPrices = "(//span[@data-testid=\"price-and-discounted-price\"])[" + i + "]";
            WebElement listOfPrices = divContainingRatingAndPrice.get(i).findElement(By.xpath(xpathListOfPrices));

            String priceText = listOfPrices.getText().replaceAll("[^0-9]+", "");
            price = Integer.parseInt(priceText);

            if (price > maxPrice) {
                maxPrice = price;
                indexOfMaxPrice++;
            }
        }
        divContainingRatingAndPrice.get(indexOfMaxPrice).findElement(By.xpath("//div[@data-testid=\"title\"]")).click();
        System.out.println("maxPrice = " + maxPrice);
        BrowserUtils.switchWindow(1);


        if (BrowserUtils.isElementVisible(bookingMain_page.removeFinishYourBooking, timeout)) {
            bookingMain_page.removeFinishYourBooking.click();
        }
        if (BrowserUtils.isElementVisible(bookingMain_page.iWillReserveAmount, timeout)) {
            Select select = new Select(bookingMain_page.iWillReserveAmount);
            select.selectByIndex(1);
        }
        bookingMain_page.iWillReserve.click();

    }
}
