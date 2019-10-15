// Unit and Integration Test Cases for HR Tool Interne

package Automated_JUnit_Tests;

import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Gopinath on 7/20/2017.
 */
public class Unit_Integration_Tests extends JUnitHTMLReporter{

    @Test
    public void startHRPortal_Test0(){
        // TEst the Title of HR Portal after launchcing HR Portal
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        // Create driver object after running the driver executable
        WebDriver driver_2 = new ChromeDriver();
        // driver_2.navigate().to("http://seleniumsimplified.com");
        driver_2.navigate().to("http://localhost:9000/");

        String URL = driver_2.getCurrentUrl();
        Assert.assertEquals(URL, "http://localhost:9000/" );



        driver_2.close();
        driver_2.quit();

    }

    @Test
    public void VerifyURLHRPortal_Test1(){   //Check my actual url is matching with the expected url
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        // Create driver object after running the driver executable
        WebDriver driver_2 = new ChromeDriver();

        // driver_2.get("https://www.gmail.com");
        driver_2.navigate().to("http://localhost:9000/");
        String URL = driver_2.getCurrentUrl();
        Assert.assertEquals(URL, "http://localhost:9000/" );
        driver_2.close();
        driver_2.quit();
    }

    @Test
    public void VerifyEmailfield_Test2() {


        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Maximize Window
        driver.manage().window().maximize();

        // Wait For Page To Load
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        //Navigate to HR Portal websites
        driver.get("http://localhost:9000/");

        WebElement Emailfield = driver.findElement(By.xpath("//*[@id='email']"));
        /*Check whether Email field exists or not */
        Assert.assertEquals(true, Emailfield.isDisplayed());


        driver.close();
        driver.quit();
    }

    @Test
    public void VerifyPasswordfield_Test3() {

        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Maximize Window
        driver.manage().window().maximize();

        // Wait For Page To Load
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        //Navigate to HR Portal websites
        driver.get("http://localhost:9000/");

        WebElement Passwordfield = driver.findElement(By.xpath("//*[@id='password']"));
        /*Check whether Password field exists or not */
        Assert.assertEquals(true, Passwordfield.isDisplayed());


        driver.close();
        driver.quit();
    }

    @Test
    public void VerifyLoginButton_Test4() {
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Maximize Window
        driver.manage().window().maximize();

        // Wait For Page To Load
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        //Navigate to HR Portal websites
        driver.get("http://localhost:9000/");

        WebElement Loginbutton = driver.findElement(By.xpath("//*[@id='login-wrapper']/input"));
        /*Check whether Login field exists or not */
        Assert.assertEquals(true, Loginbutton.isDisplayed());


        driver.close();
        driver.quit();

    }

    @Test
    public void VerifyForgotPasswordfield_Test5() {
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Maximize Window
        driver.manage().window().maximize();

        // Wait For Page To Load
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        //Navigate to HR Portal websites
        driver.get("http://localhost:9000/");

        WebElement Forgotpasswordbutton = driver.findElement(By.xpath("//*[@id='login-wrapper']/a[1]"));
        /*Check whether ForgotPassword field exists or not */
        Assert.assertEquals(true, Forgotpasswordbutton.isDisplayed());


        driver.close();
        driver.quit();

    }

    @Test
    public void veerifySignupbutton_Test6() {
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Maximize Window
        driver.manage().window().maximize();

        // Wait For Page To Load
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        //Navigate to HR Portal websites
        driver.get("http://localhost:9000/");

        WebElement Signupbutton = driver.findElement(By.xpath("//*[@id='login-wrapper']/a[2]"));
        /*Check whether Signup field exists or not */
        Assert.assertEquals(true, Signupbutton.isDisplayed());


        driver.close();
        driver.quit();

    }

    @Test
    public void VerifyHirepeoplebutton_Test7() {
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Maximize Window
        driver.manage().window().maximize();

        // Wait For Page To Load
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        //Navigate to HR Portal websites
        driver.get("http://localhost:9000/");

        WebElement Hirebutton = driver.findElement(By.xpath("//*[@id='login-wrapper']/a[3]"));
        /*Check whether Hirepeople field exists or not */
        Assert.assertEquals(true, Hirebutton.isDisplayed());


        driver.close();
        driver.quit();
    }

    @Test
    public void VerifyEmailentry_Test8() {
        // Verify Email Entry

        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Maximize Window
        driver.manage().window().maximize();

        // Wait For Page To Load
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        //Navigate to HR Portal websites
        driver.get("http://localhost:9000/");
        /*CASE- 1. Both User name and Password are entered correctly.
        Check whether Email field exists or not */
        try
        {
            WebElement a1 = driver.findElement(By.xpath("//*[@id='email']"));
            System.out.println("+++++++++Emaild exists -+++++++++-\n-+++++++++-");
            Thread.sleep(3000);
            a1.sendKeys("gopinathreddy2008@gmail.com" ); // "ENTER CORRECT  MAIL ID";
            Thread.sleep(2000);

        }
        catch(Throwable e)
        {
            System.out.println("Emaild Entry not found: " + e.getMessage());
        }

        driver.close();
        driver.quit();
    }

