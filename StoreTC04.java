package GeneralStoreTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;


public class StoreTC04 extends BaseClass {

	static AndroidDriver<MobileElement> driver;
	SoftAssert softassert = new SoftAssert();

	@Test(priority = 1)
	public void store() throws MalformedURLException, InterruptedException {  // validating the products prices & total on checkout page...
		
		test = extent.createTest("Adding Product in Cart & Checkout", "Adding Product in Cart & Checkout Test Case ");

		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

		test.info("Application started.");

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("Hello");

		driver.hideKeyboard();

		//driver.findElement(By.xpath("//*[@text='Female']"));
		driver.findElementById("com.androidsample.generalstore:id/radioFemale").click();

		driver.findElementById("android:id/text1").click();

		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));");

		driver.findElement(By.xpath("//*[@text='Argentina']")).click();

		driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
		
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click(); // after this the text changes to added to cart & array size decreases from 1 to 0... 
		//driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		
		driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click();
		
		// Mobile gesture
		
		WebElement checkbox =driver.findElement(By.className("android.widget.CheckBox"));
		TouchAction touch = new TouchAction(driver);
		touch.tap(tapOptions().withElement(element(checkbox))).perform();
		
		WebElement tc =driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));

		touch.longPress(longPressOptions().withElement(element(tc)).withDuration(ofSeconds(2))).release().perform();
		driver.findElementById("android:id/button1").click();
		driver.findElementById("com.androidsample.generalstore:id/btnProceed").click();
		
		Thread.sleep(7000);
		
		Set<String> context =driver.getContextHandles();
		
		for(String contextname:context) {
			test.info("Application Format is :" + contextname);
			
		}
		
	
		        
		
		test.info("Test Completed.");
		
	}
	


}
