package tests;

import base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.RecruitmentPage;

public class RecruitmentTests extends BaseTest {
    private RecruitmentPage recruitmentPage;

    @BeforeMethod
    public void loginAndNavigateToRecruitment() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("Admin", "admin123");
        wait.until(ExpectedConditions.urlContains("dashboard"));

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/viewCandidates");
        recruitmentPage = new RecruitmentPage(driver, wait);
    }

    @Test(description = "CP-06: Buscar candidato inexistente (Manejo de error)")
    public void testSearchInvalidCandidate() {
        recruitmentPage.searchCandidateByKeyword("CandidatoInexistente");
        recruitmentPage.clickSearch();

        int results = recruitmentPage.getResultsCount();
        Assert.assertEquals(results, 0, "Se esperaban 0 resultados, pero la tabla mostró registros.");
    }

    @Test(description = "CP-07: Filtrar candidatos por vacante con Dropdown")
    public void testFilterByVacancy() {
        recruitmentPage.selectVacancy("Software Engineer");
        recruitmentPage.clickSearch();

        int results = recruitmentPage.getResultsCount();
        Assert.assertTrue(results > 0, "No se encontraron registros para la vacante seleccionada");
    }

    @Test(description = "CP-14: Limpiar filtros de búsqueda en Recruitment")
    public void testResetRecruitmentSearchFilters() {
        recruitmentPage.searchCandidateByKeyword("Software");
        recruitmentPage.clickReset();

        Assert.assertEquals(recruitmentPage.getKeywordValue(), "", "El filtro Keywords debe quedar vacío");
    }
}
