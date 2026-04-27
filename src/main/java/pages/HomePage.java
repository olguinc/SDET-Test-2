package pages;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

	private WebDriver driver;

	private final String ROLL_AGAIN_BTN = "input[value='Roll Again']";
	private final String GO_BACK_BTN = "//input[@value='Go Back']";
	// Dice values are in the alt attribute of images like <img src="dice3.png" alt="3">
	private final String DICE_IMG_SELECTOR = "img[src^='dice']";

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	// Accepts the GDPR cookie banner that appears on every fresh Chrome session.
	// Waits for the cookieBanner() JS function to be ready, then calls it.
	public void dismissCookieBanner() {
		try {
			new WebDriverWait(driver, Duration.ofSeconds(8)).until(d ->
				Boolean.TRUE.equals(((JavascriptExecutor) d).executeScript(
					"if (typeof cookieBanner === 'function') { cookieBanner('all'); return true; }" +
					"return false;"
				))
			);
		} catch (TimeoutException ignored) {
			// Banner not present or already accepted — safe to continue
		}
	}

	public String getRolledText() {
		updateAndReportStatusPageAction("Getting rolled values...");
		int[] values = getDiceValues();
		String text = Arrays.toString(values);
		updateAndReportStatus("Rolled: " + text);
		return text;
	}

	// Reads the alt attribute of each dice image (e.g. <img src="dice3.png" alt="3">)
	@SuppressWarnings("unchecked")
	public int[] getDiceValues() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		List<Long> values = (List<Long>) js.executeScript(
			"return Array.from(document.querySelectorAll(arguments[0]))" +
			"  .map(function(img) { return parseInt(img.getAttribute('alt')); })" +
			"  .filter(function(v) { return !isNaN(v); });",
			DICE_IMG_SELECTOR
		);
		if (values == null || values.isEmpty()) return new int[0];
		return values.stream().mapToInt(Long::intValue).toArray();
	}

	public void clickOnRollAgain() {
		updateAndReportStatusPageAction("Clicking on Roll Again button...");
		driver.findElement(By.cssSelector(ROLL_AGAIN_BTN)).click();
	}

	// Retries findElement+click on any transient WebDriverException (stale element,
	// CDP node-not-in-document, etc.) caused by the page's JS modifying the DOM.
	public void rollAgainSilent() {
		new WebDriverWait(driver, Duration.ofSeconds(10))
			.ignoring(WebDriverException.class)
			.until(d -> {
				d.findElement(By.cssSelector(ROLL_AGAIN_BTN)).click();
				return true;
			});
	}

	public void clickOnGoBack() {
		updateAndReportStatusPageAction("Clicking on Go Back button...");
		driver.findElement(By.xpath(GO_BACK_BTN)).click();
	}
}
