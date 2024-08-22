package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;


public class TC_03_Login_DataDrivenTest extends BaseClass {
	
	@Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class,groups = {"sanity","regression","master","datadriven"})
	public void login_DDT(String email,String pwd,String exp) {
		logger.info("TC_03_Login_DataDrivenTest is started");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		MyAccountPage mac=new MyAccountPage(driver);
boolean targetpage=	mac.isMyAccountPageExists();
if(exp.equalsIgnoreCase("valid")) {
	if(targetpage==true) {
		mac.clickLogout();
		Assert.assertTrue(true);
	}
	else {
		Assert.assertFalse(true);
	}
}

if(exp.equalsIgnoreCase("invalid")) {
	if(targetpage==true) {
		mac.clickLogout();
		Assert.assertFalse(true);

	}
	else {
		Assert.assertFalse(false);

	}
}
		}
		catch(Exception e) {
		System.out.println(e.getMessage());	
		}
		logger.info("**** Finished TC_003_LoginDDT *****");

	}
	
	
}
