package pages;

import org.openqa.selenium.WebDriver;
import waiters.StandartWaiter;

public abstract class AbstractPage {

    protected String BASE_URL = System.getProperty("base.url");
    protected WebDriver driver;
    protected StandartWaiter standartWaiter;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        standartWaiter = new StandartWaiter(driver);
    }

    public abstract void open();


}
