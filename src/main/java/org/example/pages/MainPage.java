package org.example.pages;

import org.example.components.RoomBookingComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    private static final String PAGE_URI = "https://automationintesting.online/";
    private RoomBookingComponent roomBookingSection;

    private static final By hotelDescription = By.xpath("//div[@class='row hotel-description']");
    private static final By letMeHackButton = By.xpath("//div[@data-toggle='collapse']/button");

    public MainPage(WebDriver driver) throws InterruptedException {
        super(driver);
        driver.get(PAGE_URI);
        waitForElementToBeVisible(driver.findElement(letMeHackButton)).click();
        this.roomBookingSection = new RoomBookingComponent(driver);
    }

    public RoomBookingComponent getRoomBookingSection() {
        return roomBookingSection;
    }


}