package dk.itu.mario.level;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Joey
 * Date: 11/9/13
 * Time: 11:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class Group extends Grammar{
    private ArrayList<Grammar> group;
    public Group()
    {
        group = new ArrayList<>();
    }
    public Group addParallelEvaluation(Grammar g)
    {
        group.add(g);
        return this;
    }
    public boolean needsEvaluation()
    {
        for(Grammar g : group)
        {
            if(g.needsEvaluation())
                return true;
        }
        return false;
    }
    public ArrayList<Grammar> evaluate(Random generator)
    {
        for(int i = 0; i < group.size(); i++)
        {
            if(group.get(i).needsEvaluation())
            {
                ArrayList<Grammar> evaluation = group.get(i).evaluate(generator);
                group.remove(i);
                group.addAll(i,evaluation);
                i += evaluation.size();
            }
        }
        return group;
    }
    public Grid generate(Random generator)
    {
        Grid grid = new Grid();
        for(Grammar g : group)
        {
            grid.merge(g.generate(generator));
        }
        return grid;
    }
}
