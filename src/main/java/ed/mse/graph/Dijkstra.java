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
	
	public static double calculate(String start, String end, Graph g) {
		PriorityQueue<Pair> queue = new PriorityQueue<>(new Comparator<Pair>() {
			@Override
			public int compare(Pair arg0, Pair arg1) {
				return (int) (arg0.distance-arg1.distance);
			}
		});
		
		Node node = g.nodeLookup.get(start);
		Set<String> visited = new HashSet<String>();
		double travelledDistance = 0;
		Pair p = null;
		ArrayList<Pair> thrownPairs = new ArrayList<>();
		while(!node.label.equals(end)) {
			ArrayList<Edge> edges = node.getUnvisitedEdges(visited);
			for(Edge e : edges) {
				queue = search(queue, e, travelledDistance);
			}
			visited.add(node.label);
			p = queue.poll();
			thrownPairs.add(p);
			node = p.node;
			travelledDistance = p.distance;
		}
		
		return travelledDistance;
	}
	
	public static ArrayList<String> generatePath(String start, String end, Graph g) {
		PriorityQueue<Pair> queue = new PriorityQueue<>(new Comparator<Pair>() {
			@Override
			public int compare(Pair arg0, Pair arg1) {
				return (int) (arg0.distance-arg1.distance);
			}
		});
		
		Node node = g.nodeLookup.get(start);
		Set<String> visited = new HashSet<String>();
		double travelledDistance = 0;
		Pair p = null;
		ArrayList<Pair> thrownPairs = new ArrayList<>();
		while(!node.label.equals(end)) {
			ArrayList<Edge> edges = node.getUnvisitedEdges(visited);
			for(Edge e : edges) {
				queue = search(queue, e, travelledDistance);
			}
			visited.add(node.label);
			p = queue.poll();
			thrownPairs.add(p);
			node = p.node;
			travelledDistance = p.distance;
		}
		p = findPair(thrownPairs, end);
		ArrayList<String> result = new ArrayList<>();
		result.add(p.node.label);
		while(!p.via.label.equals(start)) {
			result.add(p.via.label);
			p = findPair(thrownPairs, p.via.label);
		}
		result.add(p.via.label);
		
		return result;
	}
	
	
	public static PriorityQueue<Pair> search(PriorityQueue<Pair> queue, Edge e, double travelledDistance) {
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
	
	public static Pair findPair(ArrayList<Pair> thrownPairs, String n) {
		Iterator<Pair> i = thrownPairs.iterator();
		Pair p = null;
		while(i.hasNext()) {
			Pair next = i.next();
			if(next.node.label.equals(n)) {
				p = next;
			}
		}
		return p;
	}
}


