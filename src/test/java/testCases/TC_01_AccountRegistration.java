package testCases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_01_AccountRegistration extends BaseClass {
	@Test(groups = {"sanity","regression","master"})
	public void accountRegistration() {
		try{
			logger.info( "TC_01_AccountRegistration started");
		HomePage hp=new HomePage(driver);
		logger.info("clicked on m,yaccount");
		hp.clickMyAccount();
		hp.clickRegister();
		logger.info("clicked on registration");
		AccountRegistrationPage arp=new AccountRegistrationPage(driver);
		logger.info("enter customer details");
		arp.setFirstName(randomString());
		arp.setLastName(randomString());
		arp.setEmail(randomString()+"@gmail.com");
		arp.setTelephone(randomNumber());
		String ps=ramdomAlphaNumeric();
		
		arp.setPassword(ps);
		arp.setConfirmPassword(ps);
		arp.setPrivacyPolicy();
		arp.clickContinue();
		logger.info("clicked on continue");
		String msg=arp.getConfirmationMsg();
		Assert.assertEquals(msg, "Your Account Has Been Created!");
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("TC_01_AccountRegistration finished");
	}
	
	
}
