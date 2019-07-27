package pages;


import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;


public class EventDetailsPage extends BasePage {

    //LABELS
    private final static By COUNTRY_CURRENCY_LABEL = By.xpath("//*[@class='economic-calendar__event-header-currency']");
    private final static By IMPORTANCE_LABEL = By.xpath("//td[contains(@class,'event-table__importance')]");


    public void validateImportanceValue(String expectedImportanceValue) {
        $(IMPORTANCE_LABEL).shouldHave(exactText(expectedImportanceValue));
    }

    public void validateCountryValue(String countryValue) {
        $(COUNTRY_CURRENCY_LABEL).shouldHave(exactText(countryValue));
    }
}
