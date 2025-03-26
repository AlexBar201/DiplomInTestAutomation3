package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private static final By CONSTRUCTOR = By.xpath(".//p[text()='Конструктор']");
    private static final By LOGO = By.className("AppHeader_header__logo__2D0X2");
    private static final By PERSONAL_ACCOUNT = By.xpath(".//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Личный Кабинет']");
    private static final By LOG_IN_BUTTON = By.xpath(".//button[contains(@class,'button_button__33qZ0 ')]");
    private static final By BURGER_CONSTRUCTOR = By.className("BurgerIngredients_ingredients__1N8v2");
    private static final By BUNS = By.xpath(".//span[text()='Булки']");
    private static final By BUNS_ELEMENT = By.xpath(".//img[@class='BurgerIngredient_ingredient__image__3e-07 ml-4 mr-4']");
    private static final By SAUCE = By.xpath(".//span[text()='Соусы']");
    private static final By SAUCE_ELEMENT = By.xpath(".//img[@alt='Соус Spicy-X']");
    private static final By FILLING = By.xpath(".//span[text()='Начинки']");
    private static final By FILLING_ELEMENT = By.xpath(".//img[@alt='Мясо бессмертных моллюсков Protostomia']");

    @Step("Кликаем на кнопку Личный кабинет")
    public void personalAccountClick() {
        driver.findElement(PERSONAL_ACCOUNT).click();
    }

    @Step("Кликаем на кнопку Войти в аккаунт")
    public void logInButtonClick() {
        driver.findElement(LOG_IN_BUTTON).click();
    }

    @Step("Кликаем на кнопку Конструктор")
    public void constructorButtonClick() {
        driver.findElement(CONSTRUCTOR).click();
    }

    @Step("Кликаем на лого")
    public void logoClick() {
        driver.findElement(LOGO).click();
    }

    @Step("Проверка редиректа при нажатии на Конструктор")
    public boolean isConstructorDisplayed() {
        WebElement constructor = new WebDriverWait(driver,5)
                .until(ExpectedConditions.visibilityOfElementLocated(BURGER_CONSTRUCTOR));
        return constructor.isDisplayed();
    }

    @Step("Проверка редиректа при нажатии на лого")
    public boolean isLogoWork() {
        logoClick();
        WebElement constructor = new WebDriverWait(driver,5)
                .until(ExpectedConditions.visibilityOfElementLocated(BURGER_CONSTRUCTOR));
        return constructor.isDisplayed();
    }

    @Step("Клик на раздел Булочки")
    public void bunsClick(){
        driver.findElement(BUNS).click();
    }

    @Step("Клик на раздел Соусы")
    public void sauceClick(){
        driver.findElement(SAUCE).click();
    }

    @Step("Клик на раздел Начинки")
    public void fillingClick(){
        driver.findElement(FILLING).click();
    }

    @Step("Ожидание появления элемента из раздела Булочки")
    public boolean isBunsElementDisplayed() {
//        bunsClick();
        WebElement element = new WebDriverWait(driver,5)
                .until(ExpectedConditions.visibilityOfElementLocated(BUNS_ELEMENT));
        return element.isDisplayed();
    }

    @Step("Ожидание появление элемента из раздела Соусы")
    public boolean isSauceElementDisplayed() {
        sauceClick();
        WebElement element = new WebDriverWait(driver,5)
                .until(ExpectedConditions.visibilityOfElementLocated(SAUCE_ELEMENT));
        return element.isDisplayed();
    }

    @Step("Ожидание появления элемента из раздела Начинки")
    public boolean isFillingElementDisplayed() {
        fillingClick();
        WebElement element = new WebDriverWait(driver,5)
                .until(ExpectedConditions.visibilityOfElementLocated(FILLING_ELEMENT));
        return element.isDisplayed();
    }
}
