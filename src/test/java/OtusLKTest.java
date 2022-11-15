import components.ModalWindowAuthComponent;
import data.ContactInfo;
import data.LevelEnglish;
import exceptions.BrowserNotSupported;
import factory.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Properties;

import pages.*;

public class OtusLKTest {

    private WebDriver driver;

    private final Logger logger = LogManager.getLogger();

    private final Properties prop = new Properties(System.getProperties());

    private final String base_url = prop.getProperty("base.url");

    @BeforeClass
        public static void startDriver(){
            WebDriverManager.chromedriver().setup();
    }

    @Before
        public void initDriver() throws BrowserNotSupported {
        driver = new WebDriverFactory().getDriver(null);
    }

    @After
    public void end(){
        if(driver != null){
            driver.close();
            driver.quit();
        }
        logger.info("Драйвер успешно закрыт");
    }

    @Test
    public void otusLKTest() throws ParseException {

        startDriver();
        driver.manage().window().maximize();

        MainPage mainPage = new MainPage(driver);
        mainPage.open("/");

        mainPage.clickRegButton();

        ModalWindowAuthComponent modalWindowAuthComponent = new ModalWindowAuthComponent(driver);

        getElement(modalWindowAuthComponent.modalWindowLocator);

        modalWindowAuthComponent.setEmailAndPassword();

        modalWindowAuthComponent.clickSubmit();

        getElement(By.xpath(modalWindowAuthComponent.menuXpathLocator));

        AboutMyselfPage aboutMyselfPage = new AboutMyselfPage(driver);

        aboutMyselfPage
                .actionsMoveToElement()
                .setFirstNameRus("иван")
                .setFirstNameLatin("ivan")
                .setLastNameRus("иванов")
                .setLastNameLatin("ivanov")
                .setBlogName("ivanec")
                .setDateOfBirth(LocalDate.of(1990,10,11))
                .setCountryName("RUSSIA")
                .setEnglishLevel(LevelEnglish.BEGINNER)
                .clearOldContact()
                .setContact(ContactInfo.WHATSAPP.toString().toLowerCase(),"7-777-00000000")
                .setContact(ContactInfo.TELEGRAM.toString().toLowerCase(),"8-888-11111111")
                .clickButtonSave();

        driver.close();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(base_url);

        mainPage = new MainPage(driver);

        mainPage.clickRegButton();

        getElement(modalWindowAuthComponent.modalWindowLocator);

        modalWindowAuthComponent = new ModalWindowAuthComponent(driver);

        modalWindowAuthComponent.setEmailAndPassword();

        modalWindowAuthComponent.clickSubmit();

        getElement(By.xpath(aboutMyselfPage.menuXpathLocator));

        aboutMyselfPage = new AboutMyselfPage(driver);

        aboutMyselfPage
                .actionsMoveToElement()
                .assertFactName()
                .assertFactNameLatin()
                .assertFactLastName()
                .assertFactLastNameLatin();

        aboutMyselfPage.assertFactNameBlog();

        aboutMyselfPage.assertFactBD();

        }

    private WebElement getElement(By locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

}
