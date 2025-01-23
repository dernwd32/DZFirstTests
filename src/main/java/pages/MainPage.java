package pages;

//import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import waiters.StandartWaiter;

public class MainPage extends AbstractPage{

    WebDriver driver;
    StandartWaiter standartWaiter;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        standartWaiter = new StandartWaiter(driver);
    }

    @Override
    public void open() {
        driver.get(BASE_URL);
    }

    //определяем локаторы объектов ДОМа, которыми будем пользоваться
    By textInputId = By.id("textInput");

    By closeModalId = By.id("closeModal");
    By myModalId = By.id("myModal");
    By openModalBtnId = By.id("openModalBtn");

    By inputNameId = By.id("name");
    By inputEmailId = By.id("email");
    By messageBoxId = By.id("messageBox");
    By thisFormId = By.id("sampleForm");
    By hideBtnId = By.id("toggleMessage");



    public void writeSomeTextIntoInput (String checkingText) {
        driver.findElement(textInputId).sendKeys(checkingText);
    }

    public String getTextFromInput () {
        return driver.findElement(textInputId).getAttribute("value");
    }

    public boolean isVisibleMyModal() {
        return standartWaiter.waitForElementVisible(driver.findElement(myModalId));
    }

    public boolean isInvisibleMyModal() {
        return standartWaiter.waitForElementNotVisible(driver.findElement(myModalId));
    }

    public void clickOpenModal() {
        driver.findElement(openModalBtnId).click();
    }

    public void clickCloseModal() {
        driver.findElement(closeModalId).click();
    }

    public void writeIntoInputName(String someName) {
        driver.findElement(inputNameId).sendKeys(someName);
    }

    public void writeIntoInputEmail(String someEmail) {
        driver.findElement(inputEmailId).sendKeys(someEmail);
    }

    public void submitForm(){
        driver.findElement(thisFormId).submit();
    }

    public String getTextValueOfMessageBox(){
        return driver.findElement(messageBoxId).getText();
    }

    public boolean ifMessageBoxMatchesValuesWhichCameFromForm(String someName, String someEmail) {
        return getTextValueOfMessageBox().matches("(.*)" + someName + "(.*)" + someEmail + "(.*)");
    }

    public void clickHideMessageBox() {
        driver.findElement(hideBtnId).click();
    }

    public void clearInputName() {
        driver.findElement(inputNameId).clear();
    }

    public void clearInputEmail() {
        driver.findElement(inputEmailId).clear();
    }
}

