package com.endava;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

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
        // replace 'username' with actual gmail address to work
        WebElement emailField = webDriver.findElement(By.xpath("//input[@id=\"Email\"]"));
        emailField.sendKeys("username");

        // press the "Next" button
        WebElement nextButton = webDriver.findElement(By.xpath("//input[@id='next']"));
        nextButton.click();

        // replace 'password' with actual password for the gmail account previously entered
        WebElement passwordField = webDriver.findElement(By.xpath("//input[@id='Passwd']"));
        passwordField.sendKeys("password");

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
        subjectField.sendKeys("Test");

        //insert the mail body
        WebElement bodyField = webDriver.findElement(By.xpath("//div[@aria-label='Message Body']"));
        bodyField.sendKeys("Test");

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

        //Find the mail sent before by sender's name
        //If the mail is send by someone else, change 'Raicu Dragos' to actual name
        WebElement senderNameField = webDriver.findElement(By.xpath("//span[@name='Raicu Dragos']"));
        senderNameField.click();

        //extra step: Access the reply link related to the conversation to enable the Message Body Field
        WebElement replyField = webDriver.findElement(By.xpath("//span[text()='Reply'][@role='link']"));
        replyField.click();

        //save the time the mail was received to a string var just to check that it was "0 minutes ago"
        String elemval=webDriver.findElement(By.xpath("//span[contains(.,'minutes ago')]")).getText();

        //extra step: Verification
        bodyField = webDriver.findElement(By.xpath("//div[@aria-label='Message Body']"));
        bodyField.sendKeys("^ the email previously sent at ",elemval);

    }

    @AfterClass
    public static void tearDown(){
//        webDriver.close();
    }
}