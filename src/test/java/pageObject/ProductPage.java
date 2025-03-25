package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage
{
  public ProductPage(WebDriver driver) 
  {
	super(driver);
  }
  
  @FindBy(xpath = "//button[@id='btn-add-to-cart']")
  WebElement addtoCartBtn;
  
  @FindBy(xpath = "//input[@data-test='quantity']")
  WebElement productQuantity;
  
  @FindBy(xpath = "//a[@data-test='nav-cart']//span[@id='lblCartCount']")
  WebElement lblCartCount;
  
  @FindBy(xpath = "//div//h1[@data-test='product-name']")
  WebElement ProductName;
  
  @FindBy(xpath = "//a[@class='nav-link']//i[@class='fa fa-shopping-cart px-1']")
  WebElement cartIcon;
  
  @FindBy(xpath = "//app-toasts[@class = 'toast-container position-fixed top-0 end-0 p-3']")
  WebElement confirmmsg;
  
  public boolean clickAddtoCart(int product_Qty)  
  {
	 if(addtoCartBtn.isEnabled())
	 {
		 productQuantity.clear();
		 productQuantity.sendKeys(Integer.toString(product_Qty));
		 addtoCartBtn.click();
		 return true;
		 //Thread.sleep(3000);
	 }
	 else 
	 {
		return false;
		 //System.out.println("add to cart button disabled");
	 }
  
  }
  public int getCartCount()
  {
	  return Integer.parseInt(lblCartCount.getText());
	  //System.out.println(lblCartCount.getText());
  }
  
  public String getProductName()
  {
	  return ProductName.getText();
  }
  public void clickcartIcon()
  {
	  cartIcon.click();
  }
  
  /*  error message function
   
  public boolean checkConfirmMsg()
  {
	  
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
	  if (wait.until(ExpectedConditions.visibilityOf(confirmmsg)).isDisplayed())
		 {
			 return false;
		 }
		 else 
		 {
			 return true;
		 }
  } */
  
}
