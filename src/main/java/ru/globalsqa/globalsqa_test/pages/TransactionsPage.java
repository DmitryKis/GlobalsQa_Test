package ru.globalsqa.globalsqa_test.pages;

import io.qameta.allure.Allure;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TransactionsPage {

    private WebDriver driver;

    @FindBy(css = "tbody")
    public WebElement transactionTable;

    public TransactionsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    public void checkCountTransactions(String count) {
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        int tryCount = 5;
        while (tryCount-- >= 0) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, 3);
                wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//td[@class='ng-binding']"), 1));
                List<WebElement> rows = transactionTable.findElements(By.tagName("tr"));
                Assert.assertEquals(rows.size(), Integer.parseInt(count));
            } catch (TimeoutException e) {
                driver.navigate().refresh();
                continue;
            }
        }
    }

    public CustomerMainPage saveTransactionsToCSVAndAllureReports() {
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        int tryCount = 5;
        while (tryCount-- >= 0) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, 2);
                wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//td[@class='ng-binding']"), 1));

                List<WebElement> rows = transactionTable.findElements(By.tagName("tr"));
                Assert.assertTrue(rows.size() >= 1);
                FileWriter outputfile = null;
                try {
                    outputfile = new FileWriter("table.csv");
                } catch (IOException e) {
                    System.err.println("Ошибка при создании/изменении файла");
                    e.printStackTrace();
                }
                String tableInCSVFormat = tableDataToCSV(rows, outputfile);
                //добавление отчёта в аллюр
                Allure.addAttachment("SaveCSVToFile", "text/csv", tableInCSVFormat);
                try {
                    outputfile.close();
                    outputfile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return new CustomerMainPage(driver);
            } catch (TimeoutException e) {
                driver.navigate().refresh();
                continue;
            }
        }
        throw new TimeoutException("Таблица не появилась за время ожидания");
    }

    private String tableDataToCSV(List<WebElement> rows, FileWriter outputfile) {
        int editableRow = 0;
        String[] data = new String[rows.size()];
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            data[editableRow] = "";
            try {
                for (int i = 0; i < cells.size(); i++) {
                    String textCell = cells.get(i).getText();
                    if (i == 0) {
                        SimpleDateFormat parser = new SimpleDateFormat("MMM d, yyyy h:mm:ss a", Locale.ENGLISH);
                        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
                        textCell = formatter.format(parser.parse(textCell));
                    }
                    data[editableRow] += " " + textCell.replaceAll("\"", "").trim();
                }
            } catch (ParseException e) {
                System.err.println("Ошибка при попытке форматирования");
                e.printStackTrace();
            }
            editableRow++;
        }
        StringBuilder sb = new StringBuilder().append("<");
        String CSVFormatString = "";
        try {
            for (String str : data)
                sb.append(str.trim()).append(">\n<");
            CSVFormatString = sb.toString().substring(0, sb.length() - 1);
            outputfile.write(CSVFormatString);
        } catch (IOException e) {
            System.err.println("Ошибка при попытке записи в файл");
            e.printStackTrace();
        }
        return CSVFormatString;
    }
}