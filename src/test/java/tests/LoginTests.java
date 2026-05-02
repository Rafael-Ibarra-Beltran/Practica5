package tests;

import base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests extends BaseTest {
    @Test(description = "CP-01: Login exitoso")
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("Admin", "admin123");

        wait.until(ExpectedConditions.urlContains("dashboard"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/dashboard/index"), "La URL no es la del dashboard");
    }

    @Test(description = "CP-02: Login inválido")
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("Admin", "12345");

        String errorText = loginPage.getErrorMessage();
        Assert.assertEquals(errorText, "Invalid credentials", "El mensaje de error no coincide");
    }

    @Test(description = "CP-09: Validar campos requeridos en Login")
    public void testRequiredFieldsOnEmptyLogin() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.clickLogin();

        Assert.assertEquals(loginPage.getRequiredMessagesCount(), 2, "Deben mostrarse dos mensajes Required");
    }
}
