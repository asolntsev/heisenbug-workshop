package ru.heisenbug;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.using;

public class TwoBrowsersTest {
  private final WebDriver admin = new ChromeDriver();
  private final WebDriver user = new FirefoxDriver();

  @Test
  void adminAndUser() {
    using(admin, () -> {
      open("https://todomvc.com/examples/react/#/");
      $$(".todo-list .view").shouldHave(size(0));
      $(".new-todo").val("i am admin").pressEnter();
      $(".new-todo").val("i am admin").pressEnter();
      $(".new-todo").val("i am admin").pressEnter();
      $(".new-todo").val("i am admin").pressEnter();
      $$(".todo-list .view").shouldHave(size(4));
      sleep(1000);
    });

    using(user, () -> {
      open("https://todomvc.com/examples/react/#/");
      $$(".todo-list .view").shouldHave(size(0));
      $(".new-todo").val("i am user").pressEnter();
      $(".new-todo").val("i am user").pressEnter();
      $(".new-todo").val("i am user").pressEnter();
      $(".new-todo").val("i am user").pressEnter();
      $(".new-todo").val("i am user").pressEnter();
      $(".new-todo").val("i am user").pressEnter();
      $$(".todo-list .view").shouldHave(size(6));
      sleep(1000);
    });

    using(admin, () -> {
      $$(".todo-list .view").shouldHave(size(4));
      $(".new-todo").val("i am admin 2").pressEnter();
      $(".new-todo").val("i am admin 22").pressEnter();
      $(".new-todo").val("i am admin 222").pressEnter();
      $(".new-todo").val("i am admin 2222").pressEnter();
      $$(".todo-list .view").shouldHave(size(8));
      sleep(1000);
    });
  }

  @AfterEach
  void tearDown() {
    admin.quit();
    user.quit();
  }
}