    @Test
    public void VerifyPasswordentry_Test9() {
        // Verify Email Entry

        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Maximize Window
        driver.manage().window().maximize();

        // Wait For Page To Load
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        //Navigate to HR Portal websites
        driver.get("http://localhost:9000/");
        /*CASE- 1. Both User name and Password are entered correctly.
        Check whether Password field exists or not */

        try
        {

            WebElement password = driver.findElement(By.xpath("//*[@id='password']"));
            System.out.println("-+++++++++Password exits +++++++++\n+++++++++-");
            Thread.sleep(2000);
            password.sendKeys("123456");  //"ENTER CORRECT PASSWORD");
            Thread.sleep(2000);
        }
        catch(Throwable e)
        {
            System.out.println("Password not found: " + e.getMessage());
        }

        driver.close();
        driver.quit();

    }

    @Test
    public void VerifyLoginbuttonclick_Test10() {
        //Asserting the Sign In button exists or not and clicking it
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Maximize Window
        driver.manage().window().maximize();

        // Wait For Page To Load
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        //Navigate to HR Portal websites
        driver.get("http://localhost:9000/");

        try
        {
            WebElement button = driver.findElement(By.xpath("//*[@id='login-wrapper']/input")); //"LogIn"
            System.out.println("+++++++++Log in button exists----------\n-+++++++++");
            //To uncheck the "Check sign in" checkbox
            // WebElement check_stay_sign_in = driver.findElement(By.xpath("//*[@id='PersistentCookie']"));
            //check_stay_sign_in.click();
            Thread.sleep(2000);
            button.click();
            Thread.sleep(2000);

        }
        catch(Throwable e)
        {
            System.out.println("-----Log in button not found: ----------"+ e.getMessage());
        }

        driver.close();
        driver.quit();
    }

    @Test
    public void VerifyAdminlogin_Test11() {
        // Admin Login Test Case

        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Maximize Window
        driver.manage().window().maximize();

        // Wait For Page To Load
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        //Navigate to Google websites
        driver.get("http://localhost:9000/");
        /*CASE- 1. Both User name and Password are entered correctly.
        Check whether Email field exists or not */
        try
        {
            WebElement a1 = driver.findElement(By.xpath("//*[@id='email']"));
            System.out.println("+++++++++Emaild exists+++++++++-\n-----------------------");
            Thread.sleep(3000);
            a1.sendKeys("gopinathputta12@gmail.com" ); // "ENTER CORRECT MAIL ID";
            Thread.sleep(2000);

        }
        catch(Throwable e)
        {
            System.out.println("Emaild not found: " + e.getMessage());
        }

        //Check whether Password field exists or not
        try
        {

            WebElement password = driver.findElement(By.xpath("//*[@id='password']"));
            System.out.println("+++++++++Passwordd exits +++++++++-\n-+++++++++--");
            Thread.sleep(2000);
            password.sendKeys("123456");  //"ENTER CORRECT PASSWORD");
            Thread.sleep(2000);
        }
        catch(Throwable e)
        {
            System.out.println("Password not found: " + e.getMessage());
        }

        //Asserting the Sign In button exists or not and clicking it
        try
        {
            WebElement button = driver.findElement(By.xpath("//*[@id='login-wrapper']/input")); //"LogIn"
            System.out.println("-+++++++++Log in button exists-+++++++++-\n-++++++++++++++++++-----");
            //To uncheck the "Check sign in" checkbox
            // WebElement check_stay_sign_in = driver.findElement(By.xpath("//*[@id='PersistentCookie']"));
            //check_stay_sign_in.click();
            Thread.sleep(2000);
            button.click();
            Thread.sleep(2000);

        }
        catch(Throwable e)
        {
            System.out.println("---------Log in button not found:-------"+ e.getMessage());
        }

//Check if login was proper or not
        try
        {

            Thread.sleep(2000);
            WebElement LoginnameText = driver.findElement(By.xpath("//*[@id='header']/div/button"));
            String text = LoginnameText.getText();
            if(text.equals("Admin-gopinathputta12"))
            {
                System.out.println("+++++++++Sucessful login +++++++++\n-+++++++++-");
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            }else
            {
                System.out.println("----------Login failure  ----------\n-----------------------");
            }
        }
        catch(Throwable e)
        {
            System.out.println("----------Inbox not found: -------"+e.getMessage());
        }
        driver.close();
        driver.quit();
    }

