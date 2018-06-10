package ed.mse.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import ed.mse.graph.Graph.Edge;
import ed.mse.graph.Graph.Node;

public class RandomWalk {
	
	public static void walk(String start, String end, int threshold, Graph g) {

		Node node = g.nodeLookup.get(start);
		Set<String> visited = new HashSet<String>();
		ArrayList<String> result = new ArrayList<>();
		result.add(start);
		int travelledDistance = 0;
		try {
		while(!node.label.equals(end)) {
			visited.add(node.label);
			int distance = 0;
			Edge e;
			int count = 0;
			do {
				e = node.getUnvisitedRandomEdge(visited);
				distance = Dijkstra.calculate(e.dest.label, end, g);
				count++;
			} while((distance + travelledDistance)  > threshold && count < node.getUnvisitedEdges(visited).size());
			System.out.print("distance: " + distance + " ");
			System.out.println("travelled distance: " + travelledDistance + " ");

			node = e.dest;
			travelledDistance += e.weight;
			result.add(node.label);
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		for(String s : result) {
			System.out.print(s + " ");
		}
	}
	

}
