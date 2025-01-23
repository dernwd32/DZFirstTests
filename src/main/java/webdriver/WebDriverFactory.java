package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;


public class WebDriverFactory implements IWebDriver{


    @Override
    public WebDriver webDriverFactory(String mode)  {

        WebDriver driver;

        /* Эти две лишние строчки только для того, чтоб  иметь возможность запускать не из консоли и без конфига
         "неправильной зеленой стрелочкой теста" для удобства, для отладки. Потом можно выбросить их и switch'ить
         константу BROWSER_DRIVER (хоть это и странно само по себе) */
        String browser = BROWSER_DRIVER;
        if (browser == null) browser = "firefox";

        switch (browser) {

            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions().addArguments(mode);
                driver = new FirefoxDriver(options);
            }
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions().addArguments(mode);
                driver = new ChromeDriver(options);
            }
            default -> {
               throw new RuntimeException(String.format("Browser <%s> is not supported by the factory", BROWSER_DRIVER));
            }

        }

        if (mode.equals("-maximize")) driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_IMPLICITLY_DURATION));

        return driver;
    }
}
