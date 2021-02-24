package SDET.SDETAppiumProject;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

public class MangetoTestWithExcelInput {
    @Test
	public static void Magneto() throws InterruptedException, IOException {
		
		
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
        
       
      //Path of the excel file
        String ProjectDir = System.getProperty("user.dir");
        FileInputStream fs = new FileInputStream(ProjectDir+"\\SDETMagentoData.xlsx");
      //Creating a workbook
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheetAt(0);
        
        //FETCHING ecxel values
        System.out.println(sheet.getRow(0).getCell(0));
        //get row and col count
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(0);
        int colcount=row.getLastCellNum();  //col count
        int rowcount=sheet.getLastRowNum()+1;
        
        for(int i=1;i<rowcount;i++) {
            
        
        
        
        driver.findElementByXPath("//*[@id='FirstName']").sendKeys((sheet.getRow(i).getCell(0)).getStringCellValue());
        driver.findElementByXPath("//*[@id='LastName']").sendKeys((sheet.getRow(i).getCell(1)).getStringCellValue());
        driver.findElementByXPath("//*[@id='Email']").sendKeys((sheet.getRow(i).getCell(2)).getStringCellValue());
        driver.findElementByXPath("//*[@id='Phone']").sendKeys((sheet.getRow(i).getCell(3)).getStringCellValue());
       
        WebElement country = driver.findElementByXPath("//*[@id='Country']");
        Select sel =  new Select(country);
        sel.selectByValue((sheet.getRow(i).getCell(4)).getStringCellValue());
       
        Select selRole =  new Select(driver.findElementByXPath("//*[@id='Job_Role__c']"));
        selRole.selectByValue((sheet.getRow(i).getCell(5)).getStringCellValue());
        
        driver.findElementByXPath("//*[@id='Company']").sendKeys((sheet.getRow(1).getCell(6)).getStringCellValue());
        Select seloRGRole =  new Select(driver.findElementByXPath("//*[@id='Organizational_Role__c']"));
        seloRGRole.selectByValue((sheet.getRow(i).getCell(7)).getStringCellValue());
        
        Select selCompSell =  new Select(driver.findElementByXPath("//*[@id='Distribution_Method__c']"));
        selCompSell.selectByValue((sheet.getRow(i).getCell(8)).getStringCellValue());
        
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
        
       // String errormsg = driver.findElement(By.xpath("//*[@id='ValidMsgLastName']")).getText();
        String errormsg = driver.findElement(By.xpath("//*[@id='ValidMsgLastName']")).getText();
        
        Assert.assertEquals(errormsg,"THIS FIELD IS REQUIRED.");  
        
        } 
        

	}

}
