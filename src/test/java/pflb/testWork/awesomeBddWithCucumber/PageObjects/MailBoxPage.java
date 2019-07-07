package pflb.testWork.awesomeBddWithCucumber.PageObjects;

import pflb.testWork.awesomeBddWithCucumber.Helper.GetPropertiesHelper;
import pflb.testWork.awesomeBddWithCucumber.Runner.WDriver;

public class MailBoxPage extends BasePage {

    private String newMessageButtonXpath = "//div[text()=\"Написать\"]";
    private String newMessageAddressXpath = "//textarea[@aria-label=\"Кому\"]";
    private String newMessageThemeXpath = "//input[@name=\"subjectbox\"]";
    private String newMessageBodyXpath = "//div[@role=\"textbox\"]";
    private String draftExistsXpath = "//span[contains(text(), \"%s\")]";
    private String draftsButtonXpath = "//div[@data-tooltip=\"Черновики\"]";
    private String filledAddressXpath = "//span[@email='%s'][text()='%s']";
    private String filledThemeXpath = "//div[@aria-label=\"%s\"]";
    private String filledBodyXpath = "//div[text()=\"%s\"]";
    private String sendMessageButtonXpath = "//div[text()=\"Отправить\"]";
    private String noDraftsFieldXpath = "//td[text()=\"Нет сохраненных черновиков.\"]";
    private String sentMessagesButtonXpath = "//div[@data-tooltip=\"Отправленные\"]";
    private String accountButtonXpath = "//a[contains(@aria-label, \"(goalrazor@gmail.com)\")]";
    private String logoutButtonXpath = "//a[text()=\"Выйти\"]";


    public void newMessageClick() {
        WDriver.click(newMessageButtonXpath);
        log.info("Удачно кликнул по элементу 'Написать письмо'");
    }

    public void newMessageFieldsAssertion() {
        WDriver.findElement(newMessageAddressXpath);
        WDriver.findElement(newMessageThemeXpath);
        WDriver.findElement(newMessageBodyXpath);
        log.info("Все поля удачно отобразились 'Написать письмо'");
    }

    public void fillNewMessageFields(String address, String theme, String body) {
        WDriver.sendKeys(newMessageAddressXpath, address);
        log.info("Поле 'Кому' заполнено: '{}'", address);

        WDriver.sendKeys(newMessageThemeXpath, theme);
        log.info("Поле 'Тема' заполнено: '{}'", theme);

        WDriver.sendKeys(newMessageBodyXpath, body);
        log.info("Поле 'Тело письма' заполнено: '{}'", body);
    }

    public void draftsChecking() {
        WDriver.click(draftsButtonXpath);
        WDriver.findElement(String.format(draftExistsXpath, GetPropertiesHelper.getProperty("body")));
        log.info("Черновик сохранился в раздел черновики");
    }

    public void draftOpen() {
        WDriver.click(String.format(draftExistsXpath, GetPropertiesHelper.getProperty("body")));
        log.info("Удачный клик по черновику");
    }

    public void draftMessageRequisitesChecking() {
        WDriver.findElement(String.format(filledAddressXpath, GetPropertiesHelper.getProperty("address"), GetPropertiesHelper.getProperty("address")));
        log.info("В поле 'Кому' присутствуют нужные данные");

        WDriver.findElement(String.format(filledThemeXpath, GetPropertiesHelper.getProperty("theme")));
        log.info("В поле 'Тема' присутствуют нужные данные");

        WDriver.findElement(String.format(filledBodyXpath, GetPropertiesHelper.getProperty("body")));
        log.info("В поле 'Тело письма' присутствуют нужные данные");
    }

    public void sendMessageClick() {
        WDriver.click(sendMessageButtonXpath);
        log.info("Письмо отправлено");
    }

    public void noDraftsChecking() {
        WDriver.findElement(noDraftsFieldXpath);
        log.info("Страница черновиков пуста");
    }

    public void sentMessageChecking() {
        WDriver.click(sentMessagesButtonXpath);
        WDriver.findElement(String.format("//span" + draftExistsXpath, GetPropertiesHelper.getProperty("theme")));
        log.info("В разделе отправленные присутствует отправленное нами письмо");
    }

    public void exit() {
        WDriver.click(accountButtonXpath);
        log.info("Удачный клик по кнопке аккаунта");

        WDriver.click(logoutButtonXpath);
        log.info("Удачный клик по кнопке 'Выйти'");
    }

}
