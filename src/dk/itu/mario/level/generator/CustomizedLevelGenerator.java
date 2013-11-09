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
import dk.itu.mario.level.Grid;

public class CustomizedLevelGenerator implements LevelGenerator{

	public LevelInterface generateLevel(GamePlay playerMetrics) {
        GrammarResolver g = new GrammarResolver(new File("Level.cfg"));
        Random random = new Random();
        long seed = random.nextLong();
        System.out.println(seed);
        Grid map = g.generate(seed);
        while(map.getMap().size() < 200 || map.getMap().size() > 400)
        {
            map = g.generate(++seed);
        }
        LevelInterface level = new CustomizedLevel(map.getMap().size(),15,map);
		return level;
	}

}
