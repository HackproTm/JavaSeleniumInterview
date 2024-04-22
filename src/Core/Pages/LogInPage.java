package Core.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import Core.Resources.Messages;
import Core.Dto.*;

public final class LogInPage extends BasePage {
    @FindBy(how = How.ID, using = "email")
    private WebElement fieldEmail;
    
    @FindBy(how = How.ID, using = "password")
    private WebElement fieldPassword;
    
    @FindBy(how = How.XPATH, using = ".//input[contains(@class, 'btn-primary') and @value =  'Login']")
    private WebElement buttonLogin;

    @FindBy(how = How.XPATH, using = ".//a[contains(@class, 'btn-primary') and text() =  'New User']")
    private WebElement buttonNewUser;
    
    public void logIn(UserDto user) {
        if (user == null) throw new IllegalArgumentException(Messages.ErrorParameterIsNull("user"));
        waitForLoadPage(0);
        typeIntoField(fieldEmail, user.getUserName());
        typeIntoField(fieldPassword, user.getPassword());
        clickInElement(buttonLogin, false);
    }
    
    public void newUser() {
    	waitForLoadPage(0);
        clickInElement(buttonNewUser, false);
    }
}
