package pflb.testWork.awesomeBddWithCucumber.PageObjects;

import pflb.testWork.awesomeBddWithCucumber.Helper.GetPropertiesHelper;
import pflb.testWork.awesomeBddWithCucumber.Runner.Runner;

public class MailBoxPage extends BasePage {

    private String newMessageButtonXpath = "//div[text()=\"Написать\"]";
    private String newMessageAddressXpath = "//textarea[@aria-label=\"Кому\"]";
    private String newMessageThemeXpath = "//input[@name=\"subjectbox\"]";
    private String newMessageBodyXpath = "//div[@role=\"textbox\"]";
    private String draftExistsXpath = "//span[contains(text(), \"%s\")]";

    public String inboxUrl() {
        Runner.findElement(newMessageButtonXpath);
        String inboxUrl = Runner.getUrl();
        log.info("Текущий URL '{}'", inboxUrl);
        return inboxUrl;
    }

    public void newMessageClick() {
        Runner.click(newMessageButtonXpath);
        log.info("Удачно кликнул по элементу 'Написать письмо'");
    }

    public void newMessageFieldsAssertion() {
        Runner.findElement(newMessageAddressXpath);
        Runner.findElement(newMessageThemeXpath);
        Runner.findElement(newMessageBodyXpath);
        log.info("Все поля удачно отобразились 'Написать письмо'");
    }

    public void fillNewMessageFields(String address, String theme, String body) {
        Runner.sendKeys(newMessageAddressXpath, address);
        log.info("Поле 'Кому' заполнено: '{}'", address);
        Runner.sendKeys(newMessageThemeXpath, theme);
        log.info("Поле 'Тема' заполнено: '{}'", theme);
        Runner.sendKeys(newMessageBodyXpath, body);
        log.info("Поле 'Тело письма' заполнено: '{}'", body);
    }

    public void draftsChecking() {
        String draftsButtonXpath = "//div[@data-tooltip=\"Черновики\"]";
        Runner.click(draftsButtonXpath);
        Runner.findElement(String.format(draftExistsXpath, GetPropertiesHelper.getProperty("body")));
        log.info("Черновик сохранился в раздел черновики");
    }

    public void draftOpen() {
        Runner.click(String.format(draftExistsXpath, GetPropertiesHelper.getProperty("body")));
        log.info("Удачный клик по черновику");
    }

    public void draftMessageRequisitesChecking(){
        String filledAddressXpath = "//span[@email='%s'][text()='%s']";
        Runner.findElement(String.format(filledAddressXpath, GetPropertiesHelper.getProperty("address"), GetPropertiesHelper.getProperty("address")));
        log.info("В поле 'Кому' присутствуют нужные данные");
        String filledThemeXpath = "//div[@aria-label=\"%s\"]";
        Runner.findElement(String.format(filledThemeXpath, GetPropertiesHelper.getProperty("theme")));
        log.info("В поле 'Тема' присутствуют нужные данные");
        String filledBodyXpath = "//div[text()=\"%s\"]";
        Runner.findElement(String.format(filledBodyXpath, GetPropertiesHelper.getProperty("body")));
        log.info("В поле 'Тело письма' присутствуют нужные данные");
    }

    public void sendMessageClick(){
        String sendMessageButtonXpath = "//div[text()=\"Отправить\"]";
        Runner.click(sendMessageButtonXpath);
        log.info("Письмо отправлено");
    }

    public void noDraftsChecking(){
        String noDraftsFieldXpath = "//td[text()=\"Нет сохраненных черновиков.\"]";
        Runner.findElement(noDraftsFieldXpath);
        log.info("Страница черновиков пуста");
    }

    public void sentMessageChecking(){
        String sentMessagesButtonXpath = "//div[@data-tooltip=\"Отправленные\"]";
        Runner.click(sentMessagesButtonXpath);
        Runner.findElement(String.format("//span"+draftExistsXpath, GetPropertiesHelper.getProperty("theme")));
        log.info("В разделе отправленные присутствует отправленное нами письмо");
    }

    public void exit(){
        String accountButtonXpath = "//a[contains(@aria-label, \"(goalrazor@gmail.com)\")]";
        Runner.click(accountButtonXpath);
        log.info("Удачный клик по кнопке аккаунта");
        String logoutButtonXpath = "//a[text()=\"Выйти\"]";
        Runner.click(logoutButtonXpath);
        log.info("Удачный клик по кнопке 'Выйти'");
    }

}
