package PageObjects;

import stepdefs.WDriver;

public class MailBoxPage extends BasePage {

    private String newMessageButtonXpath = "//div[text()=\"Написать\"]";
    private String newMessageAddressXpath = "//textarea[@aria-label=\"Кому\"]";
    private String newMessageThemeXpath = "//input[@name=\"subjectbox\"]";
    private String newMessageBodyXpath = "//div[@role=\"textbox\"]";
    private String draftExistsXpath = "//span[contains(text(), \"%s\")]";

    public String inboxUrl() {
        driver.findElement(newMessageButtonXpath);
        String inboxUrl = driver.getUrl();
        log.info("Текущий URL '{}'", inboxUrl);
        return inboxUrl;
    }

    public void newMessageClick() {
        driver.click(newMessageButtonXpath);
        log.info("Удачно кликнул по элементу 'Написать письмо'");
    }

    public void newMessageFieldsAssertion() {
        driver.findElement(newMessageAddressXpath);
        driver.findElement(newMessageThemeXpath);
        driver.findElement(newMessageBodyXpath);
        log.info("Все поля удачно отобразились 'Написать письмо'");
    }

    public void fillNewMessageFields(String address, String theme, String body) {
        driver.sendKeys(newMessageAddressXpath, address);
        log.info("Поле 'Кому' заполнено: '{}'", address);
        driver.sendKeys(newMessageThemeXpath, theme);
        log.info("Поле 'Тема' заполнено: '{}'", theme);
        driver.sendKeys(newMessageBodyXpath, body);
        log.info("Поле 'Тело письма' заполнено: '{}'", body);
    }

    public void draftsChecking() {
        String draftsButtonXpath = "//a[@title=\"Черновики\"]";
        driver.click(draftsButtonXpath);
        driver.findElement(String.format(draftExistsXpath, WDriver.getProperty("body")));
        log.info("Черновик сохранился в раздел черновики");
    }

    public void draftOpen() {
        driver.click(String.format(draftExistsXpath, WDriver.getProperty("body")));
        log.info("Удачный клик по черновику");
    }

    public void draftMessageRequisitesChecking(){
        String filledAddressXpath = "//span[@email='%s'][text()='%s']";
        driver.findElement(String.format(filledAddressXpath, WDriver.getProperty("address"), WDriver.getProperty("address")));
        log.info("В поле 'Кому' присутствуют нужные данные");
        String filledThemeXpath = "//div[@aria-label=\"%s\"]";
        driver.findElement(String.format(filledThemeXpath, WDriver.getProperty("theme")));
        log.info("В поле 'Тема' присутствуют нужные данные");
        String filledBodyXpath = "//div[text()=\"%s\"]";
        driver.findElement(String.format(filledBodyXpath, WDriver.getProperty("body")));
        log.info("В поле 'Тело письма' присутствуют нужные данные");
    }

    public void sendMessageClick(){
        String sendMessageButtonXpath = "//div[text()=\"Отправить\"]";
        driver.click(sendMessageButtonXpath);
        log.info("Письмо отправлено");
    }

    public void noDraftsChecking(){
        String noDraftsFieldXpath = "//td[text()=\"Нет сохраненных черновиков.\"]";
        driver.findElement(noDraftsFieldXpath);
        log.info("Страница черновиков пуста");
    }

    public void sentMessageChecking(){
        String sentMessagesButtonXpath = "//div[@data-tooltip=\"Отправленные\"]";
        driver.click(sentMessagesButtonXpath);
        driver.findElement(String.format("//span"+draftExistsXpath, WDriver.getProperty("theme")));
        log.info("В разделе отправленные присутствует отправленное нами письмо");
    }

    public void exit(){
        String accountButtonXpath = "//a[contains(@aria-label, \"(goalrazor@gmail.com)\")]";
        driver.click(accountButtonXpath);
        log.info("Удачный клик по кнопке аккаунта");
        String logoutButtonXpath = "//a[text()=\"Выйти\"]";
        driver.click(logoutButtonXpath);
        log.info("Удачный клик по кнопке 'Выйти'");
    }

}
