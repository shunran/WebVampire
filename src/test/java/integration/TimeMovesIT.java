package integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class TimeMovesIT extends SeleniumBase {


  @Before
  public void setUp() throws Exception {
	  super.setUp();
  }

  @Test
  public void testTimeMoves() throws Exception {
	driver.findElement(By.name("action")).sendKeys("n");
	driver.findElement(By.xpath("//form")).submit();
	driver.findElement(By.name("action")).sendKeys("get timepiece");
	driver.findElement(By.xpath("//form")).submit();
	driver.findElement(By.name("action")).sendKeys("look timepiece");
	driver.findElement(By.xpath("//form")).submit();
	String timeValue1 = driver.findElement(By.xpath("//body")).getText();
	timeValue1 = timeValue1.replaceFirst("(?s)The time is (\\d{2}:\\d{2}).*$", "$1");
	driver.findElement(By.name("action")).sendKeys("look timepiece");
	driver.findElement(By.xpath("//form")).submit();
	String timeValue2 = driver.findElement(By.xpath("//body")).getText();
	timeValue2 = timeValue2.replaceFirst("(?s)The time is (\\d{2}:\\d{2}).*$", "$1");
	System.out.println(timeValue1);
	System.out.println(timeValue2);
	assertEquals(timeValue1.length(),5);
	assertEquals(timeValue1.length(),5);
	assertFalse(timeValue1.equals(timeValue2));
  }

  @After
  public void tearDown() throws Exception {
	  super.tearDown();
  }
}
