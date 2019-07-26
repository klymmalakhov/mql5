package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    public void filterByCurrentWeek() {
        $(FILTER_BY_CURRENT_WEEK).click();
    }

    public void filterByMediumImportance() {
        $(FILTER_BY_HOLIDAY_IMPORTANCE).click();
        $(FILTER_BY_LOW_IMPORTANCE).click();
        $(FILTER_BY_HIGH_IMPORTANCE).click();
    }

    public void filterByCHFCurrency() {
        unselectAllCurrencies();
        $(FILTER_BY_CHF_CURRENCY).click();
    }

    private void unselectAllCurrencies() {
        closeCookiePopupIfPresent();
        $$(FILTER_BY_ALL_CURRENCIES).forEach(SelenideElement::click);
    }

    private void closeCookiePopupIfPresent() {
        if ($(COOKIE_POPUP_CROSS).isDisplayed()) {
            $(COOKIE_POPUP_CROSS).click();
        }
    }

}
