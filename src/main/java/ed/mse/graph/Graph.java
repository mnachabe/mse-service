package ed.mse.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Graph {
	
	public HashMap<String, Node> nodeLookup = new HashMap<String, Node>();
	public int edgeCount = 0;
	
	public class Edge {
		Node src; 
		Node dest; 
		double weight;
		public Edge(Node src, Node dest, double weight) {
			this.src = src; 
			this.dest = dest;
			this.weight = weight;
		}
	}
	
	public class Node {
	    String label;
	    ArrayList<Edge> edges = new ArrayList<>();
	    ArrayList<String> hashcodes = new ArrayList<>();
	    ArrayList<Node> neighbours = new ArrayList<>();
	    double latitude;
	    double longitude;
	    private Node(String label, double latitude, double longitude) {
	    	this.label = label;
	    	this.latitude = latitude;
	    	this.longitude = longitude;
	    }
	    
	    public ArrayList<Edge> getUnvisitedEdges(Set<String> visited) {
	    	ArrayList<Edge> result = new ArrayList<>();
	    	for(Edge e : edges) {
	    		if(!visited.contains(e.dest.label)) {
	    			result.add(e);
	    		}
	    	}
			return result;
	    }
	    
	    public ArrayList<Edge> getEdges() {
			return edges;
	    }
	    
	    public Edge getUnvisitedRandomEdge(Set<String> visited, Set<String> deadend) {
	    	ArrayList<Edge> result = new ArrayList<>();
	    	for(Edge e : edges) {
	    		if(!visited.contains(e.dest.label) && !deadend.contains(e.dest.label)) {
	    			result.add(e);
	    		}
	    	}
	    	Random r = new Random();
	    	int n = r.nextInt(result.size());
			return result.get(n);
	    }
	}
	
	public boolean addNode(String n, double latitude, double longitude) {
		if(nodeLookup.containsKey(n)) {
			return false;
		}
		nodeLookup.put(n, new Node(n, latitude, longitude));
		return true;
	}
	

	public void addEdge(String v1, String v2, double weight) {
		Node src = nodeLookup.get(v1);
		Node dest = nodeLookup.get(v2);
		Edge srcEdge = new Edge(src, dest, weight);
		Edge destEdge = new Edge(dest, src, weight);
		
		if(src.hashcodes.contains(v1.concat(v2)) || dest.hashcodes.contains(v2.concat(v1))) {
			return;
		}
		edgeCount++;
		src.edges.add(srcEdge);
		dest.edges.add(destEdge);
		src.neighbours.add(dest);
		dest.neighbours.add(src);
		
		src.hashcodes.add(v1.concat(v2));
		dest.hashcodes.add(v2.concat(v1));
	}
	
	public Set<String> getVertices() {
		return nodeLookup.keySet();
	}
	
	public String print() {
		StringBuffer buffer = new StringBuffer();
		Iterator<String> iterator = nodeLookup.keySet().iterator();
		while(iterator.hasNext()) {
			Node node = nodeLookup.get(iterator.next());
			buffer.append(node.label + ": ");
			ArrayList<Edge> edges = node.edges;
			for(Edge e : edges) {
				buffer.append("("+e.dest.label+","+e.weight+") ");
			}
			buffer.append("\n");
		}
		return buffer.toString();
	}
	
}
