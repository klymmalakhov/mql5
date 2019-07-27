package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class EventListPage extends BasePage {

    // FILTER BLOCK BY DATE
    private final static By FILTER_BY_CURRENT_WEEK = By.xpath("//*[@id='economicCalendarFilterDate']//label[@for='filterDate1']");

    //FILTER BLOCK BY IMPORTANCE
    private final static By FILTER_BY_HOLIDAY_IMPORTANCE = By.xpath("//*[@id='economicCalendarFilterImportance']//label[@for='filterImportance1']");
    private final static By FILTER_BY_LOW_IMPORTANCE = By.xpath("//*[@id='economicCalendarFilterImportance']//label[@for='filterImportance2']");
    private final static By FILTER_BY_HIGH_IMPORTANCE = By.xpath("//*[@id='economicCalendarFilterImportance']//label[@for='filterImportance8']");

    //FILTER BLOCK BY CURRENCIES
    private final static By FILTER_BY_CHF_CURRENCY = By.xpath("//*[@id='economicCalendarFilterCurrency']//label[@for='filterCurrency64']");
    private final static By FILTER_BY_ALL_CURRENCIES = By.xpath("//*[@id='economicCalendarFilterCurrency']//label");


    //COOKIE POPUP
    private final static By COOKIE_POPUP_CROSS = By.xpath("//*[@id='floatVerticalPanel']/span");

    //EVENTS LIST
    private final static By FIRST_EVENT_FROM_LIST = By.xpath("(//*[@id='economicCalendarTable']//a)[1]");


    public void filterByCurrentWeek() {
        $(FILTER_BY_CURRENT_WEEK).click();
    }

    public void filterByMediumImportance() {
        // $(FILTER_BY_HOLIDAY_IMPORTANCE).click();
        // $(FILTER_BY_LOW_IMPORTANCE).click();
        //  $(FILTER_BY_HIGH_IMPORTANCE).click();
    }

    public void filterByCHFCurrency() {
        closeCookiePopupIfPresent();
        $(FILTER_BY_CHF_CURRENCY).click();
        unselectAllCurrencies();
    }

    private void unselectAllCurrencies() {
        $$(FILTER_BY_ALL_CURRENCIES).forEach(SelenideElement::click);
    }

    private void closeCookiePopupIfPresent() {
        if ($(COOKIE_POPUP_CROSS).isDisplayed()) {
            $(COOKIE_POPUP_CROSS).click();
        }
    }

    public void openFirstEventFromList() {
        $(FIRST_EVENT_FROM_LIST).click();
    }
}
