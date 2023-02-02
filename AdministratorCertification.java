package marathon2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.text.DefaultEditorKit.BeepAction;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.sukgu.Shadow;

public class AdministratorCertification {

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
		if(title.contains("Certification - Administrator Overview"))
		{
			System.out.println("Current Window Title is Correct");
		}
		else
		{
			System.out.println("Current Window Title is InCorrect");
		}
	List<WebElement> cernames=driver.findElements(By.xpath("//div[@class='credentials-card_title']"));
	for(int i=0;i<cernames.size();i++)
	{
		System.out.println(cernames.get(i).getText());
	}
	WebElement screen = driver.findElement(By.xpath("//h1[text()='What is a Salesforce Administrator?']"));
	action.scrollToElement(screen).perform();
	File source=driver.getScreenshotAs(OutputType.FILE);
	File dest=new File("./snap/architectcertificates.png");
	FileUtils.copyFile(source, dest);
	//driver.close();
	

}}