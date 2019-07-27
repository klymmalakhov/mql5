package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.EventDetailsPage;
import pages.EventListPage;

import static com.codeborne.selenide.Selenide.open;


public class StepDefinitions {

    private EventListPage eventListPage = new EventListPage();
    private EventDetailsPage eventDetailsPage = new EventDetailsPage();

    @Given("^I open the Economic calendar page page in \"([^\"]*)\" browser$")
    public void iOpenTheEconomicCalendarPagePageInBrowser(String browserName) {
        System.setProperty("selenide.browser", browserName);
        open("https://www.mql5.com/en/economic-calendar", EventListPage.class);
    }

    @When("^I select Current month in Period filter block$")
    public void iSelectInPeriodFilterBlock() {
        eventListPage.filterByCurrentWeek();
    }

    @And("^I select Medium in Importance filter block$")
    public void iSelectMediumInImportanceFilterBlock() {
        eventListPage.filterByMediumImportance();
    }


    @And("^I select CHF in Currencies filter block$")
    public void iSelectCHFInCurrenciesFilterBlock() {
        eventListPage.filterByCHFCurrency();
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
