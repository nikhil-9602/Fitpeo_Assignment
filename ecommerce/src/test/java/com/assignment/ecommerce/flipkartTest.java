package com.assignment.ecommerce;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class flipkartTest {

	    @Test
        public void test() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\nsaha\\OneDrive\\Desktop\\Nikhil\\AFourTech\\chromedriver-win64\\chromedriver.exe");
		
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		//open flipkart and verify home page
		driver.get("https://www.flipkart.com/");
		Assert.assertTrue(driver.findElement(By.xpath("//img[@title='Flipkart']")).isDisplayed());
		
		//search laptop in search bar and validate if items are showing for laptop
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("laptop"+Keys.ENTER);
		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Showing ')]")).isDisplayed(),"Failed to display items for laptop");
	
		//click on the product and verify details are shown
		driver.findElement(By.xpath("//div[@class='_4rR01T']")).click();
		
		
		Set<String> window= driver.getWindowHandles();
		Iterator<String> it= window.iterator();
		String firstTab=it.next();
		String secondTab=it.next();
		driver.switchTo().window(secondTab);
		
		WebElement ele= driver.findElement(By.xpath("//div[text()='Specifications']"));
		String laptopName=driver.findElement(By.xpath("//h1[@class='yhB1nd']//span")).getText();
		
		JavascriptExecutor js= (JavascriptExecutor)driver;
		Thread.sleep(3000);
		js.executeScript("arguments[0].scrollIntoView();", ele);
		Assert.assertTrue(ele.isDisplayed(), "Failed to display product details");

		//add selected item to shopping cart
		driver.findElement(By.xpath("//button[text()='Add to cart']")).click();
		
		
		//Goto Shopping cart
		driver.findElement(By.xpath("//span[text()='Cart']")).click();
		
		//verify correct item is added in cart
		driver.findElement(By.xpath("//div[@class='_2-uG6-']//a")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='yhB1nd']//span")).getText(), laptopName);
		driver.navigate().back();
		
		Thread.sleep(6000);
		//click on place order
		driver.findElement(By.xpath("//span[text()='Place Order']")).click();
		
		//enter credentials to login
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("n.sahare96@gmail.com");
		
		driver.findElement(By.xpath("//span[text()='CONTINUE']")).click();
		
		driver.quit();
		
		//Imp Note: Not able to Automate further because login is not possible
		//in Automated browser.
		//It is showing message as Something went wrong
		
	}

}
