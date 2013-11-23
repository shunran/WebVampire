package integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
/**
 * FN10
 */
public class InstructionIT extends SeleniumBase {

  @Before
  public void setUp() throws Exception {
	  super.setUp();
  }

  @Test
  public void testInstruction() throws Exception {
		driver.findElement(By.name("action")).sendKeys("yes");
		driver.findElement(By.xpath("//form")).submit();
		driver.getPageSource().contains("The computer's vocabulary is good, but limited.");
  }

  @After
  public void tearDown() throws Exception {
	  super.tearDown();
  }
}
