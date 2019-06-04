package com.rodelcalasagsag.base;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.net.URL;

public class AppiumServerManager {

  private static AppiumDriverLocalService appiumService;

  public static void setupServer() {
    if (appiumService == null || !appiumService.isRunning()) {
      AppiumServiceBuilder builder = new AppiumServiceBuilder();
      builder
          .withIPAddress(Config.APPIUM_HOST)
          .usingPort(Config.APPIUM_PORT)
          .withArgument(() -> "--log-level", Config.APPIUM_LOG_LEVEL);
      appiumService = AppiumDriverLocalService.buildService(builder);
      appiumService.start();
    }
  }

  static URL getServerURL() {
    return appiumService.getUrl();
  }

  public static void tearDownServer() {
    if (appiumService.isRunning()) {
      appiumService.stop();
    }
  }
}
