package org.example;

import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class BusStopsTest {

    public static WebDriver driver;
    public static BusStops busPage;

    @BeforeClass
    public static void setup() {
        // определение пути до драйвера и его настройка
        System.setProperty("webdriver.edge.driver", ConfProperties.getProperty("edgedriver"));
        // создание экземпляра драйвера
        driver = new EdgeDriver();
        busPage = new BusStops(driver);
        // окно разворачивается на полный экран
        driver.manage().window().maximize();
        // задержка на выполнение теста = 10 сек.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // получение ссылки на страницу входа из файла настроек
        driver.get(ConfProperties.getProperty("buspage"));
    }

    @Test
    public void testExecute() {
        busPage.execute("Авт 158");
    }

    @Test
    public void testExecuteSlowInternet() {
        EdgeOptions options = new EdgeOptions();
        options.setCapability("networkThrottling", "regular2G"); // Имитация медленного соединения типа 2G

        EdgeDriver driver = new EdgeDriver(options);
        driver.get(ConfProperties.getProperty("buspage"));

        busPage.execute("Авт 158");
    }
}
