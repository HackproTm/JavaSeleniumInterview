package com.hackpro.TestCore.Ui.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.jetbrains.annotations.NotNull;

import Hackpro.TestCore.Resources.Messages;

public final class LogInPage extends BasePage {
    @FindBy(how = How.Id, using = "email")
    private WebElement fieldEmail;
    
    @FindBy(how = How.Id, using = "password")
    private WebElement fieldPassword;
    
    @FindBy(how = How.Id, using = "btnLogin")
    private WebElement buttonLogin;

    @FindBy(how = How.Id, using = "btnNext")
    private WebElement buttonNewUser;

    Messages.
    
    public void logIn(UserDto user) {
        if (user == null) throw new IllegalArgumentException(Messages.ErrorParameterIsNull("user"));
        waitForLoadPage();
        typeIntoField(fieldEmail, user.getUserName());
        typeIntoField(fieldPassword, user.getPassword());
        clickInElement(buttonLogin);
    }
    
    public void newUser() {
    	waitForLoadPage();
        clickInElement(buttonNewUser);
    }
}
