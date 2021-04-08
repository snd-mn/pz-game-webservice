package org.projectzion.game.utils.setup;

import org.projectzion.game.persitence.entities.*;
import org.projectzion.game.persitence.entities.rewards.ItemReward;
import org.projectzion.game.persitence.entities.rewards.Reward;
import org.projectzion.game.persitence.repositories.*;
import org.projectzion.game.services.KeyValueService;
import org.projectzion.game.services.overpass.turbo.NodeCriteraFilter;
import org.projectzion.game.services.overpass.turbo.NodeCriteraFilterValue;
import org.projectzion.game.services.overpass.turbo.NodeCriteria;
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

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    RewardRepository rewardRepository;

    @Transactional
    private void saveSetupDone() throws Exception {
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
                nodeType.setCooldown(60);
                nodeType.setDisplayResourceType(DisplayResourceType.CHEST);

                //Item
                Item item = new Item();
                item.setTradeAble(false);
                item.setUseAble(true);
                item.setName("Friedensblume");

                //Reward
                ItemReward itemReward = new ItemReward();
                itemReward.setItem(item);
                itemReward.setAmount(3);

                //NodeTypeReward
                NodeTypeReward nodeTypeReward = new NodeTypeReward();
                nodeTypeReward.setNodeType(nodeType);
                nodeTypeReward.setReward(itemReward);

                //OsmMatcher
                OsmMatcher osmMatcher = new OsmMatcher();
                NodeCriteria nodeCriteria = new NodeCriteria();
                nodeCriteria.setOverpassTurboNodeType(OverpassTurboNodeType.NODE);
                Map<NodeCriteraFilter, NodeCriteraFilterValue> filter = new HashMap<>();
                filter.put(NodeCriteraFilter.AMENITY, NodeCriteraFilterValue.POSTBOX);
                nodeCriteria.setFilter(filter);
                osmMatcher.setNodeCriteria(nodeCriteria);

                //OsmMatcherNodeType
                OsmMatcherNodeType osmMatcherNodeType = new OsmMatcherNodeType();
                osmMatcherNodeType.setNodeType(nodeType);
                osmMatcherNodeType.setOsmMatcher(osmMatcher);
                osmMatcherNodeType.setChance(100);

                //save
                itemRepository.save(item);
                rewardRepository.save(itemReward);
                nodeTypeRepository.save(nodeType);
                osmMatcherRepository.save(osmMatcher);
                osmMatcherNodeTypeRepository.save(osmMatcherNodeType);
            }
            saveSetupDone();
        } catch (Exception e) {
            logger.error("wtf setup", e);
        }
    }


}