package Core.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

import Core.System.Browser;
import Core.Resources.Constants;
import Core.Resources.Messages;

public class BasePage {

    private final By locatorModal = By.className("modal-body");
    private final By locatorModalWait = By.id("updateProgress");

    public boolean canClean(WebElement element) {
        try {
            configMinTimeBrowser();
            boolean status = element != null && element.getTagName().equals("input") && element.getAttribute("type").matches("text|search|email");
            configMaxTimeBrowser();
            return status;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean canInteract(WebElement element) {
        try {
            configMinTimeBrowser();
            boolean status = element != null && element.isDisplayed() && element.isEnabled();
            configMaxTimeBrowser();
            return status;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean canType(WebElement element) {
        try {
            configMinTimeBrowser();
            boolean status = element != null && (element.getTagName().equals("input") || element.getTagName().equals("select") || element.getTagName().equals("textarea"));
            configMaxTimeBrowser();
            return status;
        } catch (Exception e) {
            return false;
        }
    }

    public void activeCheckElement(WebElement element, boolean active) {
        if (element.isSelected() != active) {
            waitForElement(element);
            if (canInteract(element)) {
                showElementInScreen(element);
                element.sendKeys(Keys.SPACE);
            } else {
                throw new InvalidElementStateException(Messages.ErrorElementNotAvailable("Check"));
            }
        }
    }

    public void clickInElement(WebElement element, boolean forceClick) {
        if (forceClick) {
            // ToDo force with Javascript the click event
        } else {
            waitForElement(element);
            if (canInteract(element)) {
                showElementInScreen(element);
                element.click();
            } else {
                throw new InvalidElementStateException(Messages.ErrorElementNotAvailable("Element"));
            }
        }
    }

    public void configMaxTimeBrowser() {
        Browser.getInstance().configureBrowserTime(10, 20);
    }

    public void configMinTimeBrowser() {
        Browser.getInstance().configureBrowserTime(2, 5);
    }

    public boolean isInactive(WebElement element) {
        try {
            configMinTimeBrowser();
            boolean status = element != null && element.isDisplayed() && !element.isEnabled();
            configMaxTimeBrowser();
            return status;
        } catch (Exception e) {
            return false;
        }
    }

    public void moveMouseOverElement(WebElement element) {
        Actions overAction = new Actions(Browser.getInstance().getDriver());
        overAction.moveToElement(element).perform();
    }

    public void rightClickInElement(WebElement element, boolean forceClick) {
        if (forceClick) {
            // ToDo force with Javascript the click event
        } else {
            if (canInteract(element)) {
                showElementInScreen(element);
                Actions overAction = new Actions(Browser.getInstance().getDriver());
                overAction.contextClick(element).perform();
            } else {
                throw new InvalidElementStateException(Messages.ErrorElementNotAvailable("Element"));
            }
        }
    }

    public void scrollDownAction(WebElement element) {
        Browser.getInstance().scroll(element, true);
    }

    public void scrollUpAction(WebElement element) {
        Browser.getInstance().scroll(element, false);
    }

    public void showElementInScreen(WebElement element) {
        Browser.getInstance().showElementInScreen(element);
    }

    public void typeIntoField(WebElement element, String text) {
        if (text == null) {
            text = "";
        }
        waitForElement(element);
        if (canType(element) && canInteract(element)) {
            if (!element.getTagName().toLowerCase().equals("select")) {
                element.clear();
            }
            if (!text.isEmpty()) {
                element.sendKeys(text);
            }
        }
    }

    public boolean validateTextModal(String modalText) {
        if (modalText == null) {
            throw new IllegalArgumentException(Messages.ErrorParameterIsNull("modalText"));
        }
        waitIfProgressModalIsPresent();
        List<WebElement> modals = Browser.getInstance().getDriver().findElements(locatorModal);
        if (modals == null || modals.isEmpty()) {
            throw new NoSuchElementException(Messages.ErrorElementNotAvailable(locatorModal.toString()));
        }
        WebElement modalElement = modals.stream().filter(WebElement::isDisplayed).findFirst().orElse(null);
        if (modalElement == null) {
            throw new NoSuchElementException(Messages.ErrorElementNotAvailable(locatorModal.toString()));
        }
        return modalElement.findElement(By.tagName("span")).getText().contains(modalText);
    }

    public boolean verifyVisibilityAndContentOfLabel(WebElement labelElement, String text) {
        waitForElement(labelElement);
        return labelElement.isDisplayed() && labelElement.getText().equals(text);
    }

    public void waitForElement(By locatorElement) {
        waitIfProgressModalIsPresent();
        Browser.getInstance().getWait().until(ExpectedConditions.visibilityOfElementLocated(locatorElement));
    }

    public void waitForElement(WebElement element) {
        waitIfProgressModalIsPresent();
        Browser.getInstance().getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForLoadPage(double seconds) {
        Browser.getInstance().waitForAngularFinish(seconds);
    }

    public void waitForMenuElement(By locatorElement) {
        Browser.getInstance().getWait().until(ExpectedConditions.visibilityOfElementLocated(locatorElement));
    }

    public void waitIfProgressModalIsPresent() {
        try {
            configMinTimeBrowser();
            WebElement modal;
            String visible;
            do {
                Browser.getInstance().getWait().until(ExpectedConditions.invisibilityOfElementLocated(locatorModalWait));
                modal = Browser.getInstance().getDriver().findElement(locatorModalWait);
                visible = modal.getAttribute(Constants.HTML_ATTRIBUTE_STYLE).trim().toLowerCase().replace(" ", "");
            } while (!visible.isEmpty() && !visible.equals(Constants.STYLE_OF_WAIT_MODAL) && !modal.isDisplayed());
            configMaxTimeBrowser();
        } catch (Exception ignored) {
            //Nothing
        }
    }
}
