package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import testBase.BaseClass;

public class TC004_SearchForNonExistingProductTest extends BaseClass
{
  @Test
  public void SearchForNonExistingProductTest() 
  {
	  logger.info("**** SearchForExistingProductTest Started ****");
	  HomePage hPage = new HomePage(getDriver());
	  hPage.enterSearchText(randomString(5)); // call function to generate random string
	  hPage.clickSearchSubmit();
	  
	  if(hPage.isProductCardDisplayed())
	  {
		  Assert.assertTrue(true);
	  }
	  else 
	  {
		  Assert.fail();
	  }
	  
  }
  
}