    @Test
    public void VerifyAdminlogout_Test12() {
        // Admin Logout Test Case

        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Maximize Window
        driver.manage().window().maximize();

        // Wait For Page To Load
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        //Navigate to Google websites
        driver.get("http://localhost:9000/");
        /*CASE- 1. Both User name and Password are entered correctly.
        Check whether Email field exists or not */
        try
        {
            WebElement a1 = driver.findElement(By.xpath("//*[@id='email']"));
            System.out.println("+++++++++Emaild exists+++++++++-\n-----------------------");
            Thread.sleep(3000);
            a1.sendKeys("gopinathputta12@gmail.com" ); // "ENTER CORRECT MAIL ID";
            Thread.sleep(2000);

        }
        catch(Throwable e)
        {
            System.out.println("Emaild not found: " + e.getMessage());
        }

        //Check whether Password field exists or not
        try
        {

            WebElement password = driver.findElement(By.xpath("//*[@id='password']"));
            System.out.println("+++++++++Passwordd exits +++++++++-\n-+++++++++--");
            Thread.sleep(2000);
            password.sendKeys("123456");  //"ENTER CORRECT PASSWORD");
            Thread.sleep(2000);
        }
        catch(Throwable e)
        {
            System.out.println("Password not found: " + e.getMessage());
        }

        //Asserting the Sign In button exists or not and clicking it
        try
        {
            WebElement button = driver.findElement(By.xpath("//*[@id='login-wrapper']/input")); //"LogIn"
            System.out.println("-+++++++++Log in button exists-+++++++++-\n-++++++++++++++++++-----");
            //To uncheck the "Check sign in" checkbox
            // WebElement check_stay_sign_in = driver.findElement(By.xpath("//*[@id='PersistentCookie']"));
            //check_stay_sign_in.click();
            Thread.sleep(2000);
            button.click();
            Thread.sleep(2000);

        }
        catch(Throwable e)
        {
            System.out.println("---------Log in button not found:-------"+ e.getMessage());
        }

//Check if login was proper or not
        try
        {

            Thread.sleep(2000);
            WebElement LoginnameText = driver.findElement(By.xpath("//*[@id='header']/div/button"));
            String text = LoginnameText.getText();
            if(text.equals("Admin-gopinathputta12"))
            {
                System.out.println("+++++++++Sucessful login +++++++++\n-+++++++++-");
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            }else
            {
                System.out.println("----------Login failure  ----------\n-----------------------");
            }
        }
        catch(Throwable e)
        {
            System.out.println("----------Inbox not found: -------"+e.getMessage());
        }
        //===

        //Asserting and click on the Account link which contain Signout button.
        try
        {
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            WebElement person = driver.findElement(By.xpath("//*[@id='header']/div/button"));
            System.out.println("+++++++++The Account link containing Signout button exists +++++++++-\n-+++++++++-");
            Thread.sleep(3000);
            person.click();

        }
        catch(Throwable e)
        {
            System.out.println("------Linkthe drop-down not found:------- "+e.getMessage());
        }


        //Asserting and clicking on the Signout button.
        try
        {
            Thread.sleep(4000);
            WebElement signout = driver.findElement(By.xpath("//*[@id='header']/div/ul/li[6]/a"));
            System.out.println("-+++++++++Signbutton exists  +++++++++\n-----------------------");
            signout.click();
            Thread.sleep(3000);
        }
        catch(Throwable e)
        {
            System.out.println("----------Signbutton not found: -------"+e.getMessage());
        }
        driver.close();
        driver.quit();
    }

    @Test
    public void VerifyJobseekerlogin_Test13() {
        // Jobseeker Login Test Case

        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Maximize Window
        driver.manage().window().maximize();

        // Wait For Page To Load
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        //Navigate to Google websites
        driver.get("http://localhost:9000/");
        /*CASE- 1. Both User name and Password are entered correctly.
        Check whether Email field exists or not */
        try
        {
            WebElement a1 = driver.findElement(By.xpath("//*[@id='email']"));
            System.out.println("+++++++++Emaild exists+++++++++-\n-----------------------");
            Thread.sleep(3000);
            a1.sendKeys("gopinathputta12@gmail.com" ); // "ENTER CORRECT MAIL ID";
            Thread.sleep(2000);

        }
        catch(Throwable e)
        {
            System.out.println("Emaild not found: " + e.getMessage());
        }

        //Check whether Password field exists or not
        try
        {

            WebElement password = driver.findElement(By.xpath("//*[@id='password']"));
            System.out.println("+++++++++Passwordd exits +++++++++-\n-+++++++++--");
            Thread.sleep(2000);
            password.sendKeys("123456");  //"ENTER CORRECT PASSWORD");
            Thread.sleep(2000);
        }
        catch(Throwable e)
        {
            System.out.println("Password not found: " + e.getMessage());
        }

        //Asserting the Sign In button exists or not and clicking it
        try
        {
            WebElement button = driver.findElement(By.xpath("//*[@id='login-wrapper']/input")); //"LogIn"
            System.out.println("-+++++++++Log in button exists-+++++++++-\n-++++++++++++++++++-----");
            //To uncheck the "Check sign in" checkbox
            // WebElement check_stay_sign_in = driver.findElement(By.xpath("//*[@id='PersistentCookie']"));
            //check_stay_sign_in.click();
            Thread.sleep(2000);
            button.click();
            Thread.sleep(2000);

        }
        catch(Throwable e)
        {
            System.out.println("---------Log in button not found:-------"+ e.getMessage());
        }

//Check if login was proper or not
        try
        {

            Thread.sleep(2000);
            WebElement LoginnameText = driver.findElement(By.xpath("//*[@id='header']/div/button"));
            String text = LoginnameText.getText();
            if(text.equals("Admin-gopinathputta12"))
            {
                System.out.println("+++++++++Sucessful login +++++++++\n-+++++++++-");
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            }else
            {
                System.out.println("----------Login failure  ----------\n-----------------------");
            }
        }
        catch(Throwable e)
        {
            System.out.println("----------Inbox not found: -------"+e.getMessage());
        }
        driver.close();
        driver.quit();
    }

