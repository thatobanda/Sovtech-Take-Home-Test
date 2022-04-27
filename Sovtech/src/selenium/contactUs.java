package selenium;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import bsh.org.objectweb.asm.Constants;
import utilities.ExcelUtils;




public class contactUs {

	
static ExcelUtils excelUtils = new ExcelUtils();
    
    
   static String excelFilePath ;
	

		 public String baseUrl = "https://www.sovtech.co.za/contact-us/";
			String driverpath = "C:\\chromedriver.exe";
		    public WebDriver driver;
	         
	         
	       @BeforeTest
	       public void launch() {
	       	
	      	 System.setProperty("webdriver.chrome.driver", driverpath);
	      	 driver.manage().window().maximize();
	        driver = new ChromeDriver();
	        driver.get(baseUrl); 
	       	
	     
	      
	   	}
	        

	       @Test
	       public void sendmessage() throws IOException, InterruptedException {
	   		
	       	 WebElement name=driver.findElement(By.id("label-your_name-c2e387f9-4bd8-496f-ab2a-81fbbc31712ae"));
	           
	            WebElement email=driver.findElement(By.id("label-email-c2e387f9-4bd8-496f-ab2a-81fbbc31712a"));
	           
	            WebElement contact=driver.findElement(By.id(".hbspt-forms-0.1:$1.1:$mobilephone"));
	            
	            WebElement size=driver.findElement(By.id("label-numemployees-c2e387f9-4bd8-496f-ab2a-81fbbc31712a"));
	            WebElement service=driver.findElement(By.id(".hbspt-forms-0.1:$2.1:$what_kind_of_problem_is_your_business_currently_facing_.0"));
	            WebElement message=driver.findElement(By.id("label-message-c2e387f9-4bd8-496f-ab2a-81fbbc31712a"));
	            WebElement buttonagree=driver.findElement(By.id(".hbspt-forms-0.2.0.1:0.$LEGAL_CONSENT=1subscription_type_10841063"));
	            WebElement submitBtn=driver.findElement(By.id("submit"));
	            
	            excelUtils.setExcelFile(excelFilePath,"Sheet1");

	          
	            for(int i=1;i<=excelUtils.getRowCountInSheet();i++)
	            {
	            	Thread.sleep(1000);
	            	name.sendKeys(excelUtils.getCellData(i,0));
	            	email.clear();
	            	Thread.sleep(1000);
	            	email.sendKeys(excelUtils.getCellData(i,1));
	            	contact.clear();
	            	Thread.sleep(1000);
	            	contact.sendKeys(excelUtils.getCellData(i,2));
	            	Thread.sleep(1000);
	            	
	            	
	            	JavascriptExecutor js = (JavascriptExecutor) driver;
	            	js.executeScript("arguments[0].click();", size); //Click on the gender radio button using javascript
	           
	            	Thread.sleep(1000);
	            	js.executeScript("arguments[0].click();", service);
	            	
	            	js.executeScript("arguments[0].click();", submitBtn);


	            
	            	Thread.sleep(1000);
	            	
	                
	                WebElement errormessage = driver.findElement(By.xpath("//span[@class='form-field__msg form-field__msg--error']"));
	                
	               
	                if (errormessage.isDisplayed() ) {
	                	
	                	excelUtils.setCellValue(i,9,"PASS",excelFilePath);
	                } else {
	                    
	                    excelUtils.setCellValue(i,9,"FAIL",excelFilePath);
	                }

	               
	            }

	       	
	   	}
	       @AfterTest
	       public void closeBrowser() {
	       	driver.manage().timeouts().implicitlyWait(2000,TimeUnit.SECONDS);
	       	
	           //closing the driver
	           driver.quit();
	   	}
}
