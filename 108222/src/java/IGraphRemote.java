import java.util.ArrayList;
import java.util.Set;
import javax.ejb.Remote;
/**
 * @author Michał Śliwa
 */
@Remote
public interface IGraphRemote
{
    public void addEgde(int from, int to);
    public Set<Integer> getVertextes();
    public ArrayList<Integer> getNeighbouringVertexes(int vertex);
}