package GeneralStoreTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class StoreTC03 extends BaseClass {
	
	static AndroidDriver<MobileElement> driver;
	SoftAssert softassert = new SoftAssert();

	@Test(priority = 1)
	public void store() throws MalformedURLException, InterruptedException {  // validating the products prices & total on checkout page...

		test = extent.createTest("Adding Product in Cart", "Adding Product in Cart Test Case ");

		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

		test.info("Application started.");

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("Hello");

		driver.hideKeyboard();

		// driver.findElement(By.xpath("//*[@text='Female']")).click();
		driver.findElementById("com.androidsample.generalstore:id/radioFemale").click();

		driver.findElementById("android:id/text1").click();

		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));");

		driver.findElement(By.xpath("//*[@text='Argentina']")).click();

		driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
		
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click(); // after this the text changes to added to cart & array size decreases from 1 to 0... 
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		
		driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click();
		
		Thread.sleep(4000);
		
		String amount1 = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(0).getText();
		amount1=amount1.substring(1);  //S120  --> 120
		double  amount1value =Double.parseDouble(amount1); 
		System.out.println("product price1: " + amount1value);
		test.info("product price1: " + amount1value);
		
		String amount2 = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(1).getText();
		amount2=amount2.substring(1);
		double  amount2value =Double.parseDouble(amount2);
		System.out.println("product price2: " + amount2value);
		test.info("product price2: " + amount2value);
	
		
		double sumofproduct = amount1value + amount2value;
		System.out.println("product price2: " + sumofproduct);
		
		test.info("sum of product is :" + sumofproduct);
		
		String total = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		total=total.substring(1);
		double totalvalue=Double.parseDouble(total);
		System.out.println("Total product sum :" + totalvalue);
		test.info("Total of  product is :" + totalvalue);
		Assert.assertEquals(sumofproduct, totalvalue);
		
		
	}
	

}
