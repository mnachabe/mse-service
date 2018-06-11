package ed.mse.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;

import ed.mse.graph.Graph.Edge;
import ed.mse.graph.Graph.Node;

public class Dijkstra {
	
	public static double calculate(String start, String end, Graph g) {

		PriorityQueue<Pair> queue = new PriorityQueue<Pair>(new Comparator<Pair>() {

			@Override
			public int compare(Pair arg0, Pair arg1) {
				return (int) (arg0.distance-arg1.distance);
			}
		});
		
		Node node = g.nodeLookup.get(start);
		Set<String> visited = new HashSet<String>();
		Pair p = new Pair(node, null, 0);
		visited.add(p.node.label);
		ArrayList<Pair> thrownPairs = new ArrayList<>();
		
		while(!node.label.equals(end)) {
			ArrayList<Edge> edges = node.getUnvisitedEdges(visited);
			for(Edge e : edges) {
				queue = update(queue, e, p);
			}
			p = queue.poll();
			visited.add(p.node.label);
			thrownPairs.add(p);
			node = p.node;
		}
		
		return p.distance;
	}
	
	public static ArrayList<String> generateRandomPath(String start, String end, Graph g) {
		
		PriorityQueue<Pair> queue = new PriorityQueue<Pair>(new Comparator<Pair>() {

			@Override
			public int compare(Pair arg0, Pair arg1) {
				return (int) (arg0.distance-arg1.distance);
			}
		});
		
		Node node = g.nodeLookup.get(start);
		Set<String> visited = new HashSet<String>();
		Pair p = new Pair(node, null, 0);
		visited.add(p.node.label);
		ArrayList<Pair> thrownPairs = new ArrayList<>();
		
		while(!node.label.equals(end)) {
			ArrayList<Edge> edges = node.getUnvisitedEdges(visited);
			for(Edge e : edges) {
				queue = updateRandom(queue, e, p);
			}
			p = queue.poll();
			visited.add(p.node.label);
			thrownPairs.add(p);
			node = p.node;
		}
		p = findPair(thrownPairs, end);
		ArrayList<String> result = new ArrayList<>();
		result.add(p.node.label);
		while(!p.via.label.equals(start)) {
			result.add(p.via.label);
			p = findPair(thrownPairs, p.via.label);
		}
		result.add(p.via.label);
		System.out.println(Arrays.toString(result.toArray()));
		return result;
	}
	
	public static ArrayList<String> generatePath(String start, String end, Graph g) {

		PriorityQueue<Pair> queue = new PriorityQueue<Pair>(new Comparator<Pair>() {

			@Override
			public int compare(Pair arg0, Pair arg1) {
				return (int) (arg0.distance-arg1.distance);
			}
		});
		
		Node node = g.nodeLookup.get(start);
		Set<String> visited = new HashSet<String>();
		Pair p = new Pair(node, null, 0);
		visited.add(p.node.label);
		ArrayList<Pair> thrownPairs = new ArrayList<>();
		
		while(!node.label.equals(end)) {
			ArrayList<Edge> edges = node.getUnvisitedEdges(visited);
			for(Edge e : edges) {
				queue = update(queue, e, p);
			}
			p = queue.poll();
			visited.add(p.node.label);
			thrownPairs.add(p);
			node = p.node;
		}
		p = findPair(thrownPairs, end);
		ArrayList<String> result = new ArrayList<>();
		result.add(p.node.label);
		while(!p.via.label.equals(start)) {
			result.add(p.via.label);
			p = findPair(thrownPairs, p.via.label);
		}
		result.add(p.via.label);
		System.out.println(Arrays.toString(result.toArray()));
		return result;
	}
	
	
	public static PriorityQueue<Pair> update(PriorityQueue<Pair> queue, Edge e, Pair sourcePair) {
		Iterator<Pair> i = queue.iterator();
		Pair p = null;
		while(i.hasNext()) {
			Pair currentPair = i.next();
			if(currentPair.node.label.equals(e.dest.label)) {
				if(currentPair.distance > e.weight+sourcePair.distance) {
					queue.remove(currentPair);
					p = new Pair(e.dest, e.src, (e.weight+sourcePair.distance));
					queue.add(p);
				}
				return queue;
			} 
		}
		p = new Pair(e.dest, e.src,  (e.weight+sourcePair.distance));
		queue.add(p);
		return queue;
	}
	
	public static PriorityQueue<Pair> updateRandom(PriorityQueue<Pair> queue, Edge e, Pair sourcePair) {
		Iterator<Pair> i = queue.iterator();
		Pair p = null;
		while(i.hasNext()) {
			Pair currentPair = i.next();
			if(currentPair.node.label.equals(e.dest.label)) {
				if(currentPair.distance > e.weight+sourcePair.distance) {
					queue.remove(currentPair);
					p = new Pair(e.dest, e.src, Math.random()*(e.weight+sourcePair.distance));
					queue.add(p);
				}
				return queue;
			} 
		}
		p = new Pair(e.dest, e.src,  Math.random()*(e.weight+sourcePair.distance));
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


