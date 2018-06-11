package ed.mse.graph;

import java.util.Set;

public class Tester {
	
	public static void main(String[] args) {
		ed.mse.graph.Graph g = new ed.mse.graph.Graph();
		
//		g.addNode("S", 0, 0);
//		g.addNode("A", 0, 0);
//		g.addNode("B", 0, 0);
//		g.addNode("C", 0, 0);
//		g.addNode("D", 0, 0);
//		g.addNode("E", 0, 0);
//		g.addNode("F", 0, 0);
//		g.addNode("G", 0, 0);
//		g.addNode("H", 0, 0);
//		g.addNode("I", 0, 0);
//		g.addNode("J", 0, 0);
//		g.addNode("K", 0, 0);
//		g.addNode("L", 0, 0);
//		
//		g.addEdge("S", "A", 7/100000);
//		g.addEdge("S", "B", 2/100000);
//		g.addEdge("S", "C", 3/100000);
//		g.addEdge("A", "B", 3/100000);
//		g.addEdge("A", "D", 4/100000);
//		g.addEdge("B", "D", 4/100000);
//		g.addEdge("B", "H", 1/100000);
//		g.addEdge("D", "F", 5/100000);
//		g.addEdge("F", "H", 3/100000);
//		g.addEdge("H", "G", 2/100000);
//		g.addEdge("G", "E", 2/100000);
//		g.addEdge("E", "K", 5/100000);
//		g.addEdge("C", "L", 2/100000);
//		g.addEdge("L", "I", 4/100000);
//		g.addEdge("L", "J", 4/100000);
//		g.addEdge("I", "J", 6/100000);
//		g.addEdge("I", "K", 4/100000);
//		g.addEdge("J", "K", 4/100000);
		
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
		g.addNode("M", 0, 0);
		g.addNode("N", 0, 0);
		g.addNode("O", 0, 0);
		
		g.addEdge("A", "B", 1);
		g.addEdge("B", "C", 1);
		g.addEdge("C", "D", 1);
		g.addEdge("D", "E", 1);
		g.addEdge("E", "F", 1);
		g.addEdge("F", "G", 1);
		g.addEdge("A", "H", 1);
		g.addEdge("H", "I", 1);
		g.addEdge("I", "J", 1);
		g.addEdge("J", "K", 1);
		g.addEdge("K", "L", 1);
		g.addEdge("L", "M", 1);
		g.addEdge("M", "N", 1);
		g.addEdge("N", "O", 1);
		g.addEdge("D", "L", 1);
		
		g.print();
		
		String start = "A", end = "O";
		
//		Dijkstra.generatePath(start, end, g);
		RandomWalk.walk(start, end, g);
		
	}
	
	static void printarray(Set<String> visited) {
		System.out.print("visited: ");
		for(String s : visited) {
			System.out.print(s + " ");
		}
		System.out.println();
	}
}


