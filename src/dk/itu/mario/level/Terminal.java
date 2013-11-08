package dk.itu.mario.level;

/**
 * Created with IntelliJ IDEA.
 * User: Joey
 * Date: 11/7/13
 * Time: 9:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class Terminal {
    private int height;
    public Terminal(int height)
    {
        this.height = height;
    }
    public int generate(Level level, int x, int floor)
    {
        for (int y = 0; y < height; y++)
        {
            if (y >= floor)
            {
                level.setBlock(x, y, level.GROUND);
            }
        }
        return x + 1;
    }
}
