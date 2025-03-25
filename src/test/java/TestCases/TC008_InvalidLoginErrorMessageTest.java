package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC008_InvalidLoginErrorMessageTest extends BaseClass
{
	 @Test (dataProvider = "InvalidLoginDataExcel",dataProviderClass = DataProviders.class)
	 public void IsInvalidLoginEroroMessageDisplayed(String email,String Password) 
	 {
		
		  logger.info("**** Invalid Login Test to check error message  Started");
		  HomePage hPage = new HomePage(getDriver());
		  hPage.clickSignin();
		  hPage.enterEmail(email);
		  hPage.enterPassword(Password);
		  hPage.clickSubmitButton();
		  
		  String errorText = hPage.getErrorText();
		  
		  if(errorText.equalsIgnoreCase("Invalid email or password"))
		  {
			  Assert.assertTrue(true);
		  }
		  else 
		  {
			 Assert.fail("Login error message not displayed ");
			 
		  } 
		  
		  logger.info("**** Invalid Login Test to check error message Ended");
	 }
}
