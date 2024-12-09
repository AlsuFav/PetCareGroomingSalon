package ru.fav.petcaregroomingsalon.config;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataSourceConfiguration {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = DataSourceConfiguration.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new RuntimeException("Не удалось найти файл db.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки конфигурации базы данных", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}