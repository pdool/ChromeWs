package com.pdool.chromews.service;

import com.pdool.chromews.driver.SelfWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.atomic.AtomicReference;

public class CheckHeadRunnable implements Runnable{
    private ChromeDriver webDriver;
    AtomicReference<String> userName;
    public CheckHeadRunnable(ChromeDriver webDriver,AtomicReference<String> userName) {
        this.webDriver = webDriver;
        userName = userName;
    }

    @Override
    public void run() {
        while (true){
            WebElement portal = webDriver.findElementById("portal");
            if (portal == null){
                return ;
            }
            WebElement img = portal.findElement(By.tagName("img"));
        }
    }
}
