package com.pdool.chromews.driver;

import com.alibaba.fastjson.JSON;
import com.pdool.chromews.Msg;
import com.pdool.chromews.service.WebsocketServer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class SelfWebDriver implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(SelfWebDriver.class);

    private final LinkedBlockingQueue<Msg> taskQueue;
    private final String roomId;
    private final String chromedriverPath;
    private final WebsocketServer websocketServer;
    private Set<String> prev = new HashSet<>();
    private Map<String,String> headMap = new HashMap<>();

    ChromeDriver driver = null;

    public SelfWebDriver(LinkedBlockingQueue<Msg> taskQueue, String roomId, String chromedriverPath, WebsocketServer websocketServer) {
        this.taskQueue = taskQueue;
        this.roomId = roomId;
        this.chromedriverPath = chromedriverPath;
        this.websocketServer = websocketServer;
    }

    @Override
    public void run() {


        try {
            System.setProperty("webdriver.chrome.driver", chromedriverPath);
            driver = new ChromeDriver();
            String url = "https://live.douyin.com/" + roomId;
            logger.info("url   is {}  ,driver is {}", url, chromedriverPath);
            driver.get(url);
            Thread.sleep(1000);
            while (true) {
                try {
                    WebElement elementByClassName = driver.findElementByClassName("webcast-chatroom___item-offset");
//                logger.info(elementByClassName.getText());
                    List<WebElement> element = elementByClassName.findElements(By.className("webcast-chatroom___item"));
//                    logger.error(" item  count  {} ", element.size());
                    HashSet<String> newDataIdSet = new HashSet<>();
                    for (WebElement webElement : element) {
                        String dataId = webElement.getAttribute("data-id");
                        newDataIdSet.add(dataId);
                        if (prev.contains(dataId)) {
                            continue;
                        }
                        String name = null;
                        String chat = null;
                        String level = null;

                        try {
                            System.out.println("xxxxxxxxxxx " + webElement.getText());
                            WebElement nameEle = webElement.findElement(By.className("tfObciRM"));
                            nameEle.click();
                            name = nameEle.getText();
                            chat = webElement.findElement(By.className("Wz8LGswb")).getText();
                            level = webElement.findElement(By.className("H8MhR2lo")).findElement(By.tagName("img")).getAttribute("src");
                            int start = level.indexOf("level_");
                            int end = level.indexOf(".png");
                            level = level.substring(start,end);

                            Msg msg = new Msg();
                            msg.setMessage(chat);
                            msg.setName(name);
                            msg.setId(dataId);
                            msg.setLevel(level);
                            logger.error("", msg);
//                            taskQueue.offer(msg);
                            this.websocketServer.sendMessage(JSON.toJSONString(msg));
                        } catch (Exception e) {
                            continue;
                        }

                        logger.info(" {}  say  {}  levl {}", name, chat,level);
                    }
                    prev = newDataIdSet;
                } catch (Exception e) {
                    continue;
                }
                Thread.sleep(50);
            }
        } catch (Exception e) {
            logger.error("", e);
        }


        WebElement title = driver.findElement(By.tagName("title"));
        logger.error(title.getText());
    }
}
