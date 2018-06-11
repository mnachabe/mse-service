package ed.mse.graph;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MinHeap extends PriorityQueue<Pair> implements Comparator<Pair> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public int compare(Pair arg0, Pair arg1) {
		return (int) (arg0.distance-arg1.distance);
	}

}
