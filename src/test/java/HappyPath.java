import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HappyPath {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/vampire/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.get(baseUrl);
  }

  @Test
  public void testHappyPath() throws Exception {
	  testVampStart();
	  testVampMid();
	  testVampEnd();
	  
  }
  private void testVampStart() throws Exception {
    driver.findElement(By.name("action")).clear();
    driver.findElement(By.name("action")).sendKeys("n");
    driver.findElement(By.xpath("//form")).submit();
    driver.findElement(By.name("action")).clear();
    driver.findElement(By.name("action")).sendKeys("e");
    driver.findElement(By.xpath("//form")).submit();
    driver.findElement(By.name("action")).clear();
    driver.findElement(By.name("action")).sendKeys("e");
    driver.findElement(By.xpath("//form")).submit();
    driver.findElement(By.name("action")).clear();
    driver.findElement(By.name("action")).sendKeys("get axe");
    driver.findElement(By.xpath("//form")).submit();
    driver.findElement(By.name("action")).clear();
    driver.findElement(By.name("action")).sendKeys("w");
    driver.findElement(By.xpath("//form")).submit();
    driver.findElement(By.name("action")).clear();
    driver.findElement(By.name("action")).sendKeys("push bookcase");
    driver.findElement(By.xpath("//form")).submit();
    driver.findElement(By.name("action")).clear();
    driver.findElement(By.name("action")).sendKeys("d");
    driver.findElement(By.xpath("//form")).submit();
    driver.findElement(By.name("action")).clear();
    driver.findElement(By.name("action")).sendKeys("get rope");
    driver.findElement(By.xpath("//form")).submit();
    driver.findElement(By.name("action")).clear();
    driver.findElement(By.name("action")).sendKeys("n");
    driver.findElement(By.xpath("//form")).submit();
    driver.findElement(By.name("action")).clear();
    driver.findElement(By.name("action")).sendKeys("get oil");
    driver.findElement(By.xpath("//form")).submit();
    driver.findElement(By.name("action")).clear();
    driver.findElement(By.name("action")).sendKeys("n");
    driver.findElement(By.xpath("//form")).submit();
    driver.findElement(By.name("action")).clear();
    driver.findElement(By.name("action")).sendKeys("get bucket");
    driver.findElement(By.xpath("//form")).submit();
    driver.findElement(By.name("action")).clear();
    driver.findElement(By.name("action")).sendKeys("get crate");
    driver.findElement(By.xpath("//form")).submit();
    driver.findElement(By.name("action")).clear();
    driver.findElement(By.name("action")).sendKeys("u");
    driver.findElement(By.xpath("//form")).submit();
    driver.findElement(By.name("action")).clear();
    driver.findElement(By.name("action")).sendKeys("e");
    driver.findElement(By.xpath("//form")).submit();
    driver.findElement(By.name("action")).clear();
    driver.findElement(By.name("action")).sendKeys("e");
    driver.findElement(By.xpath("//form")).submit();
    driver.findElement(By.name("action")).clear();
    driver.findElement(By.name("action")).sendKeys("e");
    driver.findElement(By.xpath("//form")).submit();
    driver.findElement(By.name("action")).clear();
    driver.findElement(By.name("action")).sendKeys("e");
    driver.findElement(By.xpath("//form")).submit();
    driver.findElement(By.name("action")).clear();
    driver.findElement(By.name("action")).sendKeys("get hammer");
    driver.findElement(By.xpath("//form")).submit();
    driver.findElement(By.name("action")).clear();
    driver.findElement(By.name("action")).sendKeys("tie rope");
    driver.findElement(By.xpath("//form")).submit();
    driver.findElement(By.name("action")).clear();
    driver.findElement(By.name("action")).sendKeys("parapets");
    driver.findElement(By.xpath("//form")).submit();
  }
  
  private void testVampMid() throws Exception {
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("climb rope");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("get oar");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("s");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("get holywater");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("bucket");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("u");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("w");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("w");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("w");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("drop holywater");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("hit fireplace");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("hammer");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("drop bucket");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("go fireplace");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("get torch");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("N");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("W");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("l rat");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("get key");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("E");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("N");
	    driver.findElement(By.xpath("//form")).submit();
	  }
  
  private void testVampEnd() throws Exception {
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("go boat");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("row");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("drop crate");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("go overhang");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("get nails");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("d");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("hit crate");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("axe");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("drop axe");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("get stakes");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("drop oar");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("drop hammer");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("get tapestry");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("n");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("open doo");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("n");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("oil door");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("open door");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("n");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("open coffin");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("kill vampire");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.findElement(By.name("action")).clear();
	    driver.findElement(By.name("action")).sendKeys("stakes");
	    driver.findElement(By.xpath("//form")).submit();
	    driver.getPageSource().contains("Congratulations!");
	  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
