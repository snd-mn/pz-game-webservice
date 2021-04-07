package org.projectzion.game.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Data
@Configuration
@ConfigurationProperties(prefix = "pz.overpass.turbo")
public class OverpassTurboConfig {

    private String interpreterUrl;
}
