package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	private WebDriver web;
	
	@FindBy(xpath="//a[@href='/user/login']")
	private WebElement loginButton;
	
	@FindBy(css="#identifier")
	private WebElement userNameOrEmailTxtBox;
	
	
	@FindBy(css="#password")
	private WebElement pwd;
	
	@FindBy(css="button[type='submit']")
	private WebElement submitButton;
	
	
	public LoginPage(WebDriver driver) {
		this.web = driver;
		PageFactory.initElements(this.web, this);
	}
	
	public void login(String user, String pass) {
		this.loginButton.click();
        this.userNameOrEmailTxtBox.sendKeys(user);
        this.pwd.sendKeys(pass);
        this.submitButton.click();
    }
}
