package bdd.runner;

import base.BaseTest;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(
        features = "src/test/bdd/features",
        glue = {"bdd.steps"},
        plugin= {"pretty","html:cucumber-html-report.html"},
        monochrome=true
)
public class CucumberRunnerTest extends BaseTest {
}
