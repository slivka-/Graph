import javax.ejb.Remote;
/**
 * @author Michał Śliwa
 */
@Remote
public interface ISearchRemote
{
    public void setGraphReference(IGraphRemote ref);
    public int getNumOfConnectedComponents();
}