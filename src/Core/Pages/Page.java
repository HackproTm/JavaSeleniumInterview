package Core.Pages;

import org.openqa.selenium.support.PageFactory;

import Core.Dto.TestConfigDto;
import Core.System.Browser;

public final class Page {
	public static TestConfigDto TestConfig;
	
	private static <T> T getPage(Class<T> pageReference) {
        try {
        	@SuppressWarnings("deprecation")
			T page = pageReference.newInstance();
	        Browser.getInstance().waitForAngularFinish(0.3);
	        PageFactory.initElements(Browser.getInstance().getDriver(), page);
	        return page;
        } catch (Exception ignored) {
        	return null;
        }
    }
	
	public static LogInPage LogInPage() {
         return getPage(LogInPage.class);
    }
}
