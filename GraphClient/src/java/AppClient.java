import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import javax.naming.InitialContext;
import javax.naming.NamingException;
/**
 * @author Michał Śliwa
 */
public class AppClient
{
    //Deployment descriptor for IGraphRemote
    private static final String GRAPHLOOKUP = "java:global/108222/Graph!IGraphRemote";
    
    //Deployment descriptor for ISearchRemote
    private static final String SEARCHLOOKUP = "java:global/108222/Search!ISearchRemote";
    
    //remote bean representing graph
    private static IGraphRemote graphRemote = null;
    
    //remote bean providing graph searching utility
    private static ISearchRemote searchRemote = null;
    
    /**
     * Main method
     * @param args 
     */
    public static void main(String[] args)
    {
        //check command-line arguments for path to .txt file
        String inputFilePath = null;
        for (String s : args)
            if (s.endsWith(".txt"))
                inputFilePath = s;
        
        //check if file path has been set
        if (inputFilePath!=null)
        {
            try
            {
                //create new Initial context
                InitialContext ctx = new InitialContext();
                //lookup for IGraphRemote
                graphRemote = (IGraphRemote)ctx.lookup(GRAPHLOOKUP);
                //lookup for ISearchRemote
                searchRemote = (ISearchRemote)ctx.lookup(SEARCHLOOKUP);
            }
            catch(NamingException ex)
            {
                //Print errors
                System.out.println("Lookup error");
                System.out.println(ex);
            }
            ArrayList<String> inputFileValues = new ArrayList<>();
            int[] vertextes = null;
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(inputFilePath))))
            {
                //read file contents
                String line;
                while ((line = reader.readLine()) != null)
                {
                    //split file contents by space and 
                    //add individual values to list
                    String[] parts = line.split("\\ ");
                    inputFileValues.addAll(Arrays.asList(parts));
                }
            }
            catch (IOException ex)
            {
                //Print errors
                System.out.println("Read Error!");
                System.out.println(ex);
            }
            //check if individual values list is not empty
            if (!inputFileValues.isEmpty())
            {
                //initialize vertexes table
                int vSize = inputFileValues.size();
                vertextes = new int[vSize];
                for (int i=0; i<vSize; i++)
                {
                    //add parsed values from list to table
                    if (tryParseInt(inputFileValues.get(i)))
                        vertextes[i] = Integer.parseInt(inputFileValues.get(i));
                }
            }
            //if vertex table is not null and 
            //number of vertexes is divisable by 2
            if (vertextes != null && vertextes.length % 2 == 0)
            {
                //create graph structure
                createGraph(vertextes);
                //inject dependecy to search bean
                searchRemote.setGraphReference(graphRemote);
                //write number of connected components in graph
                System.out.println(searchRemote.getNumOfConnectedComponents());
            }
        }
        else
        {
            System.out.println("No input file!");
        }
    }
    
    /**
     * Creates graph in remote bean, based on input table of vertextes
     * @param vertexes 
     */
    private static void createGraph(int[] vertexes)
    {
        //create edge for each 2 values in table
        for (int i = 0; i<vertexes.length;i+=2)
            graphRemote.addEgde(vertexes[i], vertexes[i+1]);
    }
    
    /**
     * Checks if string can be parsed to int
     * @param input string to parse
     * @return true if can be parsed, otherwise false
     */
    private static Boolean tryParseInt(String input)
    {
        try
        {
            //try to parse integer, then return true
            Integer.parseInt(input);
            return true;
        }
        catch (NumberFormatException ex)
        {
            //if parsing caused an error, return false
            return false;
        }
    }
}