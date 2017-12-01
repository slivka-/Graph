import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javax.ejb.Stateful;
/**
 * @author Michał Śliwa
 */
@Stateful
public class Graph implements IGraphRemote
{
    private final HashMap<Integer,ArrayList<Integer>> graph = new HashMap<>();
    
    @Override
    public void addEgde(int v1, int v2)
    {
        if(graph.containsKey(v1))
            graph.get(v1).add(v2);
        else
            graph.put(v1, new ArrayList<>(Arrays.asList(v2)));
        
        if(graph.containsKey(v2))
            graph.get(v2).add(v1);
        else
            graph.put(v2, new ArrayList<>(Arrays.asList(v1)));
    }

    @Override
    public ArrayList<Integer> getVertextes()
    {
        return new ArrayList<>(graph.keySet());
    }

    @Override
    public ArrayList<Integer> getNeighbouringVertexes(int vertex)
    {
        return graph.get(vertex);
    }
}