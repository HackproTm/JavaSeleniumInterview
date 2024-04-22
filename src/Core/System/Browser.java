package Core.System;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import Core.Dto.UserProfilePreferenceDto;
import Core.Resources.Messages;
import Core.Enums.BrowserType;
import Core.Pages.*;

public class Browser implements AutoCloseable {

    private static final Object LOCKED = new Object();
    private static BrowserType browserType;
    private static boolean dispose;
    private static WebDriver driver;
    private static Browser instance;
    private static String profileDir = "";
    private static WebDriverWait wait;

    private Browser() {
        driver = null;
        dispose = false;
        browserType = BrowserType.None;
    } 

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        try {
            options.addArguments(Page.TestConfig.getBrowserSetup().getArguments());
            for (UserProfilePreferenceDto preference : Page.TestConfig.getBrowserSetup().getUserProfilePreferences()) {
                options.setCapability(preference.getPreference(), parsePreferenceValue(preference));
            }
            return options;
        } catch (Exception e) {
            return options;
        }
    }

    private static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        try {
            for (UserProfilePreferenceDto preference : Page.TestConfig.getBrowserSetup().getUserProfilePreferences()) {
                options.setCapability(preference.getPreference(), parsePreferenceValue(preference));
            }
            return options;
        } catch (Exception e) {
            return options;
        }
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        try {
            options.addArguments(Page.TestConfig.getBrowserSetup().getArguments());
            for (UserProfilePreferenceDto preference : Page.TestConfig.getBrowserSetup().getUserProfilePreferences()) {
                options.setCapability(preference.getPreference(), preference.getValue());
            }
            options.setProfile(new FirefoxProfile() {
                {
                    setAcceptUntrustedCertificates(true);
                    setAssumeUntrustedCertificateIssuer(false);
                }
            });
            return options;
        } catch (Exception e) {
            return options;
        }
    }

    private static void instantiateBrowser(BrowserType browserType) {
        try {
            switch (browserType) {
                case Chrome:
                    driver = new ChromeDriver(getChromeOptions());
                    break;
                case Firefox:
                    driver = new FirefoxDriver(getFirefoxOptions());
                    break;
                case Edge:
                    driver = new EdgeDriver(getEdgeOptions());
                    break;
                default:
                    driver = null;
                    throw new WebDriverException(Messages.ErrorBrowserNotSupported(browserType.toString()));
            }
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        } catch (Exception ex) {
            throw new WebDriverException(Messages.ErrorInstantiatingWebDriver() + Keys.ENTER + Keys.ENTER + ex.getMessage() + Keys.ENTER + Keys.ENTER + (ex.getCause() != null ? ex.getCause().getMessage() : ""));
        }
    }

    private static Object parsePreferenceValue(UserProfilePreferenceDto preference) {
        switch (preference.getDataType()) {
            case Bool:
                return Boolean.parseBoolean(preference.getValue());
            case Double:
                return Double.parseDouble(preference.getValue());
            case Int:
                return Integer.parseInt(preference.getValue());
            case Long:
                return Long.parseLong(preference.getValue());
            case String:
                return preference.getValue();
            default:
                throw new IllegalArgumentException();
        }
    }

    public static Browser getInstance() {
        synchronized (LOCKED) {
            return instance != null ? instance : (instance = new Browser());
        }
    }

    public BrowserType getBrowserType() {
        return browserType;
    }

    public void setBrowserType(BrowserType browserType) {
        if (driver != null) {
            return;
        }
        Browser.browserType = browserType;
        if (browserType != BrowserType.None) {
            instantiateBrowser(browserType);
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getProfileDir() {
        return profileDir;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public void dispose() {
        dispose(true);
    }

    protected void dispose(boolean dispose) {
        if (dispose && !Browser.dispose) {
            if (driver != null) {
                closeApplication();
            }
            Browser.dispose = true;
        }
    }

    public void closeApplication() {
        if (driver == null) {
            throw new WebDriverException(Messages.ErrorDriverNotInstanced());
        }
        try {
            setBrowserType(BrowserType.None);
            driver.close();
            driver.quit();
            driver = null;
        } catch (Exception ignored) {
        }
    }

    public void configureBrowser() {
        if (driver == null) {
            throw new WebDriverException(Messages.ErrorDriverNotInstanced());
        }
        try {
            if (Page.TestConfig.getBrowserSetup().isUseMinimumResolution()) {
                driver.manage().window().setPosition(new Point(0, 0));
                String[] dimensions = Page.TestConfig.getBrowserSetup().getMinimumResolution().split("x");
                int width = Integer.parseInt(dimensions[0]);
                int height = Integer.parseInt(dimensions[1]);
                driver.manage().window().setSize(new Dimension(width, height));
            } else {
                driver.manage().window().maximize();
            }
        } catch (Exception e) {
            driver.manage().window().maximize();
        }
        configureBrowserTime(10, 20);
    }

    @SuppressWarnings("deprecation")
	public void configureBrowserTime(int waitTime, int loadTime) {
        if (driver == null) {
            throw new WebDriverException(Messages.ErrorDriverNotInstanced());
        }
        driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(loadTime, TimeUnit.SECONDS);
    }

    public void goTo(String url) {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException(Messages.ErrorParameterIsNull("url"));
        }
        if (driver == null) {
            throw new WebDriverException(Messages.ErrorDriverNotInstanced());
        }
        if (!url.matches("^(https?|ftp)://.*$")) {
            throw new IllegalArgumentException(Messages.ErrorFormatUrlInvalid(url));
        }
        driver.navigate().to(url);
    }

    public void scroll(WebElement element, boolean scrollDown) {
        if (element == null) {
            throw new IllegalArgumentException(Messages.ErrorParameterIsNull("element"));
        }
        if (driver == null) {
            throw new WebDriverException(Messages.ErrorDriverNotInstanced());
        }
        try {
            ((JavascriptExecutor) getInstance().getDriver()).executeScript(scrollDown ? "arguments[0].scrollIntoView(true);" : "arguments[0].scrollIntoView(false);", element);
        } catch (Exception ignored) {
        }
    }
    
    public void showElementInScreen(WebElement element) {
        if (element == null) {
            throw new IllegalArgumentException(Messages.ErrorParameterIsNull("element"));
        }
        if (driver == null) {
            throw new WebDriverException(Messages.ErrorDriverNotInstanced());
        }
		try {
			String jsFile = Functions.getFileContent("./Core/Javascripts/AngularFinish.js");
			((JavascriptExecutor) getInstance().getDriver()).executeScript(jsFile, element);
		} catch (Exception ignored) {
		}
    }

    public void waitForAngularFinish(double seconds) {
        if (driver == null) {
            throw new WebDriverException(Messages.ErrorDriverNotInstanced());
        }
        try {
        	String jsFile = Functions.getFileContent("./Core/Javascripts/AngularFinish.js");
        	if (seconds > 0) {
                Thread.sleep((int) (seconds * 1000));
            }
            getWait().until(driver -> (boolean) ((JavascriptExecutor) driver).executeScript(jsFile));
        } catch (Exception ignored) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void close() {
        dispose();
    }
}
