package integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
// FN5
public class MoveRoomIT extends SeleniumBase {


  @Before
  public void setUp() throws Exception {
	  super.setUp();
  }

  @Test
  public void testGetDrop() throws Exception {
	driver.findElement(By.name("action")).sendKeys("n");
	driver.findElement(By.xpath("//form")).submit();
	driver.getPageSource().contains("You are in the Entrance Hall");
	driver.findElement(By.name("action")).sendKeys("e");
	driver.findElement(By.xpath("//form")).submit();
	driver.getPageSource().contains("You are in the Library");

  }

  @After
  public void tearDown() throws Exception {
	  super.tearDown();
  }
}
