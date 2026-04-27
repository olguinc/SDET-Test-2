package helpers;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class WebDriverManager {

	public static void setWindowSize(WebDriver driver, String size) {
		if ("maximized".equals(size)) {
			driver.manage().window().maximize();
		}

		if ("fullscreen".equals(size)) {
			driver.manage().window().fullscreen();
		}
	}

	public static void setWindowSize(WebDriver driver, int x, int y) {
		driver.manage().window().setSize(new Dimension(x, y));
	}
	
	public static void navigateTo(WebDriver driver, String url) {
		driver.navigate().to(url);
	}

}
