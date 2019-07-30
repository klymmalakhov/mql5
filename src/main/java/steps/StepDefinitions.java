package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.EventDetailsPage;
import pages.EventListPage;


public class StepDefinitions {

    private EventListPage eventListPage = new EventListPage();
    private EventDetailsPage eventDetailsPage = new EventDetailsPage();

    @Given("^I open the Economic calendar page page in \"([^\"]*)\" browser$")
    public void iOpenTheEconomicCalendarPagePageInBrowser(String browserName) {
        eventListPage.openPage(browserName);
    }

    @When("^I select \"([^\"]*)\" in Period filter block$")
    public void iSelectInPeriodFilterBlock(String period) {
        eventListPage.filterByCurrentWeek(period);
    }

    @And("^I select \"([^\"]*)\" in Importance filter block$")
    public void iSelectMediumInImportanceFilterBlock(String importance) {
        eventListPage.filterByImportance(importance);
    }


    @And("^I select \"([^\"]*)\" in Currencies filter block$")
    public void iSelectCHFInCurrenciesFilterBlock(String currency) {
        eventListPage.filterByCurrency(currency);
    }

    @When("^I open first event in the list$")
    public void iOpenEventNumberInTheEventList() {
        eventListPage.openFirstEventFromList();
    }

    @Then("^I validate that Label Importance has \"([^\"]*)\" value$")
    public void iValidateThatLabelImportanceHasValue(String importanceValue) {
        eventDetailsPage.validateImportanceValue(importanceValue);
    }

    @Then("^I validate that Label Country has \"([^\"]*)\" value$")
    public void iValidateThatLabelCountryHasValue(String countryValue) {
        eventDetailsPage.validateCountryValue(countryValue);
    }

    @Then("^I save the events for the last \"([^\"]*)\" months$")
    public void iSaveTheEventsForTheLastMonths(String monthNumber) {
        eventDetailsPage.saveTheEvents(Integer.parseInt(monthNumber));
    }

}
