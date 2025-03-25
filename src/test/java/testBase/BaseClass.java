package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BaseClass 
{

  private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
  //public static WebDriver driver ;
  public Properties property; 
  public  Logger logger;
  
  
  @BeforeClass
  public void initializeLogger() 
  {
    logger = LogManager.getLogger(this.getClass());
  }

  
  @Parameters({"os","browser"})
  @BeforeTest 
  public void setup(String os, String br) throws IOException 
  {
	  
	 FileReader file = new FileReader("./src//test//resources/config.properties");
	 property = new Properties();
	 property.load(file);
	 
	 if(property.getProperty("execution_env").equalsIgnoreCase("remote"))
	 {
		 DesiredCapabilities capabilities = new DesiredCapabilities();
		 if(os.equalsIgnoreCase("window"))
		 {
			 capabilities.setPlatform(Platform.WIN10);
		 }
		 else if (os.equalsIgnoreCase("linux")) 
		 {
			 capabilities.setPlatform(Platform.LINUX);
		 }
		 else if (os.equalsIgnoreCase("mac")) 
		 {
			 capabilities.setPlatform(Platform.MAC);
		 }
		 else 
		 {
			System.out.println("os not matching");
		 }
		 
		 switch (br.toLowerCase()) 
		 {
	         case "chrome":
	             capabilities.setBrowserName("chrome");
	             break;
	         case "edge":
	             capabilities.setBrowserName("MicrosoftEdge");
	             break;
	         case "firefox":
	             capabilities.setBrowserName("firefox");
	             break;
	         default:
	             System.out.println("no browser match");
	             return;
        }
		 
		 try 
		 {
	         driver.set(new RemoteWebDriver(new URL("http", "localhost", 4444, "/wd/hub"), capabilities));
	     } 
		 catch (MalformedURLException e) 
		 {
	            e.printStackTrace();
	     }
		 
		 
	 }
	 
	 
	 if(property.getProperty("execution_env").equalsIgnoreCase("local"))
	 {
		 switch(br.toLowerCase())
		 {
		 case "chrome":
			 //driver = new ChromeDriver();
			 driver.set(new ChromeDriver());
			 break;
		 case "edge":
			 //driver = new EdgeDriver();
			 driver.set(new EdgeDriver());
			 break; 
		 case "firefox":
			 //driver = new FirefoxDriver();
			 driver.set(new FirefoxDriver());
			 break;
		default:
			System.out.println("no browser matching");
			return;
		 }
	 }
	 
	 getDriver().get(property.getProperty("appURL"));
	 getDriver().manage().deleteAllCookies();
	 getDriver().manage().window().maximize();
	 getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	 
	  
  }
  
  public static WebDriver getDriver() 
  {
      return driver.get();
  }

  
  
  @AfterTest 
  public void tearDown() throws InterruptedException
  {
	  if (getDriver() != null) 
	  {
	        getDriver().quit();
	        driver.remove(); 
	  }
  }
  
  public String captureScreen(String tname)
  {
	  String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	  TakesScreenshot ts = ((TakesScreenshot) getDriver());
	  File sourseFile=ts.getScreenshotAs(OutputType.FILE);
	  String filepath = System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timestamp +".png";
	  File targetFile = new File(filepath);
	  sourseFile.renameTo(targetFile);
	  return filepath;
  }
  
  public String randomString(int length)
  {
	  final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	  StringBuffer sBuffer = new StringBuffer(length);
	  Random random = new Random();
	  for(int i=0;i<=length;i++)
	  {
	  int index =random.nextInt(CHARACTERS.length());
	  sBuffer.append(CHARACTERS.charAt(index));
	  }
	  return sBuffer.toString();
  }
  
  public int randomInt()
  {
	  Random random = new Random();
	  return random.nextInt(10)+1;
	  
  }
  
}
