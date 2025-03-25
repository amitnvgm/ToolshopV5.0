package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.CheckOutPage;
import pageObject.HomePage;
import pageObject.ProductPage;
import testBase.BaseClass;

public class TC007_SuccessfulCheckoutTest extends BaseClass
{
  @Test
  public void successfulCheckout () 
  {
	  HomePage hPage = new HomePage(getDriver());
	  Assert.assertTrue(hPage.addtoCart(), "element is not clickable");
	  ProductPage productPage = new ProductPage(getDriver());
	  int product_qty= randomInt();
	  if(productPage.clickAddtoCart(product_qty))	
	  {
		  productPage.clickcartIcon();
		  CheckOutPage cPage = new CheckOutPage(getDriver());
		  Assert.assertTrue(cPage.isSuccessfulChekedOut(), "Chekout failed");
	  }
	  else 
	  {
		Assert.fail("product not available in cart");
	  }
	   
  }
}
