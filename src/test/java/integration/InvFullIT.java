package integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
/**
 * FN7
 */
public class InvFullIT extends SeleniumBase {


  @Before
  public void setUp() throws Exception {
	  super.setUp();
  }

  @Test
	public void testHappyPath() throws Exception {
		testVampStart();
		driver.findElement(By.name("action")).sendKeys("w");
		driver.findElement(By.xpath("//form")).submit();
		driver.findElement(By.name("action")).clear();
		driver.findElement(By.name("action")).sendKeys("w");
		driver.findElement(By.xpath("//form")).submit();
		driver.findElement(By.name("action")).clear();
		driver.findElement(By.name("action")).sendKeys("get scroll");
		driver.findElement(By.xpath("//form")).submit();
		driver.findElement(By.name("action")).sendKeys("w");
		driver.findElement(By.xpath("//form")).submit();
		driver.findElement(By.name("action")).sendKeys("get timepiece");
		driver.findElement(By.xpath("//form")).submit();
		driver.getPageSource().contains("You can't carry any more");
	}

  @After
  public void tearDown() throws Exception {
	  super.tearDown();
  }
}
