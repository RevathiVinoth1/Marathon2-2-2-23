package marathon2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.sukgu.Shadow;

public class ServiceNow {

	public static void main(String[] args) throws IOException {
		ChromeOptions option1=new ChromeOptions();
		option1.addArguments("--disable-notifications");
		ChromeDriver driver=new ChromeDriver(option1);
		driver.get("https://dev62925.service-now.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		driver.findElement(By.xpath("//input[@id='user_name']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys("GAhMak34tH-^");
		
		driver.findElement(By.xpath("//button[text()='Log in']")).click();
		
		Shadow shdom=new Shadow(driver);
		shdom.setImplicitWait(30);
		shdom.findElementByXPath("//div[text()='All']").click();
		shdom.findElementByXPath("//span[text()='Service Catalog']").click();
		
		WebElement shframe = shdom.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(shframe);
		driver.findElement(By.xpath("//h2[contains(text(),'Mobiles')]")).click();
		driver.findElement(By.xpath("//strong[text()='Apple iPhone 13']")).click();
		driver.findElement(By.xpath("(//label[@class='radio-label'])[2]")).click();
		WebElement option = driver.findElement(By.xpath("//select[@class='form-control cat_item_option ']"));
		Select opt=new Select(option);
		opt.selectByValue("Unlimited");
		driver.findElement(By.xpath("(//label[@class='radio-label'])[5]")).click();
		driver.findElement(By.xpath("(//label[@class='radio-label'])[10]")).click();
		driver.findElement(By.xpath("//button[@id='oi_order_now_button']")).click();
		String request = driver.findElement(By.xpath("//div[@class='notification notification-success']")).getText();
		System.out.println(request);
		String reqnum = driver.findElement(By.xpath("//a[@id='requesturl']")).getText();
		System.out.println(reqnum);
		File source=driver.getScreenshotAs(OutputType.FILE);
		File dest=new File("./servicenow/webpage.png");
		FileUtils.copyFile(source, dest);
		//driver.close();
		
	}

}


/*
1. Launch ServiceNow application
2. Login with valid credentials
3. Click-All Enter Service catalog in filter navigator and press enter or Click the ServiceCatalog
4. Click on  mobiles
5. Select Apple iPhone 13
6. Click as No in Is this a replacement for a lost or broken iPhone?
7. Select Unlimited in  Monthly data allowance
8. Choose color field as Blue and storage field as 256 GB
9. Click  Order now Button
10. Verify order is placed and get the request number"
11. Take a Snapshot
12. Close the browser
*/