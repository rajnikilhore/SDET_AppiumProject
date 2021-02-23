package SDET.SDETAppiumProject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class loginIntoKhanUsingGoogleAccount {

	public static void main(String[] args) throws InterruptedException, MalformedURLException {
		DesiredCapabilities capability = new DesiredCapabilities();
        capability.setCapability(MobileCapabilityType.DEVICE_NAME,"Manzoor");
        capability.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        capability.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"org.khanacademy.android");
        capability.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"org.khanacademy.android.ui.library.MainActivity");
        AndroidDriver driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),capability);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Dismiss\")")).click();
        driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Sign in\")")).click();
        driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Continue with Google\")")).click();
        Thread.sleep(10000);
		driver.findElement(MobileBy.className("android.widget.LinearLayout")).click();;
           //     driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Log In\")")).click();
		Thread.sleep(10000);
		driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Search\")")).click();
		driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Math\")")).click();
		driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Class 6 (Foundation)\")")).click();
		

	}

}
