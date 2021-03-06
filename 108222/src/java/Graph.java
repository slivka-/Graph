import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import javax.ejb.Stateful;
/**
 * Class representing a graph
 * @author Michał Śliwa
 */
@Stateful
public class Graph implements IGraphRemote
{
    //Hash map representing vertexes and their neighbours
    private final HashMap<Integer,ArrayList<Integer>> graph = new HashMap<>();
    
    /**
     * Adds new edge to graph
     * @param v1 number of first vertex
     * @param v2 number of second vertex
     */
    @Override
    public void addEgde(int v1, int v2)
    {
        //check if vertex exist in graph
        if (graph.containsKey(v1))
        {
            //add neighbour to vertex
            graph.get(v1).add(v2);
        }
        else
        {
            //add vertex with neighbour to graph
            graph.put(v1, new ArrayList<>(Arrays.asList(v2)));
        }
        
        //check if vertex exist in graph
        if (graph.containsKey(v2))
        {
            //add neighbour to vertex
            graph.get(v2).add(v1);
        }
        else
        {
            //add vertex with neighbour to graph
            graph.put(v2, new ArrayList<>(Arrays.asList(v1)));
        }
    }

    /**
     * Gets set of all vertextes
     * @return Set of vertex numbers
     */
    @Override
    public Set<Integer> getVertextes()
    {
        return graph.keySet();
    }

    /**
     * Gets list of all vertextes neighbouring given vertex
     * @param vertex number of vertex
     * @return list of neighbouring vertexes or null
     */
    @Override
    public ArrayList<Integer> getNeighbouringVertexes(int vertex)
    {
        if (graph.containsKey(vertex))
            return graph.get(vertex);
        else
            return null;
    }
}