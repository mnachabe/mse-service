package ed.mse.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;

import ed.mse.graph.Graph.Edge;
import ed.mse.graph.Graph.Node;

public class Dijkstra {
	
	public static int calculate(String start, String end, Graph g) {
		PriorityQueue<Pair> queue = new PriorityQueue<>(new Comparator<Pair>() {
			@Override
			public int compare(Pair arg0, Pair arg1) {
				return (int) (arg0.distance-arg1.distance);
			}
		});
		
		Node node = g.nodeLookup.get(start);
		Set<String> visited = new HashSet<String>();
		int travelledDistance = 0;
		while(!node.label.equals(end)) {
			ArrayList<Edge> edges = node.getUnvisitedEdges(visited);
			for(Edge e : edges) {
				queue = search(queue, e, travelledDistance);
			}
			visited.add(node.label);
			Pair p = queue.poll();
			node = p.node;
			travelledDistance = p.distance;
		}
		
		return travelledDistance;
	}
	
	public static PriorityQueue<Pair> search(PriorityQueue<Pair> queue, Edge e, int travelledDistance) {
		Iterator<Pair> i = queue.iterator();
		Pair p = null;
		while(i.hasNext()) {
			Pair next = i.next();
			if(next.node.label.equals(e.dest.label)) {
				if(next.distance > e.weight+travelledDistance) {
					queue.remove(next);
					p = new Pair(next.node, e.src, e.weight + travelledDistance);
					queue.add(p);
					return queue;
				}
			} 
		}
		p = new Pair(e.dest, e.src, e.weight + travelledDistance);
		queue.add(p);
		return queue;
	}
}


