package dk.itu.mario.level.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import dk.itu.mario.MarioInterface.Constraints;
import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.MarioInterface.LevelGenerator;
import dk.itu.mario.MarioInterface.LevelInterface;
import dk.itu.mario.level.CustomizedLevel;
import dk.itu.mario.level.GrammarResolver;

public class CustomizedLevelGenerator implements LevelGenerator{

	public LevelInterface generateLevel(GamePlay playerMetrics) {
        GrammarResolver g = new GrammarResolver(new File("Level.cfg"));
        Random random = new Random();
        long seed = random.nextLong();
        System.out.println(seed);
        ArrayList<ArrayList<Character>> map = g.generate(seed);
        while(map.size() < 200 || map.size() > 400)
        {
            map = g.generate(++seed);
        }
        LevelInterface level = new CustomizedLevel(map.size(),15,map);
		return level;
	}

}
