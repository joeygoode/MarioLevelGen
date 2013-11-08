package dk.itu.mario.level;

/**
 * Created with IntelliJ IDEA.
 * User: Joey
 * Date: 11/8/13
 * Time: 8:59 AM
 * To change this template use File | Settings | File Templates.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
public class GrammarResolver {
    public GrammarResolver(File input)
    {
        BufferedReader reader;
        try
        {
            FileReader fr = new FileReader(input);
            reader = new BufferedReader(fr);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            throw new RuntimeException("File Not Found");
        }

    }
}
