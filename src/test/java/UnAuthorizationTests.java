import and.points.AndPoints;
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
import pagesStellarBurger.RegPage;
import pagesStellarBurger.ResPassPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UnAuthorizationTests {

    AndPoints andPoints = new AndPoints();

    private WebDriver driver;
    private UserSteps user;
    private Response creation;
    HomePage homePage;
    PerAccPage perAccPage;

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

        user = new UserSteps(andPoints.getURL());
        creation = user.createUser();
        driver.get(andPoints.getURL());
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
