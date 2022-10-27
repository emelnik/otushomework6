package pages;

import org.openqa.selenium.WebDriver;

public abstract class AbsBasePage {

    private final static String BASE_URL = System.getProperty("base.url");

    protected WebDriver driver;

    public AbsBasePage(WebDriver driver){
        this.driver = driver;
    }

    public void open(String path){
        driver.get(BASE_URL + path);
    }

}