    @Test
    public void VerifyJobseekerlogout_Test14() throws InterruptedException {
        // Jobseeker Logout Test Case

        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Maximize Window
        driver.manage().window().maximize();

        // Wait For Page To Load
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        //Navigate to HR Portal websites
        driver.get("http://localhost:9000/");
        /*CASE- 1. Both User name and Password are entered correctly.
        Check whether Email field exists or not */
        try
        {
            WebElement a1 = driver.findElement(By.xpath("//*[@id='email']"));
            System.out.println("+++++++++Emaild exists -+++++++++-\n-+++++++++-");
            Thread.sleep(3000);
            a1.sendKeys("hrtooljobseeker1@gmail.com" ); // "ENTER CORRECT  MAIL ID";
            Thread.sleep(2000);

        }
        catch(Throwable e)
        {
            System.out.println("Emaild not found: " + e.getMessage());
        }

        //Check whether Password field exists or not
        try
        {

            WebElement password = driver.findElement(By.xpath("//*[@id='password']"));
            System.out.println("-+++++++++Password exits +++++++++\n+++++++++-");
            Thread.sleep(2000);
            password.sendKeys("123456");  //"ENTER CORRECT PASSWORD");
            Thread.sleep(2000);
        }
        catch(Throwable e)
        {
            System.out.println("Password not found: " + e.getMessage());
        }

        //Asserting the Sign In button exists or not and clicking it
        try
        {
            WebElement button = driver.findElement(By.xpath("//*[@id='login-wrapper']/input")); //"LogIn"
            System.out.println("+++++++++Log in button exists----------\n-+++++++++");
            //To uncheck the "Check sign in" checkbox
            // WebElement check_stay_sign_in = driver.findElement(By.xpath("//*[@id='PersistentCookie']"));
            //check_stay_sign_in.click();
            Thread.sleep(2000);
            button.click();
            Thread.sleep(2000);

        }
        catch(Throwable e)
        {
            System.out.println("-----Log in button not found: ----------"+ e.getMessage());
        }

        //Check if login was proper or not
        try
        {

            Thread.sleep(2000);
            // Identify and check the Logged in username

            WebElement LoginnameText = driver.findElement(By.xpath("//*[@id='header']/div/button"));
            String text = LoginnameText.getText();
            if(text.equals("jobseeekr1"))
            {
                System.out.println("++++++++++++++++++Sucessful login +++++++++-\n-+++++++++-");
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            }else
            {
                System.out.println("----------Login failure  ----------\n-----------------------");
            }
        }
        catch(Throwable e)
        {
            System.out.println("-------Inbox not found: -------"+e.getMessage());
        }
        //===

        //Asserting and click on the Account link which contain Signout button.
        try
        {
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            WebElement person = driver.findElement(By.xpath("//*[@id='header']/div/button"));
            System.out.println("+++++++++The Account link containing Signout button exists +++++++++-\n-+++++++++-");
            Thread.sleep(3000);
            person.click();

        }
        catch(Throwable e)
        {
            System.out.println("-------Linkthe drop-down not found:--------- "+e.getMessage());
        }


        //Asserting and clicking on the Signout button.
        try
        {
            Thread.sleep(4000);
            // find the Signout button

            WebElement signout = driver.findElement(By.xpath("//*[@id='header']/div/ul/li[7]/a"));
            System.out.println("++++++++++Sign out button exists  +++++++++--\n+++++++++");
            signout.click();
            System.out.println("++++++++++ User Successfully logged out  +++++++++--\n+++++++++");
            Thread.sleep(3000);
        }
        catch(Throwable e)
        {
            System.out.println("\n-----Sign out button not found:--- "+e.getMessage());
        }

        Thread.sleep(2000);
        driver.close();
        driver.quit();
    }


    @Test
    public void VerifyRecruiterlogin_Test15() {

        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Maximize Window
        driver.manage().window().maximize();

        // Wait For Page To Load
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        //Navigate to HR Portal websites
        driver.get("http://localhost:9000/");
        /*CASE- 1. Both User name and Password are entered correctly.
        Check whether Email field exists or not */
        try
        {
            WebElement a1 = driver.findElement(By.xpath("//*[@id='email']"));
            System.out.println("+++++++++Emaild exists -+++++++++-\n-+++++++++-");
            Thread.sleep(3000);
            a1.sendKeys("gopinathreddy2008@gmail.com" ); // "ENTER CORRECT  MAIL ID";
            Thread.sleep(2000);

        }
        catch(Throwable e)
        {
            System.out.println("Emaild not found: " + e.getMessage());
        }

        //Check whether Password field exists or not
        try
        {

            WebElement password = driver.findElement(By.xpath("//*[@id='password']"));
            System.out.println("-+++++++++Password exits +++++++++\n+++++++++-");
            Thread.sleep(2000);
            password.sendKeys("123456");  //"ENTER CORRECT PASSWORD");
            Thread.sleep(2000);
        }
        catch(Throwable e)
        {
            System.out.println("Password not found: " + e.getMessage());
        }

        //Asserting the Sign In button exists or not and clicking it
        try
        {
            WebElement button = driver.findElement(By.xpath("//*[@id='login-wrapper']/input")); //"LogIn"
            System.out.println("+++++++++Log in button exists----------\n-+++++++++");
            //To uncheck the "Check sign in" checkbox
            // WebElement check_stay_sign_in = driver.findElement(By.xpath("//*[@id='PersistentCookie']"));
            //check_stay_sign_in.click();
            Thread.sleep(2000);
            button.click();
            Thread.sleep(2000);

        }
        catch(Throwable e)
        {
            System.out.println("-----Log in button not found: ----------"+ e.getMessage());
        }

//Check if login was proper or not
        try
        {

            Thread.sleep(2000);
            // Identify and check the Logged in username
            WebElement LoginnameText = driver.findElement(By.xpath("//*[@id='header']/div/button"));
            String text = LoginnameText.getText();
            if(text.equals("gopinathreddy2008(recruiter2)"))
            {
                System.out.println("++++++++++++++++++Sucessful login +++++++++-\n-+++++++++-");
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            }else
            {
                System.out.println("----------Login failure  ----------\n-----------------------");
            }
        }
        catch(Throwable e)
        {
            System.out.println("-------Inbox not found: -------"+e.getMessage());
        }
        driver.close();
        driver.quit();
    }

