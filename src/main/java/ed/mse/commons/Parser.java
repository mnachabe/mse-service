package ed.mse.commons;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * @author Mohamad Nachabe
 *
 */
public abstract class Parser {
	
	public abstract Graph<String, DefaultWeightedEdge> parse(String result);

}
