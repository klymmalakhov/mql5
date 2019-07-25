import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/site/cucumber-pretty", "json:target/cucumber.json"},
        features = "src/test/features",
        tags = {"@active"})
public class RunCucumberTest {
}