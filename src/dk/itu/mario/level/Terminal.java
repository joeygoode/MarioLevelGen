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
    private ArrayList<ArrayList<Character>> map;
    public Terminal(ArrayList<ArrayList<Character>> map)
    {
        this.map = map;
    }
    public ArrayList<ArrayList<Character>> generate(Random generator)
    {
        return this.map;
    }

    public String toString()
    {
        return map.toString();
    }
}
