package Day_10;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AlphaOrderHW8WDFormat {
	WebDriver driver;
	String baseUrl = "http://hrm.tehportal.net/";
	
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
	}

	@After
	public void tearDown() throws Exception {
		driver.close();
	}

	@Test
	public void test() throws InterruptedException {
		driver.get(baseUrl);
		assertEquals("OrangeHRM - New Level of HR Management",
				driver.getTitle());
		
		// OR
		
		String pageTitle = driver.getTitle();
		assertEquals("OrangeHRM - New Level of HR Management",
				pageTitle);
		
		WebElement username_field = driver.findElement(By.name("txtUserName"));
		username_field.clear();
		username_field.sendKeys("admin");
		
		WebElement password_field = driver.findElement(By.name("txtPassword"));
		password_field.clear();
		password_field.sendKeys("password");
		
		driver.findElement(By.name("Submit")).click();
		
		assertEquals("OrangeHRM", driver.getTitle());
		
		driver.switchTo().frame("rightMenu");
		
		driver.findElement(By.linkText("Employee Name")).click();
		
//		List<WebElement> all_rows_list = driver.findElements(By.xpath("//tbody/tr"));
		List<WebElement> all_rows_list = driver.findElements(By.xpath("//tbody/tr/td[3]/a"));
		
		int total_rows = all_rows_list.size();
		
//		for (int i = 1; i < total_rows; i++) {
		for (int i = 0; i < total_rows-1; i++) {
			
//			String top_name = driver.findElement(
//					By.xpath("//tbody/tr[" + i + "]/td[3]/a")).getText();
			String top_name = all_rows_list.get(i).getText();
			
			
//			String bottom_name = driver.findElement(
//					By.xpath("//tbody/tr[" + (i+1) + "]/td[3]/a")).getText();
			String bottom_name = all_rows_list.get(i+1).getText();

			
			assertTrue("The names are not in the expected alpha order.", top_name.compareToIgnoreCase(bottom_name) <= 0);			
		}
		
		driver.switchTo().defaultContent();
		driver.findElement(By.linkText("Logout")).click();
		
		Thread.sleep(1000);
		assertEquals("OrangeHRM - New Level of HR Management",
				driver.getTitle());
		
		
	}

}
