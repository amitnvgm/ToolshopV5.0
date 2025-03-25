package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CheckOutPage extends BasePage
{
  public CheckOutPage(WebDriver driver)
  {
	  super(driver);
  }
  
  
  @FindBy(xpath = "//table[@class='table table-hover']//tr[1]//td[1]//span[@class='product-title']")
  WebElement productName_chekout;
  
  @FindBy(xpath = "//table[@class='table table-hover']//tr[1]//td[3]//input[@data-test='product-quantity']")
  WebElement productQty_Chekout;
  
  @FindBy(xpath = "//a[@class='nav-link']//i[@class='fa fa-shopping-cart px-1']")
  WebElement cartIcon;
  
  @FindBy(xpath = "//table[@class='table table-hover']//tr[1]//td[6]//i")
  WebElement removeProductIcon;
  
  @FindBy(xpath = "//button[@data-test='proceed-1'][contains(text(),'Proceed to checkout')]")
  WebElement btnChekout1;
  
  @FindBy(xpath = "//input[@id='email']")
  WebElement email;
  
  @FindBy(xpath = "//input[@id='password']")
  WebElement password;
  
  @FindBy(xpath = "//input[@value='Login']")
  WebElement btnLogin;
  
  
  @FindBy(xpath = "//button[@data-test='proceed-2']")
  WebElement btnChekout2;
  
 @FindBy(xpath = "//input[@id='address']")
 WebElement address;
 
 @FindBy(xpath = "//input[@id='city']")
 WebElement city;
 
 @FindBy(xpath = "//input[@id='state']")
 WebElement state;
 
 @FindBy(xpath = "//input[@id='country']")
 WebElement country;
 
 @FindBy(xpath = "//input[@id='postcode']")
 WebElement postcode;
 
 @FindBy(xpath = "//button[@data-test='proceed-3']")
 WebElement btnbillingSubmit;
 
 @FindBy(xpath = "//select[@id='payment-method']")
 WebElement pymentMethod;
 
 @FindBy(xpath = "//input[@id='account-name']")
 WebElement accountName;
 
 @FindBy(xpath = "//input[@id='account-number']")
 WebElement accountNumber;
 
 @FindBy(xpath = "//button[@data-test='finish']")
 WebElement accountInfoSubmit;
 
 @FindBy(xpath = "//div[@class='help-block']")
 WebElement paymentSuccess;
 
 @FindBy(xpath = "//button[@data-test='finish']")
 WebElement btnConfirm;
 
 @FindBy(xpath = "//div[@id='order-confirmation']")
 WebElement orderconfirmmsg;
 
 
  public String getProductnameinCart()
  {
	return  productName_chekout.getText();
	    
  }
  
  public int getProductQtyCart()
  {
	return Integer.parseInt(productQty_Chekout.getAttribute("value"))  ;  
  }
  public boolean clickRemove()
  {
	  try 
	  {
		  removeProductIcon.click();
		  if(productName_chekout == null)
		  {
			  return true;
		  }
		  else 
		  {
			  return false;
		  }
		  
	  } 
	  catch (Exception e) 
	  {
		return false;
	  }   
  }
  
 
  public boolean isSuccessfulChekedOut()
  {
	  btnChekout1.click();
	  
	  email.sendKeys("admin@practicesoftwaretesting.com"); 
	  password.sendKeys("welcome01");
	  btnLogin.click();
	  
	  btnChekout2.click();
	  
	  address.clear();
	  address.sendKeys("65,lake,mg road");
	  city.clear();
	  city.sendKeys("rajkot");
	  state.clear();
	  state.sendKeys("Rajstan");
	  country.clear();
	  country.sendKeys("India");
	  postcode.clear();
	  postcode.sendKeys("383315");
	  btnbillingSubmit.click();
	  
	  Select opSelect = new Select(pymentMethod);
	  opSelect.selectByVisibleText("Bank Transfer");
	  accountName.sendKeys("Ram");
	  accountNumber.sendKeys("1234567");
	  accountInfoSubmit.click();
	  if(paymentSuccess.getText().equalsIgnoreCase("Payment was successful"))
	   {
		  btnConfirm.click();
		  if(orderconfirmmsg.isDisplayed())
		  {
			  return true;
		  }
		  else 
		  {
			return false;
		  }
	   }
	  else
	  {
		  return false;
	  }
  }
  
  
  /* Separate function for each action
   
   public void clickCheckOut()
  {
	  btnChekout1.click();
  }
  public void enterLoginDetails()
  {
	  email.sendKeys("admin@practicesoftwaretesting.com"); 
	  password.sendKeys("welcome01");
	  btnLogin.click();
  }
  public void clickCheckOut2() 
  {
	btnChekout2.click();
  }
 
  public void submitBillingInfo()
  {
	  address.clear();
	  address.sendKeys("65,lake,mg road");
	  city.clear();
	  city.sendKeys("rajkot");
	  state.clear();
	  state.sendKeys("Rajstan");
	  country.clear();
	  country.sendKeys("India");
	  postcode.clear();
	  postcode.sendKeys("383315");
	  btnbillingSubmit.click();
  }
   
   */
 
  
}
