package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailPage {
    WebDriver driver;

    public MailPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(@class, 'sidebar__compose-btn-box')]")
    private WebElement writeEmailButton;

    @FindBy(xpath = "//div[contains(@class, 'head_container')]//input")
    private WebElement toInput;

    @FindBy(xpath = "//div[contains(@class, 'subject__container')]//input")
    private WebElement subjectInput;

    @FindBy(xpath = "//div[contains(@class, 'editable-container')]/div/div/div[1]")
    private WebElement bodyInput;

    @FindBy(xpath = "//span[contains(@title, 'Отправить')]")
    private WebElement sendEmailButton;

    @FindBy(xpath = "//div[@class='layer-sent-page']//span[text()='отправлено']")
    private WebElement mailSentMessage;

    @FindBy(id = "PH_logoutLink")
    private WebElement logoutButton;

    public void clickWriteEmailButton() {
        writeEmailButton.click();
    }

    public void setToInput(String to) {
        toInput.clear();
        toInput.sendKeys(to);
    }

    public String getToInput() {
        return toInput.getAttribute("value");
    }

    public void setSubjectInput(String subject) {
        subjectInput.clear();
        subjectInput.sendKeys(subject);
    }

    public String getSubjectInput() {
        return subjectInput.getAttribute("value");
    }

    public void setBodyInput(String body) {
        bodyInput.clear();
        bodyInput.sendKeys(body);
    }

    public String getBodyInput() {
        return bodyInput.getText();
    }

    public void clickSendEmailButton() {
        sendEmailButton.click();
    }

    public void clickLogoutButton() {
        logoutButton.click();
    }

    public boolean isPageOpened() {
        return writeEmailButton.isDisplayed();
    }

    public boolean isEmailSent() {
        return mailSentMessage.isDisplayed();
    }
}
