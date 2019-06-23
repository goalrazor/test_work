package stepdefs;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.internal.collections.Pair;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GetPropertiesHelper {
    private static Properties property = new Properties();
    private static final Logger log = LogManager.getLogger();

    /**
     * Возвратить аккаунт-пару для пользователя
     *
     * @return Пара - имя/пароль пользователя
     */
    static Pair getAccountPair() {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/gmail.properties");
            property.load(fis);
            return Pair.create(property.getProperty("login"), property.getProperty("password"));
        } catch (IOException e) {
            log.error("ОШИБКА: Файл свойств отсуствует!");
        }
        return null;
    }

    public static String getProperty(String key) {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/gmail.properties");
            property.load(fis);
            return property.getProperty(key);
        } catch (IOException e) {
            log.error("ОШИБКА: Файл свойств отсуствует!");
        }
        return null;
    }


}
