package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends AbsBasePage{

    public MainPage(WebDriver driver){
        super(driver);
    }

    public MainPage clickRegButton(){
        driver.findElement(By.xpath("//button[@data-modal-id='new-log-reg']"))
                .click();
        return new MainPage(driver);

    }
}
