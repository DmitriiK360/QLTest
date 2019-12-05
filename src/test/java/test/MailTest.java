package test;

import constants.DriverCapabilities;
import constants.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.MailHomePage;
import pages.MailPage;

import java.util.concurrent.TimeUnit;

public class MailTest {
    public static WebDriver driver;

    @BeforeClass
    public void setUp() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, DriverCapabilities.PLATFORM_NAME);
        capabilities.setCapability(CapabilityType.BROWSER_NAME, DriverCapabilities.BROWSER_NAME);

        System.setProperty("webdriver.chrome.driver", Properties.CHROME_DRIVER_PATH);

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(Properties.URL_MAIL_RU);
    }

    @Test
    public void testLoginMail() {
        SoftAssert softAssert = new SoftAssert();
        MailHomePage mailHomePage = new MailHomePage(driver);

        softAssert.assertTrue(
                mailHomePage.isPageOpened(),
                "Mail.ru home page doesn't open.");

        mailHomePage.setEmail(Properties.LOGIN_MAIL_RU);

        softAssert.assertEquals(
                mailHomePage.getTextEmail(),
                Properties.LOGIN_MAIL_RU);

        mailHomePage.clickTypePasswordButton();
        mailHomePage.setPassword(Properties.PASSWORD_MAIL_RU);

        softAssert.assertEquals(
                mailHomePage.getTextPassword(),
                Properties.PASSWORD_MAIL_RU);

        mailHomePage.clickLoginButton();

        softAssert.assertAll();
    }

    @Test
    public void testSendEmail() {
        //set variables
        String sendTo = Properties.LOGIN_MAIL_RU + "@mail.ru";
        String subjectText = "test subject";
        String bodyText = "test body";

        SoftAssert softAssert = new SoftAssert();
        MailPage mailPage = new MailPage(driver);

        softAssert.assertTrue(
                mailPage.isPageOpened(),
                "Mail page doesn't open.");

        mailPage.clickWriteEmailButton();
        mailPage.setToInput(sendTo);
        softAssert.assertEquals(mailPage.getToInput(), sendTo);

        mailPage.setSubjectInput(subjectText);
        softAssert.assertEquals(mailPage.getSubjectInput(), subjectText);

        mailPage.setBodyInput(bodyText);
        softAssert.assertEquals(mailPage.getBodyInput(), bodyText);

        mailPage.clickSendEmailButton();

        softAssert.assertTrue(
                mailPage.isEmailSent(),
                "Email not sent.");

        mailPage.clickLogoutButton();

        softAssert.assertAll();
    }

    @AfterClass
    public void close() {
        driver.quit();
    }
}
