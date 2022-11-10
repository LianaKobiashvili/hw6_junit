package org.example;

import com.codeborne.selenide.CollectionCondition;
import org.example.data.Locale;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class WebTest {

    //    ТЕСТОВЫЕ ДАННЫЕ: ["Selenide", "JUnit"]
    @ValueSource(strings = {"Selenide", "JUnit"})
    @ParameterizedTest(name = "Проверка числа результатов поиска яндекса для запроса {0}")
    // [test_data] == String testData
    void yandexSearchCommonTest(String testData) {

        open("https://ya.ru");
        $("#text").setValue(testData);
        $("button[type='submit']").click();
        $$("li.serp-item")
                .shouldHave(CollectionCondition.size(10))
                .first()
                .shouldHave(text(testData));
    }

    @CsvSource(value = {
            "Selenide, мы с гордостью заявляем",
            "JUnit, В этот туториале по JUnit рассказывается о том"
    })
    @ParameterizedTest(name = "Проверка числа результатов поиска яндекса для запроса {0}")
        // [test_data] == String testData
    void yandexSearchCommonDifferentExpectedTextTest(String searchQuery, String expectedText) {

        open("https://ya.ru");
        $("#text").setValue(searchQuery);
        $("button[type='submit']").click();
        $$("li.serp-item")
                .shouldHave(CollectionCondition.size(10))
                .first()
                .shouldHave(text(expectedText));
    }
    static Stream <Arguments> selenideSiteButtonsTextDataProvider (){
        return Stream.of(
                Arguments.of(Locale.EN, List.of("Quick start", "Docs", "FAQ", "Blog", "Javadoc", "Users", "Quotes")),
                Arguments.of(Locale.RU, List.of("С чего начать?", "Док", "ЧАВО", "Блог" ,"Javadoc", "Пользователи", "Отзывы"))
                );
    }

    @MethodSource ("selenideSiteButtonsTextDataProvider")
    @ParameterizedTest (name = "Проверка отображения названия кнопок для локали: {0}")
    void selenideSiteButtonsText(Locale locale, List<String> buttonsTexts) {
        open("https://ru.selenide.org");
        $$ ("#languages a").find(text(locale.name())).click();
        $$ (".main-menu-pages a").filter(visible)
                .shouldHave(CollectionCondition.texts(buttonsTexts));
    }

    @EnumSource (Locale.class)
    @ParameterizedTest
    void checkLocaleTest(Locale locale) {
        open("https://ru.selenide.org");
        $$ ("#languages a").find(text(locale.name())).shouldBe(visible);
    }
}























//    /*@Test
//    void yandexSearchSelenideTest() {
//        open ("https://ya.ru");
//        $ ("#text").setValue ("Selenide");
//        $ ("button[type='submit']").click();
//        $$ ("li.serp-item")
//                .shouldHave(CollectionCondition.size(10))
//                .first()
//                .shouldHave(text("Selenide"));
//    }
//    @Test
//    void yandexSearchJUnitTest() {
//        open ("https://ya.ru");
//        $ ("#text").setValue ("JUnit");
//        $ ("button[type='submit']").click();
//        $$ ("li.serp-item")
//                .shouldHave(CollectionCondition.size(10))
//                .first()
//                .shouldHave(text("JUnit"))