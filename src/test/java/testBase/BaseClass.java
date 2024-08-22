package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public Logger logger;
	public static WebDriver driver;
	public Properties p;
	
	@BeforeClass(groups = {"sanity","regression","master","datadriven"})
	@Parameters({"os","browser"})
	public void setUp(String os,String br ) throws IOException {
		logger=LogManager.getLogger(this.getClass());
		switch(br) {
		case "chrome" :driver=new ChromeDriver();
		break;
		case "edge":driver=new EdgeDriver();
		break;
		case "firefox":driver=new FirefoxDriver();
		break;
		default:System.out.println("invalid browser");
		return;
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		FileReader file=new FileReader(".//src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		driver.get(p.getProperty("app_url"));
	}
	@AfterClass(groups = {"sanity","regression","master","datadriven"})
	public void close() {
		driver.quit();
	}
	public String randomString() {
		String name=RandomStringUtils.randomAlphabetic(6);
		return name;
	}
	public String randomNumber() {
		String num=RandomStringUtils.randomNumeric(10);
		return num;
	}
	public String ramdomAlphaNumeric() {
		String s1=RandomStringUtils.randomAlphabetic(4);
		String s2=RandomStringUtils.randomNumeric(4);
		return(s1+"@"+s2);
	}
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\screenShots\\" + tname + "_" + timeStamp + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;

	}
	}

