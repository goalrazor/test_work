package pflb.testWork.awesomeBddWithCucumber.PageObjects;

import pflb.testWork.awesomeBddWithCucumber.Runner.WDriver;

public class LoginPage extends BasePage {
    private String passwordFieldXpath = "//input[@type=\"password\"]";

    public void goToGmail(){
        String gmailUrl = "https://www.gmail.com";
        open(gmailUrl);
        log.info("Перешел по адресу '{}'", gmailUrl);
    }

    public void login(String login, String password) {
        String loginFieldXpath = "//input[@type=\"email\"]";
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
