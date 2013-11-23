package integration;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
/**
 * FN9
 */
public class TimeOverIT extends SeleniumBase {
	
  final int noMoreTurns = 250;


  @Before
  public void setUp() throws Exception {
	  super.setUp();
  }

  @Test
  public void testTimeOver() throws Exception {
	driver.findElement(By.name("action")).sendKeys("n");
	driver.findElement(By.xpath("//form")).submit();
	driver.findElement(By.name("action")).sendKeys("get timepiece");
	driver.findElement(By.xpath("//form")).submit();;
	/*
	 * It's midnight: the Vampire is awake, He's at your neck
	 */
	spendTime();
  }
  
  private void spendTime() throws InterruptedException {
	for (int i = 0; i < noMoreTurns; i++) {
		
		driver.findElement(By.name("action")).sendKeys("look timepiece");
		driver.findElement(By.xpath("//form")).submit();
		String timeValue = driver.findElement(By.xpath("//body")).getText();
		timeValue = timeValue.replaceFirst("(?s)The time is (\\d{2}:\\d{2}).*$", "$1");
		if (timeValue.length() != 5) {
			assertTrue(timeValue.matches("(?s).*It's midnight.*"));
			return;
		}
	}
	fail("Midnight takes too much time.");
  }

  @After
  public void tearDown() throws Exception {
	  super.tearDown();
  }
}
