package tests;


import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {


    @Test(testName = "Проверка позитивного логина",
            description = "Проверка позитивного логина",
            priority = 1,
            groups = {"login"})
    @Epic("Модуль логина интернет-магазина")//Глобальная задача
    @Feature("TMS-25")//Подгруппа эпика
    @Story("TMS-25.11")//"Кто я, что я, чего хочу"
    @Severity(SeverityLevel.CRITICAL)//желательно
    @Link("https://www.saucedemo.com/")
    @Owner("Elizaveta Nikolaenya")//кто ответственный
    @Issue("BUG-10")
    @TmsLink("TestKeys-10")
    @Description("Проверка входа в систему интернет магазина юзера с позитивными кредами.")//обязательно, подробное описание
    @Flaky//если тест нестабильный
    public void checkLogin() {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(productsPage.getTitle(),
                "Products",
                "переход на страницу не выполнен");
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"", password, "Epic sadface: Username is required"},
                {user, "", "Epic sadface: Password is required"},
                {user, "123243", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "loginData",
            testName = "Проверка негативного логина",
            description = "Проверка негативного логина",
            priority = 2,
            groups = {"login"})
    @Description("Проверка негативного входа в систему: негативный username, негативный password, некорректное значение password.")
    public void checkLoginWithInvalidData(String user, String password, String expectedError) {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(loginPage.getErrorMessage(),
                expectedError,
                "Сообщение об ошибке неверное.");
    }
}