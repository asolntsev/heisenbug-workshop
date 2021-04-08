package ru.heisenbug;

import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.SelenideDriver;
import org.junit.jupiter.api.Test;

public class TwoSelenideDriversTest {
  SelenideDriver admin = new SelenideDriver(new SelenideConfig()
      .browser("firefox"));
  SelenideDriver user = new SelenideDriver(new SelenideConfig()
      .browser("edge"));

  @Test
  void adminAndUser() {
    admin.open("https://todomvc.com/examples/react/#/");
    user.open("https://todomvc.com/examples/react/#/");
    admin.$(".todo-list").val("item 2").pressEnter();
  }
}
