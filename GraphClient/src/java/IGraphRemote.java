import java.util.ArrayList;
import java.util.Set;
import javax.ejb.Remote;
/**
 * Represents a graph
 * @author Michał Śliwa
 */
@Remote
public interface IGraphRemote
{
    /**
     * Adds new edge to graph
     * @param from number of first vertex
     * @param to number of second vertex
     */
    public void addEgde(int from, int to);
    
    /**
     * Gets set of all vertextes 
     * @return Set of vertex numbers
     */
    public Set<Integer> getVertextes();
    
    /**
     * Gets list of all vertextes neighbouring given vertex
     * @param vertex number of vertex
     * @return list of neighbouring vertexes or null
     */
    public ArrayList<Integer> getNeighbouringVertexes(int vertex);
}