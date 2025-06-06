package pages;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.logging.Level;

import org.testng.Assert;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class ChallengesPage {
	private WebDriver web;

	@FindBy(css = "#navbarDropdownMenuLink ~ a")
	private WebElement challengesExpandIcon;

	@FindBy(xpath = "//a[contains(text(), 'Create Challenge')]")
	private WebElement createChallengeItem;

	@FindBy(xpath = "//a[@href=\"/challenge/by/testingcbtw123\"]")
	private WebElement myChallengesItem;

	@FindBy(id = "title")
	private WebElement challengTitleTxtbox;

	@FindBy(id = "flag")
	private WebElement challengFlagTxtbox;

	@FindBy(id = "howtosolve")
	private WebElement challengHowToSolveTxtbox;

	@FindBy(css = "button[type='submit']")
	private WebElement submitChallengeButton;

	@FindBy(id = "profileDropdown']")
	private WebElement profileDropdown;

	@FindBy(xpath = "//a[@href='/user/logout'")
	private WebElement logoutButton;

	public ChallengesPage(WebDriver driver) {
		this.web = driver;
		PageFactory.initElements(this.web, this);
	}

	public void createChallenge(String title, String flag, String howtosolve) {
		this.challengesExpandIcon.click();
		Wait<WebDriver> wait = new FluentWait<>(this.web).withTimeout(Duration.ofSeconds(2))
				.pollingEvery(Duration.ofMillis(2000)).ignoring(ElementNotInteractableException.class);

		wait.until(d -> {
			return this.createChallengeItem.isDisplayed();

		});
		this.createChallengeItem.click();

		wait.until(d -> {
			return this.challengTitleTxtbox.isDisplayed();

		});
		this.challengTitleTxtbox.clear();
		this.challengTitleTxtbox.sendKeys(title);
		this.challengFlagTxtbox.clear();
		this.challengFlagTxtbox.sendKeys(flag);
		this.challengHowToSolveTxtbox.clear();
		this.challengHowToSolveTxtbox.sendKeys(howtosolve);
		this.submitChallengeButton.click();

		PageFactory.initElements(this.web, this);
		WebElement titleDisplay = web.findElement(By.id("title-display"));
		wait.until(d -> {
			return titleDisplay.isDisplayed();

		});

	}

	public void verifyChallengeSuccessfully(String title, String status) {
		this.challengesExpandIcon.click();
		PageFactory.initElements(this.web, this);
		Wait<WebDriver> wait = new FluentWait<>(this.web).withTimeout(Duration.ofSeconds(2))
				.pollingEvery(Duration.ofMillis(2000)).ignoring(ElementNotInteractableException.class);

		wait.until(d -> {
			return this.myChallengesItem.isDisplayed();

		});

		List<WebElement> listOfChallenge = web.findElements(By.cssSelector("div.card"));

		for (WebElement challenge : listOfChallenge) {

			if (challenge.getText().contains(title) == true) {
				Assert.assertTrue(challenge.getText().contains(title));
				Assert.assertTrue(challenge.getText().contains(status));
				break;
			}
		}

	}

	public void logout() {
		this.profileDropdown.click();

		Wait<WebDriver> wait = new FluentWait<>(this.web).withTimeout(Duration.ofSeconds(2))
				.pollingEvery(Duration.ofMillis(2000)).ignoring(ElementNotInteractableException.class);

		wait.until(d -> {
			return this.logoutButton.isDisplayed();

		});

		this.logoutButton.click();
	}

}
