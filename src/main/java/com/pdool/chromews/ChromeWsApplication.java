package com.pdool.chromews;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ChromeWsApplication {
    public static String roomId;
    public static void main(String[] args) {
        if (args.length == 0){
            System.out.println("请输入房间号");
        }
        roomId = args[0];
        ConfigurableApplicationContext run = SpringApplication.run(ChromeWsApplication.class, args);

        String driver = run.getEnvironment().getProperty("driver");
        System.out.println(driver);
    }

}
