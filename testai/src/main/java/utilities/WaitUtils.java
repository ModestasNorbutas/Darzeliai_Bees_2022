package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.*;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

import static org.openqa.selenium.remote.ErrorCodes.TIMEOUT;

public class WaitUtils {
    public static void waitForJS(WebDriver driver) {

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(new Function<>() {
            public Boolean apply(WebDriver driver) {
                return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
            }
        });
    }
    public static void waitForElement(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementToBeClickable(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, 3);

    }

    public static void waitForPresenceOfElementLocated(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
    }

    public static Boolean waitForTextToBe(WebDriver driver, WebElement element, String text) {
        WebDriverWait waitForTextToBe = new WebDriverWait(driver, 3);
        return waitForTextToBe.until(ExpectedConditions.textToBePresentInElementValue(element, text));
    }


    public static void fluentWait(WebDriver driver, WebElement element){
        FluentWait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(500));
        wait.ignoring(NoSuchElementException.class);
    }

}
