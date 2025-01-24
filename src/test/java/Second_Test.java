import asserts.AssertWithLog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import webdriver.WebDriverFactory;

import static org.junit.jupiter.api.Assertions.assertAll;


public class Second_Test {
    //подключаем логгер
    private static final Logger logger = LogManager.getLogger(Second_Test.class);
    WebDriver driver;
    WebDriverFactory webDriverFactory = new WebDriverFactory();


    //подключаем класс-обёртку, объединяющую логгирование и assertTrue
    AssertWithLog assertWithLog = null;
    MainPage mainPage = null;

    SoftAssertions softAssertions = new SoftAssertions();

    @BeforeEach
    void beforeEach() {
        //передаем аргументы запуска и получаем готовый вебдрайвер по заданным параметрам
        driver = webDriverFactory.webDriverFactory(
                "-kiosk"
        );

        mainPage = new MainPage(driver);
        assertWithLog = new AssertWithLog(driver, logger);
        mainPage.open();
    }

    //  @Test
    @Test
    @DisplayName("Второй тест домашки: модалка")
    void ifModalShowAndHideIsCorrect()  {

        //видимость до открытия
        boolean invisibleBeforeOpening = mainPage.isInvisibleMyModal();


        mainPage.clickOpenModal();

        //видимость после открытия
        boolean visibleAfterOpening = mainPage.isVisibleMyModal();

        mainPage.clickCloseModal();

        //видимость после закрытия
        boolean invisibleAfterClosing = mainPage.isInvisibleMyModal();

        assertAll(
                () -> assertWithLog.assertWithLog( invisibleBeforeOpening,
                        "ifModalShowAndHideIsCorrect > invisibleBeforeOpening"),

                () -> assertWithLog.assertWithLog( visibleAfterOpening,
                        "ifModalShowAndHideIsCorrect > visibleAfterOpening"),

                () -> assertWithLog.assertWithLog( invisibleAfterClosing,
                        "ifModalShowAndHideIsCorrect > invisibleAfterClosing")
        );

    }


    @AfterEach
    void tearDown() {
        if (driver != null) driver.close();
    }
}
