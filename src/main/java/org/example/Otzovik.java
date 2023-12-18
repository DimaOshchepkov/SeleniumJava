package org.example;

import lombok.Data;
import lombok.val;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Data
public class Otzovik {
    private static final String SITE = "https://otzovik.com/";
    private final WebDriver driver;
    private List<String> data;

    @FindBy(xpath = "//*[@id=\"header\"]/div[2]/div/a[2]")
    private WebElement postReviewButton;

    @FindBy(id = "tproduct")
    private WebElement productInput;

    @FindBy(id = "rating0")
    private WebElement selectRating;

    @FindBy(id = "content_title")
    private WebElement contentTitle;

    @FindBy(xpath = "//*[@id=\"content_body_main\"]/div[2]/div[2]/div/textarea[2]")
    private WebElement editorContent;

    @FindBy(xpath = "//*[@id=\"review_post\"]/div[6]/div[2]/textarea")
    private WebElement contentPros;

    @FindBy(xpath = "//*[@id=\"review_post\"]/div[5]/div[2]/textarea")
    private WebElement contentCons;

    @FindBy(id = "previewbtn")
    private WebElement previewButton;

    @FindBy(id = "noproduct")
    private WebElement onwardButton;

    @FindBy(xpath = "//*[@id=\"review_post\"]/div[7]/div[1]/div[2]/label[2]/span")
    private WebElement notRecommendedBtn;

    @FindBy(xpath = "//*[@id=\"review_post\"]/div[7]/div[1]/div[2]/label[1]/span")
    private WebElement recommendedBtn;

    @FindBy(xpath = "//*[@id=\"wayofac\"]/div[2]/div[1]/label/span[1]")
    private WebElement buyRadioBtn;

    @FindBy(xpath = "//*[@id=\"wayofac\"]/div[2]/div[2]/label/span[1]")
    private WebElement presentRadioBtn;

    @FindBy(xpath = "//*[@id=\"wayofac\"]/div[2]/div[3]/label/span[1]")
    private WebElement askedRadioBtn;

    public Otzovik(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void execute() {
        driver.get(SITE);

        val product = ReviewProperties.getProperty("product");
        val rating = ReviewProperties.getProperty("rating");
        val reviewTitle = ReviewProperties.getProperty("reviewTitle");
        val review = ReviewProperties.getProperty("review");
        val pros = ReviewProperties.getProperty("pros");
        val cons = ReviewProperties.getProperty("cons");
        val recommendValue = ReviewProperties.getProperty("recommendValue");
        val purchasing = ReviewProperties.getProperty("purchasing");

        postReviewButton.click();

        val wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains(SITE + "postreview.php"));

        productInput.sendKeys(product);

        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//*[@id=\"product_select\"]/div[2]/div[2]/div")));

        onwardButton.click();

        //wait.until(ExpectedConditions.urlContains(SITE + "postreview.php?pid=2311284&"));

        (new Select(selectRating)).selectByValue(rating);

        contentTitle.sendKeys(reviewTitle);

        wait.until(ExpectedConditions.elementToBeClickable(editorContent));
        editorContent.sendKeys(review);

        contentPros.sendKeys(pros);

        contentCons.sendKeys(cons);

        clickRecommenedBtn(recommendValue);

        clickPurchasingRadionBtn(purchasing);

        //previewButton.click();
    }

    private void clickRecommenedBtn(String recommendValue) {
        if ("Рекомендую".equalsIgnoreCase(recommendValue)) {
            recommendedBtn.click();
        } else if ("Не рекомендую".equalsIgnoreCase(recommendValue)) {
            notRecommendedBtn.click();
        } else {
            throw new IllegalStateException("Некорректное значение для рекомендации: " + recommendValue);
        }
    }

    private void clickPurchasingRadionBtn(String purchasing) {
        if ("Купили".equalsIgnoreCase(purchasing)) {
            buyRadioBtn.click();
        } else if ("Подарили".equalsIgnoreCase(purchasing)) {
            presentRadioBtn.click();
        }else if ("Подарили".equalsIgnoreCase(purchasing)) {
            askedRadioBtn.click();
        } else {
            throw new IllegalStateException("Некорректное значение для рекомендации: " + purchasing);
        }
    }
}
