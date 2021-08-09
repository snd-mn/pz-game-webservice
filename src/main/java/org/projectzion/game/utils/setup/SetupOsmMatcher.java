package org.projectzion.game.utils.setup;

import org.projectzion.game.persitence.entities.*;
import org.projectzion.game.persitence.repositories.*;
import org.projectzion.game.services.KeyValueService;
import org.projectzion.game.services.overpass.turbo.NodeCriteraFilter;
import org.projectzion.game.services.overpass.turbo.NodeCriteraFilterValue;
import org.projectzion.game.utils.DisplayResourceType;
import org.projectzion.game.utils.OverpassTurboNodeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Component
public class SetupOsmMatcher implements ApplicationListener<ContextRefreshedEvent> {
    Logger logger = LoggerFactory.getLogger(SetupOsmMatcher.class);

    public static final String OSM_MATCHER_SETUP = "OSM_MATCHER_SETUP";

    @Autowired
    KeyValueService keyValueService;

    @Autowired
    OsmMatcherRepository osmMatcherRepository;

    @Autowired
    OsmMatcherNodeTypeRepository osmMatcherNodeTypeRepository;

    @Autowired
    NodeTypeRepository nodeTypeRepository;

    @Transactional
    protected void saveSetupDone() throws Exception {
        keyValueService.save(OSM_MATCHER_SETUP, OSM_MATCHER_SETUP);
    }

    private boolean isSetupDone() throws Exception {
        return keyValueService.read(OSM_MATCHER_SETUP, String.class) != null;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            if (isSetupDone()) {
                return;
            }

            //PostBox 100% -->
            {
                //NodeType
                NodeType nodeType = new NodeType();
                nodeType.setId(1L);
                nodeType.setCooldown(60);
                nodeType.setDisplayResourceType(DisplayResourceType.CHEST);
                nodeType = nodeTypeRepository.save(nodeType);

                //OsmMatcher
                OsmMatcher osmMatcher = new OsmMatcher();
                osmMatcher.setOverpassTurboNodeType(OverpassTurboNodeType.NODE);
                Map<NodeCriteraFilter, NodeCriteraFilterValue> filter = new HashMap<>();
                filter.put(NodeCriteraFilter.AMENITY, NodeCriteraFilterValue.POST_BOX);
                osmMatcher.setFilter(filter);
                osmMatcher = osmMatcherRepository.save(osmMatcher);

                //OsmMatcherNodeType
                OsmMatcherNodeType osmMatcherNodeType = new OsmMatcherNodeType();
                osmMatcherNodeType.setNodeType(nodeType);
                osmMatcherNodeType.setOsmMatcher(osmMatcher);
                osmMatcherNodeType.setChance(100);
                osmMatcherNodeType = osmMatcherNodeTypeRepository.save(osmMatcherNodeType);
            }
            saveSetupDone();
        } catch (Exception e) {
            logger.error("wtf setup", e);
        }
    }


}