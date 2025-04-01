import and.points.AndPoints;
import user.UserSteps;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.PerAccPage;
import pages.RegPage;
import pages.ResPassPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AuthorizationTests {

    AndPoints andPoints = new AndPoints();

    private WebDriver driver;
    private UserSteps user;
    private Response creation;
    HomePage homePage;
    PerAccPage perAccPage;
    RegPage regPage;
    ResPassPage resetPage;

    @Before
    public void before() {
        // Загрузка настроек из файла properties
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("config.properties")) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Получаем браузер из свойств или системной переменной
        String browser = properties.getProperty("browser", System.getenv("BROWSER"));
        driver = SetWebDriver.getDriver(browser != null ? browser : "chrome");

        homePage = new HomePage(driver);
        perAccPage = new PerAccPage(driver);
        regPage = new RegPage(driver);
        resetPage = new ResPassPage(driver);

        user = new UserSteps(andPoints.getURL());
        creation = user.createUser();
        driver.get(andPoints.getURL());
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной странице")
    @Description("При клике происходит редирект в ЛК, в котором можно ввести данные аккаунта")
    public void mainPageLogInTest() {
        homePage.logInButtonClick();
        perAccPage.loginFlow(user.getEmail(), user.getPassword());
        Assert.assertTrue(homePage.isConstructorDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("При клике происходит редирект в ЛК, в котором можно ввести данные аккаунта")
    public void personalAccountLogInTest() {
        homePage.personalAccountClick();
        perAccPage.loginFlow(user.getEmail(), user.getPassword());
        Assert.assertTrue(homePage.isConstructorDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации,")
    @Description("При клике происходит редирект в ЛК, в котором можно ввести данные аккаунта")
    public void registrationFormLogInTest() {
        homePage.personalAccountClick();
        perAccPage.registerButtonClick();
        regPage.toLogInButtonClick();
        perAccPage.loginFlow(user.getEmail(), user.getPassword());
        Assert.assertTrue(homePage.isConstructorDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("При клике происходит редирект в ЛК, в котором можно ввести данные аккаунта")
    public void resetPasswordLogInTest() {
        homePage.personalAccountClick();
        perAccPage.resetPasswordButtonClick();
        resetPage.loginButtonClick();
        perAccPage.loginFlow(user.getEmail(), user.getPassword());
        Assert.assertTrue(homePage.isConstructorDisplayed());
    }

    @After
    public void after() {
        user.getTokenAndDeleteUser(creation);
        driver.quit();
    }
}
