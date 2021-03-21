package com.example.restservice;

import com.example.restservice.tos.Gps;
import com.example.restservice.tos.Node;
import com.example.restservice.utils.Constants;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class NodesController {


	@GetMapping("/nodes")
	public List<Node> getNodesByGps(@RequestBody Gps gps) {
		List<Node> nodes = new ArrayList<>();
		nodes.add(dummyNode());
		nodes.add(dummyNode());

		return nodes;
	}

	@PatchMapping("/node/{id}")
	public Node patch(
			@PathVariable(value="id") long id,
			@RequestBody Node node
					    ) {
		System.out.println(id);
		System.out.println(node);
		return node;
	}

	private Node dummyNode(){
		Node node = new Node();
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
