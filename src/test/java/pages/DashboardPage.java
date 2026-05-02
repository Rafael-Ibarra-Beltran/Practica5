package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Selectores
    private By timeAtWorkWidget = By.xpath("//p[text()='Time at Work']");
    private By adminMenuOption = By.xpath("//span[text()='Admin']");
    private By pimMenuOption = By.xpath("//span[text()='PIM']");
    private By userDropdown = By.cssSelector(".oxd-userdropdown-tab");
    private By logoutOption = By.xpath("//a[text()='Logout']");
    private By headerTitle = By.xpath("//h6[contains(@class, 'oxd-topbar-header-breadcrumb-module')]");

    public DashboardPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isTimeAtWorkWidgetVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(timeAtWorkWidget)).isDisplayed();
    }

    public void navigateToAdminModule() {
        wait.until(ExpectedConditions.elementToBeClickable(adminMenuOption)).click();
    }

    public void navigateToPimModule() {
        wait.until(ExpectedConditions.elementToBeClickable(pimMenuOption)).click();
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(userDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutOption)).click();
    }

    public String getHeaderTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(headerTitle)).getText();
    }
}
