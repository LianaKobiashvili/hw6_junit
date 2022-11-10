package org.example;

import com.codeborne.selenide.Configuration;
import org.example.data.ButtonsName;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class WebTestHw {

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    @ValueSource(strings = {"naruto", "гарри поттер"})
    @ParameterizedTest(name = "Проверка соответствия результатов поиска по фандому сайта фанфикс.ру для запроса {0}")
    void fanficsSearchCommonTest(String testData) {

        open("https://fanfics.me");
        $("#fdmain-search").setValue(testData).pressEnter();
        $(".FandomTable_Title").shouldHave(text(testData));

    }

    @CsvSource(value = {
            "Лига Справедливости|По знаменитой вселенной DC Comics, посвященной команде супергероев",
            "Ведьмак|По эпопее А. Сапковского"},
            delimiter = '|'
    )
    @ParameterizedTest(name = "Проверка соответствия описания результатов поиска сайта фанфикс.ру для запроса {0}")

    void fanficsSearchCommonDifferentExpectedTextTest(String searchQuery, String expectedText) {
        open("https://fanfics.me");
        $("#fdmain-search").setValue(searchQuery).pressEnter();
        $(".FandomTable_Description").shouldHave(text(expectedText));

    }

    @EnumSource(ButtonsName.class)
    @ParameterizedTest
    void checkButtonsTest(ButtonsName buttonName) {
        open("https://fanfics.me");
        $$ (".topbar-menu")
                .find(text(buttonName.getButtonName()))
                .shouldBe(visible);
    }
}