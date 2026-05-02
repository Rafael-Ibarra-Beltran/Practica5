package tests;

import base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class DashboardTests extends BaseTest {
    private DashboardPage dashboardPage;

    @BeforeMethod
    public void loginToDashboard() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("Admin", "admin123");
        wait.until(ExpectedConditions.urlContains("dashboard"));
        dashboardPage = new DashboardPage(driver, wait);
    }

    @Test(description = "CP-03: Carga y visibilidad de widgets en Dashboard")
    public void testWidgetVisibility() {
        Assert.assertTrue(dashboardPage.isTimeAtWorkWidgetVisible(), "El widget 'Time at Work' no está visible");
    }

    @Test(description = "CP-08: Navegación al módulo Admin")
    public void testNavigateToAdmin() {
        dashboardPage.navigateToAdminModule();

        wait.until(ExpectedConditions.urlContains("admin"));
        Assert.assertEquals(dashboardPage.getHeaderTitle(), "Admin", "El header no cambió a Admin");
    }

    @Test(description = "CP-10: Cerrar sesión desde Dashboard")
    public void testLogoutFromDashboard() {
        dashboardPage.logout();

        wait.until(ExpectedConditions.urlContains("/auth/login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/auth/login"), "El usuario no regresó al login");
    }

    @Test(description = "CP-11: Navegación al módulo PIM")
    public void testNavigateToPim() {
        dashboardPage.navigateToPimModule();

        wait.until(ExpectedConditions.urlContains("pim"));
        Assert.assertEquals(dashboardPage.getHeaderTitle(), "PIM", "El header no cambió a PIM");
    }
}
