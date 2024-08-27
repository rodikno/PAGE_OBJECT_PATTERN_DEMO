package org.example.components;

import com.github.javafaker.Faker;
import org.example.helpers.CalendarCommonNavigation;
import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class RoomBookingComponent extends BasePage {


    private static WebElement componentRoot;

    public static final By componentRootLocator = By.xpath("//div[@class='row hotel-room-info']");

    // Booking Calendar Controls
    public static final By calendarNextMonthButton = By.xpath("//button[text()='Next']");
    public static final By calendarPreviousMonthButton = By.xpath("//button[text()='Back']");
    public static final By calendarTodayButton = By.xpath("//button[text()='Today']");
    public static final By monthViewCalendarDiv = By.xpath("//div[@class='rbc-month-view']");
    public static final By weeksOfMonthElements = By.xpath("//div[@class='rbc-row-bg']");
    public static final By daysOfWeekElements = By.xpath("//div[@class='rbc-day-bg']");


    // Personal data form (booking)
    public static final By firstNameField = By.name("firstname");
    public static final By lastNameField = By.name("lastname");
    public static final By emailField = By.name("email");
    public static final By phoneField = By.name("phone");

    // Buttons
    public static final By bookThisRoomButton = By.cssSelector("button.openBooking");
    public static final By cancelBookingButton = By.xpath("//button[text()='Cancel']");
    public static final By submitBookingButton = By.xpath("//button[text()='Book']");


    // Booking success modal window
    public static final By bookingSuccessModalWindow = By.xpath("//div[contains(@class, 'confirmation-modal')]");
    public static final By bookingSuccessTitle = By.xpath("//div[contains(@class, 'confirmation-modal')]/div/div/h3");


    public RoomBookingComponent(WebDriver driver) throws InterruptedException {
        super(driver);
        Thread.sleep(2000);
        componentRoot = waitForElementToBeVisible(driver.findElement(componentRootLocator));
    }

    public void populateBookingFormWithRandomData() {
        Faker faker = new Faker();

        driver.findElement(firstNameField).sendKeys(faker.name().firstName());
        driver.findElement(lastNameField).sendKeys(faker.name().lastName());
        driver.findElement(emailField).sendKeys(faker.internet().emailAddress());
        driver.findElement(phoneField).sendKeys(faker.phoneNumber().cellPhone());
    }

    public void selectRangeInCalendarForARandomWeek() {
        WebElement calendar = driver.findElement(monthViewCalendarDiv);
        List<WebElement> weeks = calendar.findElements(weeksOfMonthElements);
        List<WebElement> daysOfWeek = weeks.get(0).findElements(daysOfWeekElements);

        new Actions(driver)
                .clickAndHold(daysOfWeek.get(0))
                .moveToElement(daysOfWeek.get(daysOfWeek.size() - 1))
                .release()
                .perform();
    }

    public void expand() {
        waitForElementToBeClickable(componentRoot.findElement(bookThisRoomButton)).click();
    }

    public void collapse() {
        WebElement cancelButtonElement = driver.findElement(cancelBookingButton);

        if (cancelButtonElement.isDisplayed()) {
            cancelButtonElement.click();
        }
    }

    public void navigateCalendar(CalendarCommonNavigation navigationPoint) {
        switch (navigationPoint) {
            case TODAY:
                driver.findElement(calendarTodayButton).click();
                break;
            case NEXT_MONTH:
                driver.findElement(calendarNextMonthButton).click();
                break;
            case PREVIOUS_MONTH:
                driver.findElement(calendarPreviousMonthButton).click();
                break;
            default:
                throw new IllegalArgumentException("Calendar navigation case was not selected properly");
        }

    }

    public void submitBooking() {
        driver.findElement(submitBookingButton).click();
    }

    public WebElement getSuccessWindow() {
        return driver.findElement(bookingSuccessModalWindow);
    }

}
