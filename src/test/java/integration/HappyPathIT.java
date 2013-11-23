package integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
// FN4
public class HappyPathIT extends SeleniumBase {


  @Before
  public void setUp() throws Exception {
	  super.setUp();
  }

  @Test
	public void testHappyPath() throws Exception {
		testVampStart();
		testVampMid();
		testVampEnd();
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
	  super.tearDown();
  }
}
