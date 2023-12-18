package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ReviewProperties {
    protected static FileInputStream fileInputStream;
    protected static Properties PROPERTIES;
    protected static String defaultReview = "src\\test\\resources\\review.properties";
    static {
        try {
            // указание пути до файла с настройками
            fileInputStream = new FileInputStream(defaultReview);
            PROPERTIES = new Properties();
            PROPERTIES.load(new InputStreamReader(fileInputStream , StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
            // обработка возможного исключения (нет файла и т.п.)
        } finally {
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * метод для возврата строки со значением из файла с настройками
     */
    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}
