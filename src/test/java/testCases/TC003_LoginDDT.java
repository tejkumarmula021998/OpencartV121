package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
Data is Valid - login success - test pass -logout
Data is Valid - login failed - test fail

Data is InValid - login success - test fail - logout
Data is InValid - login failed - test pass
*/

public class TC003_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class ,groups="DataDriven") //getting data provider from different class
	public void verify_loginDDT(String email, String pwd, String exp) throws InterruptedException
	{
		logger.info("****Starting TC_003_LoginDDT ****");
		
		try
		{
		//HomePage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
				
		//Login
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
				
		//MyAccount
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		
		if(exp.equalsIgnoreCase("Valid"))   //Data is Valid - login success - test pass -logout
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(true);
				
			}
			else                           //Data is Valid - login failed - test fail
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("InValid"))  //Data is InValid - login success - test fail - logout
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(false);
				
			}
			else                           //Data is InValid - login failed - test pass
			{
				Assert.assertTrue(true);
			}
			
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		Thread.sleep(3000);
		logger.info("****Finished TC_003_LoginDDT ****");
	}

}
