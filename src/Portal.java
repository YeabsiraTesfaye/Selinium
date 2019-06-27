import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;

public class Portal {
    static Writer writer = null;
    static WebDriver driver = null;
    public static void main(String[]args){

        try {
            //System.setProperty("webdriver.chrome.driver","C:\\Users\\YeabsiraTesfaye\\Downloads\\chromedriver.exe");
            //Your Driver Path
            driver = new ChromeDriver();
            signin();
            printOut();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static void signin() throws InterruptedException {

        driver.get("https://portal.aait.edu.et");

        driver.findElement(By.name("UserName")).sendKeys("Enter Id Here");
        driver.findElement(By.name("Password")).sendKeys("Enter Password Here");

        driver.findElement(By.className("btn-success")).click();
        driver.navigate().to("https://portal.aait.edu.et/Grade/GradeReport");


    }
    static void printOut(){
        String grades = driver.findElement(By.xpath("//*[@class='table-bordered table-striped table-hover']")).getText();

        try{
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Grade.txt"), "utf-8"));
            writer.write(grades);
        }catch (IOException e){

        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
