package ru.globalsqa.globalsqa_test.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.globalsqa.globalsqa_test.common.WebDriverInstance;
import ru.globalsqa.globalsqa_test.utils.FibonacciUtils;

public class CustomerMainPage {

    private WebDriver driver;

    @FindBy(xpath = "//button[@ng-click='transactions()']")
    private WebElement transactionsButton;

    @FindBy(xpath = "//button[@ng-click='deposit()']")
    private WebElement depositButton;

    @FindBy(xpath = "//button[@ng-click='withdrawl()']")
    private WebElement withdrawlButton;

    @FindBy(xpath = "//input[@ng-model='amount']")
    private WebElement amountArea;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//strong[@class='ng-binding'][2]")
    private WebElement balanceField;

    public CustomerMainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public TransactionsPage clickTransactionsButton() {
        transactionsButton.click();
        return new TransactionsPage(WebDriverInstance.getDriver());
    }

    public void clickDepositButton() {
        depositButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.textToBe(By.xpath("//button[@type='submit']"), "Deposit"));
    }

    public void clickWithdrawlButton() {
        withdrawlButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.textToBe(By.xpath("//button[@type='submit']"), "Withdraw"));
    }

    public void submitTransaction() {
        submitButton.click();
    }

    public void enterAmount(String sum) {
        if (sum.equals("числа Фибоначчи")) {
            amountArea.sendKeys(String.valueOf(FibonacciUtils.result));
        } else {
            try {
                amountArea.sendKeys(String.valueOf(Integer.getInteger(sum)));
            } catch (NumberFormatException e) {
                System.err.println("Неверно введена сумма");
                e.printStackTrace();
            }
        }
    }

    public void checkBalance(String balance) {
        Assert.assertEquals(balance.trim(), balanceField.getText().trim());
    }
}
