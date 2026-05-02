package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PimPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Selectores
    private By employeeNameInput = By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//input");
    private By searchButton = By.cssSelector("button[type='submit']");
    private By resetButton = By.xpath("//button[normalize-space()='Reset']");
    private By searchResultsList = By.cssSelector(".oxd-table-body .oxd-table-card");
    private By loadingSpinner = By.className("oxd-loading-spinner");
    private By employeeIdInput = By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div//input");
    private By noRecordsMessage = By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//span[text()='No Records Found']");

    public PimPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void searchEmployeeByName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(employeeNameInput)).sendKeys(name);
        driver.findElement(searchButton).click();
    }

    public void selectEmploymentStatus(String status) {
        By dropdownArrow = By.xpath("//label[text()='Employment Status']/parent::div/following-sibling::div//i");
        wait.until(ExpectedConditions.elementToBeClickable(dropdownArrow)).click();

        By optionToSelect = By.xpath("//div[@role='listbox']//span[text()='" + status + "']");
        wait.until(ExpectedConditions.elementToBeClickable(optionToSelect)).click();
    }

    public void clickSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        waitUntilLoadingFinishes();
    }

    public void clickReset() {
        wait.until(ExpectedConditions.elementToBeClickable(resetButton)).click();
    }

    public String getEmployeeNameValue() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(employeeNameInput)).getAttribute("value");
    }

    public boolean isNoRecordsMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(noRecordsMessage)).isDisplayed();
    }

    public int getResultsCount() {
        wait.until(ExpectedConditions.presenceOfElementLocated(searchResultsList));
        return driver.findElements(searchResultsList).size();
    }

    public int getResultsCountWithoutWaitingForRows() {
        waitUntilLoadingFinishes();
        return driver.findElements(searchResultsList).size();
    }

    private void waitUntilLoadingFinishes() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingSpinner));
        } catch (Exception e) {
            // El spinner puede no aparecer si la respuesta es inmediata.
        }
    }

    public void searchEmployeeById(String id) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(employeeIdInput)).sendKeys(id);
        driver.findElement(searchButton).click();
        waitUntilLoadingFinishes(); // Aprovechando tu excelente método
    }
}
