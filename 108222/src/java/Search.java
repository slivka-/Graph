import java.util.HashSet;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateful;
/**
 * Class providing utility to search for connected components in graph 
 * @author Michał Śliwa
 */
@Stateful
public class Search implements ISearchRemote
{
    //injected reference to graph bean
    @EJB
    private IGraphRemote graphReference = null;
    
    /**
     * Injects dependency to remote graph object
     * @param ref 
     */
    @Override
    public void setGraphReference(IGraphRemote ref)
    {
        this.graphReference = ref;
    }

    /**
     * Gets number of connected components in graph
     * @return number of connected components in graph
     */
    @Override
    public int getNumOfConnectedComponents()
    {
        
        int numOfComp = 0;
        
        Set<Integer> vertextes = graphReference.getVertextes();
        Set<Integer> vertToCheck = new HashSet<>();
        /*
        while(!vertextes.isEmpty())
        {
            Integer currV = vertextes.iterator().next();
            vertToCheck.add(currV);
            while(!vertToCheck.isEmpty())
            {
               Integer currToCheck = vertToCheck.iterator().next();
               vertToCheck.addAll(graphReference.getNeighbouringVertexes(currToCheck));
               vertextes.remove(currToCheck);
               vertToCheck.remove(currToCheck);
            }
            numOfComp++;
        } 
        */
        return numOfComp;
    }
}