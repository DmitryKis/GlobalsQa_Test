package ru.globalsqa.globalsqa_test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        stepNotifications = true,
        features = "src/test/java/ru/globalsqa/globalsqa_test/features",
        glue = ".",
        tags = "@checkFibonacciSumTransaction",
        plugin = {"pretty","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"})
public class TestRunner {
}
