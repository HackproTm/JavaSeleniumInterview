package Core.Resources;

public final class Messages {

	private static String _ErrorBrowserNotSupported = "'{0}' is not a browser supported.";
	private static String _ErrorDriverNotInstanced = "The web driver is not instanced.";
	private static String _ErrorElementNotAvailable = "The element '{0}' is not available to interact with it.";
	private static String _ErrorFilePathNotExist = "The file path does not exist in '{0}'.";
	private static String _ErrorFormatUrlInvalid = "Url '{0}' has an invalid format.";
	private static String _ErrorInstantiatingWebDriver = "An error occurred while instantiating the webdriver for the specified browser.";
	private static String _ErrorParameterIsNull = "Parameter '{0}' can't be null.";
	
	private Messages() {}

	public static String ErrorBrowserNotSupported(String browserName) {
		return String.format(_ErrorBrowserNotSupported, browserName);
    }
	
	public static String ErrorDriverNotInstanced() {
		return _ErrorDriverNotInstanced;
    }
	
	public static String ErrorElementNotAvailable(String elementName) {
		return String.format(_ErrorElementNotAvailable, elementName);
    }
	
	public static String ErrorFilePathNotExist(String path) {
		return String.format(_ErrorFilePathNotExist, path);
    }
	
	public static String ErrorFormatUrlInvalid(String url) {
		return String.format(_ErrorFormatUrlInvalid, url);
    }
	
	public static String ErrorInstantiatingWebDriver() {
		return _ErrorInstantiatingWebDriver;
    }
	
	public static String ErrorParameterIsNull(String parameterName) {
		return String.format(_ErrorParameterIsNull, parameterName);
    }
}
