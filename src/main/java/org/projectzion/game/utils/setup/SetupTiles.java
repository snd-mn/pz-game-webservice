package org.projectzion.game.utils.setup;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.projectzion.game.services.KeyValueService;
import org.projectzion.game.configs.SpatialConfig;
import org.projectzion.game.services.SpatialConstantsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Component
public class SetupTiles implements ApplicationListener<ContextRefreshedEvent> {
    Logger logger = LoggerFactory.getLogger(SetupTiles.class);

    public static final String KV_TILE_SETUP_WIDTH = "KV_TILE_SETUP_WIDTH";
    public static final String KV_TILE_SETUP_HEIGHT = "KV_TILE_SETUP_HEIGHT";

    @Autowired
    KeyValueService keyValueService;

    @Autowired
    SpatialConfig spatialConfig;

    @Autowired
    SpatialConstantsService spatialConstantsService;

    @Transactional
    protected void saveSpatialConstants(double tileLon, double tileLat) throws Exception {
        keyValueService.save(KV_TILE_SETUP_WIDTH, tileLon);
        keyValueService.save(KV_TILE_SETUP_HEIGHT, tileLat);
    }

    private boolean isSetupDone() throws Exception {
        return keyValueService.read(KV_TILE_SETUP_WIDTH, Double.class) != null && keyValueService.read(KV_TILE_SETUP_HEIGHT, Double.class) != null;
    }

    private void setSpatialConstants() throws IOException {
        spatialConstantsService.setTileSizeX((Double) keyValueService.read(KV_TILE_SETUP_WIDTH, Double.class));
        spatialConstantsService.setTileSizeY((Double) keyValueService.read(KV_TILE_SETUP_HEIGHT, Double.class));
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            if (isSetupDone()) {
                setSpatialConstants();
                return;
            }
            logger.info("tiles x:" + spatialConfig.tileLon + " * y:" + spatialConfig.tileLat);
            saveSpatialConstants(spatialConfig.tileLon, spatialConfig.tileLat);
            setSpatialConstants();
            //TODO create 300.333 tiles on startup? or create em on demand
            logger.error("x:" + spatialConstantsService.getTileSizeX());
            logger.error("y:" + spatialConstantsService.getTileSizeY());
        } catch (Exception e) {
            logger.error("wtf setup", e);
        }
    }


}