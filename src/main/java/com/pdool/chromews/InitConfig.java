package com.pdool.chromews;

import com.pdool.chromews.service.WebsocketServer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InitConfig implements InitializingBean {
    @Value("${driver}")
    public String dirver;

    @Override
    public void afterPropertiesSet() throws Exception {
        WebsocketServer.driverPath = this.dirver;
    }
}
