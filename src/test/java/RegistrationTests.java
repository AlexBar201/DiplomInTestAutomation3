import and.points.AndPoints;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.PerAccPage;
import pages.RegPage;
import steps.AuthorizationAndToken;

public class RegistrationTests {

    AndPoints andPoints = new AndPoints();

    AuthorizationAndToken delete = new AuthorizationAndToken();
    Faker faker = new Faker();
    private final String email = faker.internet().emailAddress();
    private final String password = faker.internet().password(6,8);
    private final String name = faker.name().firstName();

    private WebDriver driver;
    HomePage homePage;
    PerAccPage perAccPage;
    RegPage register;

    @Before
    public void before() {
        String browser = System.getProperty("browser","chrome");
        driver = SetWebDriver.getDriver(browser);

        homePage = new HomePage(driver);
        perAccPage = new PerAccPage(driver);
        register = new RegPage(driver);

        driver.get(andPoints.getURL());
        homePage.personalAccountClick();
        perAccPage.registerButtonClick();

        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Для успешной регистрации нужно заполнить все обязательные поля и кликнуть на Зарегистрироваться")
    public void successfulRegistrationTest() {
        register.fillRegForm(email, name, password);
        Assert.assertTrue(register.isPersonalAccountAppear());
        delete.deleteUser(delete.getAccessToken(delete.authorizationExistingUser(email, password)));
    }

    @Test
    @DisplayName("Появлении ошибки при вводе некорректного пароля")
    @Description("Ввести в поле Пароль значение длиной менее 6 символов")
    public void registrationWithIncorrectPasswordTest() {
        register.wrongRegistration();
        Assert.assertTrue(register.isErrorMessageDisplayed());
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
