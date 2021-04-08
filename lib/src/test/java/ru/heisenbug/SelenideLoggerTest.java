package ru.heisenbug;

import com.codeborne.selenide.junit5.TextReportExtension;
import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.LogEventListener;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.localStorage;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.WebDriverRunner.addListener;

@ExtendWith(TextReportExtension.class)
public class SelenideLoggerTest {
  private static final Logger log = LoggerFactory.getLogger(SelenideLoggerTest.class);

  @BeforeEach
  void setUp() {
    SelenideLogger.addListener("logger", new LogEventListener() {
      @Override
      public void beforeEvent(LogEvent currentLog) {
        log.info("before {} {} {} {} {}", currentLog.getSubject(), currentLog.getElement(),
            currentLog.getStatus(), currentLog.getError(), currentLog.getDuration());
      }

      @Override
      public void afterEvent(LogEvent currentLog) {
        log.info("after {} {} {} {} {}", currentLog.getSubject(), currentLog.getElement(),
            currentLog.getStatus(), currentLog.getError(), currentLog.getDuration());
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
