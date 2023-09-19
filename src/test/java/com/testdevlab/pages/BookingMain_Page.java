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

    @FindBy(xpath = "//input[@placeholder=\"Where are you going?\"]")
    public WebElement destination;

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

    @FindBy(xpath = "aria-describedby=\"materialized_tooltip_gqyvs\"")
    public WebElement IWillReserve;

    @FindBy(xpath = "//span[@class=\"bui-button__text js-button__text\"]")
    public WebElement finalDetails;

}
