package pages;

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
    protected By textInputId = By.id("textInput");

    protected By closeModalId = By.id("closeModal");
    protected By myModalId = By.id("myModal");
    protected By openModalBtnId = By.id("openModalBtn");

    protected By inputNameId = By.id("name");
    protected  By inputEmailId = By.id("email");
    protected By messageBoxId = By.id("messageBox");
    protected By thisFormId = By.id("sampleForm");
    protected  By hideBtnId = By.id("toggleMessage");



    public void writeSomeTextIntoInput (String someText) {
        driver.findElement(textInputId).sendKeys(someText);
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

