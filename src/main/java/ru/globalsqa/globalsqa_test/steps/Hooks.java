package ru.globalsqa.globalsqa_test.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import ru.globalsqa.globalsqa_test.common.ConfProperties;
import ru.globalsqa.globalsqa_test.common.WebDriverInstance;
public class Hooks {

    @Before
    public void driverInitialize() {
        WebDriverInstance.getDriver().get(ConfProperties.getProperty("url.page.mainPage"));
    }


    @After
    public void addCSVtoAllure() {
        WebDriverInstance.getDriver().quit();
    }

}
