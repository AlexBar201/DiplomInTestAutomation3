package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResPassPage {

    private final WebDriver driver;

    public ResPassPage(WebDriver driver) {
        this.driver = driver;
    }

    public static final By EMAIL = By.className("input pr-6 pl-6 input_type_text input_size_default");
    public static final By TO_RESET_BUTTON = By.className("button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa");
    public static final By TO_LOG_IN = By.className("Auth_link__1fOlj");

    @Step("Клик на кнопку Войти")
    public void loginButtonClick() {
        driver.findElement(TO_LOG_IN).click();
    }
}
