package CrossBrowserTesting;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.net.MalformedURLException;

public class LauchBrowsers {
	public WebDriver driver;
	public String URL, Node;
	protected ThreadLocal<RemoteWebDriver> threadDriver = null;

	@Parameters({"browser"})
	@BeforeTest
	public void launchapp(String browser) throws MalformedURLException {
		String URL = "http://www.calculator.net";

		if (browser.equalsIgnoreCase("firefox")) {
			System.out.println("Application Running on ...." + browser);
			String Node = "http://localhost:4444/wd/hub";
			DesiredCapabilities cap =new DesiredCapabilities();
			cap.setBrowserName("firefox");
			
			FirefoxOptions options = new FirefoxOptions();
			options.merge(cap);

			driver = new RemoteWebDriver(new URL(Node), options);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		} else if (browser.equalsIgnoreCase("chrome")) {
			System.out.println("Application Running on ...." + browser);
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("chrome");
			
			ChromeOptions options = new ChromeOptions();
			options.merge(cap);
			String Node = "http://localhost:4444/wd/hub";
			driver = new RemoteWebDriver(new URL(Node), options);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		} else if (browser.equalsIgnoreCase("ie")) {
			System.out.println("Application Running on ...." + browser);
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("ie");
			
			InternetExplorerOptions options = new InternetExplorerOptions();
			options.merge(cap);
			String Node = "http://localhost:4444/wd/hub";
			driver = new RemoteWebDriver(new URL(Node), options);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		} /*else {
			throw new IllegalArgumentException("The Browser Type is Undefined");
		}*/

		driver.navigate().to(URL);
		driver.manage().window().maximize();
	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
}
