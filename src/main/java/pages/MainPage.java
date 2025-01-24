package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends AbstractPage{


    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(BASE_URL);
    }

    //определяем локаторы объектов ДОМа, которыми будем пользоваться
    private By textInputId = By.id("textInput");

    private By closeModalId = By.id("closeModal");
    private By myModalId = By.id("myModal");
    private By openModalBtnId = By.id("openModalBtn");

    private By inputNameId = By.id("name");
    private By inputEmailId = By.id("email");
    private By messageBoxId = By.id("messageBox");
    private By thisFormId = By.id("sampleForm");
    private By sampleFormSubmitBtnSelector = By.cssSelector("#sampleForm button");
    private By hideBtnId = By.id("toggleMessage");



    @Step("Вводим текст {someText} в поле textInput")
    public void writeSomeTextIntoInput (String someText) {
        driver.findElement(textInputId).sendKeys(someText);
    }

    @Step("Получаем значение поля textInput")
    public String getTextFromInput () {
        return driver.findElement(textInputId).getAttribute("value");
    }

    @Step("Проверяем видимость модального окна")
    public boolean isVisibleMyModal() {
        return standartWaiter.waitForElementVisible(driver.findElement(myModalId));
    }

    @Step("Проверяем невидимость модального окна")
    public boolean isInvisibleMyModal() {
        return standartWaiter.waitForElementNotVisible(driver.findElement(myModalId));
    }

    @Step("Открываем модальное окно")
    public void clickOpenModal() {
        driver.findElement(openModalBtnId).click();
    }

    @Step("Закрываем модальное окно")
    public void clickCloseModal() {
        driver.findElement(closeModalId).click();
    }

    @Step("Вводим текст {someName} в поле name")
    public void writeIntoInputName(String someName) {
        driver.findElement(inputNameId).sendKeys(someName);
    }

    @Step("Вводим текст {someEmail} в поле email")
    public void writeIntoInputEmail(String someEmail) {
        driver.findElement(inputEmailId).sendKeys(someEmail);
    }

    @Step("Отправляем форму")
    public void submitForm(){
        driver.findElement(thisFormId).submit();
    }

    @Step("Отправляем форму, кликая на Отправить")
    public void clickForSubmitForm(){
        driver.findElement(sampleFormSubmitBtnSelector).click();
    }

    @Step("Получаем текст внутри div'a messageBox")
    public String getTextValueOfMessageBox(){
        return driver.findElement(messageBoxId).getText();
    }

    @Step("Проверяем на наличие значений {someName} и {someEmail} в div'e messageBox")
    public boolean ifMessageBoxMatchesValuesWhichCameFromForm(String someName, String someEmail) {
        return getTextValueOfMessageBox().matches("(.*)" + someName + "(.*)" + someEmail + "(.*)");
    }

    @Step("Кликаем по кнопке Скрыть сообщение")
    public void clickHideMessageBox() {
        driver.findElement(hideBtnId).click();
    }

    @Step("Очищаем поле name")
    public void clearInputName() {
        driver.findElement(inputNameId).clear();
    }

    @Step("Очищаем поле email")
    public void clearInputEmail() {
        driver.findElement(inputEmailId).clear();
    }
}

