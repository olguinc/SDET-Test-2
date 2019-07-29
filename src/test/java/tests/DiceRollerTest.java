package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import helpers.Helpers;
import helpers.ScreenshotHelper;
import helpers.WebDriverManager;
import pages.HomePage;

public class DiceRollerTest extends BaseTest {

	private WebDriver driver;

	@BeforeMethod
	public void setUp() {
		Helpers helpers = new Helpers();
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		WebDriverManager.setWindowSize(driver, "maximized");
		helpers.sleepSeconds(5);
	}

	@Test
	public void oneDiceTest() {
		WebDriverManager.navigateTo(driver, "https://www.random.org/dice/?num=1");
		Helpers helpers = new Helpers();
		HomePage homePage = new HomePage(driver);

		updateAndReportStatusFlow("Starting test...");
		homePage.getRolledText();
		homePage.clickOnRollAgain();
		updateAndReportStatusFlow(homePage.getTimestamp());
		helpers.sleepSeconds(5);

		updateAndReportStatusFlow("Test Passed");
	}

	@Test
	public void twoDicesTest() {
		WebDriverManager.navigateTo(driver, "https://www.random.org/dice/?num=2");
		Helpers helpers = new Helpers();
		HomePage homePage = new HomePage(driver);

		updateAndReportStatusFlow("Starting test...");
		homePage.getRolledText();
		homePage.clickOnRollAgain();
		updateAndReportStatusFlow(homePage.getTimestamp());
		helpers.sleepSeconds(5);

		updateAndReportStatusFlow("Test Passed");
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (!result.isSuccess()) {
			ScreenshotHelper.takeScreenshot(driver);
		}
		driver.close();
	}
}
