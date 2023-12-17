package org.example;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Irecommend {

    @FindBy(xpath = "/html/body/div[1]/header/div[1]/a/button")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@id=\"user-login\"]/div/div/div[6]/div[2]/a")
    private WebElement registerButton;
    
    @FindBy(xpath = "//*[@id=\"edit-name\"]")
    private WebElement nameTextBox;

    @FindBy(xpath = "//*[@id=\"edit-mail\"]")
    private WebElement mailTextBox;

    @FindBy(xpath = "//*[@id=\"edit-pass-pass1\"]")
    private WebElement passwordTextBox;


    @FindBy(xpath = "//*[@id=\"edit-pass-pass2\"]")
    private WebElement password2TextBox;

    @FindBy(xpath = "//*[@id='edit-reg-policy']")
    private WebElement regPolicyCheckBox;

    @FindBy(xpath = "//*[@id=\"edit-submit\"]")
    private WebElement submitBtn;
    
    private WebDriver driver;
    private WebDriverWait wait;
    public Irecommend(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void submitBtnClick() {
        submitBtn.click();
    }

    public void loginBtnClick() {
        loginButton.click();
    }
    
    public void registerBtnClick() {
        registerButton.click();
    }

    public void nameTextBoxInput(String name) {
        nameTextBox.sendKeys(name);
    }

    public void mailTextBoxInput(String mail) {
        mailTextBox.sendKeys(mail);
    }

    public void passwordTextBoxInput(String password) {
        passwordTextBox.sendKeys(password);
    }

    public void password2TextBoxInput(String password2) {
        password2TextBox.sendKeys(password2);
    }

    public void regPolicyCheckBoxClick() {
        regPolicyCheckBox.click();
    }

    public void execute(){
        driver.get("https://irecommend.ru/");

        
        loginButton.click();

        registerBtnClick();

        nameTextBoxInput("Oshchepkov");

        mailTextBox.sendKeys("odo.2003@yandex.ru");

        passwordTextBox.sendKeys("435363");

        password2TextBox.sendKeys("435363");

        regPolicyCheckBoxClick();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        submitBtnClick();
    }
}