    @Test
    public void VerifyRecruiterlogout_Test16() throws InterruptedException {
        // Recruiter Logout Test Case

        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Maximize Window
        driver.manage().window().maximize();

        // Wait For Page To Load
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        //Navigate to HR Portal websites
        driver.get("http://localhost:9000/");
        /*CASE- 1. Both User name and Password are entered correctly.
        Check whether Email field exists or not */
        try
        {
            WebElement a1 = driver.findElement(By.xpath("//*[@id='email']"));
            System.out.println("+++++++++Emaild exists -+++++++++-\n-+++++++++-");
            Thread.sleep(3000);
            a1.sendKeys("gopinathreddy2008@gmail.com" ); // "ENTER CORRECT  MAIL ID";
            Thread.sleep(2000);

        }
        catch(Throwable e)
        {
            System.out.println("Emaild not found: " + e.getMessage());
        }

        //Check whether Password field exists or not
        try
        {

            WebElement password = driver.findElement(By.xpath("//*[@id='password']"));
            System.out.println("-+++++++++Password exits +++++++++\n+++++++++-");
            Thread.sleep(2000);
            password.sendKeys("123456");  //"ENTER CORRECT PASSWORD");
            Thread.sleep(2000);
        }
        catch(Throwable e)
        {
            System.out.println("Password not found: " + e.getMessage());
        }

        //Asserting the Sign In button exists or not and clicking it
        try
        {
            WebElement button = driver.findElement(By.xpath("//*[@id='login-wrapper']/input")); //"LogIn"
            System.out.println("+++++++++Log in button exists----------\n-+++++++++");
            //To uncheck the "Check sign in" checkbox
            // WebElement check_stay_sign_in = driver.findElement(By.xpath("//*[@id='PersistentCookie']"));
            //check_stay_sign_in.click();
            Thread.sleep(2000);
            button.click();
            Thread.sleep(2000);

        }
        catch(Throwable e)
        {
            System.out.println("-----Log in button not found: ----------"+ e.getMessage());
        }

//Check if login was proper or not
        try
        {

            Thread.sleep(2000);
            // Identify and check the Logged in username
            WebElement LoginnameText = driver.findElement(By.xpath("//*[@id='header']/div/button"));
            String text = LoginnameText.getText();
            if(text.equals("gopinathreddy2008(recruiter2)"))
            {
                System.out.println("++++++++++++++++++Sucessful login +++++++++-\n-+++++++++-");
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            }else
            {
                System.out.println("----------Login failure  ----------\n-----------------------");
            }
        }
        catch(Throwable e)
        {
            System.out.println("-------Inbox not found: -------"+e.getMessage());
        }
        //===

        //Asserting and click on the Account link which contain Signout button.
        try
        {
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            WebElement person = driver.findElement(By.xpath("//*[@id='header']/div/button"));
            System.out.println("+++++++++The Account link containing Signout button exists +++++++++-\n-+++++++++-");
            Thread.sleep(3000);
            person.click();

        }
        catch(Throwable e)
        {
            System.out.println("-------Linkthe drop-down not found:--------- "+e.getMessage());
        }


        //Asserting and clicking on the Signout button.
        try
        {
            Thread.sleep(4000);
            // find the Signout button

            WebElement signout = driver.findElement(By.xpath("//*[@id='header']/div/ul/li[4]/a"));
            System.out.println("++++++++++Sign out button exists  +++++++++--\n+++++++++");
            signout.click();
            System.out.println("++++++++++ User Successfully logged out  +++++++++--\n+++++++++");
            Thread.sleep(3000);
        }
        catch(Throwable e)
        {
            System.out.println("\n-----Signbutton not found:--- "+e.getMessage());
        }

        Thread.sleep(3000);
        driver.close();
        driver.quit();

    }


    @Test
    public void VerifyForgotPasswordprocess_Test17() {
        // Recruiter Login Test Case

        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Maximize Window
        driver.manage().window().maximize();

        // Wait For Page To Load
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        //Navigate to HR Portal websites
        driver.get("http://localhost:9000/");

    }


    @Test
    public void VerifySignupbuttonclick_Test18() {
        //Asserting the Sign up button exists or not and clicking it
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Maximize Window
        driver.manage().window().maximize();

        // Wait For Page To Load
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        //Navigate to HR Portal websites
        driver.get("http://localhost:9000/");

        try
        {
            WebElement button = driver.findElement(By.xpath("//*[@id='login-wrapper']/a[2]")); //"LogIn"
            System.out.println("+++++++++Signup button exists----------\n-+++++++++");
            //To uncheck the "Check sign in" checkbox
            // WebElement check_stay_sign_in = driver.findElement(By.xpath("//*[@id='PersistentCookie']"));
            //check_stay_sign_in.click();
            Thread.sleep(2000);
            button.click();
            Thread.sleep(2000);

        }
        catch(Throwable e)
        {
            System.out.println("-----Signup button not found: ----------"+ e.getMessage());
        }

        driver.close();
        driver.quit();
    }

