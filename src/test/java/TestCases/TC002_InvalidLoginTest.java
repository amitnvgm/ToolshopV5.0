package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC002_InvalidLoginTest extends BaseClass
{
	
 
	 @Test(dataProvider="InvalidLoginDataExcel",dataProviderClass = DataProviders.class)
	  public void InvalidLogin(String email,String password) 
	  {
		 
		  logger.info("**** Invalid Login Test Started");
		  HomePage hPage = new HomePage(getDriver());
		  hPage.clickSignin();
		  hPage.enterEmail(email);
		  hPage.enterPassword(password);
		  hPage.clickSubmitButton();
		  
		  Assert.assertTrue(!hPage.getDashboardUrl(), "logged in with wrong credentail");
		
		  logger.info("**** Invalid Login Test Ended"); 
	  }
	 
	 /*
	
	 }*/
  
}
