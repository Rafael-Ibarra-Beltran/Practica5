package tests;

import base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AdminPage;
import pages.LoginPage;

public class AdminTests extends BaseTest {
    private AdminPage adminPage;

    @BeforeMethod
    public void loginAndNavigateToAdmin() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("Admin", "admin123");
        wait.until(ExpectedConditions.urlContains("dashboard"));

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
        adminPage = new AdminPage(driver, wait);
    }

    @Test(description = "CP-15: Buscar usuario existente en Admin")
    public void testSearchExistingSystemUser() {
        adminPage.searchByUsername("Admin");

        int results = adminPage.getResultsCount();
        Assert.assertTrue(results > 0, "No se encontraron usuarios con el username Admin");
    }

    @Test(description = "CP-16: Filtrar usuarios por estado Enabled")
    public void testFilterUsersByEnabledStatus() {
        adminPage.selectStatus("Enabled");
        adminPage.clickSearch();

        int results = adminPage.getResultsCount();
        Assert.assertTrue(results > 0, "La tabla debería mostrar usuarios con estado Enabled");
    }
}
