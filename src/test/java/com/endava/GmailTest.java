package com.endava;

import com.sun.glass.ui.*;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.SystemClock;

import java.awt.*;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by draicu on 7/29/2016.
 */
public class GmailTest {

    static WebDriver webDriver;

    @BeforeClass
    // static - variabila sau metoda de clasa, poate fi folosita chiar daca metoda nu e instantiata
    public static void setUp(){
        webDriver = new FirefoxDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://gmail.com");

        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) ;
    }

    @Test
    public void testGmail(){

        String mailUsername="a@gmail.com";
        String mailPassword="asdf";
        String mailSubject="subject123";
        String mailBody="body123";

        // replace 'username' with actual gmail address to work
        WebElement emailField = webDriver.findElement(By.xpath("//input[@id=\"Email\"]"));
        emailField.sendKeys(mailUsername);

        // press the "Next" button
        WebElement nextButton = webDriver.findElement(By.xpath("//input[@id='next']"));
        nextButton.click();

        // replace 'password' with actual password for the gmail account previously entered
        WebElement passwordField = webDriver.findElement(By.xpath("//input[@id='Passwd']"));
        passwordField.sendKeys(mailPassword);

        //press the "Sign in" button
        WebElement signInButton = webDriver.findElement(By.xpath("//input[@id='signIn']"));
        signInButton.click();

        //press the "COMPOSE" button
        WebElement composeButton = webDriver.findElement(By.xpath("//div[text()=\"COMPOSE\"]"));
        composeButton.click();

        //insert the mail address to whom you want to send the mail to
        WebElement toField = webDriver.findElement(By.xpath("//textarea[@name='to']"));
        toField.sendKeys("endavaselenium@gmail.com");
        toField.sendKeys(Keys.RETURN);

        //insert the mail subject
        WebElement subjectField = webDriver.findElement(By.xpath("//input[@name='subjectbox']"));
        subjectField.sendKeys(mailSubject);

        //insert the mail body
        WebElement bodyField = webDriver.findElement(By.xpath("//div[@aria-label='Message Body']"));
        bodyField.sendKeys(mailBody);

        //press the "Send" button
        WebElement sendButton = webDriver.findElement(By.xpath("//div[contains(@aria-label, 'Send')]"));
        sendButton.click();

        //press the upper-right button - the Google Account pictogram
        WebElement accountIcon = webDriver.findElement(By.xpath("//a[contains(@href,'SignOut')]"));
        accountIcon.click();

        //press the "Sign-out" button
        WebElement signOutButton = webDriver.findElement(By.xpath("//a[text()='Sign out']"));
        signOutButton.click();

        //access the link that allows you to enter a new mail address
        WebElement changeAccountLink = webDriver.findElement(By.xpath("//a[@id='account-chooser-link']"));
        changeAccountLink.click();

        //choose the "Add account" option
        WebElement addAccountLink = webDriver.findElement(By.xpath("//a[@id='account-chooser-add-account']"));
        addAccountLink.click();

        ///insert the mail address which you previously sent the mail to
        emailField = webDriver.findElement(By.xpath("//input[@id=\"Email\"]"));
        emailField.sendKeys("endavaselenium");

        //press the "Next" button
        nextButton = webDriver.findElement(By.xpath("//input[@id='next']"));
        nextButton.click();

        //insert the password corresponding to the address entered
        passwordField = webDriver.findElement(By.xpath("//input[@id='Passwd']"));
        passwordField.sendKeys("endavaqa");

        //press the "Sign in" button
        signInButton = webDriver.findElement(By.xpath("//input[@id='signIn']"));
        signInButton.click();

        //Find and access the mail sent before by sender's name
        //If you changed mailUsername varible, please change the attribute below "@email" to the same string
        WebElement senderNameField = webDriver.findElement(By.xpath("(//span[@email='rdragos05@gmail.com'])[position()<2]"));
        senderNameField.click();

        //now the last mail/ conversation with mailUsername is opened and testing can be done


        //Tests:

        //test mail subject
        //change "subject123" to the string you entered for mailSubject variable
        WebElement testMessageSubject = webDriver.findElement(By.xpath("(//h2[contains(@tabindex,\"-1\")])[last()]"));
        String messageSubject = testMessageSubject.getText();
        assertTrue(messageSubject.contains("subject123"));


        //test mail body
        //change "body123" to the string you entered for mailBody variable
        WebElement testMessageBody = webDriver.findElement(By.xpath("((((//.[@role='listitem'])[last()]/div/div/div/div/div[@style=\"display:\"]/div)[last()-1]/div)[last()-2]/div/div)[last()-1]"));
        String messageBody = testMessageBody.getText();
        assertEquals(mailBody,"body123");


        //test mail sender
        //change "rdragos05@gmail.com" to the string you entered for mailUsername variable
        WebElement testMessageSender = webDriver.findElement(By.xpath("(//h3)[last()]"));
        String messageSender = testMessageSender.getText();
        assertTrue(messageSender.contains("rdragos05@gmail.com"));

        //test time the message arrived
        String elemval=webDriver.findElement(By.xpath("(//span[contains(.,'minutes ago')])[last()]")).getText();
        assertTrue(elemval.contains("0 minutes ago"));

    }

    @AfterClass
    public static void tearDown(){
//        webDriver.close();
    }
}