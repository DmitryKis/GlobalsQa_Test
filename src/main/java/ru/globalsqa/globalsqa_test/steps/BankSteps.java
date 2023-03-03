package ru.globalsqa.globalsqa_test.steps;

import io.cucumber.java.ru.Когда;
import ru.globalsqa.globalsqa_test.common.WebDriverInstance;
import ru.globalsqa.globalsqa_test.pages.*;
import ru.globalsqa.globalsqa_test.utils.FibonacciUtils;

public class BankSteps {

    public static RoleChoicePage roleChoicePage;
    public static CustomerMainPage customerMainPage;
    public static ManagerMainPage managerMainPage;
    public static TransactionsPage transactionsPage;

    static {
        roleChoicePage = new RoleChoicePage(WebDriverInstance.getDriver());
    }

    @Когда("^Авторизоваться клиентом \"(.*)\"$")
    public void loginAsCustomer(String client) {
        customerMainPage = roleChoicePage.clickCustomerButton().selectCustomerUser(client);
    }

    @Когда("^Авторизоваться менеджером$")
    public void loginAsManager() {
        managerMainPage = roleChoicePage.clickManagerButton();
    }

    @Когда("^Вычислить N-ое число Фибоначчи по дате$")
    public void calculateFibonacci() {
        FibonacciUtils.calculateFibonacci();
    }

    @Когда("^Выполнить (пополнение|списание со) счета на сумму (.*)$")
    public void doTransaction(String transactionType, String count) {
        if (transactionType.equals("пополнение")) {
            customerMainPage.clickDepositButton();
            customerMainPage.enterAmount(count);
            customerMainPage.submitTransaction();
        } else {
            customerMainPage.clickWithdrawlButton();
            customerMainPage.enterAmount(count);
            customerMainPage.submitTransaction();
        }
    }

    @Когда("^Проверить что баланс равен (.*)$")
    public void checkValueBalance(String balance) {
        customerMainPage.checkBalance(balance);
    }

    @Когда("^Сохранить таблицу транзакций в CSV$")
    public void saveTransactionTableToCSV() {
        transactionsPage.saveTransactionsToCSVAndAllureReports();
    }

    @Когда("^Проверить что отображено (.*) транзакции$")
    public void checkCountTransaction(String count) {
        transactionsPage.checkCountTransactions(count);
    }

    @Когда("^Перейти на экран транзакций$")
    public void goToTransactionsPage() {

        transactionsPage = customerMainPage.clickTransactionsButton();
    }
}
