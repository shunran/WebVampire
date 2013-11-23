package integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
/**
 * FN13
 */
public class DieDrownIT extends SeleniumBase {


  @Before
  public void setUp() throws Exception {
	  super.setUp();
  }

  @Test
  public void testGetDrop() throws Exception {
	testVampStart();
	testVampMid();
	driver.findElement(By.name("action")).sendKeys("swim lake");
	driver.findElement(By.xpath("//form")).submit();
	driver.getPageSource().contains("You have Drowned in the ice cold water");
  }

  @After
  public void tearDown() throws Exception {
	  super.tearDown();
  }
}
