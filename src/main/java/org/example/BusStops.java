package org.example;

import lombok.Data;
import lombok.val;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Data
public class BusStops {
    private final WebDriver driver;

    @FindBy(xpath = "//*[@id='marshlist']")
    private WebElement routeDropdown;

    @FindBy(xpath = "//form[@id='marshSearch']//input[@value='Найти']")
    private WebElement searchButton;


    public BusStops(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver =driver;
    }

    public void selectBus(String bus) {
        (new Select(routeDropdown)).selectByValue(handleRoute(bus));
    }

    public void searchButtonClick() {
        searchButton.click();
    }

    private String handleRoute(String route) {
        if (route.matches("Авт \\d{1,2}")) {
            return "100" + route.substring(4);
        } else if (route.matches("Авт \\d{1,3}")) {
            return "3" + route.substring(4);
        } else if (route.matches("Тролл Т\\d{1,2}")) {
            return "500" + route.substring(7, 8) + "0";
        }
        return null;
    }

    public void execute(String bus) {
        selectBus(bus);
        searchButtonClick();
    }

}
