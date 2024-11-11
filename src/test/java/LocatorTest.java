import org.openqa.selenium.By;
import org.testng.annotations.Test;


public class LocatorTest extends BaseTest {


    @Test
    public void locatorTest() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElements(By.name("password"));

        driver.findElement(By.xpath("//*[text()='Swag Labs']//ancestor::div"));//предок
        driver.findElement(By.xpath("//div[@class='login-box']//descendant::input"));//потомок -- не уверена
        driver.findElement(By.xpath("//input[@id='user-name']//following::input"));//следующий -- не уверена
        driver.findElement(By.xpath("//input[@id='user-name']//parent::div"));//родитель -- не уверена
        driver.findElement(By.xpath("//input[@id='password']//preceding::input"));//предшествующий -- не уверена
        driver.findElement(By.xpath("//input[@class='input_error form_input' and @type='text']"));
        driver.findElement(By.className("submit-button"));
        driver.findElement(By.cssSelector(".login-box"));
        driver.findElement(By.cssSelector(".input_error.form_input"));
        driver.findElement(By.cssSelector(".login_wrapper .login_wrapper-inner"));
        driver.findElement(By.cssSelector("#user-name"));
        driver.findElement(By.cssSelector("input"));
        driver.findElement(By.cssSelector("input.input_error"));
        driver.findElement(By.cssSelector("[data-test='username']"));
        driver.findElement(By.cssSelector("[data-test~='username']"));
        driver.findElement(By.cssSelector("[data-test|='username']"));
        driver.findElement(By.cssSelector("[data-test^='user']"));
        driver.findElement(By.cssSelector("[data-test$='name']"));
        driver.findElement(By.cssSelector("[data-test*='user']"));
        driver.findElement(By.id("login-button")).click();

        driver.findElement(By.tagName("a"));//или button или input
        driver.findElement(By.linkText("Sauce Labs Backpack"));
        driver.findElement(By.partialLinkText("Labs"));
        driver.findElement(By.xpath("//div[@id='shopping_cart_container']"));//по атрибуту
        driver.findElement(By.xpath("//div[text()='Swag Labs']"));//по тексту
        driver.findElement(By.xpath("//div[contains(@id,'cart_container')]"));// по частичному совпадению атрибута
        driver.findElement(By.xpath("//div[contains(text(),'Swag')]"));//по частичному совпадению текста
        driver.findElement(By.xpath("//*[text()='Swag Labs']//ancestor::div"));
    }
}