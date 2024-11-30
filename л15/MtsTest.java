package com.example.tests;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

class MtsTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\falee\\Downloads\\chromedriver-win\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.mts.by/");
    }
    @AfterEach
    void tearDown() {
     driver.quit();
    }
    @Test
    void testOnlineRechargeBlock() {
        String blockTitle = driver.findElement(By.cssSelector("#pay-section > div > div > div.col-12.col-xl-8 > section > div > h2")).getText();
        assertEquals("Онлайн пополнение\nбез комиссии", blockTitle);

        WebElement partnersList = driver.findElement(By.cssSelector("#pay-section > div > div > div.col-12.col-xl-8 > section > div > div.pay__partners > ul"));
        assertTrue(partnersList.isDisplayed());

        var infoLink = driver.findElement(By.cssSelector("#pay-section .col-12.col-xl-8 a"));
        infoLink.click();

        String currentUrl = driver.getCurrentUrl();
        assertFalse(currentUrl.contains("expected-part-of-url-or-parameter"));
    }
    @Test
    void testConnectionServiceForm() {

        Select payTypeSelect = new Select(driver.findElement(By.id("pay")));
        payTypeSelect.selectByValue("Услуги связи");

        WebElement phoneField = driver.findElement(By.id("connection-phone"));
        phoneField.clear();
        phoneField.sendKeys("297777777");

        WebElement sumField = driver.findElement(By.id("connection-sum"));
        sumField.clear();
        sumField.sendKeys("500,23");

        WebElement emailField = driver.findElement(By.id("connection-email"));
        emailField.clear();
        emailField.sendKeys("example@example.com");

        WebElement continueButton = driver.findElement(By.cssSelector(".button.button__default[type='submit']"));
        continueButton.click();
    }
}