package pflb.testWork.awesomeBddWithCucumber.PageObjects;

import pflb.testWork.awesomeBddWithCucumber.Runner.WDriver;

public class LoginPage extends BasePage {
    private String passwordFieldXpath = "//input[@type=\"password\"]";
    private String loginFieldXpath = "//input[@type=\"email\"]";

    private String gmailUrl = "https://www.gmail.com";

    public void goToGmail(){
        open(gmailUrl);
        log.info("Перешел по адресу '{}'", gmailUrl);
    }

    public void login(String login, String password) {
        WDriver.sendKeys(loginFieldXpath, login + "\n");
        log.info("Логин '{}' удачно введен", login);

        WDriver.sendKeys(passwordFieldXpath, password + "\n");
        log.info("Пароль '{}' удачно введен", password);
    }

    public void logOutChecking(){
        WDriver.findElement(passwordFieldXpath);
        log.info("Выход из аккаунта совершен");
    }
}
