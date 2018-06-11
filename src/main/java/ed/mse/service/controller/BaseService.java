package ed.mse.service.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import ed.mse.commons.GraphRequest;
import ed.mse.commons.Logger;
import ed.mse.commons.MapNode;
import ed.mse.graph.Graph;
import ed.mse.graph.RandomWalk;

@Service
public class BaseService {
	
	public String getPath() { 
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
		
		Graph graph = mapRequest.execute();

		long start = 4042155730L, end = 13881929;
		Logger.getLogger().log("Finding shortest path between ".concat(String.valueOf(start)).concat(" and ").concat(String.valueOf(end)));
		
		ArrayList<String> walk = RandomWalk.walk(String.valueOf(start), String.valueOf(end), graph);
		
//		ArrayList<String> walk = Dijkstra.generateRandomPath(String.valueOf(start), String.valueOf(end), graph);
		
		System.out.println(Arrays.toString(walk.toArray()));
		
		List<MapNode> vertecies = RandomWalk.getDetails(walk, graph);
		/*ResponsePath path = new ResponsePath();
		path.setPath(vertecies);*/
		
		return getCoordinatesList(vertecies);
	}
	
	public String getCoordinatesList(List<MapNode> vertecies) {
		JSONObject json = new JSONObject();
		try {
			json.put("type", "FeatureCollection");
			
			JSONArray features = new JSONArray();
			JSONObject featuresList = new JSONObject();
			featuresList.put("type", "Feature");
			JSONObject properties = new JSONObject();
			properties.put("name", "Edinburgh");
			featuresList.put("properties", properties);
			JSONObject geometry = new JSONObject();
			geometry.put("type", "LineString");
			
			JSONArray coordinates = new JSONArray();
			
			for(MapNode v : vertecies) {
				JSONArray c = new JSONArray();
				c.put(v.getLongitude());
				c.put(v.getLatitude());
				coordinates.put(c);
			}
			
			geometry.put("coordinates", coordinates);
			featuresList.put("geometry", geometry);
			features.put(featuresList);
			json.put("features", features);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json.toString();
	}

}
