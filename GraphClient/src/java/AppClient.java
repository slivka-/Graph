import javax.ejb.EJB;
/**
 * @author Michał Śliwa
 */
public class AppClient
{
    @EJB
    private static IGraphRemote graphRemote;
    @EJB
    private static ISearchRemote searchRemote;
    
    public static void main(String[] args)
    {
        System.out.println("Hello Main!");
        System.out.println(graphRemote.Hello());
        System.out.println(searchRemote.Hello());
    }
}