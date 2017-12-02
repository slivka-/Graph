import javax.ejb.Remote;
/**
 * Provides utility to search for connected components in graph
 * @author Michał Śliwa
 */
@Remote
public interface ISearchRemote
{
    /**
     * Injects dependency to remote graph object
     * @param ref 
     */
    public void setGraphReference(IGraphRemote ref);
    
    /**
     * Gets number of connected components in graph
     * @return number of connected components in graph
     */
    public int getNumOfConnectedComponents();
}