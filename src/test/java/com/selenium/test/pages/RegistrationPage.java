package com.selenium.test.pages;

import com.selenium.test.webtestsbase.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

/**
 * Created by Polina on 21.01.2018.
 */
public class RegistrationPage extends BasePage {

    private static final String PAGE_URL = "http://demoqa.com/registration/";

    @FindBy(id = "name_3_firstname")
    private WebElement firstName;

    @FindBy(id = "name_3_lastname")
    private WebElement lastName;

    @FindBy(xpath = "//*[@id='pie_register']/li[3]/div/div/input[1]")
    private WebElement danceHobby;

    @FindBy(xpath = "//*[@id='pie_register']/li[3]/div/div/input[2]")
    private WebElement readingHobby;

    @FindBy(xpath = "//*[@id='pie_register']/li[3]/div/div/input[3]")
    private WebElement cricketHobby;

    @FindBy(id = "phone_9")
    private WebElement phone;

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "email_1")
    private WebElement email;

    @FindBy(id = "password_2")
    private WebElement passwordField;

    @FindBy(id = "confirm_password_password_2")
    private WebElement passwordConfirmField;

    @FindBy(name = "pie_submit")
    private WebElement submitBtn;

    @FindBy(css = ".legend.error")
    private List<WebElement> errors;

    @FindBy(xpath = "//*[@id='pie_register']/li[12]/div/div/span")
    private WebElement errorField;

    public RegistrationPage(){
        super(true);
    }

    @Override
    protected void openPage() {
        getDriver().get(PAGE_URL);
    }

    @Override
    public boolean isPageOpened() {
        return passwordField.isDisplayed();
    }

    public void setFirstName(String name){
        firstName.sendKeys(name);
    }

    public void setLastName(String lastNameValue){
        lastName.sendKeys(lastNameValue);
    }

    public void setHobby(int hobbyValue){
        switch (hobbyValue) {
            case 1:
                danceHobby.click();
                break;
            case 2:
                readingHobby.click();
                break;
            case 3:
                cricketHobby.click();
                break;
        }
    }

    public void setPhone(String phoneValue){
        phone.sendKeys(phoneValue);
    }

    public void setUserName(String usernameValue){
        username.sendKeys(usernameValue);
    }

    public void setEmail(String emailValue){
        email.sendKeys(emailValue);
    }

    public void setPassword(String password){
        passwordField.sendKeys(password);
    }

    public void setConfirmPassword(String confirmPassword){
        passwordConfirmField.sendKeys(confirmPassword);
    }

    public void submitButton(){
        submitBtn.submit();
    }

    public boolean isErrorExists(){
        for (WebElement error : errors){
            if (error.getText().equals("* This field is required"))
                return true;
        }
        return false;
    }

    public String getErrorText(){
        return errorField.getText();
    }
}
