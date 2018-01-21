import org.testng.Assert;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import com.selenium.test.webtestsbase.WebDriverFactory;

import com.selenium.test.pages.RegistrationPage;

/**
 * Created by Polina on 20.01.2018.
 */
public class RegistrationTests {

    @BeforeTest
    public void beforeMethod(){
        System.setProperty("webdriver.chrome.driver", "C:/Program Files/chromedriver.exe");
        WebDriverFactory.startBrowser(true);
    }

    @DataProvider
    public Object[][] testAtLeastOneRequiredFieldIsEmptyData(){
        return new Object[][]
        {
            { "", "Bobkov", 1, "999999999999", "cat", "av@mail.ru", "qwerty11", "qwerty11" },
            { "Kate", "", 1, "999999999999", "cat", "av@mail.ru", "qwerty11", "qwerty11" },
            { "Matvey12", "111111=+", 0, "mynomber", "friend", "312@g.r", "12noterdam12", "depari1313" },
            { "Kate", "Bobkova", 1, "", "cat", "av@mail.ru", "qwerty11", "qwerty11" },
            { "Kate", "Bobkova", 1, "98759654", "", "av@mail.ru", "qwerty11", "qwerty11" },
            { "Kate", "Bobkova", 1, "675432", "cat", "", "qwerty11", "qwerty11" },
            { "Kate", "Bobkova", 1, "123658778067", "cat", "av@mail.ru", "", "qwerty11" },
            { "Kate", "Bobkova", 1, "111111111", "cat", "av@mail.ru", "qwerty11", "" },
            { "", "", 0, "", "", "", "", "" }
        };
    }

    @Test (dataProvider = "testAtLeastOneRequiredFieldIsEmptyData")
    public void testAtLeastOneRequiredFieldIsEmpty(
            String firstName, String lastName, int hobby, String phone,
            String username, String email, String password, String confirmPassword
    ){
        RegistrationPage page = new RegistrationPage();
        page.setFirstName(firstName);
        page.setLastName(lastName);
        page.setHobby(hobby);
        page.setPhone(phone);
        page.setUserName(username);
        page.setEmail(email);
        page.setPassword(password);
        page.setConfirmPassword(confirmPassword);

        page.submitButton();
        Assert.assertTrue(page.isErrorExists());
    }

    @DataProvider
    public Object[][] testPasswordsMatchData(){
        return new Object[][]{
            {"qwerty11","qwerty22"},
            {"qwerty11","1"}
        };
    }

    @Test (dataProvider = "testPasswordsMatchData")
    public void testPasswordsMatch(String passwordFieldValue, String passwordConfirmFieldValue){
        RegistrationPage page = new RegistrationPage();
        page.setPassword(passwordFieldValue);
        page.setConfirmPassword(passwordConfirmFieldValue);
        page.submitButton();
        String errorText = page.getErrorText();
        Assert.assertEquals("* Fields do not match", errorText);
    }

    @AfterTest
    public void afterTest(){
        WebDriverFactory.finishBrowser();
    }
}
