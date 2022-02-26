package GeneralStoreTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class StoreTC02 extends BaseClass {

	static AndroidDriver<MobileElement> driver;
	SoftAssert softassert = new SoftAssert();

	@Test(priority = 1)
	public void store() throws MalformedURLException, InterruptedException {

		test = extent.createTest("Adding Product in Cart", "Adding Product in Cart Test Case ");

		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

		test.info("Application started.");

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("Hello");

		driver.hideKeyboard();

		driver.findElementById("com.androidsample.generalstore:id/radioFemale").click();

		driver.findElementById("android:id/text1").click();

		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));");

		driver.findElement(By.xpath("//*[@text='Argentina']")).click();

		driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();


		driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\"Jordan 6 Rings\").instance(0))"));

		int count = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();

		for (int i = 0; i < count; i++) {

			String name = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();

			if (name.equals("Jordan 6 Rings")) {
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
				break;
			}

		}

		driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click();
		Thread.sleep(2000);
		
		String text = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
		
		Assert.assertEquals("Jordan 6 Rings", text);   //  validating the product name 
		test.info("Watch Name: " + text);
		
		driver.findElementById("com.androidsample.generalstore:id/btnProceed").click();

	}

	@AfterTest
	public void ConClose() throws InterruptedException {
		Thread.sleep(1000);
		driver.quit();
	}

}
