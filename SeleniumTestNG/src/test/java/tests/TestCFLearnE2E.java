package tests;

import java.time.Instant;

import org.testng.Assert;
import org.testng.annotations.Test;

import helpers.BaseTest;
import helpers.Log;
import pages.ChallengesPage;
import pages.LoginPage;

public class TestCFLearnE2E extends BaseTest {

	@Test
	public void testLogin() {
		Log.info("Navigate and Login to CTFLearn");
		driver.get("https://ctflearn.com/user/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("testingcbtw123", "Abc.123");
		Assert.assertTrue(driver.getTitle().contains("Dashboard"));
		
		Log.info("Navigate to Challenge");
		driver.get("https://ctflearn.com/challenge/1/browse");
		
		Log.info("Create a Challenge");
		ChallengesPage challengePage = new ChallengesPage(driver);
		long unixTimestamp = Instant.now().getEpochSecond();
		String challengeName = "TestCBTW".concat(String.valueOf(unixTimestamp));
		String challengeFlag = "CTFlearn{a1b2c3e4}";
		String challengeHowToSolve = "Test How To Solve A Challenge With Automated Code";
		challengePage.createChallenge(challengeName, challengeFlag, challengeHowToSolve);
		
		Log.info("Verify challenge was created and displayed successfully in My Challenge");
		challengePage.verifyChallengeSuccessfully(challengeName, "In Review");

	}

}
