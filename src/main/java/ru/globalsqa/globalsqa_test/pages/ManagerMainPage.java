package ru.globalsqa.globalsqa_test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManagerMainPage {

    private WebDriver driver;

    @FindBy(xpath = "//button[text()='Add Customer']")
    private WebElement addCustomerButton;

    @FindBy(xpath = "//button[text()='Open Account']")
    private WebElement openAccountButton;

    @FindBy(xpath = "//button[text()='Customers']")
    private WebElement customersButton;

    public ManagerMainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        if (!driver.getCurrentUrl().endsWith("/manager")) {
            throw new RuntimeException("Неверная страница: Ожидалось: /manager Полученый: " + driver.getCurrentUrl());
        }
    }

    public void clickAddCustomerButton() {
        addCustomerButton.click();
    }

    public void clickOpenAccountButton() {
        openAccountButton.click();
    }

    public void clickCustomersButton() {
        customersButton.click();
    }

    public void selectCustomer(String customerName) {
        driver.findElement(By.xpath("//option[text()='" + customerName + "']")).click();
    }

    public String getBalance() {
        return driver.findElement(By.xpath("//td[text()='Balance']/following-sibling::td")).getText();
    }

}