    @Test
    public void VerifySignupprocess_Test19() {

        //Asserting the Sign up Process by jobseeker
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Maximize Window
        driver.manage().window().maximize();

        // Wait For Page To Load
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        //Navigate to HR Portal websites
        driver.get("http://localhost:9000/");

        try
        {
            WebElement button = driver.findElement(By.xpath("//*[@id='login-wrapper']/a[2]")); //"Signup"
            System.out.println("+++++++++Signup button exists----------\n-+++++++++");
            //To uncheck the "Check sign in" checkbox
            // WebElement check_stay_sign_in = driver.findElement(By.xpath("//*[@id='PersistentCookie']"));
            //check_stay_sign_in.click();
            Thread.sleep(2000);
            button.click();
            Thread.sleep(2000);

        }
        catch(Throwable e)
        {
            System.out.println("-----Signup button not found: ----------"+ e.getMessage());
        }

        try
        {       //Identify Your name field in Sign up page
            WebElement a1 = driver.findElement(By.xpath("//*[@id='name']"));
            System.out.println("+++++++++ Your Name Field exists -+++++++++-\n-+++++++++-");
            Thread.sleep(3000);
            a1.sendKeys("jobseeker1" ); // "ENTER CORRECT  MAIL ID";
            Thread.sleep(2000);

        }
        catch(Throwable e)
        {
            System.out.println("Your Name Field  not found: " + e.getMessage());
        }

        try
        {       //Identify Your Email field in Sign up page
            WebElement a1 = driver.findElement(By.xpath(".//*[@id='email']"));
            System.out.println("+++++++++ Email Field exists -+++++++++-\n-+++++++++-");
            Thread.sleep(3000);
            a1.sendKeys("hrtooljobseeker1@gmail.com" ); // "ENTER CORRECT  MAIL ID";
            Thread.sleep(2000);

        }
        catch(Throwable e)
        {
            System.out.println("Your Email Field   not found: " + e.getMessage());
        }


        //Check whether Password field exists or not
        try
        {

            WebElement password = driver.findElement(By.xpath("//*[@id='password']"));
            System.out.println("-+++++++++Password exits +++++++++\n+++++++++-");
            Thread.sleep(2000);
            password.sendKeys("123456");  //"ENTER CORRECT PASSWORD");
            Thread.sleep(2000);
        }
        catch(Throwable e)
        {
            System.out.println("Password not found: " + e.getMessage());
        }

        //Check whether Repeat Password field exists or not
        try
        {

            WebElement repeatpassword = driver.findElement(By.xpath("//*[@id='repeatPassword']"));
            System.out.println("-+++++++++Password exits +++++++++\n+++++++++-");
            Thread.sleep(2000);
            repeatpassword.sendKeys("123456");  //"ENTER CORRECT PASSWORD");
            Thread.sleep(2000);
        }
        catch(Throwable e)
        {
            System.out.println(" Repeat Password not found: " + e.getMessage());
        }

        //Check whether Clicking Terms & COndition check box exists or not
        try
        {

            WebElement terms = driver.findElement(By.xpath("//*[@id='main']/div/form/input"));
            System.out.println("-+++++++++Terms & Conditions Exist +++++++++\n+++++++++-");
            Thread.sleep(2000);
            //terms.click(); //"ENTER CORRECT PASSWORD");
            Thread.sleep(2000);

            if ( !terms.isSelected() )
            {
                terms.click();
            }

        }
        catch(Throwable e)
        {
            System.out.println(" Terms & Conditions not found: " + e.getMessage());
        }



        //Asserting the Sign up button exists or not and clicking it
        try
        {
            WebElement button = driver.findElement(By.xpath("//*[@id='main']/div/form/div[4]/div/input")); //"Signup"
            System.out.println("+++++++++Signup  button exists----------\n-+++++++++");
            //To uncheck the "Check sign in" checkbox
            // WebElement check_stay_sign_in = driver.findElement(By.xpath("//*[@id='PersistentCookie']"));
            //check_stay_sign_in.click();
            Thread.sleep(2000);
            button.click();
            Thread.sleep(5000);

        }
        catch(Throwable e)
        {
            System.out.println("----- Signup button not found: ----------"+ e.getMessage());
        }



        driver.close();
        driver.quit();
    }




    @Test
    public void VerifyRecruiter_registrationpage_Test20() {

        //Asserting the Sign up Process by jobseeker
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Maximize Window
        driver.manage().window().maximize();

        // Wait For Page To Load
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        //Navigate to HR Portal websites
        driver.get("http://localhost:9000/");

        try
        {
            WebElement button = driver.findElement(By.xpath("//*[@id='login-wrapper']/a[3]")); //"Signup"
            System.out.println("+++++++++Hire People button exists----------\n-+++++++++");
            //To uncheck the "Check sign in" checkbox
            // WebElement check_stay_sign_in = driver.findElement(By.xpath("//*[@id='PersistentCookie']"));
            //check_stay_sign_in.click();
            Thread.sleep(2000);
            button.click();
            Thread.sleep(2000);

        }
        catch(Throwable e)
        {
            System.out.println("-----Hirepeople button not found: ----------"+ e.getMessage());
        }

        driver.close();
        driver.quit();
    }


