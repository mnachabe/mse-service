package ed.mse.service.controller;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.springframework.stereotype.Service;

import ed.mse.commons.GraphRequest;
import ed.mse.commons.Logger;
import ed.mse.service.response.ResponsePath;

@Service
public class BaseService {
	
	public ResponsePath getPath(String start, String end) { 
		Logger.getLogger().log("Starting...");
		
		GraphRequest mapRequest = new GraphRequest.Builder()
				.setOutputFormat(GraphRequest.OUTPUT_FORMAT_JSON)
				.setStartLatitude(55.9505)
				.setStartLongitude(-3.1937)
				.setEndLatitude(55.9421)
				.setEndLongitude(-3.1683)
				.setFootway(true)
				.debugMode(true)
				.build();
		
		Graph<String, DefaultWeightedEdge> graph = mapRequest.execute();

//		String startNode = "4042155730", endNode = "13881929";
		Logger.getLogger().log("Finding shortest path between ".concat(start).concat(" and ").concat(end));
		GraphPath<String, DefaultWeightedEdge> findPathBetween = DijkstraShortestPath.findPathBetween(graph, start, end);
		
		List<String> vertecies = findPathBetween.getVertexList();
		ResponsePath path = new ResponsePath();
		path.setPath(vertecies);
		return path;
	}

}
