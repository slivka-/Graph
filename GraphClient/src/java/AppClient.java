import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
    
    /**
     * @param args 
     */
    public static void main(String[] args)
    {
        String inputFilePath = null;
        for (String s : args)
            if (s.endsWith(".txt"))
                inputFilePath = s;
        
        if (inputFilePath!=null)
        {
            ArrayList<String> inputFileValues = new ArrayList<>();
            int[] vertextes = null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFilePath))))
            {
                String line;
                while( (line = reader.readLine()) != null)
                {
                    String[] parts = line.split("\\ ");
                    inputFileValues.addAll(Arrays.asList(parts));
                }
            }
            catch (IOException ex)
            {
                System.out.println("Read Error!");
                System.out.println(ex);
            }
            if (!inputFileValues.isEmpty())
            {
                int vSize = inputFileValues.size();
                vertextes = new int[vSize];
                for (int i=0; i<vSize; i++)
                {
                    if (tryParseInt(inputFileValues.get(i)))
                        vertextes[i] = Integer.parseInt(inputFileValues.get(i));
                }
            }
            if (vertextes != null)
            {
                createGraph(vertextes);
            }
        }
        else
        {
            System.out.println("No input file!");
        }
    }
    
    private static void createGraph(int[] vertexes)
    {
        for (int i = 0; i<vertexes.length;i+=2)
            graphRemote.addEgde(vertexes[i], vertexes[i+1]);
    }
    
    private static Boolean tryParseInt(String input)
    {
        try
        {
            Integer.parseInt(input);
            return true;
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
    }
}