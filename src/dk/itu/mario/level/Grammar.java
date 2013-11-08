package dk.itu.mario.level;

/**
 * Created with IntelliJ IDEA.
 * User: Joey
 * Date: 11/7/13
 * Time: 10:00 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Grammar {
    protected Level level;
    protected int height;
    public Grammar(Level level, int height)
    {
        this.level = level;
        this.height = height;
    }
    public abstract int generate(int height, int floor);
}
