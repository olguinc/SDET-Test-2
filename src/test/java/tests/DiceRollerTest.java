package tests;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import helpers.ScreenshotHelper;
import helpers.WebDriverManager;
import pages.HomePage;

public class DiceRollerTest extends BaseTest {

	private static final int ROLLS = 1000;
	private static final double MAX_DEVIATION = 0.05;
	private static final By ROLL_BTN = By.cssSelector("input[value='Roll Again']");

	private WebDriver driver;

	@BeforeMethod
	public void setUp() {
		// Selenium 4.6+ includes Selenium Manager, which downloads ChromeDriver automatically
		driver = new ChromeDriver();
		WebDriverManager.setWindowSize(driver, "maximized");
	}

	@Test
	public void oneDiceTest() {
		WebDriverManager.navigateTo(driver, "https://www.random.org/dice/?num=1");
		HomePage homePage = new HomePage(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		// Dismiss the GDPR cookie banner that appears on every fresh Chrome session
		homePage.dismissCookieBanner();
		// Ensure the dice form is fully ready before starting the loop
		wait.until(ExpectedConditions.elementToBeClickable(ROLL_BTN));

		int[] counts = new int[7]; // index 1–6; 0 unused

		updateAndReportStatusFlow("Starting 1-dice distribution test (" + ROLLS + " rolls)...");
		for (int i = 0; i < ROLLS; i++) {
			homePage.rollAgainSilent();
			int[] values = wait.until(d -> {
				int[] v = homePage.getDiceValues();
				return v.length > 0 ? v : null;
			});
			counts[values[0]]++;

			if ((i + 1) % 100 == 0) {
				updateAndReportStatusFlow("Progress: " + (i + 1) + "/" + ROLLS);
			}
		}

		printChart(counts);
		assertUniformDistribution(counts);
	}

	@Test
	public void twoDicesTest() {
		WebDriverManager.navigateTo(driver, "https://www.random.org/dice/?num=2");
		HomePage homePage = new HomePage(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		// Dismiss the GDPR cookie banner that appears on every fresh Chrome session
		homePage.dismissCookieBanner();
		// Ensure the dice form is fully ready before starting the loop
		wait.until(ExpectedConditions.elementToBeClickable(ROLL_BTN));

		int[] die1Counts = new int[7];
		int[] die2Counts = new int[7];

		updateAndReportStatusFlow("Starting 2-dice distribution test (" + ROLLS + " rolls)...");
		for (int i = 0; i < ROLLS; i++) {
			homePage.rollAgainSilent();
			int[] values = wait.until(d -> {
				int[] v = homePage.getDiceValues();
				return v.length >= 2 ? v : null;
			});
			die1Counts[values[0]]++;
			die2Counts[values[1]]++;

			if ((i + 1) % 100 == 0) {
				updateAndReportStatusFlow("Progress: " + (i + 1) + "/" + ROLLS);
			}
		}

		updateAndReportStatusFlow("=== Die 1 ===");
		printChart(die1Counts);
		assertUniformDistribution(die1Counts);

		updateAndReportStatusFlow("=== Die 2 ===");
		printChart(die2Counts);
		assertUniformDistribution(die2Counts);
	}

	private void assertUniformDistribution(int[] counts) {
		double expected = (double) ROLLS / 6.0;
		double maxDeviation = 0;
		int worstFace = -1;

		for (int face = 1; face <= 6; face++) {
			double deviation = Math.abs(counts[face] - expected) / expected;
			if (deviation > maxDeviation) {
				maxDeviation = deviation;
				worstFace = face;
			}
		}

		updateAndReportStatusFlow(String.format(
			"Max deviation: %.2f%% (face %d — count %d, expected %.0f)",
			maxDeviation * 100, worstFace, counts[worstFace], expected));

		Assert.assertTrue(maxDeviation <= MAX_DEVIATION,
			String.format("Deviation %.2f%% on face %d exceeds the 5%% limit", maxDeviation * 100, worstFace));
	}

	private void printChart(int[] counts) {
		double expected = (double) ROLLS / 6.0;
		int maxCount = Arrays.stream(counts, 1, 7).max().getAsInt();
		updateAndReportStatusFlow(String.format("--- Distribution Chart (expected: %.0f per face) ---", expected));
		for (int face = 1; face <= 6; face++) {
			double deviation = (counts[face] - expected) / expected * 100;
			int bars = (int) ((double) counts[face] / maxCount * 40);
			String bar = "█".repeat(bars);
			updateAndReportStatusFlow(String.format(
				"Face %d [%-40s] %4d (%+.1f%%)", face, bar, counts[face], deviation));
		}
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (!result.isSuccess()) {
			ScreenshotHelper.takeScreenshot(driver);
		}
		driver.quit();
	}
}
