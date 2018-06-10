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
	
	public static ArrayList<String> walk(String start, String end, int threshold, Graph g) {

		Node node = g.nodeLookup.get(start);
		Set<String> visited = new HashSet<String>();
		ArrayList<String> result = new ArrayList<>();
		result.add(start);
		int travelledDistance = 0;
		boolean rollbackFlag;
		Stack<Node> stack = new Stack<Node>();
		
		while(!node.label.equals(end)) {
			double distance = 0;
			Edge e = null;
			int count = 0;
			visited.add(node.label);
			do {
				rollbackFlag = false;
				try {
					do {
						e = node.getUnvisitedRandomEdge(visited);
						distance = Dijkstra.calculate(e.dest.label, end, g);
						count++;
					} while((distance + travelledDistance)  > threshold && count < node.getUnvisitedEdges(visited).size()*2 || rollbackFlag);
				} catch(Exception ex) {
					rollbackFlag = true;
					String pop = stack.pop().label;
					result.remove(pop);
					visited.remove(pop);
					node= stack.peek();
					ex.printStackTrace();
				}
			} while(rollbackFlag);
			
			rollbackFlag = false;
			node = e.dest;
			travelledDistance += e.weight;
			stack.push(node);
			result.add(node.label);
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
		Iterator<String> keySet = graph.nodeLookup.keySet().iterator();
		while(keySet.hasNext()) {
			Node node = graph.nodeLookup.get(keySet.next());
			if(walk.contains(node.label)) {
				MapNode m = new MapNode(Long.parseLong(node.label), node.latitude, node.longitude);
				result.add(m);
			}
		}
		return result;
	}
	

}
