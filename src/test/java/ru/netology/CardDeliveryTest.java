package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;

class CardDeliveryTest {
    @BeforeEach

    void SetUp() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
    }

    public static String getLocalDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy", new Locale("ru")));
    }

    @Test
    void shouldTest() {
        String meetingDate = getLocalDate(7);
        SelenideElement form = $(".form");
        $("[data-test-id=city] input").setValue("ек");
        $$(".menu-item__control").findBy(Condition.exactText("Екатеринбург")).click();
        $(".input__icon").click();
        $$(".calendar__day").findBy(Condition.exactText(String.valueOf(LocalDate.now().plusDays(7).format(DateTimeFormatter.ofPattern("d"))))).click();
        /*$("[data-test-id='date'] .input__control").doubleClick().sendKeys(meetingDate);*/
        $("[data-test-id=name] input").setValue("Першиков Александр");
        $("[data-test-id=phone] input").setValue("+79222161614");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='notification']").shouldBe(Condition.hidden);
        $("[data-test-id='notification'] .notification__content").shouldHave(Condition.exactText("Встреча успешно забронирована на " + meetingDate), Duration.ofSeconds(11));


    }
}
