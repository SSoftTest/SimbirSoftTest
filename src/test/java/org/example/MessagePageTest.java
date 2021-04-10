package org.example;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;

public class MessagePageTest {
    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    public WebDriver driver;

    public MessagePageTest(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @Test
    public void amountSameSubject() {
        int expected = driver.findElements(By.xpath("//*[contains(@title, 'Simbirsoft Тестовое задание')]")).size();
    }
}