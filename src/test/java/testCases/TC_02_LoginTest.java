package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_02_LoginTest extends BaseClass{
    @Test(groups = {"sanity","regression","master"})
	public void loginTest() {
    	try {
    		logger.info("TC_02_LoginTest started");
	HomePage hp=new HomePage(driver);
	
	hp.clickMyAccount();
	logger.info("clicked on myaccount");
	hp.clickLogin();
	logger.info("clicked on login");

	LoginPage lp=new LoginPage(driver);
	lp.setEmail(p.getProperty("email"));
	lp.setPassword(p.getProperty("password"));
	logger.info("entered email and password");

	lp.clickLogin();
	MyAccountPage mp=new MyAccountPage(driver);
	boolean status=mp.isMyAccountPageExists();
	logger.info(" verified maccuntapge");

	Assert.assertTrue(status);
    	}
    	catch(Exception e) {
    		Assert.fail();
    	}
}
}
