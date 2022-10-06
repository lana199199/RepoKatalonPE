import java.util.concurrent.TimeUnit

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.ScriptTimeoutException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword


public class ImprovedKeywords {
	@Keyword
	def clickUsingJS(TestObject to) {
		WebDriver webDriver = DriverFactory.getWebDriver();
		try {
			WebElement webElement = WebUIAbstractKeyword.findWebElement(to)

			webDriver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS)
			JavascriptExecutor executor = (JavascriptExecutor) webDriver;
			Thread thread = new Thread(){
						public void run(){
							executor.executeScript("arguments[0].click();", webElement)
						}
					}
			thread.start();
		} catch (ScriptTimeoutException e) {
		} finally {
			webDriver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS)
		}
	}
}
