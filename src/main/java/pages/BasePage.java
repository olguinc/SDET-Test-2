package pages;

import org.testng.Reporter;
import tools.ANSIDef;
import java.util.List;

public class BasePage {

	public static void updateAndReportStatus(String newStatus) {
		Reporter.log(newStatus);
		System.out.println(newStatus);
	}

	public static void updateAndReportStatus(List<String> newStatus) {
		for (String status : newStatus) {
			updateAndReportStatus(status);
		}
	}

	public void updateAndReportStatusPageAction(String newStatus) {
		Reporter.log(newStatus);
		System.out.println(ANSIDef.ANSI_YELLOW + newStatus + ANSIDef.ANSI_CLOSE);
	}

	public void updateAndReportStatusPageWarning(String newStatus) {
		Reporter.log(newStatus);
		System.out.println(ANSIDef.ANSI_ORANGE + "WARNING: " + newStatus + ANSIDef.ANSI_CLOSE);
	}

}
