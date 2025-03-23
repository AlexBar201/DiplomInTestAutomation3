import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SetWebDriver {

    public static WebDriver getDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver","C:\\Program Files\\WebDriver\\bin\\chromedriver-win64\\chromedriver.exe");
                return new ChromeDriver();
            case "yandex":
                System.setProperty("webdriver.chrome.driver","C:\\Program Files\\WebDriver\\bin\\yandexdriver-win64\\yandexdriver.exe");
                return new ChromeDriver();
            default:
                throw new IllegalArgumentException("Неподдерживаемый браузер:" + browser);
        }
    }
}
