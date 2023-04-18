package Testcases;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class WebTesting {
    public static WebDriver driver;

    @BeforeClass
    public void setDriver() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\chris\\Documents\\Java_testing\\chromedriver.exe");
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(opt);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://centz.herokuapp.com/");
        Thread.sleep(2000);

        WebElement html = driver.findElement(By.tagName("html"));
        html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
    }

    @Test(priority = 3)
    public void LogoClickGoesHome() {
        driver.findElement(By.xpath("//*[@id=\"root\"]/nav/a/img")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://centz.herokuapp.com/");
    }
    @Test(priority = 1)
    public void AboutClickGoesToAbout() {
        driver.findElement(By.xpath("//*[@id=\"root\"]/nav/ul/li/a")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://centz.herokuapp.com/about");
    }

    @Test(priority = 2)
    public void ChristianLILink() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"root\"]/nav/ul/li/a")).click();
        JavascriptExecutor je = (JavascriptExecutor) driver;
        Thread.sleep(1000);
        je.executeScript("window.scrollBy(0,750)", "");
        Thread.sleep(1000);
        String temp = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[3]/div[1]/a")).getAttribute("href");
        Assert.assertEquals(temp, "https://www.linkedin.com/in/christianapostoli/");
    }

    @Test(priority = 6)
    public void JakeLILink() throws InterruptedException {

        driver.findElement(By.xpath("//*[@id=\"root\"]/nav/ul/li/a")).click();
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("window.scrollBy(0, 1050)", "");
        Thread.sleep(1000);
        String temp = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[3]/div[2]/a")).getAttribute("href");
        System.out.println(temp);
        Assert.assertEquals(temp, "https://www.linkedin.com/in/jakewhans/");
    }

    @Test(priority = 9)
    public void AustinLILink() throws InterruptedException {
        // scenario 2
        driver.findElement(By.xpath("//*[@id=\"root\"]/nav/ul/li/a")).click();
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("window.scrollBy(0,1250)", "");
        Thread.sleep(1000);
        String temp = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[3]/div[3]/a")).getAttribute("href");
        Assert.assertEquals(temp, "https://www.linkedin.com/in/austin-hood7/");
    }

    @Test(priority = 4)
    public void CoinRankingLink() throws InterruptedException {
        Thread.sleep(5000);
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("window.scrollBy(0,10000)", "");
        Thread.sleep(1000);

        driver.findElement(By.linkText("Coinranking")).click();
        ArrayList<String> handles = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(handles.get(2));
        Assert.assertEquals(driver.getCurrentUrl(), "https://coinranking.com/");
    }

    @Test(priority = 5)
    public void SearchCoinsOne() throws InterruptedException {
        // scenario 2
        driver.findElement(By.xpath("//*[@id=\"root\"]/nav/div/div/input")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"root\"]/nav/div/div/input")).sendKeys("bit");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"root\"]/nav/div/div/input")).clear();
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"root\"]/nav/div/div/input")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"root\"]/nav/div/div/input")).sendKeys("shi");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"root\"]/nav/div/div/input")).clear();
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"root\"]/nav/div/div/input")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"root\"]/nav/div/div/input")).sendKeys("dog");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"root\"]/nav/div/div/input")).clear();
    }

    @Test(priority = 8)
    public void OpenCloseSidebar() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"root\"]/nav/a/img")).click();
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/nav/ul/div[1]/a")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div[1]/a")).click();
    }

    @Test(priority = 3)
    public void CardClickAndScroll() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"root\"]/nav/a/img")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/nav/ul/div[2]/div[2]/div")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/nav/ul/div[2]/div[3]/div")).click();
        Thread.sleep(2000);
    }



    @AfterMethod
    public void SetUpNextTest() throws InterruptedException {
        // wait and clear for the next test to be visible
        Thread.sleep(2000);
        JavascriptExecutor je = (JavascriptExecutor) driver;
        ArrayList<String> handles = new ArrayList<String>(driver.getWindowHandles());
        if (!driver.getCurrentUrl().contains("https://centz.herokuapp.com") || driver.getCurrentUrl().contains("linkedin")) {
            driver.close();
            Thread.sleep(1000);
            driver.switchTo().window(handles.get(1));
            je.executeScript("window.scrollBy(0,-2000)", "");
        }
        Thread.sleep(500);
    }

    @AfterClass
    public void terminate() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }
}
