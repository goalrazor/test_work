package PageObjects;

import Runner.Runner;

public class LoginPage extends BasePage {
    private String passwordFieldXpath = "//input[@type=\"password\"]";

    public void goToGmail(){
        String gmailUrl = "https://www.gmail.com";
        open(gmailUrl);
        log.info("Перешел по адресу '{}'", gmailUrl);
    }

    public void login(String login, String password) {
        String loginFieldXpath = "//input[@type=\"email\"]";
        Runner.sendKeys(loginFieldXpath, login + "\n");
        log.info("Логин '{}' удачно введен", login);

        Runner.sendKeys(passwordFieldXpath, password + "\n");
        log.info("Пароль '{}' удачно введен", password);
    }

    public void logOutChecking(){
        Runner.findElement(passwordFieldXpath);
        log.info("Выход из аккаунта совершен");
    }
}
