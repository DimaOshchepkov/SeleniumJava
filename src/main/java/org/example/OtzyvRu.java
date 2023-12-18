package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class OtzyvRu {
    private static final String SITE = "https://otzyv.ru/";

    private final WebDriver driver;

    @FindBy(xpath = "//a[text()='Форумы']")
    private WebElement forumLink;

    @FindBy(id = "s")
    private WebElement searchInput;

    @FindBy(xpath = "//select[@name = 'f']")
    private WebElement selectSubforum;

    @FindBy(xpath = "//input[@name = 'onlytheme']")
    private WebElement onlyThemeCheckBox;

    @FindBy(xpath = "//*[@id=\"mainCol\"]/div[4]/form/div[8]/input")
    private WebElement searchButton;

    public OtzyvRu(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void execute() {
        driver.get(SITE);

        forumLink.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("https://forum.otzyv.ru/allforums.php"));

        searchInput.sendKeys("Россия");

        // Заменяем Select на обычный WebElement и устанавливаем значение
        selectSubforum.sendKeys("57");

        onlyThemeCheckBox.click();

        searchButton.click();
    }
}
