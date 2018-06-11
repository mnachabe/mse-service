package ed.mse.commons;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.json.JSONArray;
import org.json.JSONObject;

import ed.mse.graph.Graph;

/**
 * @author Mohamad Nachabe
 *
 */
public class JSONParser extends Parser {
	private HashMap<Long, MapNode> hash = new HashMap<Long, MapNode>(); 

	public Graph parse(String result) {
		Graph g = new Graph();
		org.jgrapht.Graph<MapNode, DefaultWeightedEdge> gra = new SimpleWeightedGraph<MapNode, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Logger.getLogger().log("Generating Graph");
		try {
			JSONObject root = new JSONObject(result);
			JSONArray elements = root.getJSONArray("elements");
			
			for(int i=0; i < elements.length(); i++) {
				JSONObject o = elements.getJSONObject(i);
				if(o.getString("type").equals("node")) {
					MapNode node = null;
					double longitude = o.getDouble("lon");
					double latitude = o.getDouble("lat");
					long id = o.getLong("id");
					node = new MapNode(id, latitude, longitude);
					hash.put(node.getId(), node);
				} else if(o.getString("type").equals("way")) {
					JSONArray nodes = o.getJSONArray("nodes");
					Long lastNodeId = nodes.getLong(0);
					MapNode mapNode = hash.get(lastNodeId);
					g.addNode(String.valueOf(mapNode.getId()), mapNode.getLatitude(), mapNode.getLongitude());
					gra.addVertex(hash.get(lastNodeId));
					for(int j=1; j < nodes.length(); j++) {
						Long nodeId = nodes.getLong(j);
						gra.addVertex(hash.get(nodeId));
						MapNode m = hash.get(nodeId);
						g.addNode(String.valueOf(m.getId()), m.getLatitude(), m.getLongitude());
						MapNode node1 = hash.get(nodeId), node2 = hash.get(lastNodeId);
						DefaultWeightedEdge e = gra.addEdge(node1, node2);
						if(e == null) {
							continue;
						}
						double distance = MathUtils.haversine(node1.getLatitude(), node1.getLongitude(), node2.getLatitude(), node2.getLongitude());
						gra.setEdgeWeight(e, distance);
						g.addEdge(String.valueOf(node1.getId()), String.valueOf(node2.getId()), distance*10000000000L);
						lastNodeId = nodeId;
					}
				}
			}
			
			Logger.getLogger().log("Graph generated successfully");
	
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	
	    FileWriter fileWriter;
		try {
			Logger.getLogger().log("Creating xml file from response. dir=\"".concat(System.getProperty("user.home")).concat("\\OneDrive\\Desktop\\debug\\reponse.osm\""));
			fileWriter = new FileWriter(System.getProperty("user.home").concat("\\OneDrive\\Desktop\\debug\\graph.txt"));
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.print(g.print());
			printWriter.close();
			Logger.getLogger().log("File created successfully");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return g;
	}

	public HashMap<Long, MapNode> getHash() {
		return hash;
	}	
	
}
