package pageObject;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage
{
  public HomePage(WebDriver driver) 
  {
	super(driver);
  }
  
  @FindBy(xpath = "//a[@class='nav-link'][normalize-space()='Sign in']")
  WebElement linkSignin;
  
  @FindBy(xpath = "//input[@id='email']")
  WebElement inputEmail;
  
  @FindBy(xpath = "//input[@id='password']")
  WebElement inputPassword;
  
  @FindBy(xpath = "//input[@type='submit']")
  WebElement btnSubmit;
  
  @FindBy(xpath = "//div[@class='help-block']")
  WebElement errorMsg;
  
  @FindBy(xpath = "//input[@data-test='search-query']")
  WebElement inputSearch;
  
  @FindBy(xpath = "//button[@data-test='search-submit']")
  WebElement searchSubmit;
  
  @FindBy(xpath = "(//h3[@data-test='search-caption'])[1]//span")
  WebElement searchCaption;
  
  @FindBy(xpath = "//div[@data-test='search_completed']//a[@class='card']//h5")
  List<WebElement> searchProductTitles;
  
  @FindBy(xpath = "//div[@data-test='search_completed']//a[@class='card']")
  List<WebElement> searchProductCards;
  
  	//div[@data-test='search_completed']//a[@class='card']
  	//div[@class='col-md-9']//a[@class='card']
  
 @FindBy(xpath = "//div[@class='col-md-9']//a[@class='card']")
 List<WebElement> allProductCard;
 
 @FindBy(xpath = "//a[@class='nav-link']//i[@class='fa fa-shopping-cart px-1']")
 WebElement cartIcon;
 
 @FindBy(xpath = "//a[@id='admin-menu']")
 WebElement adminMenu;
 
 @FindBy(xpath = "//a[@data-test='nav-sign-out']")
 WebElement btnSignOut;



  
  public void clickSignin()
  {
	  linkSignin.click();
  }
  public void enterEmail(String email) 
  {
	inputEmail.clear();
	inputEmail.sendKeys(email);  
  }
  public void enterPassword(String password)
  {
	  inputPassword.clear();
	  inputPassword.sendKeys(password);
  }
  public void clickSubmitButton()
  {
	  btnSubmit.click();
  }
  public boolean isErrorMessagedisplayed() 
  {
	  
	  Wait<WebDriver> wait = new FluentWait<>(driver)
			    .withTimeout(Duration.ofSeconds(10))
			    .pollingEvery(Duration.ofMillis(500))
			    .ignoring(NoSuchElementException.class);
	  
	  try 
	  {
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='help-block']")));
		  return errorMsg != null && errorMsg.isDisplayed();
	  }
	  catch (Exception e) 
	  {
			return false;
	  }
	  
	  /*
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	 try 
	 {
		WebElement errorMsg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='help-block']")));
		return errorMsg != null && errorMsg.isDisplayed();
	 } 
	 catch (Exception e) 
	 {
		return false;
	 }
	 */
	 
  }
  public String getErrorText()
  {
	  return  errorMsg.getText();
  }
  public boolean getDashboardUrl()
  {
	  //System.out.println(driver.getCurrentUrl());
	  String url=driver.getCurrentUrl();
	 return url.equalsIgnoreCase("https://with-bugs.practicesoftwaretesting.com/#/admin/dashboard");
  }
  
  public void clickSignOut()
  {
	  adminMenu.click();
	  btnSignOut.click();
  }
  
  public void enterSearchText(String searchtext)
  {
	  inputSearch.clear();
	  inputSearch.sendKeys(searchtext);
  }
  
  public void clickSearchSubmit()
  {
	  searchSubmit.click();
  }
  public boolean getSearchCaption(String searchText)
  {
	  if(searchCaption.getText().equalsIgnoreCase(searchText))
	  {
		  return true;
	  }
	  else 
	  {
		  return false;
	  }
	 
  }
  
  public boolean compareSearchResults(String searchText)
  {
	 
	  for(WebElement searchProductTitle :searchProductTitles)
	  {
		  String displayedText=searchProductTitle.getText().toLowerCase();
		  if(!displayedText.contains(searchText.toLowerCase()))
		  {
			  return false;  // return false if both texts not matching and exit loop
			  
		  }
	  }
	  		  return true; // return true after loop (if text matches for all WebElement)
  }
  
  public boolean isProductCardDisplayed()
  {
	  if(searchProductCards.isEmpty())
	  {
		  return true;  
	  }
	  else 
	  {
		  return false;
	  }
  }
  
  public boolean addtoCart()
  {
	  Random random = new Random();
	  int totalDispledProduct=allProductCard.size();
	  //System.out.println(totalDispledProduct);
	  try 
	  {
		  WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		  
		  WebElement producttoclick=allProductCard.get(random.nextInt(totalDispledProduct));
		  wait.until(ExpectedConditions.elementToBeClickable(producttoclick)).click();
		  return true;
	  }
	  catch (ElementClickInterceptedException e) 
	  {
		return false;
	  }
	  
  }

  
  
}
 
  

