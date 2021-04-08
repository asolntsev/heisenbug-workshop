package ru.heisenbug;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public class ParametrizedBrowsersTest {
  @ParameterizedTest
  @ValueSource(strings = {"chrome", "firefox", "edge"})
  void allBrowser(String browser) {
    closeWebDriver();
    Configuration.browser = browser;
    open("https://todomvc.com/examples/react/");

    $$(".todo-list .view").shouldHave(size(0));
    $(".new-todo").val("this is " + browser).pressEnter();
    $$(".todo-list .view").shouldHave(size(1));

    sleep(2000);
  }
}
