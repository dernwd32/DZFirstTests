import asserts.AssertWithLog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import tools.GenerateRandoms;
import webdriver.WebDriverFactory;

import static org.junit.jupiter.api.Assertions.assertAll;


public class Third_Test {
    //подключаем логгер
    private static final Logger logger = LogManager.getLogger(Third_Test.class);
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
                "maximize"
        );

        mainPage = new MainPage(driver);
        assertWithLog = new AssertWithLog(driver, logger);
        mainPage.open();
    }

    //  @Test
    @Test
    @DisplayName("Третий тест домашки: отправка формы")
    void ifTextGotValuesFromForm()  {

        String checkingName = generateRandoms.generateString(10);
        String checkingEmail = generateRandoms.generateEmail();

        mainPage.writeIntoInputName(checkingName);
        mainPage.writeIntoInputEmail(checkingEmail);
        mainPage.submitForm();
        boolean ifDivContainsValuesWhenDisplayed =
                mainPage.ifMessageBoxMatchesValuesWhichCameFromForm(checkingName, checkingEmail);

        String checkingName2 = generateRandoms.generateString(8);
        String checkingEmail2 = generateRandoms.generateEmail();

        //System.out.printf("%s, %s, %s, %s%n", checkingName, checkingEmail, checkingName2, checkingEmail2);

        mainPage.clearInputName();
        mainPage.clearInputEmail();
        mainPage.writeIntoInputName(checkingName2);
        mainPage.writeIntoInputEmail(checkingEmail2);
        mainPage.clickHideMessageBox();
        mainPage.submitForm();
        // потому что селениум 4 считает, что раз пользователь не видит этих значений, значит их нет.
        // Хотя они есть, и даже в спрятанный блок значения из формы исправно передаются
        boolean ifDivDoesntContainValuesWhenHidden =
                !mainPage.ifMessageBoxMatchesValuesWhichCameFromForm(checkingName, checkingEmail);

        assertAll(
                () -> assertWithLog.assertWithLog( ifDivContainsValuesWhenDisplayed,
                        "ifTextGotValuesFromForm > ifDivContainsValuesWhenDisplayed"),

                () -> assertWithLog.assertWithLog( ifDivDoesntContainValuesWhenHidden,
                        "ifTextGotValuesFromForm > ifDivDoesntContainValuesWhenHidden")
        );
    }


    @AfterEach
    void tearDown() {
        driver.close();
    }
}
