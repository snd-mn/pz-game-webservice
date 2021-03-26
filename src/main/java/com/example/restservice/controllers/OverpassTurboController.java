package com.example.restservice.controllers;

import com.example.restservice.services.OverpassTurboService;
import com.example.restservice.tos.OverpassTurboResult;
import com.example.restservice.utils.Gps;
import com.example.restservice.utils.OverpassTurboNode;
import com.example.restservice.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class OverpassTurboController {

	@Autowired
	OverpassTurboService overpassTurboService;

	@GetMapping("/nodes")
	public OverpassTurboResult getNodesByGps(@RequestBody Gps gps) throws IOException {
		OverpassTurboResult overpassTurboNodes = overpassTurboService.getNodesByGps(OverpassTurboService.query_drinkingwater_and_postbox, gps, new BigDecimal(2000));
		return overpassTurboNodes;
	}

	@GetMapping("/nodes/dummy")
	public List<OverpassTurboNode> getDummyNodesByGps(@RequestBody Gps gps) {
		List<OverpassTurboNode> nodes = new ArrayList<>();
		nodes.add(dummyNode());
		nodes.add(dummyNode());

		return nodes;
	}

	@PatchMapping("/node/{id}")
	public OverpassTurboNode patch(
			@PathVariable(value="id") long id,
			@RequestBody OverpassTurboNode node
					    ) {
		System.out.println(id);
		System.out.println(node);
		return node;
	}

	private OverpassTurboNode dummyNode(){
		OverpassTurboNode node = new OverpassTurboNode();
		node.setId((long) (Math.random()*100));

		node.getGps().setLat(new BigDecimal(Math.random()*10));
		node.getGps().setLon(new BigDecimal(Math.random()*10));

		int flags = node.getFlags();
		flags |= 1 << Constants.NODE_FLAG_USABLE;
		flags |= 1 << Constants.NODE_FLAG_VISIBLE;
		node.setFlags(flags);



		node.getAttributes().put(Math.random(), Math.random());
		node.getAttributes().put(Math.random(), Math.random());
		//for useableAt nodes
		node.getAttributes().put("useableAt", new Date().getTime() - (Math.random()*10));
		return node;
	}

}
