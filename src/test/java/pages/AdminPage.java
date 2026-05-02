package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By usernameInput = By.xpath("//label[text()='Username']/parent::div/following-sibling::div//input");
    private By searchButton = By.cssSelector("button[type='submit']");
    private By searchResultsList = By.cssSelector(".oxd-table-body .oxd-table-card");
    private By loadingSpinner = By.className("oxd-loading-spinner");

    public AdminPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void searchByUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput)).sendKeys(username);
        clickSearch();
    }

    public void selectStatus(String status) {
        By dropdownArrow = By.xpath("//label[text()='Status']/parent::div/following-sibling::div//i");
        wait.until(ExpectedConditions.elementToBeClickable(dropdownArrow)).click();

        By optionToSelect = By.xpath("//div[@role='listbox']//span[text()='" + status + "']");
        wait.until(ExpectedConditions.elementToBeClickable(optionToSelect)).click();
    }

    public void clickSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        waitUntilLoadingFinishes();
    }

    public int getResultsCount() {
        wait.until(ExpectedConditions.presenceOfElementLocated(searchResultsList));
        return driver.findElements(searchResultsList).size();
    }

    private void waitUntilLoadingFinishes() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingSpinner));
        } catch (Exception e) {
            // El spinner puede no mostrarse cuando la busqueda responde rapido.
        }
    }
}
