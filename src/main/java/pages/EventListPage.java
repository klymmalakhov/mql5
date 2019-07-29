package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class EventListPage extends BasePage {

    private final Logger log = LoggerFactory.getLogger(EventDetailsPage.class);

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
        elements.forEach(SelenideElement::click);
    }

    public void filterByCurrency(String currency) {
        log.info("Filtering: by Currency -> {}", currency);
        closeCookiePopupIfPresent();
        ElementsCollection elements = $$(FILTER_BY_ALL_CURRENCIES).exclude(text(currency));
        elements.forEach(SelenideElement::click);
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
        if ($(FIRST_EVENT_FROM_LIST).isDisplayed()) {
            log.info("Opening first event from the events list");
            $(FIRST_EVENT_FROM_LIST).click();
        } else {
            log.error("There's no events for the specified filters!");
        }
    }
}
