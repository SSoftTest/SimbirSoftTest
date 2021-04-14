package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MessagePage {
    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    public WebDriver driver;

    public MessagePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    /**
     * определение локатора "Написать сообщение"
     */
    @FindBy(xpath = "//*[contains(@class, 'mail-ComposeButton')]")
    private WebElement writeMsgBtn;
    /**
     * определение локатора поля "Кому"
     */
    @FindBy(xpath = "//*[contains(@class, 'composeYabbles')]")
    private WebElement forWhomField;
    /**
     * определение локатора темы сообщения
     */
    @FindBy(xpath = "//*[contains(@class, 'composeTextField')]")
    private WebElement subjectBtn;
    /**
     * определение локатора тела письма
     */
    @FindBy(xpath = "//*[contains(@class, 'cke_contents_ltr')]")
    private WebElement textField;
    /**
     * определение локатора для отправки сообщения
     */
    @FindBy(xpath = "//*[contains(@class, 'ComposeSendButton')]")
    private WebElement sendMsgBtn;
    /**
     * определение локатора для отправленных сообщений
     */
    @FindBy(xpath = "//*[contains(@title, 'Отправленные')]")
    private WebElement sentMsgBtn;
    /**
     * определение локатора на теме отправленных сообщений
     */
    @FindBy(xpath = "//*[contains(@title, 'Simbirsoft Testovoe zadanie. Prokofiev')]")
    private WebElement sentSubjectEq;
    /**
     * метод для подсчёта входящих сообщений с темой "Simbirsoft Тестовое задание"
     */
    public int amountSameSubject() {
        List<WebElement> subjectCount = driver.findElements(By.xpath("//*[contains(@title, 'Simbirsoft Тестовое задание')]"));
        return subjectCount.size();
    }
    /**
     * метод для нажатия кнопки "Написать сообщение"
     */
    public void writeMsg() {
        writeMsgBtn.click();
    }
    /**
     * метод для ввода адреса электронной почты получателя
     */
    public void inputEmail(String login) {
        forWhomField.sendKeys(login + "\n");
    }
    /**
     * метод для ввода содержимого в поле темы сообщения
     */
    public void inputSubject(String subject) {
        subjectBtn.sendKeys(subject);
    }
    /**
     * метод, вводящий в тело письма сообщение о количестве найденных писем
     */
    public void inputAmountSubject(String amount) {
        textField.sendKeys(amount + amountSameSubject());
    }
    /**
     * метод для отправки письма
     */
    public void sendMsg() {
        sendMsgBtn.click();
    }
    /**
     * метод для нажатия кнопки папки "Отправленное"
     */
    public void sentMsg() {
        sentMsgBtn.click();
    }
    /**
     * метод для проверки наличия отправленного письма с темой "Simbirsoft Testovoe zadanie. Prokofiev" в папке "Отправленное"
     */
    public boolean presenceSubject() {
        List<WebElement> subjectPresence = driver.findElements(By.xpath("//*[contains(@title, 'Simbirsoft Testovoe zadanie. Prokofiev')]"));
        boolean presence = true;
        if (subjectPresence.isEmpty()) {
            presence = false;
        }
        return presence;
    }
}
