package marathon2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xslf.model.TextBodyPropertyFetcher;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.sukgu.Shadow;

public class ArchitectCertifications {

	public static void main(String[] args) throws InterruptedException, IOException {
		ChromeOptions option1=new ChromeOptions();
		option1.addArguments("--disable-notifications");
		ChromeDriver driver=new ChromeDriver(option1);
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("hari.radhakrishnan@qeagle.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Leaf@123");
		
		driver.findElement(By.xpath("//input[@id='Login']")).click();
		
		driver.findElement(By.xpath("(//span[text()='Home'])[1]")).click();
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();
		
		String windowHandle = driver.getWindowHandle();
		Set<String> newwindow = driver.getWindowHandles();
		List<String> newhandle = new ArrayList<String>(newwindow);
			
		driver.switchTo().window(newhandle.get(1));
		System.out.println(driver.getTitle());
		
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		
		Shadow shadow=new Shadow(driver);
		shadow.findElementByXPath("//span[text()='Learning']").click();
		Thread.sleep(3000);

		Actions action=new Actions(driver);
		WebElement trailhead = shadow.findElementByXPath("//span[text()='Learning on Trailhead']");
		action.moveToElement(trailhead).perform();
		
		WebElement certificate = shadow.findElementByXPath("//a[text()='Salesforce Certification']");
		certificate.click();
		
		String title = driver.getTitle();
		System.out.println(title);
		
		driver.findElement(By.xpath("(//div[@class='roleMenu-item_text'])[2]")).click();
		WebElement text = driver.findElement(By.xpath("//div[@class='slds-container--center slds-container--medium slds-p-vertical--large']"));
		String content= text.getText();
		System.out.println(content);
		
		List<WebElement> arccertifications = driver.findElements(By.xpath("//div[@class='credentials-card_title']"));
		for(int i=0;i<arccertifications.size();i++)
		{
			System.out.println(arccertifications.get(i).getText());
		}
		driver.findElement(By.xpath("//a[text()='Application Architect']")).click();
		List<WebElement> apparccertifications = driver.findElements(By.xpath("//div[@class='credentials-card_title']"));
		for(int j=0;j<apparccertifications.size();j++)
		{
			System.out.println(apparccertifications.get(j).getText());
		}
		WebElement scshot= driver.findElement(By.xpath("(//div[@class='credentials-card '])[9]"));
		action.scrollToElement(scshot).perform();
		File source=driver.getScreenshotAs(OutputType.FILE);
		File dest=new File("./snap/certificate.png");
		FileUtils.copyFile(source, dest);
		//driver.close();
		
		
		
		
		
		
		/*1. Launch Salesforce application https://login.salesforce.com/
			2. Login with Provided Credentials
			3. Click on Learn More link in Mobile Publisher
			4. Click  On Learning
			5. And mouse hover on Learning On Trailhead
			6. Clilck on Salesforce Certifications
			7. Choose Your Role as Salesforce Architect
			8. Get the Text(Summary) of Salesforce Architect 
			9. Get the List of Salesforce Architect Certifications Available
			10. Click on Application Architect 
			11.Get the List of Certifications available
			12.Take a snapshot of Ceritificate
			13. Close
			*/
	}

}
