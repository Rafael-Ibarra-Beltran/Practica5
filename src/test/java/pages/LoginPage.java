package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Selectores
    private By usernameInput = By.name("username");
    private By passwordInput = By.name("password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By errorMessage = By.xpath("//p[contains(@class, 'oxd-alert-content-text')]");
    private By requiredMessages = By.xpath("//span[text()='Required']");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void login(String user, String pass) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput)).sendKeys(user);
        driver.findElement(passwordInput).sendKeys(pass);
        clickLogin();
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    public int getRequiredMessagesCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(requiredMessages));
        return driver.findElements(requiredMessages).size();
    }
}
