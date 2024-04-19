import org.openqa.selenium.support.PageFactory;

public static class Page {

	private static <T> T getPage(Class<T> pageReference) throws IllegalAccessException, InstantiationException {
        T page = pageReference.newInstance();
        // Browser.getInstance().waitForAngularFinish(0.3);
        PageFactory.initElements(Browser.getInstance().getDriver(), page);
        return page;
    }
	
	public static LoginPage Login() throws IllegalAccessException, InstantiationException {
        return getPage(LoginPage.class);
    }
}
