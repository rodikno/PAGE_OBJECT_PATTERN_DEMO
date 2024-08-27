import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.components.RoomBookingComponent;
import org.example.helpers.CalendarCommonNavigation;
import org.example.pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void testLogin() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        RoomBookingComponent roomBookingSection = mainPage.getRoomBookingSection();
        roomBookingSection.expand();
        roomBookingSection.navigateCalendar(CalendarCommonNavigation.NEXT_MONTH);

        roomBookingSection.selectRangeInCalendarForARandomWeek();
        roomBookingSection.populateBookingFormWithRandomData();
        roomBookingSection.submitBooking();

        Thread.sleep(15000);
        String bookingSuccessText = roomBookingSection.getSuccessWindow().findElement(RoomBookingComponent.bookingSuccessTitle).getText();

        Assert.assertEquals(bookingSuccessText, "Booking Successful!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}