import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import webdriver.WebDriverFactory;


public class SecondJ_Test {
    //подключаем логгер
    private static final Logger logger = LogManager.getLogger(SecondJ_Test.class);
    WebDriver driver;
    WebDriverFactory webDriverFactory = new WebDriverFactory();

    MainPage mainPage = null;

    SoftAssertions softAssertions = new SoftAssertions();

    @BeforeEach
    void beforeEach() {
        //передаем аргументы запуска и получаем готовый вебдрайвер по заданным параметрам
        driver = webDriverFactory.webDriverFactory(
                "-kiosk"
        );

        mainPage = new MainPage(driver);
        mainPage.open();
    }

    //  @Test
    @Test
    @DisplayName("Второй тест домашки: модалка")
    void ifModalShowAndHideIsCorrect()  {

        softAssertions.assertThat( mainPage.isInvisibleMyModal()).isTrue();
        mainPage.clickOpenModal();
        softAssertions.assertThat( mainPage.isVisibleMyModal()).isTrue();
        mainPage.clickCloseModal();
        softAssertions.assertThat( mainPage.isInvisibleMyModal()).isTrue();
        softAssertions.assertAll();
    }


    @AfterEach
    void tearDown() {
        if (driver != null) driver.close();
    }
}
