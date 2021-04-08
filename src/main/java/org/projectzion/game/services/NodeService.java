package org.projectzion.game.services;

import org.projectzion.game.factories.NodeFactory;
import org.projectzion.game.persitence.entities.Node;
import org.projectzion.game.persitence.repositories.NodeRepository;
import org.projectzion.game.tos.OverpassTurboElement;
import org.projectzion.game.tos.OverpassTurboResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NodeService {

    @Autowired
    NodeRepository nodeRepository;

    @Autowired
    NodeFactory nodeFactory;

    public Collection<Node> createNodesFromOverpassTurboResult(OverpassTurboResult turboResult) throws Exception {
        List<Node> nodes = new ArrayList<>();
        List<Long> idList = turboResult.getElements().stream().map(OverpassTurboElement::getId).collect(Collectors.toList());
        List<Node> allreadyExistingNodes = nodeRepository.findAllByOsmIds(idList);
        List<OverpassTurboElement> deltaTurboList =
                turboResult
                        .getElements()
                        .stream()
                        .filter(
                                element ->
                                        allreadyExistingNodes
                                                .stream()
                                                .filter(
                                                        existing_element ->
                                                                existing_element.getOsmId() == element.getId()
                                                )
                                                .collect(Collectors.toList()).size() == 0
                        )
                        .collect(Collectors.toList());

        Collection<Node> nodesToCreate = nodeFactory.fromOverpassTurboResult(deltaTurboList);
        Collection<Node> createdNodes = (Collection<Node>) nodeRepository.saveAll(nodesToCreate);
        nodes.addAll(allreadyExistingNodes);
        nodes.addAll(createdNodes);
        return nodes;
    }

    public void saveNodes(List<Node> nodes) {
        nodeRepository.saveAll(nodes);
    }
}
