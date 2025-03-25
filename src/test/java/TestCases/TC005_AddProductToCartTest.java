package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.CheckOutPage;
import pageObject.HomePage;
import pageObject.ProductPage;
import testBase.BaseClass;

public class TC005_AddProductToCartTest extends BaseClass
{
	
  @Test
  public void addProductToCart() throws InterruptedException 
  {
	  
	  HomePage hPage = new HomePage(getDriver());
	  Assert.assertTrue(hPage.addtoCart(), "element is not clickable");
	  ProductPage productPage = new ProductPage(getDriver());
	  String productName=productPage.getProductName();
	  int product_qty= randomInt();
	  Assert.assertTrue(productPage.clickAddtoCart(product_qty), "iteam might out of stock");
	  
	  // item add to cart successfully but error message displaying when click add to cart..if you want to make test fail
	  //because of this uncomment below statement. and uncomment productPage.checkConfirmMsg() 
	  
	  //Assert.assertTrue(productPage.checkConfirmMsg(), "item added to cart but error message displayed");
	  
	  int cartCount=productPage.getCartCount();
	  Assert.assertEquals(product_qty, cartCount,"entered " + product_qty + " quantities but in cart " + cartCount + " showing" );
	  productPage.clickcartIcon();
	  CheckOutPage cPage = new CheckOutPage(getDriver());
	  cPage.getProductnameinCart();
	  cPage.getProductQtyCart();
	  Assert.assertTrue(productName.equalsIgnoreCase(cPage.getProductnameinCart()), "product name Mismatch expected :" + productName + " but Found : " + cPage.getProductnameinCart());
	  Assert.assertEquals(cartCount, cPage.getProductQtyCart(),"product qty Mismatch expected : " + cartCount + " but found : " + cPage.getProductQtyCart());
	   
  }
  
}



