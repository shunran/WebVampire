package integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
/**
 * FN11
 */
public class BurnIT extends SeleniumBase {


  @Before
  public void setUp() throws Exception {
	  super.setUp();
  }

  @Test
  public void testGetDrop() throws Exception {
	driver.findElement(By.name("action")).sendKeys("n");
	driver.findElement(By.xpath("//form")).submit();
	driver.findElement(By.name("action")).sendKeys("w");
	driver.findElement(By.xpath("//form")).submit();
	driver.findElement(By.name("action")).sendKeys("go fireplace");
	driver.findElement(By.xpath("//form")).submit();
	driver.getPageSource().contains("You have Burned to Death");
  }

  @After
  public void tearDown() throws Exception {
	  super.tearDown();
  }
}
