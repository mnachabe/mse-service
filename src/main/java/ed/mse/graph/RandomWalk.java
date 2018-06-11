package ed.mse.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import ed.mse.commons.MapNode;
import ed.mse.graph.Graph.Edge;
import ed.mse.graph.Graph.Node;

public class RandomWalk {
	
	public static ArrayList<String> walk(String start, String end, Graph g) {

		Node node = g.nodeLookup.get(start);
		Set<String> visited = new HashSet<String>();
		Set<String> deadend = new HashSet<String>();
		ArrayList<String> result = new ArrayList<>();
		result.add(start);
		int travelledDistance = 0;
		boolean rollbackFlag = false;
		Stack<Node> rollbackStack = new Stack<Node>();
		
		int threshold = (int) (5 * Dijkstra.calculate(start, end, g));
		
		while(!node.label.equals(end)) {
			double distance = 0.0;
			Edge e = null;
			int count = 0;
			visited.add(node.label);
			do {
				try {
					do {
						rollbackFlag = false;
						e = node.getUnvisitedRandomEdge(visited, deadend);
						distance = Dijkstra.calculate(e.dest.label, end, g);
						count++;
					} while((distance + travelledDistance)  > threshold && count < node.getUnvisitedEdges(visited).size()*4);
				} catch(Exception ex) {
					ex.printStackTrace();
					rollbackFlag = true;
					if(!rollbackStack.isEmpty()) {
						Node pop2 = rollbackStack.pop();
						String pop = pop2.label;
						result.remove(pop);
						visited.remove(pop);
						deadend.add(node.label);
						
						if(!rollbackStack.isEmpty()) {
							node= rollbackStack.peek();
						} else {
							node = g.nodeLookup.get(start);
						}
					} else {
						break;
					}
				}
			} while(rollbackFlag);
			
			rollbackFlag = false;
			node = e.dest;
			rollbackStack.push(node);
			result.add(node.label);
			travelledDistance += e.weight;
			System.out.println(node.label);
		}
		
		for(String s : result) {
			System.out.print(s+" ");
		}
		System.out.println();
		
		return result;
	}

	public static List<MapNode> getDetails(ArrayList<String> walk, Graph graph) {
		ArrayList<MapNode> result = new ArrayList<>();
		for(String s : walk) {
			Iterator<String> keySet = graph.nodeLookup.keySet().iterator();
			while(keySet.hasNext()) {
				Node node = graph.nodeLookup.get(keySet.next());
				if(s.equals(node.label)) {
					MapNode m = new MapNode(Long.parseLong(node.label), node.latitude, node.longitude);
					result.add(m);
				}
			}
		}
		return result;
	}
	

}
