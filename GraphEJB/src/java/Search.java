import javax.ejb.Stateless;
/**
 * @author Michał Śliwa
 */
@Stateless
public class Search implements ISearchRemote
{
    @Override
    public String Hello()
    {
        return "Hello Search!";
    }
}