package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SimpleJUnitExamplesTest {

    @Disabled ("THEeYHe56") // тест должен упасть и id бага в скобках
    @DisplayName("coment") // описание теста, которое везде видно

    @Test
    void simpleTest() {
        Assertions.assertTrue("Str".equals("Str")); //почти не исп тестировщиками на селениде
    }
}
