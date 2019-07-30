import cucumber.api.java.After;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rest.LstRest;

public class Hooks {

    private final Logger log = LoggerFactory.getLogger(RunCucumberTest.class);

    @After
    public void afterScenario() {
        log.info("Deleting generated short link {}", LstRest.generatedShortLink);
        LstRest.deleteLink();
    }
}