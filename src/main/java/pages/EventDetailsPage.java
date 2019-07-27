package pages;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;


public class EventDetailsPage extends BasePage {

    //LABELS
    private final static By COUNTRY_CURRENCY_LABEL = By.xpath("//*[@class='economic-calendar__event-header-currency']");
    private final static By IMPORTANCE_LABEL = By.xpath("//td[contains(@class,'event-table__importance')]");

    //HISTORY
    private final static By HISTORY_CONTENT_LINK = By.xpath("//*[@data-content='history']");
    private final static String HISTORY_ONE_ITEM_XPATH = "(//div[@class='event-table-history__item'])[%s]";
    private final static String HISTORY_ONE_ITEM_DATA_DATE_XPATH = HISTORY_ONE_ITEM_XPATH + "//div[@class='event-table-history__date']";
    private final static String HISTORY_ONE_ITEM_DATE_XPATH = HISTORY_ONE_ITEM_XPATH + "//div[@class='event-table-history__date']/span";
    private final static String HISTORY_ONE_ITEM_ACTUAL_XPATH = HISTORY_ONE_ITEM_XPATH + "//div[@class='event-table-history__actual ']/span";
    private final static String HISTORY_ONE_ITEM_FORECAST_XPATH = HISTORY_ONE_ITEM_XPATH + "//div[@class='event-table-history__forecast']/span";
    private final static String HISTORY_ONE_ITEM_PREVIOUS_XPATH = HISTORY_ONE_ITEM_XPATH + "//div[@class='event-table-history__previous']/span";
    private final static String HISTORY_PAGINATOR_XPATH = "//div[@class='paginatorEx']/a[contains(text(),%s)]";

    public void validateImportanceValue(String expectedImportanceValue) {
        $(IMPORTANCE_LABEL).shouldHave(exactText(expectedImportanceValue));
    }

    public void validateCountryValue(String countryValue) {
        $(COUNTRY_CURRENCY_LABEL).shouldHave(exactText(countryValue));
    }

    private void clickOnPageInHistoryByNumber(int pageNumber) {
        if ($(By.xpath(String.format(HISTORY_PAGINATOR_XPATH, pageNumber))).isDisplayed()) {
            $(By.xpath(String.format(HISTORY_PAGINATOR_XPATH, pageNumber))).click();
            $(By.xpath(String.format(HISTORY_PAGINATOR_XPATH, pageNumber)))
                    .shouldHave(attribute("class", "selected"));
        }
    }

    private String getHistoryOneItemPreviousValueIfPresent(int itemCount) {
        if ($(By.xpath(String.format(HISTORY_ONE_ITEM_PREVIOUS_XPATH, itemCount))).isDisplayed()) {
            return $(By.xpath(String.format(HISTORY_ONE_ITEM_PREVIOUS_XPATH, itemCount))).getText();
        }
        return "";
    }

    private String getHistoryOneItemForecastValueIfPresent(int itemCount) {
        if ($(By.xpath(String.format(HISTORY_ONE_ITEM_FORECAST_XPATH, itemCount))).isDisplayed()) {
            return $(By.xpath(String.format(HISTORY_ONE_ITEM_FORECAST_XPATH, itemCount))).getText();
        }
        return "";
    }

    private String getHistoryOneItemActualValueIfPresent(int itemCount) {
        if ($(By.xpath(String.format(HISTORY_ONE_ITEM_ACTUAL_XPATH, itemCount))).isDisplayed()) {
            return $(By.xpath(String.format(HISTORY_ONE_ITEM_ACTUAL_XPATH, itemCount))).getText();
        }
        return "";
    }

    private String getHistoryOneItemDateValueIfPresent(int itemCount) {
        if ($(By.xpath(String.format(HISTORY_ONE_ITEM_DATE_XPATH, itemCount))).isDisplayed()) {
            return $(By.xpath(String.format(HISTORY_ONE_ITEM_DATE_XPATH, itemCount))).getText();
        }
        return "";
    }

    public void saveTheEvents(int monthNumber) {
        $(HISTORY_CONTENT_LINK).click();
        List<String> historyList;
        long endOfPeriodInMs = getEndOfPeriodInMilliSeconds(monthNumber);
        historyList = getHistoryDataFromThePage(endOfPeriodInMs);
        System.out.println(historyList);
    }

    private List<String> getHistoryDataFromThePage(long endOfPeriodInMs) {
        int pageCount = 1;
        int itemCount = 1;
        boolean shouldIContinue;
        ArrayList<String> result = new ArrayList<>();

        do {
            shouldIContinue = false;
            if (itemCount > 50) {
                itemCount = 1;
                pageCount++;
                clickOnPageInHistoryByNumber(pageCount);
            }
            SelenideElement oneItemDate = $(By.xpath(String.format(HISTORY_ONE_ITEM_DATA_DATE_XPATH, itemCount)));
            long dataDateOfOneItemDate = Long.parseLong(oneItemDate.attr("data-date"));
            if (dataDateOfOneItemDate > endOfPeriodInMs) {
                result.add(String.format("| %s | %s | %s | %s |",
                        getHistoryOneItemDateValueIfPresent(itemCount),
                        getHistoryOneItemActualValueIfPresent(itemCount),
                        getHistoryOneItemForecastValueIfPresent(itemCount),
                        getHistoryOneItemPreviousValueIfPresent(itemCount)));
                shouldIContinue = true;
            }
            itemCount++;
        }
        while (shouldIContinue);

        return result;
    }

    private long getEndOfPeriodInMilliSeconds(int monthNumber) {
        LocalDateTime date = LocalDateTime.now().minusMonths(monthNumber);
        return date.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
