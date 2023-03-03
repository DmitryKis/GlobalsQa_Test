package ru.globalsqa.globalsqa_test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CustomerLoginPage {

    private WebDriver driver;

    @FindBy(xpath = "//select[@id='userSelect']")
    public WebElement userSelect;

    public CustomerLoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        if (!driver.getCurrentUrl().endsWith("/login")) {
            throw new RuntimeException("Неверная страница: Ожидалось: /login Полученый: " + driver.getCurrentUrl());
        }

    }

    public CustomerMainPage selectCustomerUser(String username) {
        userSelect.click();
        Select select = new Select(userSelect);
        select.selectByVisibleText(username);
        driver.findElement(By.cssSelector(".btn-default")).click();
        return new CustomerMainPage(driver);
    }
}