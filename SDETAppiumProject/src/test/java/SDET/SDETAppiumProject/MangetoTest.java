package SDET.SDETAppiumProject;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class MangetoTest {
    @Test
	public static void Magneto() throws MalformedURLException, InterruptedException {
		
		
		DesiredCapabilities capability = new DesiredCapabilities();
        capability.setCapability(MobileCapabilityType.DEVICE_NAME,"sdet");
        capability.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        capability.setCapability(MobileCapabilityType.BROWSER_NAME,"Chrome");
        //capability.setCapability(MobileCapabilityType.NO_RESET,true);
        AndroidDriver driver =  new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),capability);
        driver.get("https://magento.com/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        
        driver.findElement(By.xpath("//*[text()='View a product tour']")).click();
        
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id='navbar']/div[3]")).click();
        Thread.sleep(10000);
       
        driver.findElementByXPath("//*[@id='FirstName']").sendKeys("test");
        driver.findElementByXPath("//*[@id='LastName']").sendKeys("");
        driver.findElementByXPath("//*[@id='Email']").sendKeys("asdakd@gmail.com");
        driver.findElementByXPath("//*[@id='Phone']").sendKeys("123456789");
       
        WebElement country = driver.findElementByXPath("//*[@id='Country']");
        Select sel =  new Select(country);
        sel.selectByValue("US");
       
        Select selRole =  new Select(driver.findElementByXPath("//*[@id='Job_Role__c']"));
        selRole.selectByValue("Ecommerce Director");
        
        driver.findElementByXPath("//*[@id='Company']").sendKeys("MIB");
        Select seloRGRole =  new Select(driver.findElementByXPath("//*[@id='Organizational_Role__c']"));
        seloRGRole.selectByValue("Merchant");
        
        Select selCompSell =  new Select(driver.findElementByXPath("//*[@id='Distribution_Method__c']"));
        selCompSell.selectByValue("B2B");
        
        TouchAction act=new TouchAction(driver);
        Dimension d= driver.manage().window().getSize();

        int width=d.width;
        int height=d.height;
        int x1=width/2;
        int y1=4*height/5;
        int x2=width/2;
        int y2=2*height/5;
        Thread.sleep(10000);
        act.press(PointOption.point(x1, y1)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(x2, y2)).release().perform();
        Thread.sleep(10000);
        act.press(PointOption.point(x1, y1)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(x2, y2)).release().perform();
	
        
        driver.findElement(By.xpath("//*[text()='Submit']")).click();
     
        //VALIDATE page is not submitted and user is on same page
        
        String errormsg = driver.findElement(By.xpath("//*[@id='ValidMsgLastName']")).getText();
        
        Assert.assertEquals(errormsg,"THIS FIELD IS REQUIRED.");  
        
        
        

	}

}
