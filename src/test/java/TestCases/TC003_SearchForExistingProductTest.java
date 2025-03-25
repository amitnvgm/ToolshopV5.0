package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import testBase.BaseClass;


public class TC003_SearchForExistingProductTest extends BaseClass
{
  @Test
  public void SearchForExistingProduct() 
  {
	  logger.info("**** SearchForExistingProductTest Started ****");
	  String searchText = "Hammer";
	  HomePage hPage = new HomePage(getDriver());
	  hPage.enterSearchText(searchText);
	  hPage.clickSearchSubmit();
	  if(hPage.isProductCardDisplayed()) 
	  {
		  Assert.fail();
	  }
	  else 
	  {
		  Assert.assertTrue(true);
	  }
	  
	  
	  //second logic : which will pass test only if product card displayed and search key match with resulting 
	  //products title
	  //boolean captionStatus=hPage.getSearchCaption(searchText);
	  
	  /*
	  boolean productDisplayStatus=hPage.isProductCardDisplayed();
	  
	  boolean resultStatus=hPage.compareSearchResults(searchText);

	  if(!productDisplayStatus && resultStatus)
	  {
		  Assert.assertTrue(true);
	  }
	  else 
	  {
		  Assert.fail();
	  }
	 
	  
	  //Assert.assertTrue(captionStatus && resultStatus,"Search results did not match expectations."); 
	   
	  */
	  
	  logger.info("**** SearchForExistingProductTest Ended ****");
  }
}
