
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class StepDefinitions {

    @Given("^the \"([^\"]*)\" browser is opened$")
    public void theBrowserIsOpened(String browserName) {

    }


    @When("^I open the \"([^\"]*)\" page$")
    public void iOpenThePage(String pageName)  {

    }

    @And("^I filter economic events by$")
    public void iFilterEconomicEventsBy(DataTable filterTable) {
    }

    @When("^I open event number \"([^\"]*)\" in the event list$")
    public void iOpenEventNumberInTheEventList(String eventNumber) {

    }

    @Then("^I validate that the following elements have a values$")
    public void iValidateThatTheFollowingElementsHaveAValues(DataTable elementTable) {

    }

    @Then("^I save the events for the last \"([^\"]*)\" months$")
    public void iSaveTheEventsForTheLastMonths(String monthNumber) {

    }

    @Then("^close browser$")
    public void closeBrowser() {
    }
}
