package integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
/**
 * FN15
 */
public class LookItemIT extends SeleniumBase {


  @Before
  public void setUp() throws Exception {
	  super.setUp();
  }

  @Test
  public void testGetDrop() throws Exception {
	driver.findElement(By.name("action")).sendKeys("n");
	driver.findElement(By.xpath("//form")).submit();
	driver.findElement(By.name("action")).sendKeys("e");
	driver.findElement(By.xpath("//form")).submit();
	driver.findElement(By.name("action")).sendKeys("take scroll");
	driver.findElement(By.xpath("//form")).submit();
	driver.findElement(By.name("action")).sendKeys("look scroll");
	driver.findElement(By.xpath("//form")).submit();
	driver.getPageSource().contains("The Scroll reads");
  }

  @After
  public void tearDown() throws Exception {
	  super.tearDown();
  }
}
