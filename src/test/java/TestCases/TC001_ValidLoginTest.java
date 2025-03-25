package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import testBase.BaseClass;

public class TC001_ValidLoginTest extends BaseClass
{
  @Test
  public void validLogin() 
  {
	  logger.info("**** Valid Login Test Started");
	  HomePage hPage = new HomePage(getDriver());
	  hPage.clickSignin();
	  
	  hPage.enterEmail("admin@practicesoftwaretesting.com");
	  hPage.enterPassword("welcome01");
	  hPage.clickSubmitButton();
	  
	  boolean errormsgStatus = hPage.isErrorMessagedisplayed(); 
	  boolean dashboardStatus = hPage.getDashboardUrl();
	  
	  
	  
	  if( !errormsgStatus && dashboardStatus)
	  {
		  Assert.assertTrue(true);
		  hPage.clickSignOut();
		 
	  }
	  else 
	  {
		 Assert.fail();
		  
	  }
		
	
	  logger.info("**** Valid Login Test Ended");
  }
}
