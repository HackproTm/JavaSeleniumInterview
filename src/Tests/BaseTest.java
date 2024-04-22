package Tests;

import org.testng.annotations.BeforeClass;

import Core.Enums.BrowserType;
import Core.System.Browser;

public class BaseTest {
		@BeforeClass
		public void initializeSuite() {
			Browser.getInstance().setBrowserType(BrowserType.Firefox);
		}
}
