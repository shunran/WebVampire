package integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class TimeMoves extends SeleniumBase {


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
	/*<BODY>
<br />The time is 08:29<br /><br />What do you want to do? <form action="" method="get">
	<input name="action" type="text">
</form>
</BODY>*/
  }

  @After
  public void tearDown() throws Exception {
	  super.tearDown();
  }
}
