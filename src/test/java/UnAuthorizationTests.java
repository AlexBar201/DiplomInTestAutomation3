import apiCreateUser.UserSteps;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pagesStellarBurger.HomePage;
import pagesStellarBurger.PerAccPage;

public class UnAuthorizationTests {

    private static final String URL = "https://stellarburgers.nomoreparties.site/";

    private WebDriver driver;
    private UserSteps user;
    private Response creation;
    HomePage homePage;
    PerAccPage perAccPage;

    @Before
    public void before() {
        String browser = System.getProperty("browser","chrome");
        driver = SetWebDriver.getDriver(browser);

        homePage = new HomePage(driver);
        perAccPage = new PerAccPage(driver);

        user = new UserSteps(URL);
        creation = user.createUser();
        driver.get(URL);
    }

    @Test
    @DisplayName("Проверка выхода из аккаунта")
    @Description("Необходимо войти в аккаунт и перейти в ЛК, там кликнуть на кнопку Выйти")
    public void logOutTest() {
        homePage.personalAccountClick();
        perAccPage.loginFlow(user.getEmail(), user.getPassword());
        homePage.personalAccountClick();
        perAccPage.logOutButtonClick();
        Assert.assertTrue(perAccPage.isPageDisplayed());
    }

    @After
    public void after() {
        user.getTokenAndDeleteUser(creation);
        driver.quit();
    }
}
