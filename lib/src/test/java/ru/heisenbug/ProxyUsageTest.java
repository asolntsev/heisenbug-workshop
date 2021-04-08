package ru.heisenbug;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.DownloadOptions;
import com.codeborne.selenide.FileDownloadMode;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.files.FileFilter;
import com.codeborne.selenide.files.FileFilters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.DownloadOptions.using;
import static com.codeborne.selenide.FileDownloadMode.FOLDER;
import static com.codeborne.selenide.FileDownloadMode.PROXY;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.files.FileFilters.withExtension;

public class ProxyUsageTest {
  @BeforeEach
  void setUp() {
    Configuration.proxyEnabled = true;
    // Configuration.fileDownload = PROXY;

    // 1. http status
    // 2. замедлять
    // 3. блокировать
    // 4. внедрить
    // 5. Basic Auth
    // 6. security testing
    // 7. file download
  }

  // MACHINES:
  //   TEST         -> proxy      localhost:2222
  //   BROWSER      -> browser -> 10.10.10.178:2222   -> yandex.ru

  @Test
  void downloadFile() throws FileNotFoundException {
    open("https://the-internet.herokuapp.com/download");
    File theFile = $(byText("hello_world.txt")).download(using(FOLDER));
    System.out.println("THE FILE: " + theFile);
    sleep(3000);
  }
}
