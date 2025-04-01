package pages;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.ref.SoftReference;

import static pages.PerAccPage.PA_EMAIL;

public class RegPage {

    private final WebDriver driver;

    public RegPage(WebDriver driver) {
        this.driver = driver;
    }

    Faker faker = new Faker();
    private final String email = faker.internet().emailAddress();
    private final String password = faker.internet().password(6,8);
    private final String name = faker.name().firstName();
    private final String WrongPassword = faker.internet().password(2,5);

    public static final By REG_NAME = By.xpath(".//fieldset[1]/div/div/input");
    public static final By REG_EMAIL = By.xpath(".//fieldset[2]/div/div/input");
    public static final By REG_PASSWORD = By.xpath(".//input[@type = 'password']");
    public static final By TO_REGISTER_BUTTON = By.xpath(".//button[text()='Зарегистрироваться']");
    public static final By TO_LOG_IN_BUTTON = By.className("Auth_link__1fOlj");
    private static final By ERROR_MESSAGE = By.xpath(".//p[@class = 'input__error text_type_main-default']");

    @Step("Вводим имя в поле Name")
    public void inputName(String name) {
        driver.findElement(REG_NAME).click();
        new WebDriverWait(driver,5)
                .until(ExpectedConditions.visibilityOfElementLocated(REG_NAME));
        driver.findElement(REG_NAME).sendKeys(name);
    }

    @Step("Вводим почту в поле Email")
    public void inputEmail(String email) {
        driver.findElement(REG_EMAIL).click();
        new WebDriverWait(driver,5)
                .until(ExpectedConditions.visibilityOfElementLocated(REG_EMAIL));
        driver.findElement(REG_EMAIL).sendKeys(email);
    }

    @Step("Вводим пароль в поле Пароль")
    public void inputPassword(String password) {
        driver.findElement(REG_PASSWORD).click();
        new WebDriverWait(driver,5)
                .until(ExpectedConditions.visibilityOfElementLocated(REG_PASSWORD));
        driver.findElement(REG_PASSWORD).sendKeys(password);
    }

    @Step("Вводим некорректный пароль")
    public void inputIncorrectPassword() {
        driver.findElement(REG_PASSWORD).click();
        new WebDriverWait(driver,5)
                .until(ExpectedConditions.visibilityOfElementLocated(REG_PASSWORD));
        driver.findElement(REG_PASSWORD).sendKeys(WrongPassword);
    }

    @Step("Кликаем на кнопку Зарегистрироваться")
    public void regButtonClick() {
        driver.findElement(TO_REGISTER_BUTTON).click();
    }

    @Step("Ожидание появления сообщения о некорректном пароле")
    public boolean isErrorMessageDisplayed() {
        WebElement error = new WebDriverWait(driver,5)
                .until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE));
        return error.isDisplayed();
    }

    public void fillRegForm(String email, String name, String password) {
        inputName(name);
        inputEmail(email);
        inputPassword(password);
        regButtonClick();
    }

    public void wrongRegistration() {
        inputName(name);
        inputEmail(email);
        inputIncorrectPassword();
        regButtonClick();
        isErrorMessageDisplayed();
    }

    @Step("Проверяем редирект в личный кабинет после успешной регистрации")
    public boolean isPersonalAccountAppear() {
        WebElement account = new WebDriverWait(driver,5)
                .until(ExpectedConditions.visibilityOfElementLocated(PA_EMAIL));
        return account.isDisplayed();
    }

    @Step("Клик на кнопку Войти")
    public void toLogInButtonClick() {
        driver.findElement(TO_LOG_IN_BUTTON).click();
    }
}
