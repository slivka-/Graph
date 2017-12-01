import javax.ejb.Remote;
/**
 * @author Michał Śliwa
 */
@Remote
public interface IGraphRemote
{
    public String Hello();
}