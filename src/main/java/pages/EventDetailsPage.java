package pages;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;


public class EventDetailsPage extends BasePage {

    private final Logger log = LoggerFactory.getLogger(EventDetailsPage.class);

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

    //Actions with page:

    public void validateImportanceValue(String expectedImportanceValue) {
        $(IMPORTANCE_LABEL).shouldHave(exactText(expectedImportanceValue.toUpperCase()));
    }

    public void validateCountryValue(String countryValue) {
        String expectedCurrencyValue = countryValue.replace(" -", ",");
        $(COUNTRY_CURRENCY_LABEL).shouldHave(exactText(expectedCurrencyValue));
    }

    private void clickOnPageInHistoryByNumber(int pageNumber) {
        if ($(By.xpath(String.format(HISTORY_PAGINATOR_XPATH, pageNumber))).isDisplayed()) {
            log.info("Switching to page number '{}' ", pageNumber);
            $(By.xpath(String.format(HISTORY_PAGINATOR_XPATH, pageNumber))).click();
            $(By.xpath(String.format(HISTORY_PAGINATOR_XPATH, pageNumber)))
                    .shouldHave(attribute("class", "selected"));
        } else {
            log.warn("There's no page number '{}' at the page", pageNumber);
        }
    }

    private String getHistoryOneItemPreviousValueIfPresent(int itemCount) {
        if ($(By.xpath(String.format(HISTORY_ONE_ITEM_PREVIOUS_XPATH, itemCount))).isDisplayed()) {
            log.info("Gathering the Previous Value of one item that has '{}' number", itemCount);
            return $(By.xpath(String.format(HISTORY_ONE_ITEM_PREVIOUS_XPATH, itemCount))).getText();
        }
        log.warn("There's no Previous value for item that has {} number", itemCount);
        return "";
    }

    private String getHistoryOneItemForecastValueIfPresent(int itemCount) {
        if ($(By.xpath(String.format(HISTORY_ONE_ITEM_FORECAST_XPATH, itemCount))).isDisplayed()) {
            log.info("Gathering the Forecast Value of one item that has '{}' number", itemCount);
            return $(By.xpath(String.format(HISTORY_ONE_ITEM_FORECAST_XPATH, itemCount))).getText();
        }
        log.warn("There's no Forecast value for item that has {} number", itemCount);
        return "";
    }

    private String getHistoryOneItemActualValueIfPresent(int itemCount) {
        if ($(By.xpath(String.format(HISTORY_ONE_ITEM_ACTUAL_XPATH, itemCount))).isDisplayed()) {
            log.info("Gathering the Actual Value of one item that has '{}' number", itemCount);
            return $(By.xpath(String.format(HISTORY_ONE_ITEM_ACTUAL_XPATH, itemCount))).getText();
        }
        log.warn("There's no Actual value for item that has {} number", itemCount);
        return "";
    }

    private String getHistoryOneItemDateValueIfPresent(int itemCount) {
        if ($(By.xpath(String.format(HISTORY_ONE_ITEM_DATE_XPATH, itemCount))).isDisplayed()) {
            log.info("Gathering the Date Value of one item that has '{}' number", itemCount);
            return $(By.xpath(String.format(HISTORY_ONE_ITEM_DATE_XPATH, itemCount))).getText();
        }
        log.warn("There's no Date value for item that has {} number", itemCount);
        return "";
    }

    public void saveTheEvents(int monthNumber) {
        log.info("Open the history content");
        $(HISTORY_CONTENT_LINK).click();
        long endOfPeriodInMs = getEndOfPeriodInMilliSeconds(monthNumber);
        log.info("The system time in millisecond in past for '{}' months is '{}' ", monthNumber, endOfPeriodInMs);
        List<String> historyList = getHistoryDataFromThePage(endOfPeriodInMs);
        log.info("Displaying the history data in the log file");
        printDataInTheLogFile(historyList);
    }

    /**
     * Print the data in the log file regarding the required format
     *
     * @param historyList data that should be saved
     */
    private void printDataInTheLogFile(List<String> historyList) {
        log.info("| Date | Actual | Forecast | Previous |");
        historyList.forEach(log::info);
    }

    /**
     * Gathering the values of Date, Actual, Forecast, Previous for all available items on the page.
     * The period is from now till @endOfPeriodInMs in Milliseconds.
     * The date in milliseconds is specified in the 'data-date' tag of each history item
     *
     * @param endOfPeriodInMs  is end of the search period
     * @return List of values, separated by item
     */
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

    /**
     * Calculate the system time in millisecond from now till @mothNumber in past
     *
     * @param monthNumber - the number of month that we should take from the past
     * @return system time in ms from this moment till @mothNumber in past
     */
    private long getEndOfPeriodInMilliSeconds(int monthNumber) {
        LocalDateTime date = LocalDateTime.now().minusMonths(monthNumber);
        return date.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
