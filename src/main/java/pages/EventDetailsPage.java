package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class EventDetailsPage extends BasePage {

    private final static By AMOUNT_INPUT = By.id("main_amount");



    public EventDetailsPage inputAmountValue(String amount) {
        $(AMOUNT_INPUT).sendKeys(amount);
        return this;
    }



}
