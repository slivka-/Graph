import java.util.ArrayList;
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
        //number of connected components in graph
        int numOfComp = 0;
        
        //all vertextes in graph
        Set<Integer> vertextes = graphReference.getVertextes();
        //vertextes currently to check
        Set<Integer> vertToCheck = new HashSet<>();
        //checked vertexes
        Set<Integer> removedVerts = new HashSet<>();
        while (!vertextes.isEmpty())
        {
            //get first vertex
            Integer currV = vertextes.iterator().next();
            //add it to check set
            vertToCheck.add(currV);
            while (!vertToCheck.isEmpty())
            {
               //get first vertex to check
               Integer currToCheck = vertToCheck.iterator().next();
               //get all of its neighbouring vertextes
               ArrayList<Integer> neighbours;
               neighbours = graphReference.getNeighbouringVertexes(currToCheck);
               if (neighbours != null)
               {
                   //add all previously not checked vertextes to check
                   neighbours.removeAll(removedVerts);
                   vertToCheck.addAll(neighbours);
               }
               //add checked vertex to checked list
               removedVerts.add(currToCheck);
               //remove checked vertexes from list to check and from graph
               vertextes.remove(currToCheck);
               vertToCheck.remove(currToCheck);
            }
            //increase number of connected components in graph
            numOfComp++;
        }
        //return number of connected components in graph
        return numOfComp;
    }
}