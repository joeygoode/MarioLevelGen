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
    private String symbol;
    private ArrayList<Pair<Integer,ArrayList<Grammar>>> evaluations;
    private int weightSum;
    public Rule(String symbol)
    {
        this.symbol = symbol;
        evaluations = new ArrayList<>();
    }
    void AddEvaluation(Pair<Integer,ArrayList<Grammar>> phrase)
    {
        evaluations.add(phrase);
        weightSum += phrase.getKey();
    }
    public ArrayList<ArrayList<Character>> generate(Random generator)
    {
        int index = Math.abs(generator.nextInt()) % weightSum;
        ArrayList<ArrayList<Character>> map = new ArrayList<>();
        int sum = 0;
        for(Pair<Integer,ArrayList<Grammar>> evaluation : evaluations)
        {
            sum += evaluation.getKey();
            if (sum > index)
            {
                for(Grammar g : evaluation.getValue())
                {
                    map.addAll(g.generate(generator));
                }
                break;
            }
        }
        return map;
    }
}
