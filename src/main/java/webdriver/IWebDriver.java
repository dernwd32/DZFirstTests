package webdriver;

import org.openqa.selenium.WebDriver;

public interface IWebDriver {

    int DEFAULT_IMPLICITLY_DURATION = 5;
    String BROWSER_DRIVER = System.getProperty("browser");

    WebDriver webDriverFactory(String mode);

}
