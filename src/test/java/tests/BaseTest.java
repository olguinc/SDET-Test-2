package tests;

import org.testng.Reporter;

import tools.ANSIDef;

public class BaseTest {
	
	public void updateAndReportStatus(String newStatus) {
        Reporter.log(newStatus);
        System.out.println(newStatus);
    }

    public void updateAndReportStatusFlow(String newStatus) {
        Reporter.log(newStatus);
        System.out.println(ANSIDef.ANSI_BLUE + newStatus + ANSIDef.ANSI_CLOSE);
    }

    public void updateAndReportStatusTestTitle(String newStatus) {
        Reporter.log(newStatus);
        System.out.println(ANSIDef.ANSI_GREEN_BOLD + newStatus + ANSIDef.ANSI_CLOSE);
    }

}
