package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import properties.PropertyHolder;
import rest.LstRest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;


public class EventListPage extends BasePage {

    private final Logger log = LoggerFactory.getLogger(EventDetailsPage.class);

    private final static String PAGE_URL = "https://www.mql5.com/en/economic-calendar";

    // FILTER BLOCK BY DATE
    private final static By FILTER_BY_ALL_PERIODS = By.xpath("//*[@id='economicCalendarFilterDate']//li/label");

    //FILTER BLOCK BY IMPORTANCE
    private final static By FILTER_ALL_IMPORTANCES = By.xpath("//*[@id='economicCalendarFilterImportance']//li/label");

    //FILTER BLOCK BY CURRENCIES
    private final static By FILTER_BY_ALL_CURRENCIES = By.xpath("//*[@id='economicCalendarFilterCurrency']//li/label");

    //COOKIE POPUP
    private final static By COOKIE_POPUP_CROSS = By.xpath("//*[@id='floatVerticalPanel']/span");

    //EVENTS LIST
    private final static By FIRST_EVENT_FROM_LIST = By.xpath("(//*[@id='economicCalendarTable']//a)[1]");

    //Actions with page:

    public void filterByCurrentWeek(String period) {
        $$(FILTER_BY_ALL_PERIODS).filter(text(period)).get(0).click();
        log.info("Filtering: by Period -> {}", period);
    }

    public void filterByImportance(String importance) {
        log.info("Filtering: by Importance -> {}", importance);
        ElementsCollection elements = $$(FILTER_ALL_IMPORTANCES).exclude(text(importance));
        elements.forEach(element-> element.scrollIntoView(true).click());
    }

    public void filterByCurrency(String currency) {
        log.info("Filtering: by Currency -> {}", currency);
        closeCookiePopupIfPresent();
        ElementsCollection elements = $$(FILTER_BY_ALL_CURRENCIES).exclude(text(currency));
        elements.forEach(element-> element.scrollIntoView(true).click());
    }

    private void closeCookiePopupIfPresent() {
        if ($(COOKIE_POPUP_CROSS).isDisplayed()) {
            log.info("Closing popup with Cookie Police condition");
            $(COOKIE_POPUP_CROSS).click();
        } else {
            log.warn("There's no popup with Cookie Police condition");
        }
    }

    public void openFirstEventFromList() {
        if ($(FIRST_EVENT_FROM_LIST).scrollIntoView(true).isDisplayed()) {
            log.info("Opening first event from the events list");
            $(FIRST_EVENT_FROM_LIST).click();
            log.info("The current URL is {} ", LstRest.createShortLink(url()));
        } else {
            log.error("There's no events for the specified filters!");
        }
    }

    public void openPage(String browserName) {
        log.info("Starting the {} browser",browserName);
        Configuration.browser = browserName;
        Configuration.browserCapabilities.setCapability("user-agent", PropertyHolder.getPropValue("USER_AGENT"));
        Configuration.reportsFolder = "target/surefire-reports";
        open(PAGE_URL, EventListPage.class);
    }
}
