import asserts.AssertWithLog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import tools.GenerateRandoms;
import webdriver.WebDriverFactory;



public class First_Test {
    //подключаем логгер
    private static final Logger logger = LogManager.getLogger(First_Test.class);
    WebDriver driver;
    WebDriverFactory webDriverFactory = new WebDriverFactory();
    GenerateRandoms generateRandoms = new GenerateRandoms();

    //подключаем класс-обёртку, объединяющую логгирование и assertTrue
    AssertWithLog assertWithLog = null;
    MainPage mainPage = null;

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
        String someText = generateRandoms.generateString(12);
        mainPage.writeSomeTextIntoInput(someText);
        String textValue = mainPage.getTextFromInput();
        assertWithLog.assertWithLog( someText.equals(textValue) );
    }


    @AfterEach
    void tearDown() {
        driver.close();
    }
}
