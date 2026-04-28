package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RecruitmentPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Selectores
    private By candidateNameInput = By.xpath("//label[text()='Candidate Name']/parent::div/following-sibling::div//input");
    private By keywordsInput = By.xpath("//label[text()='Keywords']/parent::div/following-sibling::div//input");
    private By searchButton = By.cssSelector("button[type='submit']");
    private By noRecordsMessage = By.xpath("//p[contains(@class, 'oxd-toast-content-text') and contains(text(), 'No Records Found')]");
    private By searchResultsList = By.cssSelector(".oxd-table-body .oxd-table-card");
    private By loadingSpinner = By.className("oxd-loading-spinner");

    public RecruitmentPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void searchCandidateByName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(candidateNameInput)).sendKeys(name);
    }

    public void searchCandidateByKeyword(String keyword) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(keywordsInput)).sendKeys(keyword);
    }

    public void selectVacancy(String vacancyName) {
        By dropdownArrow = By.xpath("//label[text()='Vacancy']/parent::div/following-sibling::div//i");
        wait.until(ExpectedConditions.elementToBeClickable(dropdownArrow)).click();

        By optionToSelect = By.xpath("//div[@role='listbox']//span[text()='" + vacancyName + "']");
        wait.until(ExpectedConditions.elementToBeClickable(optionToSelect)).click();
    }

    public void clickSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingSpinner));
        } catch (Exception e) {
            // Se ignora si el spinner no aparece
        }
    }

    public boolean isNoRecordsMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(noRecordsMessage)).isDisplayed();
    }

    public int getResultsCount() {
        try { Thread.sleep(1000); } catch (InterruptedException e) {}

        // Si no hay resultados, la tabla no genera las tarjetas (.oxd-table-card)
        return driver.findElements(searchResultsList).size();
    }
}
