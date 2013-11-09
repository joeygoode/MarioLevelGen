package dk.itu.mario.level;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Joey
 * Date: 11/7/13
 * Time: 10:00 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Grammar {
    public abstract ArrayList<ArrayList<Character>> generate(Random generator);
    public abstract ArrayList<Grammar> evaluate(Random generator);
    public abstract boolean needsEvaluation();
}
