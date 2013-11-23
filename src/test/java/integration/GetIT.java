package integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
//FN1
public class GetIT extends SeleniumBase {


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
	driver.findElement(By.name("action")).sendKeys("take wine");
	driver.findElement(By.xpath("//form")).submit();
	driver.findElement(By.name("action")).sendKeys("inventory");
	driver.findElement(By.xpath("//form")).submit();
	driver.getPageSource().contains("Wine");
  }

  @After
  public void tearDown() throws Exception {
	  super.tearDown();
  }
}
