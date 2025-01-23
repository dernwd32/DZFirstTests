package pages;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {
    String BASE_URL =  System.getProperty("base.url");
    public abstract void open();

}