    @Test
    public void AdminHomepage_Test21(){
        // Admin Login Test Case

        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Maximize Window
        driver.manage().window().maximize();

        // Wait For Page To Load
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        //Navigate to Google websites
        driver.get("http://localhost:9000/");
        /*CASE- 1. Both User name and Password are entered correctly.
        Check whether Email field exists or not */
        try
        {
            WebElement a1 = driver.findElement(By.xpath("//*[@id='email']"));
            System.out.println("+++++++++Emaild exists+++++++++-\n-----------------------");
            Thread.sleep(3000);
            a1.sendKeys("gopinathputta12@gmail.com" ); // "ENTER CORRECT MAIL ID";
            Thread.sleep(2000);

        }
        catch(Throwable e)
        {
            System.out.println("Emaild not found: " + e.getMessage());
        }

        //Check whether Password field exists or not
        try
        {

            WebElement password = driver.findElement(By.xpath("//*[@id='password']"));
            System.out.println("+++++++++Passwordd exits +++++++++-\n-+++++++++--");
            Thread.sleep(2000);
            password.sendKeys("123456");  //"ENTER CORRECT PASSWORD");
            Thread.sleep(2000);
        }
        catch(Throwable e)
        {
            System.out.println("Password not found: " + e.getMessage());
        }

        //Asserting the Sign In button exists or not and clicking it
        try
        {
            WebElement button = driver.findElement(By.xpath("//*[@id='login-wrapper']/input")); //"LogIn"
                    System.out.println("-+++++++++Log in button exists-+++++++++-\n-++++++++++++++++++-----");
                    //To uncheck the "Check sign in" checkbox
                   // WebElement check_stay_sign_in = driver.findElement(By.xpath("//*[@id='PersistentCookie']"));
            //check_stay_sign_in.click();
            Thread.sleep(2000);
            button.click();
            Thread.sleep(2000);

        }
        catch(Throwable e)
        {
            System.out.println("---------Log in button not found:-------"+ e.getMessage());
        }

//Check if login was proper or not
        try
        {

            Thread.sleep(2000);
            WebElement LoginnameText = driver.findElement(By.xpath("//*[@id='header']/div/button"));
            String text = LoginnameText.getText();
            if(text.equals("Admin-gopinathputta12"))
            {
                System.out.println("+++++++++Sucessful login +++++++++\n-+++++++++-");
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            }else
            {
                System.out.println("----------Login failure  ----------\n-----------------------");
            }
        }
        catch(Throwable e)
        {
            System.out.println("----------Inbox not found: -------"+e.getMessage());
        }
        //===

        //Asserting and click on the Account link which contain Signout button.
        try
        {
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            WebElement person = driver.findElement(By.xpath("//*[@id='header']/div/button"));
            System.out.println("+++++++++The Account link containing Signout button exists +++++++++-\n-+++++++++-");
            Thread.sleep(3000);
            person.click();

        }
        catch(Throwable e)
        {
            System.out.println("------Linkthe drop-down not found:------- "+e.getMessage());
        }


        //Asserting and clicking on the Signout button.
        try
        {
            Thread.sleep(4000);
            WebElement signout = driver.findElement(By.xpath("//*[@id='header']/div/ul/li[6]/a"));
            System.out.println("-+++++++++Signbutton exists  +++++++++\n-----------------------");
            signout.click();
            Thread.sleep(3000);
        }
        catch(Throwable e)
        {
            System.out.println("----------Signbutton not found: -------"+e.getMessage());
        }
        driver.close();
        driver.quit();
    } //end of Admin Login Test case



    /************* Recruiter Login Test Case **************/
    @Test
    public void Recruiterhomepage_Test22() throws InterruptedException {
        // Recruiter Login Test Case

        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Maximize Window
        driver.manage().window().maximize();

        // Wait For Page To Load
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        //Navigate to HR Portal websites
        driver.get("http://localhost:9000/");
        /*CASE- 1. Both User name and Password are entered correctly.
        Check whether Email field exists or not */
        try
        {
            WebElement a1 = driver.findElement(By.xpath("//*[@id='email']"));
            System.out.println("+++++++++Emaild exists -+++++++++-\n-+++++++++-");
            Thread.sleep(3000);
            a1.sendKeys("gopinathreddy2008@gmail.com" ); // "ENTER CORRECT  MAIL ID";
            Thread.sleep(2000);

        }
        catch(Throwable e)
        {
            System.out.println("Emaild not found: " + e.getMessage());
        }

        //Check whether Password field exists or not
        try
        {

            WebElement password = driver.findElement(By.xpath("//*[@id='password']"));
            System.out.println("-+++++++++Password exits +++++++++\n+++++++++-");
            Thread.sleep(2000);
            password.sendKeys("123456");  //"ENTER CORRECT PASSWORD");
            Thread.sleep(2000);
        }
        catch(Throwable e)
        {
            System.out.println("Password not found: " + e.getMessage());
        }

        //Asserting the Sign In button exists or not and clicking it
        try
        {
            WebElement button = driver.findElement(By.xpath("//*[@id='login-wrapper']/input")); //"LogIn"
            System.out.println("+++++++++Log in button exists----------\n-+++++++++");
            //To uncheck the "Check sign in" checkbox
            // WebElement check_stay_sign_in = driver.findElement(By.xpath("//*[@id='PersistentCookie']"));
            //check_stay_sign_in.click();
            Thread.sleep(2000);
            button.click();
            Thread.sleep(2000);

        }
        catch(Throwable e)
        {
            System.out.println("-----Log in button not found: ----------"+ e.getMessage());
        }

//Check if login was proper or not
        try
        {

            Thread.sleep(2000);
            // Identify and check the Logged in username
            WebElement LoginnameText = driver.findElement(By.xpath("//*[@id='header']/div/button"));
            String text = LoginnameText.getText();
            if(text.equals("gopinathreddy2008(recruiter2)"))
            {
                System.out.println("++++++++++++++++++Sucessful login +++++++++-\n-+++++++++-");
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            }else
            {
                System.out.println("----------Login failure  ----------\n-----------------------");
            }
        }
        catch(Throwable e)
        {
            System.out.println("-------Inbox not found: -------"+e.getMessage());
        }
        //===

        //Asserting and click on the Account link which contain Signout button.
        try
        {
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            WebElement person = driver.findElement(By.xpath("//*[@id='header']/div/button"));
            System.out.println("+++++++++The Account link containing Signout button exists +++++++++-\n-+++++++++-");
            Thread.sleep(3000);
            person.click();

        }
        catch(Throwable e)
        {
            System.out.println("-------Linkthe drop-down not found:--------- "+e.getMessage());
        }


        //Asserting and clicking on the Signout button.
        try
        {
            Thread.sleep(4000);
            // find the Signout button

            WebElement signout = driver.findElement(By.xpath("//*[@id='header']/div/ul/li[4]/a"));
            System.out.println("++++++++++Sign out button exists  +++++++++--\n+++++++++");
            signout.click();
            System.out.println("++++++++++ User Successfully logged out  +++++++++--\n+++++++++");
            Thread.sleep(3000);
        }
        catch(Throwable e)
        {
            System.out.println("\n-----Signbutton not found:--- "+e.getMessage());
        }

        Thread.sleep(3000);
        driver.close();
        driver.quit();

    } //end of Recruiter LoginTest case



