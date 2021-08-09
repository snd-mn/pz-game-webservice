package org.projectzion.game.services;

import org.projectzion.game.factories.NodeFactory;
import org.projectzion.game.persitence.entities.CollectedNode;
import org.projectzion.game.persitence.entities.Node;
import org.projectzion.game.persitence.entities.security.User;
import org.projectzion.game.persitence.repositories.CollectedNodesRepository;
import org.projectzion.game.persitence.repositories.NodeRepository;
import org.projectzion.game.scoped.request.RequestScoped;
import org.projectzion.game.tos.OverpassTurboElement;
import org.projectzion.game.tos.OverpassTurboResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CollectedNodeService {

    @Autowired
    NodeRepository nodeRepository;

    @Autowired
    CollectedNodesRepository collectedNodesRepository;

    @Autowired
    RequestScoped requestScoped;

    public boolean isUserAbleToInteractWithNode(Long nodeId){
        boolean ret = false;
        User user = requestScoped.currentUserPrincipal().getUser();

        CollectedNode collectedNode = collectedNodesRepository.findNodeByNodeIdForUser(nodeId, user.getId());
        if(collectedNode == null || (collectedNode != null && collectedNode.getNextAvailability() <= new Date().getTime())){
            ret = true;
        }

        return ret;
    }


    public void interactWithNode(long nodeId) {
        User user = requestScoped.currentUserPrincipal().getUser();

        Optional<Node> node = nodeRepository.findById(nodeId);
        CollectedNode collectedNode = collectedNodesRepository.findNodeByNodeIdForUser(nodeId, user.getId());

        if(collectedNode == null){
            collectedNode = new CollectedNode();
            collectedNode.setNode(node.get());
            collectedNode.setUser(user);
        }

        collectedNode.setNextAvailability(node.get().getRespawnTime() + new Date().getTime());

        collectedNodesRepository.save(collectedNode);
    }
}
