package ed.mse.graph;

import java.util.Set;

public class Tester {
	
	public static void main(String[] args) {
		ed.mse.graph.Graph g = new ed.mse.graph.Graph();
		
		g.addNode("S", 0, 0);
		g.addNode("A", 0, 0);
		g.addNode("B", 0, 0);
		g.addNode("C", 0, 0);
		g.addNode("D", 0, 0);
		g.addNode("E", 0, 0);
		g.addNode("F", 0, 0);
		g.addNode("G", 0, 0);
		g.addNode("H", 0, 0);
		g.addNode("I", 0, 0);
		g.addNode("J", 0, 0);
		g.addNode("K", 0, 0);
		g.addNode("L", 0, 0);
		
		g.addEdge("S", "A", 7);
		g.addEdge("S", "B", 2);
		g.addEdge("S", "C", 3);
		g.addEdge("A", "B", 3);
		g.addEdge("A", "D", 4);
		g.addEdge("B", "D", 4);
		g.addEdge("B", "H", 1);
		g.addEdge("D", "F", 5);
		g.addEdge("F", "H", 3);
		g.addEdge("H", "G", 2);
		g.addEdge("G", "E", 2);
		g.addEdge("E", "K", 5);
		g.addEdge("C", "L", 2);
		g.addEdge("L", "I", 4);
		g.addEdge("L", "J", 4);
		g.addEdge("I", "J", 6);
		g.addEdge("I", "K", 4);
		g.addEdge("J", "K", 4);
		
		g.print();
		
		String start = "S", end = "E";
		int threshold = 25;
		
//		Dijkstra.calculate(start, end, g);
//		RandomWalk.walk(start, end, threshold, g);
		
	}
	
	static void printarray(Set<String> visited) {
		System.out.print("visited: ");
		for(String s : visited) {
			System.out.print(s + " ");
		}
		System.out.println();
	}
}


