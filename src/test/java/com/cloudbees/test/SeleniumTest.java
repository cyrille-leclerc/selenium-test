package com.cloudbees.test;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author <a href="mailto:cleclerc@cloudbees.com">Cyrille Le Clerc</a>
 */
public class SeleniumTest {


    @Test
    public void testSeleniumRemoteWebDriverUrl() throws Exception {
        URL url = new URL("http://localhost:4444/wd/hub");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        System.out.println(url + " -> " + urlConnection.getResponseCode() + urlConnection.getResponseMessage());
    }

    @Test
    public void testFirefoxDriver() throws Exception {
        System.err.println("");
        System.err.println("#############################");
        System.err.println("# INSTANTIATE FirefoxDriver");
        System.err.println("#############################");


        FirefoxDriver webDriver = new FirefoxDriver();
        testSeleniumDriver(webDriver);
    }

    @Test
    public void testRemoteWebDriverWithFirefox() throws Exception {
        testSeleniumRemoteWebDriver(DesiredCapabilities.firefox());
    }

    @Ignore
    @Test
    public void testRemoteWebDriverWithChrome() throws Exception {
        testSeleniumRemoteWebDriver(DesiredCapabilities.chrome());
    }

    private void testSeleniumRemoteWebDriver(DesiredCapabilities desiredCapabilities) {
        System.err.println("");
        System.err.println("#############################");
        System.err.println("# INSTANTIATE RemoteWebDriver (" + desiredCapabilities + ")");
        System.err.println("#############################");
        WebDriver webDriver = new RemoteWebDriver(desiredCapabilities);
        testSeleniumDriver(webDriver);
    }

    private void testSeleniumDriver(WebDriver webDriver) {
        System.err.println("");
        System.err.println("#############################");
        System.err.println("# TEST WITH " + webDriver);
        System.err.println("#############################");
        try {
            String url = "https://google.com";
            webDriver.get(url);
            Assert.assertEquals(
                    "Unexpected page title requesting " + url + " with selenium driver " + webDriver.toString() + " displaying " + webDriver.getCurrentUrl(),
                    "Google",
                    webDriver.getTitle());
            System.err.println("SUCCESSFULLY invoked Selenium driver" + webDriver.toString() + " with URL " + webDriver.getCurrentUrl() + ", page.title='" + webDriver.getTitle() + "'");
        } finally {
            webDriver.close();
            webDriver.quit();
        }
    }
}
