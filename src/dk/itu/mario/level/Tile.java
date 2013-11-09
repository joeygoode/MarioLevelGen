package dk.itu.mario.level;

/**
 * Created with IntelliJ IDEA.
 * User: Joey
 * Date: 11/9/13
 * Time: 12:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class Tile {
    private char value;
    public Tile(char c)
    {
        value = c;
    }
    public Tile set(char c)
    {
        value = c;
        return this;
    }
    public char get()
    {
        return value;
    }
    public Tile resolve(Tile t)
    {
        //Cases where we return the new value
        boolean useNew = false;
        useNew |= value == 'E';
        useNew |= value == '-';
        if (useNew)
            value = t.get();
        return this;
    }
    public boolean equals(Object o)
    {
        return (o instanceof Tile) ?  value == ((Tile) o).get() : false;
    }
    public int hashCode()
    {
        return new Character(value).hashCode();
    }
    public Tile copy()
    {
        return new Tile(value);
    }
}
