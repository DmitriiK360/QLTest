package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailHomePage {
    private WebDriver driver;

    public MailHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "mailbox:login")
    private WebElement mailInput;

    @FindBy(id = "mailbox:password")
    private WebElement passwordInput;

    @FindBy(xpath = "//label[contains(@id, 'mailbox:submit') and text() = 'Ввести пароль']")
    private WebElement typePasswordButton;

    @FindBy(xpath = "//label[contains(@id, 'mailbox:submit') and text() = 'Войти']")
    private WebElement loginButton;

    @FindBy(id = "q")
    private WebElement searchInput;

    public void setEmail(String email) {
        mailInput.clear();
        mailInput.sendKeys(email);
    }

    public String getTextEmail() {
        return mailInput.getAttribute("value");
    }

    public void clickTypePasswordButton() {
        typePasswordButton.click();
    }

    public void setPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public String getTextPassword() {
        return passwordInput.getAttribute("value");
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public boolean isPageOpened() {
        return searchInput.isDisplayed();
    }
}
