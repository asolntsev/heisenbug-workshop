package ru.heisenbug;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.localStorage;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.WebDriverRunner.addListener;

public class ListenerTest {
  private static final Logger log = LoggerFactory.getLogger(ListenerTest.class);

  @BeforeEach
  void setUp() {
    addListener(new AbstractWebDriverEventListener() {
      @Override
      public void beforeNavigateTo(String url, WebDriver driver) {
        log.info("opening {} ...", url);
        System.out.println("opening " + url + " ...");
      }

      @Override
      public void afterNavigateTo(String url, WebDriver driver) {
        log.info("opened " + url + " ...");
      }

      @Override
      public void afterClickOn(WebElement element, WebDriver driver) {
        log.info("clicked " + element);
      }
    });
    open("https://todomvc.com/examples/react/");
    localStorage().clear();
    refresh();

  }

  @ParameterizedTest
  @ValueSource(strings = {"встать", "проснуться", "кофе", "чай"})
  void hardFlow(String todoItem) {
    $$(".todo-list .view").shouldHave(size(0));
    $(".new-todo").val(todoItem).pressEnter();
    $$(".todo-list .view").shouldHave(size(1));
  }
}
