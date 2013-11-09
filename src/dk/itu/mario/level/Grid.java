package dk.itu.mario.level;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Joey
 * Date: 11/9/13
 * Time: 12:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class Grid {
    private ArrayList<ArrayList<Tile>> map;
    public Grid()
    {
        map = new ArrayList<>();
    }
    public Grid(ArrayList<ArrayList<Tile>> map)
    {
        this.map = map;
    }
    public ArrayList<ArrayList<Tile>> getMap() {
        return map;
    }

    public Grid setMap(ArrayList<ArrayList<Tile>> map) {
        this.map = map;
        return this;
    }
    public Grid append(ArrayList<Tile> column)
    {
        map.add(column);
        return this;
    }
    public Grid append(Grid map)
    {
        this.map.addAll(map.getMap());
        return this;
    }
    public Grid merge(Grid map)
    {
        if(this.map.isEmpty())
        {
            this.map = ((Grid)map.copy()).getMap();
        }
        else
        {
            for (int x = 0; x < Math.min(this.map.size(),map.getMap().size()); x++)
            {
                for (int y = 0; y < Math.min(this.map.get(0).size(),map.getMap().get(0).size()); y++)
                {
                    Tile t = this.map.get(x).get(y);
                    t.resolve(map.getMap().get(x).get(y));
                }
            }
        }
        return this;
    }
    public Grid copy()
    {
        ArrayList<ArrayList<Tile>> newmap = new ArrayList<>();
        for (ArrayList<Tile> list : this.map)
        {
            ArrayList<Tile> newlist = new ArrayList<>();
            for(Tile t : list)
            {
                newlist.add(t.copy());
            }
            newmap.add(newlist);
        }
        return new Grid(newmap);
    }

}
