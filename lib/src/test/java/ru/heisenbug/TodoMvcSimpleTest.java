/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ru.heisenbug;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.localStorage;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.Selenide.sleep;

class TodoMvcSimpleTest {

  @Test
  void simpleFlow() {
    $$(".todo-list .view").shouldHave(size(0));
    $(".new-todo").val("Single test").pressEnter();
    $(".new-todo").val("Single test2").pressEnter();
    $(".new-todo").val("Single test3").pressEnter();
    $$(".todo-list .view").shouldHave(size(3));
    sleep(10000);
  }

  @BeforeEach
  void setUp() {
    open("https://todomvc.com/examples/react/");
    localStorage().clear();
    refresh();
  }
}
