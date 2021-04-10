package org.example;

import net.bytebuddy.asm.Advice;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.openqa.grid.common.SeleniumProtocol.Selenium;

public class CountTest {
    public static  LoginPage loginPage;
    public static  ProfilePage profilePage;
    public static MessagePage messagePage;
    public static WebDriver driver;
    /**
     * осуществление первоначальной настройки
     */
    @BeforeClass
    public static void setup() {
        //Selenium selenium = new DefaultSelenium("localhost", 4444, "*chrome", "https://www.browserstack.com");
        //DesiredCapabilities capability = DesiredCapabilities.chrome();
        //определение пути до драйвера и его настройка
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        //создание экземпляра драйвера
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        messagePage = new MessagePage(driver);
        //окно разворачивается на полный экран
        driver.manage().window().maximize();
        //задержка на выполнение теста = 10 сек.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //получение ссылки на страницу входа из файла настроек
        driver.get(ConfProperties.getProperty("loginpage"));
    }
    @Test
    public void countTest() {
        //получение доступа к методам класса LoginPage для взаимодействия с элементами страницы
        //вводим логин
        loginPage.inputLogin(ConfProperties.getProperty("login"));
        //нажимаем кнопку входа
        loginPage.clickLoginBtn();
        //вводим пароль
        loginPage.inputPasswd(ConfProperties.getProperty("password"));
        //нажимаем кнопку входа
        loginPage.clickLoginBtn();
        //нажимаем кнопку меню пользователя
        profilePage.entryMenu();
        //нажимаем кнопку перехода на страницу входящих сообщений
        profilePage.goToIncomMsg();
        //определяем количество входящих сообщений с темой "Simbirsoft Тестовое задание"
        messagePage.amountSameSubject();
        //нажимаем кнопку для написания сообщения
        messagePage.writeMsg();
        //нажимаем на поле "Кому"
        messagePage.toWhom();
        //вводим адрес электронной почты получателя
        messagePage.inputEmail(ConfProperties.getProperty("login"));
        //нажимаем на поле ввода темы
        messagePage.subjectField();
        //вводим содержимое в поле темы сообщения
        messagePage.inputSubject(ConfProperties.getProperty("subject"));
        //нажимаем на поле текста
        messagePage.textField();
        //выводим в теле письма найденное количество входящих сообщений с темой "Simbirsoft Тестовое задание"
        messagePage.inputAmountSubject(ConfProperties.getProperty("amount"));
        //нажимаем кнопку для отправки письма
        //messagePage.sendMsg();
    }
}
