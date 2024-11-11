import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest{

    @Test
    public void checkLogin(){
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String title = driver.findElement(By.cssSelector(".title")).getText();
        assertEquals(title,
                "Products",
                "переход на страницу не выполнен");
    }

    // пустой пароль, пустой юзернейм, невалидный пароль
    @Test
    public void checkEmptyPasswordLogin(){
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.id("login-button")).click();
        String title = driver.findElement(By.cssSelector(".error")).getText();
        assertEquals(title,
                "Epic sadface: Password is required");
    }

    @Test
    public void checkInvalidUserNameLogin(){
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String title = driver.findElement(By.cssSelector(".error")).getText();
        assertEquals(title,
                "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void checkInvalidPasswordLogin(){
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("123user!");
        driver.findElement(By.id("login-button")).click();
        String title = driver.findElement(By.cssSelector(".error")).getText();
        assertEquals(title,
                "Epic sadface: Username and password do not match any user in this service");
    }
}
