package cydeo;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.awt.event.InputEvent;


public class StepDefs {

    @Given("^I am on the home page$")
    public void i_am_on_the_home_page() throws Throwable {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.ikea.com.tr/urun-katalogu/calisma-alanlari/kesonlar-ve-dolaplar/30326177/lennart-keson.aspx");
        Thread.sleep(3000);

        //identify the select element
        WebElement MagazaStokDurumu=driver.
                findElement(By.linkText("Mağaza Stok Durumu"));


        //Mağaza Stok Durumu Aç
        MagazaStokDurumu.click();

        //POP-UP KAPAT
        //driver.switchTo().alert().dismiss();
        //Alert AlertObj=driver.switchTo().alert();
        //AlertObj.dismiss();

        Robot robot = new Robot();
        robot.mouseMove(400,85);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(2000);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(2000);

        //Ankara Stok Bak
        WebElement AnkaraStokDurumu=driver.findElement(By.
                xpath("//*[@id=\"divCheckStock\"]/table/tbody/tr[1]/td[2]/div/div/span"));
        System.out.println("Ankara StokDurumu = " + AnkaraStokDurumu.getText());

        //Antalya Stok Bak
        WebElement AntalyaStokDurumu=driver.findElement(By.
                xpath("//*[@id=\"divCheckStock\"]/table/tbody/tr[2]/td[2]/div/div/span"));
        System.out.println("Antalya StokDurumu = " + AntalyaStokDurumu.getText());

        //ÜRÜN ADI BUL
        WebElement LENNARTUrunAdı=driver.findElement(By.id("ctl00_ContentPlaceHolder1_hlSearchButton"));
        System.out.println("LENNARTUrunAdı.getText() = " + LENNARTUrunAdı.getText());


        //Sonucu_Mail_At();
        Email email = new SimpleEmail();

        email.setHostName("smtp.gmail.com");

        email.setSmtpPort(465);

        email.setAuthenticator(new DefaultAuthenticator
                ("elorsoft@gmail.com", "pqmrdxdxtoflwlae"));
        email.setSSLOnConnect(true);
        email.setFrom("elorsoft@gmail.com");
        email.setSubject(LENNARTUrunAdı.getText()+" - Ankara: "+AnkaraStokDurumu.getText());

        email.setMsg(LENNARTUrunAdı.getText()+" - Ankara Stok Durumu: "+AnkaraStokDurumu.getText()+
                "\n\nBu mail Ankara Stok Durumu için sizin talebiniz üzerine atildi, " +
                "Lütfen Cevaplamayiniz... " +
                "\n\nVerilerin alındığı adres: " +
                "https://www.ikea.com.tr/urun-katalogu/calisma-alanlari/kesonlar-ve-dolaplar/30326177/lennart-keson.aspx"+
                "\n\n ELOR Soft Help Team");
        email.addTo("elorsoft@gmail.com");
        //email.addTo("suleyman1998@hotmail.com");  //Süleyman Aydın
        email.addTo("omerozdil54@gmail.com");
        email.addTo("nazim@cydeo.com");
        email.addTo("fatihatasoypa@gmail.com");
        email.send();

        driver.close();

        System.out.println("the end");

        System.out.println("MAİL GİTTİ");

    }

    @When("^I search for \"([^\"]*)\"$")
    public void i_search_for(String search) throws Throwable {
        //Driver.getDriver().findElement(By.cssSelector("[id*='search-query']")).sendKeys(search + Keys.ENTER);
    }

    @Then("^I should see the results$")
    public void i_should_see_the_results() throws Throwable {
        //Thread.sleep(2000);
        //Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("search"));
    }

    @Then("^I should see more results$")
    public void i_should_see_more_results() throws Throwable {
        //Thread.sleep(2000);
        //Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("search"));
    }

    @After
    public void tearDown(Scenario scenario) {
        /*if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }*/
        //Driver.closeDriver();
    }

}
