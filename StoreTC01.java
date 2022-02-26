package GeneralStoreTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;


public class StoreTC01 extends BaseClass {

	static AndroidDriver<MobileElement> driver;
	SoftAssert softassert = new SoftAssert();

	@Test(priority = 1)
	public void store() throws MalformedURLException {

		test = extent.createTest("Lets Shop", "Lets Shop Test Case ");

		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

		test.info("Application started.");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("Hello");

		driver.hideKeyboard();

		// driver.findElement(By.xpath("//*[@text='Female']")).click(); //
		// com.androidsample.generalstore:id/radioFemale // //*[@text='Female']
		driver.findElementById("com.androidsample.generalstore:id/radioFemale").click();

		driver.findElementById("android:id/text1").click();

		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));");

		driver.findElement(By.xpath("//*[@text='Argentina']")).click();

		driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();

	}

	@Test(priority = 2)
	public void VerifyToastMsg() throws MalformedURLException {

		test = extent.createTest("Verify Toast Msg", "Verify Toast Msg Test Case ");

		cap.setCapability("appPackage", "com.androidsample.generalstore");
		cap.setCapability("appActivity", "com.androidsample.generalstore.SplashActivity");

		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

		test.info("Application started.");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.hideKeyboard();

		// driver.findElement(By.xpath("//*[@text='Female']")).click();
		driver.findElementById("com.androidsample.generalstore:id/radioFemale").click();

		driver.findElementById("android:id/text1").click();

		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));");

		driver.findElement(By.xpath("//*[@text='Argentina']")).click();

		driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();

		String toastmsg = driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");

		test.info("Validation Toast Msg: " + toastmsg);

		softassert.assertEquals("Please enter your name", toastmsg);

	}

	@AfterTest
	public void ConClose() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

}
