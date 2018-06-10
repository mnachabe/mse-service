package ed.mse.graph;

import ed.mse.graph.Graph.Node;

public class Pair {
	Node node; 
	Node via; 
	int distance; 
	public Pair(Node node, Node via, int distance) {
		this.node = node;
		this.via = via; 
		this.distance = distance;
	}
}