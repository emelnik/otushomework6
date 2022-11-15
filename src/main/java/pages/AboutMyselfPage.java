package pages;

import data.CountryCity;
import data.LevelEnglish;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AboutMyselfPage extends AbsBasePage{

    public AboutMyselfPage(WebDriver driver) {
        super(driver);
    }

    public String menuXpathLocator = "//p[contains(@class,'header2-menu__item-text__username')]";

    public AboutMyselfPage actionsMoveToElement(){
        WebElement menu = driver.findElement(By.xpath(menuXpathLocator));
        By aboutMyselfLocator = By.xpath("//h3[contains(text(),'Персональные данные')]");
        new Actions(driver).moveToElement(menu).moveToElement(driver
                .findElement(By.xpath("//*[text()[contains(.,'Мой профиль')]]")))
                .click()
                .build()
                .perform();
//        getElement(aboutMyselfLocator);
        waitUntil(ExpectedConditions.visibilityOfElementLocated(aboutMyselfLocator));
        return new AboutMyselfPage(driver);
    }

    public AboutMyselfPage setFirstNameRus(String firstNameRus){
        WebElement elementInputFirstNameRus = driver.findElement(By.xpath("//input[@name='fname']"));
        elementInputFirstNameRus.clear();
        elementInputFirstNameRus.sendKeys(firstNameRus);
        return new AboutMyselfPage(driver);
    }

    public AboutMyselfPage setFirstNameLatin(String firstNameLatin){
        WebElement elementInputFirstNameLatin = driver.findElement(By.xpath("//input[@name='fname_latin']"));
        elementInputFirstNameLatin.clear();
        elementInputFirstNameLatin.sendKeys(firstNameLatin);
        return new AboutMyselfPage(driver);
    }

    public AboutMyselfPage setLastNameRus(String lastNameRus){
        WebElement elementInputLastNameRus = driver.findElement(By.xpath("//input[@name='lname']"));
        elementInputLastNameRus.clear();
        elementInputLastNameRus.sendKeys(lastNameRus);
        return new AboutMyselfPage(driver);
    }

    public AboutMyselfPage setLastNameLatin(String lastNameLatin){
        WebElement elementInputLastNameLatin = driver.findElement(By.xpath("//input[@name='lname_latin']"));
        elementInputLastNameLatin.clear();
        elementInputLastNameLatin.sendKeys(lastNameLatin);
        return new AboutMyselfPage(driver);
    }

    public AboutMyselfPage setBlogName(String blogName){
        WebElement elementBlogName = driver.findElement(By.xpath("//input[@name='blog_name']"));
        elementBlogName.clear();
        elementBlogName.sendKeys(blogName);
        return new AboutMyselfPage(driver);
    }

    public AboutMyselfPage setDateOfBirth(LocalDate dateOfBirth){
        WebElement elementDateOfBirth = driver.findElement(By.xpath("//input[@name='date_of_birth']"));
        elementDateOfBirth.clear();
        elementDateOfBirth.click();
        elementDateOfBirth.sendKeys(dateOfBirth.toString());
        elementDateOfBirth.sendKeys(Keys.ENTER);
        return new AboutMyselfPage(driver);
    }

    public AboutMyselfPage setCountryName(String countryName){

        //Тут задаем страну по умолчанию - Россия
        CountryCity country = CountryCity.RUSSIA;

        for (CountryCity cc : CountryCity.values()) {
            if(countryName.toUpperCase().equals(cc.toString())){
                country = cc;
            }
        }

        driver.findElement(By.xpath("//div[@data-slave-selector]/label/div")).click();
        driver.findElement(By.xpath("//button[contains(text(),'" + country.getCountry() + "')]")).click();

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement city = driver.findElement(By.xpath("//input[@data-title='Город']"));
        jse.executeScript("arguments[0].removeAttribute('disabled')", city);
        driver.findElement(By.xpath("//div[contains(@class,'js-lk-cv-dependent-slave-city')]/label/div")).click();
        waitUntil(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'js-lk-cv-dependent-slave-city')]/label/div")));

        waitUntil(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'" + country.getCity() + "')]")));
        driver.findElement(By.xpath("//button[contains(text(),'" + country.getCity() + "')]")).click();

        return new AboutMyselfPage(driver);
    }

    public AboutMyselfPage setEnglishLevel(LevelEnglish levelEnglish){
        driver.findElement(By.xpath("//input[@name='english_level']/following::div[1]")).click();
        driver.findElement(By.xpath("//button[contains(text(),'" + levelEnglish.getLevelEnglish() + "')]")).click();
        return new AboutMyselfPage(driver);
    }

    public AboutMyselfPage clearOldContact(){
        List<WebElement> listButtonDelete = driver
                .findElements(By.xpath("//div[contains(@class,'container__col_md-0')]//button[contains(text(),'Удалить')]"));
        if(listButtonDelete != null){
            for (WebElement list:listButtonDelete) {
                list.click();
            }
        }
        return new AboutMyselfPage(driver);
    }

    public AboutMyselfPage setContact(String contactType, String contactNumber){

        driver.findElement(By.xpath("//button[contains(text(),'Добавить')]"))
                .click();
        driver.findElement(By.xpath("//span[contains(text(),'Способ связи')]"))
                .click();
        waitUntil(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@data-value=\"" + contactType + "\"])[last()]")));
        driver.findElement(By.xpath("(//button[@data-value=\"" + contactType + "\"])[last()]"))
                .click();
        WebElement elementContact = driver
                .findElement(By.xpath("(//div[contains(@class, 'container__col_12')]/input[contains(@name,'value')])[last()]"));
        elementContact
                .clear();
        elementContact
                .sendKeys(contactNumber);
        return new AboutMyselfPage(driver);
    }

    public AboutMyselfPage clickButtonSave(){
        driver.findElement(By.xpath("//button[contains(text(),'Сохранить и заполнить позже')]")).click();
        return new AboutMyselfPage(driver);
    }

    public AboutMyselfPage assertFactName(){
        String factName = driver.findElement(By.xpath("//input[@name='fname']")).getAttribute("value");
        Assert.assertEquals("иван",factName);
        return new AboutMyselfPage(driver);
    }

    public AboutMyselfPage assertFactNameLatin(){
        String factNameLatin = driver.findElement(By.xpath("//input[@name='fname_latin']")).getAttribute("value");
        Assert.assertEquals("ivan",factNameLatin);
        return new AboutMyselfPage(driver);
    }

    public AboutMyselfPage assertFactLastName(){
        String factLastName = driver.findElement(By.xpath("//input[@name='lname']")).getAttribute("value");
        Assert.assertEquals("иванов",factLastName);
        return new AboutMyselfPage(driver);
    }

    public void assertFactLastNameLatin(){
        String factLastNameL = driver.findElement(By.xpath("//input[@name='lname_latin']")).getAttribute("value");
        Assert.assertEquals("ivanov",factLastNameL);
    }

    public void assertFactNameBlog(){
        String factNameBlog = driver.findElement(By.xpath("//input[@name='blog_name']")).getAttribute("value");
        Assert.assertEquals("ivanec",factNameBlog);
    }

    public void assertFactBD() throws ParseException {
        String factBD = driver.findElement(By.xpath("//input[@name='date_of_birth']")).getAttribute("value");
        Assert.assertEquals(LocalDate.of(1990,10,11).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),factBD);
    }

    private void waitUntil(ExpectedCondition<?> expectedCondition){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(expectedCondition);
    }
}
