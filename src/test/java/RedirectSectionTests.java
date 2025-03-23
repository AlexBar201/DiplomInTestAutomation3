import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pagesStellarBurger.HomePage;
import pagesStellarBurger.PerAccPage;

public class RedirectSectionTests {
    private static final String URL = "https://stellarburgers.nomoreparties.site/";
    private WebDriver driver;

    HomePage homePage;
    PerAccPage perAccPage;

    @Before
    public void before() {
        String browser = System.getProperty("browser","chrome");
        driver = SetWebDriver.getDriver(browser);

        homePage = new HomePage(driver);
        perAccPage = new PerAccPage(driver);
        driver.get(URL);
    }

    @Test
    @DisplayName("Редирект по клику на «Личный кабинет».")
    @Description("При клике на кнопку Личный кабинет происходит редирект в ЛК")
    public void goToPersonalAccountTest() {
        homePage.personalAccountClick();
        Assert.assertTrue(perAccPage.isPageDisplayed());
    }

    @Test
    @DisplayName("Редирект по клику на «Конструктор»")
    @Description("При клике на кнопку Конструктор происходит редирект к конструктору бургеров")
    public void goToConstructorTest() {
        homePage.personalAccountClick();
        homePage.constructorButtonClick();
        Assert.assertTrue(homePage.isConstructorDisplayed());
    }

    @Test
    @DisplayName("Редирект по клику на логотип Stellar Burgers")
    @Description("При клике на логотип происходит редирект на главную страницу")
    public void goToLogoTest() {
        homePage.personalAccountClick();
        Assert.assertTrue(homePage.isLogoWork());
    }

    @Test
    @DisplayName("Редирект к разделу «Булки»")
    @Description("При клике на раздел «Булки» отображаются доступные булочки")
    public void goToBunsTest() {
        Assert.assertTrue(homePage.isBunsElementDisplayed());
    }

    @Test
    @DisplayName("Редирект к разделу «Соусы»")
    @Description("При клике на раздел «Соусы» отображаются доступные соусы")
    public void goToSauceTest() {
        Assert.assertTrue(homePage.isSauceElementDisplayed());
    }

    @Test
    @DisplayName("Редирект к разделу «Начинки»")
    @Description("При клике на раздел «Начинки» отображаются доступные начинки")
    public void goToFillingTest() {
        homePage.fillingClick();
        Assert.assertTrue(homePage.isFillingElementDisplayed());
    }

    @After
    public void after() {
        driver.quit();
    }
}
