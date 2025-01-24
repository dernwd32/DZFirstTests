import asserts.AssertWithLog;
import com.github.javafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import webdriver.WebDriverFactory;

import java.util.Locale;


public class First_Test {
    //подключаем логгер
    private static final Logger logger = LogManager.getLogger(First_Test.class);
    WebDriver driver;
    WebDriverFactory webDriverFactory = new WebDriverFactory();

    //подключаем класс-обёртку, объединяющую логгирование и assertTrue
    AssertWithLog assertWithLog = null;

    MainPage mainPage = null;
    Faker faker = new Faker(new Locale("ru"));

    @BeforeEach
    void beforeEach() {
        //передаем аргументы запуска и получаем готовый вебдрайвер по заданным параметрам
        driver = webDriverFactory.webDriverFactory(
                "-headless"
        );

        mainPage = new MainPage(driver);
        assertWithLog = new AssertWithLog(driver, logger);
        mainPage.open();

    }

    //  @Test
    @Test
    @DisplayName("Первый тест домашки: поле ввода")
    void ifEqualsInputText()  {
        String someText = faker.address().fullAddress() + " " + faker.name().name();//= generateRandoms.generateString(12);
        System.out.println(someText);

        mainPage.writeSomeTextIntoInput(someText);
        String textValue = mainPage.getTextFromInput();
        assertWithLog.assertWithLog( someText.equals(textValue), "ifEqualsInputText: " + someText );
    }


    @AfterEach
    void tearDown() {
        if (driver != null) driver.close();
    }
}
