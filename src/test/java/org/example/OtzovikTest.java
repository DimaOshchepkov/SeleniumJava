package org.example;

import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class OtzovikTest {

    public static WebDriver driver;
    public static Otzovik otzovik;

    @BeforeClass
    public static void setup() {
        // определение пути до драйвера и его настройка
        System.setProperty("webdriver.edge.driver", ConfProperties.getProperty("edgedriver"));
        // создание экземпляра драйвера
        driver = new EdgeDriver();
        otzovik = new Otzovik(driver);
        // окно разворачивается на полный экран
        driver.manage().window().maximize();
        // задержка на выполнение теста = 10 сек.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // получение ссылки на страницу входа из файла настроек
        driver.get(ConfProperties.getProperty("otzovikpage"));
    }

    @Test
    public void testExecute() {
        otzovik.execute();
    }
}
