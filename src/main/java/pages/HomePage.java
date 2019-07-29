package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

	private WebDriver driver;
	// private By userField;

	private final String ROLL_AGAIN_BTN = "input[value='Roll Again']";
	private final String GO_BACK_BTN = "//input[@value='Go Back']";
	private final String TIMESTAMP = "div#invisible > p:nth-of-type(4)";
	private final String ROLLED_TEXT = "div#invisible > p:nth-of-type(2)";

	public HomePage(WebDriver driver) {
		this.driver = driver;
		// userField=By.tagName("input"); //Test
	}

	public String getTimestamp() {
		updateAndReportStatusPageAction("Getting timestamp...");
		return driver.findElement(By.cssSelector(TIMESTAMP)).getText();
	}

	public String getRolledText() {
		updateAndReportStatusPageAction("Getting text...");
		updateAndReportStatus("Text: " + driver.findElement(By.cssSelector(ROLLED_TEXT)).getText());
		return driver.findElement(By.cssSelector(ROLLED_TEXT)).getText();
	}

	public void clickOnRollAgain() {
		updateAndReportStatusPageAction("Clicking on Roll Again button...");
		driver.findElement(By.cssSelector(ROLL_AGAIN_BTN)).click();
	}

	public void clickOnGoBack() {
		updateAndReportStatusPageAction("Clicking on Go Back button...");
		driver.findElement(By.cssSelector(GO_BACK_BTN)).click();
	}

//Test
//    public void verifyElements() {
//    	List<WebElement> userList = driver.findElements(userField);
//    	System.out.println(userList.size());
//    	Assert.assertTrue(userList.size()==5);
//    	
//    }
}
