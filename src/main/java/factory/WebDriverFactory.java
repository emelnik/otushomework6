package factory;

import data.DriverData;
import exceptions.BrowserNotSupported;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class WebDriverFactory {

    private String browserName = System.getProperty("browser.name");

    public WebDriver getDriver(MutableCapabilities options) throws BrowserNotSupported {

        DriverData browser;

        for (DriverData data: DriverData.values()) {
            if (browserName.toUpperCase().equals(data.toString())){
                browser = data;
                switch (browser){
                    case CHROME:
                        ChromeOptions chromeOptions = new ChromeOptions();
                        if(options != null){
                            chromeOptions.merge(options);
                        }
                        return new ChromeDriver(chromeOptions);
                    case FIREFOX:
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        if(options != null){
                            firefoxOptions.merge(options);
                        }
                        return new FirefoxDriver(firefoxOptions);
                    case EDGE:
                        EdgeOptions edgeOptions = new EdgeOptions();
                        if(options != null){
                            edgeOptions.merge(options);
                        }
                        return new EdgeDriver(edgeOptions);
                    case OPERA:
                        OperaOptions operaOptions = new OperaOptions();
                        if(options != null){
                            operaOptions.merge(options);
                        }
                        return new OperaDriver(operaOptions);
                    case SAFARI:
                        SafariOptions safariOptions = new SafariOptions();
                        if(options != null){
                            safariOptions.merge(options);
                        }
                        return new SafariDriver(safariOptions);
                    default:
                        throw new BrowserNotSupported(browser);
                }
            }
        }
        return null;
    }

}
