package com.sky;

import org.aspectj.lang.annotation.Before;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ReadingApplication.class)
@WebIntegrationTest(randomPort = true)
public class ReadingControllerTest {

    private static FirefoxDriver browser;

    @Value("${local.server.port}")
    private int port;

    @BeforeClass
    public static void openBrowser(){
        browser=new FirefoxDriver();
    browser.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }

    @AfterClass
    public static void closeBrowser(){
        browser.close();
    }

    @Test
    public void addtoReading() {
    String baseUrl="http://localhost:8080/readinglist/jjj";
    browser.get(baseUrl);
    assertEquals("You have no books in your book list!",browser.findElementByTagName("div").getText());
    browser.findElementByName("title").sendKeys("sky's");
        browser.findElementByName("author").sendKeys("sky");
        browser.findElementByName("isbn").sendKeys("16335355");
        browser.findElementByName("description").sendKeys("sky's book,it's good!");
        browser.findElementByTagName("form").submit();

        WebElement dl=browser.findElementByCssSelector("dt.bookHeadline");
        assertEquals("sky's by sky (ISBN:16335355)",dl.getText());
    }
}