package org.projectzion.game.services;

import org.projectzion.game.factories.NodeFactory;
import org.projectzion.game.persitence.entities.Node;
import org.projectzion.game.persitence.entities.OsmMatcher;
import org.projectzion.game.persitence.repositories.NodeRepository;
import org.projectzion.game.persitence.repositories.OsmMatcherRepository;
import org.projectzion.game.tos.OverpassTurboElement;
import org.projectzion.game.tos.OverpassTurboResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OsmMatcherService {

    @Autowired
    OsmMatcherRepository osmMatcherRepository;

    public Iterable<OsmMatcher> getAllOsmMatchers(){
        return osmMatcherRepository.findAll();
    }
}
