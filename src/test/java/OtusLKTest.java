import data.ContactInfo;
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

import java.time.Duration;
import java.time.LocalDate;
import java.util.Properties;

import pages.*;

public class OtusLKTest {

    private WebDriver driver;

    private final Logger logger = LogManager.getLogger();

    Properties p = new Properties(System.getProperties());

    String base_url = p.getProperty("base.url");

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
    public void otusLKTest() {

        startDriver();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        MainPage mp = new MainPage(driver);
        mp.open("/");

        mp.clickRegButton();

        getElement(mp.modalWindowLocator);

        mp.setEmailAndPassword();

        mp.clickSubmit();

        getElement(By.xpath(mp.menuXpathLocator));

        AboutMyselfPage amp = new AboutMyselfPage(driver);

        amp.actionsMoveToElement();

        amp.setFirstName();

        amp.setLastName();

        amp.setBlogName();

        amp.setDateOfBirth(LocalDate.now());

        amp.setCountryName();

        amp.setCityName();

        amp.setEnglishLevel();

        amp.clearOldContact();

        amp.setContact(ContactInfo.WHATSAPP.toString().toLowerCase(),"7-777-00000000");
        amp.setContact(ContactInfo.TELEGRAM.toString().toLowerCase(),"8-888-11111111");

        amp.clickButtonSave();

        driver.close();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(base_url);

        mp = new MainPage(driver);

        mp.clickRegButton();

        getElement(mp.modalWindowLocator);

        mp.setEmailAndPassword();

        mp.clickSubmit();

        getElement(By.xpath(amp.menuXpathLocator));

        amp = new AboutMyselfPage(driver);

        amp.actionsMoveToElement();

        amp.assertFactName();

        amp.assertFactNameLatin();

        amp.assertFactLastName();

        amp.assertFactLastNameLatin();

        amp.assertFactNameBlog();

        amp.assertFactBD();

        }

    private WebElement getElement(By locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

}
