package dk.itu.mario.level;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Joey
 * Date: 11/7/13
 * Time: 9:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class Terminal extends Grammar {
    private Grid map;
    public Terminal(Grid map)
    {
        this.map = map;
    }
    public Grid generate(Random generator)
    {
        return this.map;
    }
    public boolean needsEvaluation()
    {
        return false;
    }
    public ArrayList<Grammar> evaluate(Random generator)
    {
        throw new RuntimeException("Cannot evaluate a terminal");
    }
    public String toString()
    {
        return map.toString();
    }
}
