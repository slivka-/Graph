import javax.ejb.Stateless;
/**
 * @author Michał Śliwa
 */
@Stateless
public class Graph implements IGraphRemote
{
    @Override
    public String Hello()
    {
        return "Hello Graph!";
    }
}