    /* ******************* // Jobseeker Login Test Case ***************************** */
    @Test
    public void Jobseekerhomepage_Test23() throws InterruptedException {
        // Jobseeker Login Test Case

        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Maximize Window
        driver.manage().window().maximize();

        // Wait For Page To Load
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        //Navigate to HR Portal websites
        driver.get("http://localhost:9000/");
        /*CASE- 1. Both User name and Password are entered correctly.
        Check whether Email field exists or not */
        try
        {
            WebElement a1 = driver.findElement(By.xpath("//*[@id='email']"));
            System.out.println("+++++++++Emaild exists -+++++++++-\n-+++++++++-");
            Thread.sleep(3000);
            a1.sendKeys("hrtooljobseeker1@gmail.com" ); // "ENTER CORRECT  MAIL ID";
            Thread.sleep(2000);

        }
        catch(Throwable e)
        {
            System.out.println("Emaild not found: " + e.getMessage());
        }

        //Check whether Password field exists or not
        try
        {

            WebElement password = driver.findElement(By.xpath("//*[@id='password']"));
            System.out.println("-+++++++++Password exits +++++++++\n+++++++++-");
            Thread.sleep(2000);
            password.sendKeys("123456");  //"ENTER CORRECT PASSWORD");
            Thread.sleep(2000);
        }
        catch(Throwable e)
        {
            System.out.println("Password not found: " + e.getMessage());
        }

        //Asserting the Sign In button exists or not and clicking it
        try
        {
            WebElement button = driver.findElement(By.xpath("//*[@id='login-wrapper']/input")); //"LogIn"
            System.out.println("+++++++++Log in button exists----------\n-+++++++++");
            //To uncheck the "Check sign in" checkbox
            // WebElement check_stay_sign_in = driver.findElement(By.xpath("//*[@id='PersistentCookie']"));
            //check_stay_sign_in.click();
            Thread.sleep(2000);
            button.click();
            Thread.sleep(2000);

        }
        catch(Throwable e)
        {
            System.out.println("-----Log in button not found: ----------"+ e.getMessage());
        }

        //Check if login was proper or not
        try
        {

            Thread.sleep(2000);
            // Identify and check the Logged in username

            WebElement LoginnameText = driver.findElement(By.xpath("//*[@id='header']/div/button"));
            String text = LoginnameText.getText();
            if(text.equals("jobseeekr1"))
            {
                System.out.println("++++++++++++++++++Sucessful login +++++++++-\n-+++++++++-");
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            }else
            {
                System.out.println("----------Login failure  ----------\n-----------------------");
            }
        }
        catch(Throwable e)
        {
            System.out.println("-------Inbox not found: -------"+e.getMessage());
        }
        //===

        //Asserting and click on the Account link which contain Signout button.
        try
        {
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            WebElement person = driver.findElement(By.xpath("//*[@id='header']/div/button"));
            System.out.println("+++++++++The Account link containing Signout button exists +++++++++-\n-+++++++++-");
            Thread.sleep(3000);
            person.click();

        }
        catch(Throwable e)
        {
            System.out.println("-------Linkthe drop-down not found:--------- "+e.getMessage());
        }


        //Asserting and clicking on the Signout button.
        try
        {
            Thread.sleep(4000);
            // find the Signout button

            WebElement signout = driver.findElement(By.xpath("//*[@id='header']/div/ul/li[7]/a"));
            System.out.println("++++++++++Sign out button exists  +++++++++--\n+++++++++");
            signout.click();
            System.out.println("++++++++++ User Successfully logged out  +++++++++--\n+++++++++");
            Thread.sleep(3000);
        }
        catch(Throwable e)
        {
            System.out.println("\n-----Sign out button not found:--- "+e.getMessage());
        }

        Thread.sleep(3000);
        driver.close();
        driver.quit();

    } //end of JobseekerLogin Test Case





}//end of Unit_Integration_Tests class Package suits
