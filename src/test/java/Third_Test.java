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

import static org.junit.jupiter.api.Assertions.assertAll;


public class Third_Test {
    //подключаем логгер
    private static final Logger logger = LogManager.getLogger(Third_Test.class);
    WebDriver driver;
    WebDriverFactory webDriverFactory = new WebDriverFactory();


    //подключаем класс-обёртку, объединяющую логгирование и assertTrue
    AssertWithLog assertWithLog = null;
    MainPage mainPage = null;

    Faker faker = new Faker(new Locale("it"));

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

        String checkingName = faker.name().name();
        String checkingEmail = faker.internet().emailAddress();

        mainPage.writeIntoInputName(checkingName);
        mainPage.writeIntoInputEmail(checkingEmail);
        mainPage.clickForSubmitForm();
        //mainPage.submitForm();
        boolean ifDivContainsValuesWhenDisplayed =
                mainPage.ifMessageBoxMatchesValuesWhichCameFromForm(checkingName, checkingEmail);

        String checkingName2 = faker.name().name();
        String checkingEmail2 = faker.internet().emailAddress();

        mainPage.clearInputName();
        mainPage.clearInputEmail();
        mainPage.writeIntoInputName(checkingName2);
        mainPage.writeIntoInputEmail(checkingEmail2);
        mainPage.clickHideMessageBox();
        mainPage.clickForSubmitForm();
        //mainPage.submitForm();
        // потому что селениум 4 считает, что раз пользователь не видит этих значений, значит их нет.
        // Хотя они есть, и даже в спрятанный блок значения из формы исправно передаются
        boolean ifDivDoesntContainValuesWhenHidden =
                !mainPage.ifMessageBoxMatchesValuesWhichCameFromForm(checkingName, checkingEmail);

        assertAll(
                () -> assertWithLog.assertWithLog( ifDivContainsValuesWhenDisplayed,
                        "ifTextGotValuesFromForm > ifDivContainsValuesWhenDisplayed: " + checkingName + " " + checkingEmail),

                () -> assertWithLog.assertWithLog( ifDivDoesntContainValuesWhenHidden,
                        "ifTextGotValuesFromForm > ifDivDoesntContainValuesWhenHidden: " + checkingName2 + " " + checkingEmail2)
        );
    }


    @AfterEach
    void tearDown() {
        if (driver != null) driver.close();
    }
}
