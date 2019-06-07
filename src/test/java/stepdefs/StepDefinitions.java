package stepdefs;


import PageObjects.LoginPage;
import PageObjects.MailBoxPage;
import cucumber.api.java.ru.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import utils.Listener;

import static stepdefs.WDriver.getAccountPair;


@Listeners(Listener.class)
public class StepDefinitions {
    private LoginPage loginPage;
    private MailBoxPage mailBoxPage;

    @Дано("^открыта страница входа в почтовый ящик Gmail\\.com$")
    public void openGmail() throws Throwable {
        loginPage = new LoginPage();
        loginPage.goToGmail();
    }

    @Когда("^пользователь вводит логин и нажимает Enter и пароль и нажимает Enter$")
    public void enterLoginAndPass() {

        String login = getAccountPair().first().toString();
        String password = getAccountPair().second().toString();

        loginPage.login(login, password);
    }


    @Тогда("^открыта старница почтового ящика$")
    public void openMailBox() {
        mailBoxPage = new MailBoxPage();
        Assert.assertEquals(mailBoxPage.inboxUrl(), "https://mail.google.com/mail/u/0/#inbox", "Страница почтового ящика не открылась");

    }

    @Тогда("^пользователь нажимает на создание нового письма$")
    public void newMessageClick() {
        mailBoxPage.newMessageClick();
    }


    @Тогда("^корректно отображаются поля адресат, тема письма и тело письма$")
    public void newMessageFieldsAssertion() {
        mailBoxPage.newMessageFieldsAssertion();
    }

    @Если("^пользователь вводит в поле кому адрес 'адресс' в поле тема 'тема', а в тело письма 'текст тела'$")
    public void fillNewMessageFields() {
        String address = WDriver.getProperty("address");
        String theme = WDriver.getProperty("theme");
        String body = WDriver.getProperty("body");
        mailBoxPage.fillNewMessageFields(address, theme, body);
    }

    @То("^черновик письма корректно сохраняется в раздел черновики$")
    public void draftsChecking() {
        mailBoxPage.draftsChecking();
    }

    @Если("^пользователь открывает черновик письма$")
    public void draftOpen() {
        mailBoxPage.draftOpen();
    }

    @То("^разделы адреса, тема письма и тело письма заполнены корректными данными$")
    public void draftChecking() {
        mailBoxPage.draftMessageRequisitesChecking();
    }

    @Если("^пользователь нажимает отправить$")
    public void sendMessage() {
        mailBoxPage.sendMessageClick();
    }


    @То("^письмо отправляется и удаляется из раздела черновики$")
    public void noDraftChecking() {
        mailBoxPage.noDraftsChecking();
    }

    @То("^добавляется в раздел отправленные$")
    public void sentMessageCheck() {
       mailBoxPage.sentMessageChecking();
    }

    @Тогда("^пользователь выходит из ученой записи$")
    public void logout() {
        mailBoxPage.exit();
    }

    @Тогда("^выход происходит корректно$")
    public void logoutChecking() {
        loginPage.logOutChecking();
    }


}
