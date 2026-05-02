package tests;

import base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PimPage;

public class PimTests extends BaseTest {
    private PimPage pimPage;

    @BeforeMethod
    public void loginAndNavigateToPIM() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("Admin", "admin123");
        wait.until(ExpectedConditions.urlContains("dashboard"));

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList");
        pimPage = new PimPage(driver, wait);
    }

    @Test(description = "CP-04: Buscar empleado existente")
    public void testSearchEmployeeByName() {
        pimPage.searchEmployeeByName("Joy");
        int results = pimPage.getResultsCount();

        Assert.assertTrue(results > 0, "No se encontraron empleados con ese nombre");
    }

    @Test(description = "CP-05: Filtrar usando dropdown de estado laboral")
    public void testFilterByEmploymentStatus() {
        pimPage.selectEmploymentStatus("Full-Time Permanent");
        pimPage.clickSearch();

        int results = pimPage.getResultsCount();
        Assert.assertTrue(results > 0, "La tabla debería mostrar resultados para Full-Time Permanent");
    }

    @Test(description = "CP-12: Buscar empleado inexistente")
    public void testSearchNonexistentEmployee() {
        pimPage.searchEmployeeByName("EmpleadoInexistenteQA");
        int results = pimPage.getResultsCountWithoutWaitingForRows();

        Assert.assertEquals(results, 0, "No deberían mostrarse empleados para una búsqueda inexistente");
        Assert.assertTrue(pimPage.isNoRecordsMessageDisplayed(), "Debe mostrarse el mensaje No Records Found");
    }

    @Test(description = "CP-13: Limpiar filtros de búsqueda en PIM")
    public void testResetEmployeeSearchFilters() {
        pimPage.searchEmployeeByName("Joy");
        pimPage.clickReset();

        Assert.assertEquals(pimPage.getEmployeeNameValue(), "", "El filtro Employee Name debe quedar vacío");
    }
}
