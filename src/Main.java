
import java.awt.AWTException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Main extends Parameters {

	@BeforeTest
	public void beforeTest() {
		driver.get(URLS[1]);
		driver.manage().window().maximize();
		
	}
	
	
	@Test(priority = 1)
	public void defaulLanguageEnglish() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebElement test = driver.findElement(By.linkText("العربية"));
		String actual ="العربية";
		String expected = test.getText();
		
		myAssert.assertEquals(actual, expected);
		myAssert.assertAll();
	}
		
	@Test(priority = 2)
	public void defaultCurrencyIsCorrect()  {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebElement sar = driver.findElement(By.xpath("//header/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/button[1]"));
		String actualResult = sar.getText();
		String expectedResult = "SAR";

		myAssert.assertEquals(actualResult, expectedResult);
	}
	
	@Test(priority = 3)
	public void checkWhatsAppNum() throws InterruptedException {
       WebElement urlActual = driver.findElement(By.linkText("+966 55 440 0000"));
       String actual = urlActual.getText().replaceAll("\\s","");
       actual =  actual.substring(1, actual.length());
       urlActual.click();
       Thread.sleep(2000);
       
       String url= driver.getCurrentUrl();
       boolean urlExpect = url.contains(actual);
       myAssert.assertEquals(urlExpect, true);
      
  
	}
	
	@Test(priority = 4)
	public void checkQitafLogo() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebElement qitafDiv =	driver.findElement(By.xpath("/html/body/div[2]/footer/div[3]/div[3]/div[1]/div[2]/div/div[2]"));
		
		List <WebElement> qitaf = qitafDiv.findElements(By.tagName("svg"));
		
		
	for(int i =0 ; i< qitaf.size() ; i++) {
	
		WebElement getQitaf = qitaf.get(0);
		getQitaf.isDisplayed();
		System.out.println(getQitaf.isDisplayed());

	}
  
}	
	
	@Test(priority = 5)
	public void checkHotelTab() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebElement hotelTab =driver.findElement(By.xpath("/html/body/div[2]/section[2]/div[4]/div/div/nav/a[2]"));
		String actual = hotelTab.getAttribute("aria-selected");
		String expected = "false";
		myAssert.assertEquals(actual, expected);
		myAssert.assertAll();
  
}	
	
	@Test(priority = 5)
	public void checkFromDate() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		LocalDate currentDate = LocalDate.now();
		// Add one day to the current date
		LocalDate updatedDate = currentDate.plusDays(1);
		int expectedDateFrom = updatedDate.getDayOfMonth();
		System.out.println(expectedDateFrom);
		
		WebElement actualtDate = driver.findElement(By.xpath("/html/body/div[2]/section[2]/div[4]/div/div/div/div[1]/div/div[2]/div[1]/div/div[3]/div/div/div/div[1]"));
		List <WebElement> findDate = actualtDate.findElements(By.tagName("span"));
		
			String date = findDate.get(1).getText();
			int dateActual = Integer.parseInt(date);
			System.out.println(dateActual);
			
			myAssert.assertEquals(dateActual, expectedDateFrom);
			

}
	
	@Test(priority = 6)
	public void checkToDate() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		LocalDate currentDate = LocalDate.now();
		// Add two days to the current date
		LocalDate updatedDate = currentDate.plusDays(2);

		int expectedDateFrom = updatedDate.getDayOfMonth();
		System.out.println(expectedDateFrom);
		
		
		WebElement actualtDate = driver.findElement(By.xpath("/html/body/div[2]/section[2]/div[4]/div/div/div/div[1]/div/div[2]/div[1]/div/div[3]/div/div/div/div[2]"));
		List <WebElement> findDate = actualtDate.findElements(By.tagName("span"));
		
			String date = findDate.get(1).getText();
			int dateActual = Integer.parseInt(date);
			System.out.println(dateActual);
			
			myAssert.assertEquals(dateActual, expectedDateFrom);
			

}
	
	@Test(priority = 7 , invocationCount = 4)
	public void updateLanguage() throws InterruptedException {
		int rand = URLS.length;
		int random= myRandom.nextInt(rand);
		driver.get(URLS[random]);
	
	}
	

	
	@Test(priority = 8)
	public void hotelSearchTab() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String url = driver.getCurrentUrl();
		
		if(url.equals(URLS[1])) {
			System.out.println("dddddd");
			int rand = citiesInEnglish.length;
			int random= myRandom.nextInt(rand);
			WebElement searchTab = driver.findElement(By.xpath("/html/body/div[2]/section[2]/div[4]/div/div/nav/a[2]"));
			searchTab.click();
			
			WebElement search = driver.findElement(By.xpath("/html/body/div[2]/section[2]/div[4]/div/div/div/div[2]/div/div/div/div[1]/div/div/div/div/input"));
			search.sendKeys(citiesInEnglish[random]+ Keys.ENTER);

		}else {
			
			int rand = citiesInArabic.length;
			int random= myRandom.nextInt(rand);
			WebElement searchTab = driver.findElement(By.xpath("/html/body/div[2]/section[2]/div[4]/div/div/nav/a[2]"));
			searchTab.click();
			WebElement search = driver.findElement(By.xpath("/html/body/div[2]/section[2]/div[4]/div/div/div/div[2]/div/div/div/div[1]/div/div/div/div/input"));
			search.sendKeys(citiesInArabic[random]+ Keys.ENTER);
			
			
		}
		
		
	}
	@Test(priority = 9)
	public void selectRoomRandomly() throws InterruptedException, AWTException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		WebElement roomSelect = driver.findElement(By.tagName("select"));
		Select mySelect = new Select(roomSelect);
		int random= myRandom.nextInt(2);
		mySelect.selectByIndex(random);
		Thread.sleep(5000);
		
		WebElement clickSearch = driver.findElement(By.className("btn-block"));
		clickSearch.click();
	
	}
	
	@Test(priority = 10)
	public void sortPrice() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	

		
		WebElement  sortButton = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div[2]/section[1]/div/button[2]"));
		sortButton.click();
		
		List <WebElement> price = driver.findElements(By.className("Price__Value"));

		String actual = price.get(2).getText();
		String  expected = price.get(0).getText();

		myAssert.assertEquals(actual, expected);
		System.out.println(actual);
		System.out.println(expected);

	
	}
	
	@AfterTest
	public void afterTest() {
	 	myAssert.assertAll();
//		driver.quit();
		
		
	}
	
	
	
}
