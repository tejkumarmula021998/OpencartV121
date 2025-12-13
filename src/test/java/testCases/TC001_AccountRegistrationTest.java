package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	

	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("***** Starting TC001_AccountRegistrationTest *****");
		
		try
		{
	     HomePage hp= new HomePage(driver);
	     hp.clickMyAccount();
	     logger.info("Clicked on MyAccount Link..");
	     hp.clickRegister();
	     logger.info("Clicked on Register Link..");
	     
	     AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
	     logger.info("Providing customer details....");
	     
	     /*
	     regpage.setFirstName("Tejkumar");
	     regpage.setLastName("Mula");
	     regpage.setEmail("tejkumar021998@gmail.com");
	     regpage.setTelephone("6309171814");
	     regpage.setPassword("Tej@021998");
	     regpage.setConfirmPassword("Tej@021998");
	     regpage.setPrivacyPolicy();
	     regpage.clickContinue();
	     */
	     
	     regpage.setFirstName(randomeString().toUpperCase());
	     regpage.setLastName(randomeString().toUpperCase());
	     regpage.setEmail(randomeString()+"@gmail.com");  //randomly generated the email
	     regpage.setTelephone(randomeNumber());
	     
	    String password= randomeAlphaNumeric();
	     regpage.setPassword(password);
	     regpage.setConfirmPassword(password);
	     
	     regpage.setPrivacyPolicy();
	     regpage.clickContinue();
	     
	    
	     
	     
	     logger.info("Validating expected message..");
	     String confmsg=regpage.getConfirmationMsg();
	     /*
	     if(confmsg.equals("Your Account Has Been Created!"))
	     {
	    	     Assert.assertTrue(true);
	     }
	     else
	     {
	    	        logger.error("Test failed...");
				logger.debug("Debug logs..");
	    	     Assert.assertTrue(false);
	     }
	     */
	     
	     Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			logger.error("Test failed...");
			logger.debug("Debug logs..");
			
			Assert.fail();
		}
		
		logger.info("***** Finished TC001_AccountRegistrationTest *****");
	}
	


}
