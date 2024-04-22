package Tests;

import org.testng.annotations.BeforeSuite;

import Core.Enums.BrowserType;
import Core.System.Browser;

public class BaseTest {
		@BeforeSuite
		public void initializeSuite() {
			Browser.getInstance().setBrowserType(BrowserType.Firefox);
		}
}
