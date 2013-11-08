package dk.itu.mario.level;

/**
 * Created with IntelliJ IDEA.
 * User: Joey
 * Date: 11/7/13
 * Time: 10:02 PM
 * To change this template use File | Settings | File Templates.
 */
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Random;

public class Rule extends Grammar {
    ArrayList<ArrayList<Grammar>> evaluations;
    public Rule()
    {
        evaluations = new ArrayList<>();
    }
    void AddEvaluation(ArrayList<Grammar> phrase)
    {
        evaluations.add(phrase);
    }
    public ArrayList<ArrayList<Character>> generate(Random generator)
    {
        int index = Math.abs(generator.nextInt()) % evaluations.size();
        ArrayList<ArrayList<Character>> map = new ArrayList<>();
        for(Grammar g : evaluations.get(index))
        {
            map.addAll(g.generate(generator));
        }
        return map;
    }
}
