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
        open("http://localhost:9999");
    }

    public String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void shouldTest() {

        $("[data-test-id=city] input").setValue("ек");
        $$(".menu-item__control").findBy(Condition.exactText("Екатеринбург")).click();
        $(".input__icon").click();
        $(".calendar__arrow_direction_right[data-step='1']").click();
        $$(".calendar__day").findBy(Condition.exactText("4")).click();
        $("[data-test-id=name] input").setValue("Першиков Александр");
        $("[data-test-id=phone] input").setValue("+79222161614");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='notification']").shouldBe(Condition.hidden);
        $("[data-test-id='notification'] .notification__title").shouldHave(Condition.exactText("Успешно!"), Duration.ofSeconds(11));
    }
}
