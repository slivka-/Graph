import java.util.ArrayList;
import javax.ejb.Remote;
/**
 * @author Michał Śliwa
 */
@Remote
public interface IGraphRemote
{
    public void addEgde(int from, int to);
    public ArrayList<Integer> getVertextes();
    public ArrayList<Integer> getNeighbouringVertexes(int vertex);
}