package com.testdevlab.pages;

import com.testdevlab.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookingMain_Page {
    public BookingMain_Page() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//*[@aria-label=\"Sign in with Google\"]")
    public WebElement signInByGoogle;

    @FindBy(id = "identifierId")
    public WebElement emailOrPhone;

    @FindBy(xpath = "//*[text()=\"Next\"]")
    public WebElement next;

    @FindBy(xpath = "//input[@type=\"password\"]")
    public WebElement password;

    @FindBy(id = "accommodations")
    public WebElement staysIcon;

    @FindBy(id = "flights")
    public WebElement flightsIcon;

    @FindBy(id = "cars")
    public WebElement carsCarRentalsIcon;

    @FindBy(id = "attractions")
    public WebElement attractionsIcon;

    @FindBy(id = "airport_taxis")
    public WebElement airportTaxisIcon;

    @FindBy(xpath = "//input[@name=\"ss\"]")
    public WebElement destination;

    @FindBy(xpath = "//div[@role=\"button\"]")
    public WebElement selectDestinationFromDropDown;

    @FindBy(xpath = "//div[@data-testid=\"searchbox-dates-container\"]")
    public WebElement dateReservation;

    @FindBy(xpath = "//button[@data-testid=\"occupancy-config\"]")
    public WebElement adultsAndChildren;

    //On fire fox pop up continue

    @FindBy(xpath = "//div[@class=\"LgbsSe-bN97Pc\"]")
    public WebElement continueOnFirefox;

    @FindBy(id = "group_adults")
    public WebElement adults;

    @FindBy(id = "group_children")
    public WebElement children;

    @FindBy(xpath = "//select[@name=\"age\"]")
    public WebElement selectAgeForChildren;

    @FindBy(xpath = "//button[@class=\"a83ed08757 c21c56c305 bf0537ecb5 ab98298258 d2529514af af7297d90d d285d0ebe9\"]")
    public WebElement done;

    @FindBy(xpath = "//button[@class=\"a83ed08757 c21c56c305 a4c1805887 f671049264 d2529514af c082d89982 aa11d0d5cd\"]")
    public WebElement search;

    @FindBy(xpath = "//a[@data-testid=\"availability-cta-btn\"]//span[@class=\"e4adce92df\"]")
    public WebElement seeAvailability;

    @FindBy(xpath = "//h2[@class=\"d2fee87262 pp-header__title\"]")
    public WebElement hotelName;

    @FindBy(id = "hp_book_now_button")
    public WebElement reserve;

    @FindBy(xpath = "//div[@class=\"hprt-reservation-cta\"]/button")
    public WebElement iWillReserve;

    @FindBy(xpath = "//button[@aria-description=\"Reserve this apartment now\"]")
    public WebElement reserveSecond;

    @FindBy(xpath = "//div[@class=\"notice-item-close-x\"]")
    public WebElement removeFinishYourBooking;

    @FindBy(xpath = "//span[@class=\"bui-button__text js-button__text\"]")
    public WebElement finalDetails;

    @FindBy(xpath = "//div[@data-testid=\"searchbox-checkin-container\"]")
    public WebElement gettingStartingDate;

    @FindBy(xpath = "//div[@data-testid=\"searchbox-checkout-container\"]")
    public WebElement gettingEndingDate;

    @FindBy(xpath = "(//button[@data-testid=\"occupancy-config\"])[1]")
    public WebElement gettingAdultsAndChild;

    @FindBy(xpath = "//span[@class=\"f419a93f12 c7b191f510\"]")
    public WebElement ratting;

    @FindBy(xpath = "//div[@class=\"bp-price-details__total-price\"]")
    public WebElement checkOutTotal;


    @FindBy(xpath = "//span[@class=\"bui-radio__label\"]")
    public WebElement checkOutYesRadioButton;

    @FindBy(xpath = "//input[@id=\"bp_travel_purpose_leasure\"]")
    public WebElement checkOutNoRadioButton;

    @FindBy(id = "firstname")
    public WebElement checkOutFirstName;

    @FindBy(id = "lastname")
    public WebElement checkOutLastName;

    @FindBy(id = "email")
    public WebElement checkOutEmail;

    @FindBy(xpath = "//li[@role=\"option\"]")
    public WebElement checkOutEmailFirstOption;

    @FindBy(id = "notstayer_false")
    public WebElement checkOutIAmTheMainGuest;

    @FindBy(id = "remarks")
    public WebElement checkOutDescription;



    @FindBy(id = "notstayer_true")
    public WebElement checkOutImBookingForSomeoneElse;










}
