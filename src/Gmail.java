import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Gmail {


    static Writer writer = null;
    static WebDriver driver = null;
    public static void main(String[]args){

        try {
            System.setProperty("webdriver.chrome.driver","C:\\Users\\YeabsiraTesfaye\\Downloads\\chromedriver.exe");
            driver = new ChromeDriver();
            signin();
            checkUnreadAndPrint();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static void signin() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, 5);

        driver.get("https://mail.google.com");

        driver.findElement(By.id("identifierId")).sendKeys("EMAIL");

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/span/span")).click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input"));

        wait.until(ExpectedConditions.elementToBeClickable(password));

        password.sendKeys("PWD");

        driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/span/span")).click();


    }
    static void checkUnreadAndPrint(){
        List<WebElement> unreadMails = driver.findElements(By.className("zF"));

        int count =  unreadMails.size();
        try {

            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Messages.txt"), "utf-8"));
            writer.write("Unread Messages: "+count);
            for(WebElement we:unreadMails){
                if(!we.isDisplayed()){
                    WebElement header = we.findElement(By.xpath("//span[@class='bqe']"));
                    WebElement body = we.findElement(By.xpath("//span[@class='y2']"));

                    writer.write("From: "+we.getText().toString()+"\n");
                    writer.write("Header: "+header.getText().toString()+"\n");
                    writer.write("Body: "+body.getText().toString()+"\n");
                    writer.write("\n\n");
                }


            }

        } catch (IOException ex) {

        } finally {
            try {writer.close();} catch (Exception ex) {}
        }
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        driver.close();
    }

}
