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
        return 0;
    }

}