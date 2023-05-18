package ru.netology.web;

import com.codeborne.selenide.Condition;
import entities.RegistrationInfo;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import utils.DataGenerator;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class WebTest {

    @Test
    void shouldTestV1() {
        open("http://localhost:9999");
        RegistrationInfo info = DataGenerator.Registration.generationInfo("ru");
        String date = DataGenerator.NewDate.newDatePlusFormat(4, "dd.MM.yyyy");
        $("[data-test-id=city] input").setValue(info.getCity());
        $("[data-test-id=date] input").doubleClick().sendKeys(date);
        $("[data-test-id=name] input").setValue(info.getName());
        $("[data-test-id=phone] input").setValue(info.getPhone());
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=success-notification]").should(visible);
    }

    @Test
    void shouldTestV2() {
        open("http://localhost:9999");
        RegistrationInfo info = DataGenerator.Registration.generationInfo("ru");
        String date = DataGenerator.NewDate.newDatePlusFormat(4, "dd.MM.yyyy");
        $("[data-test-id=city] input").setValue(info.getCity().substring(0, 3));
        $x("//*[contains(text(),'" + info.getCity() + "')]").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.ARROW_DOWN, Keys.ARROW_RIGHT, Keys.ENTER);
        $("[data-test-id=name] input").setValue(info.getName());
        $("[data-test-id=phone] input").setValue(info.getPhone());
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=success-notification]").shouldHave(Condition.text("Встреча успешно запланирована на " + date)).should(visible);
    }

    @Test
    void shouldTestV3() {
        open("http://localhost:9999");
        RegistrationInfo info = DataGenerator.Registration.generationInfo("ru");
        String date = DataGenerator.NewDate.newDatePlusFormat(4, "dd.MM.yyyy");
        $("[data-test-id=city] input").setValue(info.getCity().substring(0, 3));
        $x("//*[contains(text(),'" + info.getCity() + "')]").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.ARROW_DOWN, Keys.ARROW_RIGHT, Keys.ENTER);
        $("[data-test-id=name] input").setValue(info.getName());
        $("[data-test-id=phone] input").setValue(info.getPhone());
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=success-notification] .notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + date));
        refresh();
        String newDate = DataGenerator.NewDate.newDatePlusFormat(5, "dd.MM.yyyy");
        $("[data-test-id=city] input").setValue(info.getCity().substring(0, 3));
        $x("//*[contains(text(),'" + info.getCity() + "')]").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.ARROW_DOWN, Keys.ARROW_RIGHT, Keys.ARROW_RIGHT, Keys.ENTER);
        $("[data-test-id=name] input").setValue(info.getName());
        $("[data-test-id=phone] input").setValue(info.getPhone());
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=replan-notification] .notification__content").shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?")).should(visible);
        $("[data-test-id=replan-notification] .button").click();
        $("[data-test-id=success-notification] .notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + newDate));
    }
}
