package dk.itu.mario.level;

/**
 * Created with IntelliJ IDEA.
 * User: Joey
 * Date: 11/7/13
 * Time: 10:02 PM
 * To change this template use File | Settings | File Templates.
 */
import java.util.ArrayList;

public class Rule extends Grammar {
    ArrayList<Grammar> phrase;
    public Rule(Level level, int height)
    {
        super(level,height);
    }
    public int generate(int x, int floor)
    {
        for(Grammar g : phrase)
        {
            x = g.generate(x,floor);
        }
        return x;
    }
}
