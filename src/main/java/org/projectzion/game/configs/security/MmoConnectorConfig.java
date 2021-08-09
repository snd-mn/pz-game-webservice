package org.projectzion.game.configs.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "pz.mmoconnector")
public class MmoConnectorConfig {
    String ip;
    String port;

    public String getUrl(){
        return "http://" + ip + ":" + port + "/pick";
    }
}
