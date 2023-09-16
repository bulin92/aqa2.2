package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import com.codeborne.selenide.Selectors;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;


public class CardDeliveryTest {
    @Test
    public void shouldSuccessfulFormSubmission() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Томск");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        String verificationDate = LocalDate.now() .plusDays(5)
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(verificationDate);
        $("[data-test-id=name] input").setValue("Булин Алексей");
        $("[data-test-id=phone] input").setValue("+79293020000");
        $("[data-test-id=agreement]").click();
        $(".button").shouldHave(Condition.text("Забронировать")).click();
        $("[data-test-id=notification]")
                .shouldHave(Condition.text("Успешно! Встреча успешно забронирована на " + verificationDate),
                        Duration.ofSeconds(15));
    }
}
