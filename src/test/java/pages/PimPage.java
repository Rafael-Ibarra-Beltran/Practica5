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
    private By searchResultsList = By.cssSelector(".oxd-table-body .oxd-table-card");

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
    }

    public int getResultsCount() {
        wait.until(ExpectedConditions.presenceOfElementLocated(searchResultsList));
        return driver.findElements(searchResultsList).size();
    }
}
