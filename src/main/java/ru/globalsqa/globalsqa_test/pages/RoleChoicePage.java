package ru.globalsqa.globalsqa_test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RoleChoicePage {

    private WebDriver driver;

    @FindBy(css = "button[ng-click='customer()']")
    public WebElement customerButton;

    @FindBy(css = "button[ng-click='manager()']")
    public WebElement managerButton;

    public RoleChoicePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        if (!driver.getCurrentUrl().endsWith("/login")) {
            throw new RuntimeException("Неверная страница: Ожидалось: /login Полученый: " + driver.getCurrentUrl());
        }
    }

    public CustomerLoginPage clickCustomerButton() {
        customerButton.click();
        return new CustomerLoginPage(driver);
    }

    public ManagerMainPage clickManagerButton() {
        managerButton.click();
        return new ManagerMainPage(driver);
    }
}