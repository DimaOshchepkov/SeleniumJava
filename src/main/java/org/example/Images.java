package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.imageio.ImageIO;

public class Images {
    private Integer countImages = 200;
    private WebDriverWait wait;
    private WebDriver driver;
    @FindBy(xpath = "/html/body/div[1]/header/div/div[2]/div[1]/form/div[1]/span/span/input")
    private WebElement searchTextBox;

    @FindBy(xpath = "/html/body/div[1]/header/div/div[2]/div[1]/form/div[2]/button/div[3]")
    private WebElement searchButton;

    public Images(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void sendMessage(String message) {
        wait.until(ExpectedConditions.elementToBeClickable(searchTextBox));
        searchTextBox.sendKeys(message);
    }

    public void searchBtnClick() {
        searchButton.click();
    }

    public void execute() throws InterruptedException {
        driver.get("https://yandex.ru/images");

        sendMessage("котики");
        searchBtnClick();

        int imagePtr = 0;
        int initSize = 0;
        int newSize = 0;
        List<WebElement> allElements;

        do {
            initSize = newSize;
            // Получаем все элементы, которые есть на странице на момент начала работы
            allElements = driver.findElements(By.cssSelector("img.SimpleImage-Image.SimpleImage-Image_clickable"));
            newSize = allElements.size();

            // Обрабатываем новые элементы
            for (int i = imagePtr; i < allElements.size() && i < countImages; i++) {
                String imageUrl = allElements.get(i).getAttribute("src");
                downloadImage(imageUrl, "src\\images\\" + Integer.toString(imageUrl.hashCode()) + ".jpg");
                imagePtr++;
            }

            // Прокручиваем страницу вниз
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                    By.cssSelector("img.SimpleImage-Image.SimpleImage-Image_clickable"), allElements.size()));

        } while (newSize > initSize && imagePtr < countImages);
    }

    private void downloadImage(String imageUrl, String destinationPath) {
        try {
            URL url = new URL(imageUrl);
            BufferedImage image = ImageIO.read(url);

            File outputFile = new File(destinationPath);
            ImageIO.write(image, "jpg", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
