package dk.itu.mario.level;

/**
 * Created with IntelliJ IDEA.
 * User: Joey
 * Date: 11/8/13
 * Time: 8:59 AM
 * To change this template use File | Settings | File Templates.
 */

import javafx.util.Pair;

import java.io.*;
import java.util.*;

public class GrammarResolver
{
    private HashMap<String, Grammar> grammar;
    public GrammarResolver(File input)
    {
        BufferedReader reader;
        try
        {
            FileReader fr = new FileReader(input);
            reader = new BufferedReader(fr);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            throw new RuntimeException("File Not Found");
        }

        TreeMap<String, ArrayList<Pair<Integer,ArrayList<String>>>> unresolvedRules = new TreeMap<>();
        TreeMap<String, ArrayList<ArrayList<Character>>> unresolvedTerminals = new TreeMap<>();
        try
        {
            //See if this line is a rule
            for(String line = reader.readLine(); line != null; line = reader.readLine())
                if (line.contains("->")) {
                    //Split off the start symbols and terminals
                    String[] rule = line.split("->");
                    rule[0] = rule[0].replaceAll("\\s","");
                    String[] startSymbols = rule[0].split(",");
                    rule[1] = rule[1].replaceAll("\\s", "");
                    String[] terminals = rule[1].split("\\+");
                    //Put them in the map
                    for (String s : startSymbols)
                    {
                        String[] weightedString = s.split("\\|");
                        ArrayList<String> unresolvedTargets = new ArrayList<>();
                        for (String w : terminals) {
                            unresolvedTargets.add(w);
                        }
                        if (unresolvedRules.containsKey(weightedString[0]))
                            unresolvedRules.get(weightedString[0]).add(new Pair<>(Integer.parseInt(weightedString[1]),unresolvedTargets));
                        else {
                            ArrayList<Pair<Integer,ArrayList<String>>> destinations = new ArrayList<>();
                            destinations.add(new Pair<>(Integer.parseInt(weightedString[1]),unresolvedTargets));
                            unresolvedRules.put(weightedString[0], destinations);
                        }
                    }
                }
                else if (line == "");
                else //This is a terminal
                {
                    String terminal = line.trim();
                    line = reader.readLine();
                    //If it's not a terminal, throw.
                    if (!line.equals("{"))
                        throw new RuntimeException("Expected \"{\" but found \""+line+"\"");
                    //Set up the ArrayLists for the level.
                    ArrayList<ArrayList<Character>> y = new ArrayList<>();
                    // Read each line and assign blocks.
                    for (line = reader.readLine(); !line.equals("}"); line = reader.readLine()) {
                        if (line == null)
                            throw new RuntimeException("Unexpected end of file");
                        line.trim();
                        line.replaceAll("\\s", "");
                        //If the line is the wrong length, reject the file.
                        if (!y.isEmpty()) {
                            if (y.get(0).size() != line.length())
                            {
                                throw new RuntimeException(terminal + " is improperly formatted:  Line lengths not equal.  Offending line: " + line + " | Length = " + line.length() + " Expected: " + y.get(0).size());
                            }
                        }
                        //Add each character in the line to the x array.
                        char[] characters = line.toCharArray();
                        ArrayList<Character> x = new ArrayList<>();
                        for (char c : characters) {
                            x.add(c);
                        }
                        //Add the x list to the y list.
                        y.add(x);
                    }
                    unresolvedTerminals.put(terminal, flip(y));
                }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Can't read line");
        }
        //Convert the terminals into classes.
        grammar = new HashMap<>();
        Set<String> symbols = unresolvedTerminals.keySet();
        for (String s : symbols)
        {
            grammar.put(s, new Terminal(unresolvedTerminals.get(s)));
        }
        //Resolve the rules
        System.out.println(unresolvedRules);
        symbols = unresolvedRules.keySet();
        // Add each rule without evaluating it.
        for (String s : symbols)
        {
            grammar.put(s,new Rule(s));
        }

        // Add all evaluations to each rule
        for (String s : symbols)
        {
            Rule r = (Rule) grammar.get(s);
            ArrayList<Pair<Integer,ArrayList<String>>> evaluations = unresolvedRules.get(s);
            for (Pair<Integer,ArrayList<String>> unresolvedEvaluation : evaluations)
            {
                //Evaluate each symbol in the evaluation.
                ArrayList<Grammar> evaluation = new ArrayList<>();
                for (String symbol : unresolvedEvaluation.getValue())
                {
                    evaluation.add(grammar.get(symbol));
                }
                r.AddEvaluation(new Pair<>(unresolvedEvaluation.getKey(),evaluation));
            }
        }
    }

    public ArrayList<ArrayList<Character>> generate(long seed)
    {
        Random generator = new Random(seed);
        ArrayList<Grammar> grammars = new ArrayList<>();
        grammars.add(grammar.get("S"));
        boolean evaluated = true;
        //Loop while we're still evaluating things.
        while(evaluated)
        {
            evaluated = false;
            for(int i = 0; i < grammars.size(); i++)
            {
                if(grammars.get(i).needsEvaluation())
                {
                    ArrayList<Grammar> evaluation = grammars.get(i).evaluate(generator);
                    grammars.remove(i);
                    grammars.addAll(i,evaluation);
                    i += evaluation.size();
                    evaluated = true;
                }
            }
        }
        //Now every element of grammars is a terminal.
        ArrayList<ArrayList<Character>> map = new ArrayList<>();
        for (Grammar g : grammars)
        {
            map.addAll(g.generate(generator));
        }
        return map;
    }

    private ArrayList<ArrayList<Character>> flip(ArrayList<ArrayList<Character>> terminals)
    {
        //Make sure the terminal isn't empty
        if (terminals.isEmpty())
            return terminals;
        if (terminals.get(0).isEmpty())
            return terminals;
        ArrayList<ArrayList<Character>> lx = new ArrayList<>();
        for(int x = 0; x < terminals.get(0).size(); x++)
        {
            ArrayList<Character> ly = new ArrayList<>();
            for(int y = 0; y < terminals.size(); y++)
            {
                ly.add(terminals.get(y).get(x));
            }
            lx.add(ly);
        }
        return lx;
    }
    public void generateToFile(long seed)
    {
        ArrayList<ArrayList<Character>> map = flip(generate(seed));
        PrintWriter pr = null;
        try
        {
            pr = new PrintWriter("Level.txt","UTF-8");
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        for (ArrayList<Character> x : map)
        {
            String s = "";
            for (Character y : x)
            {
                s += y;
            }
            pr.println(s);
        }
        pr.close();
    }
    public String toString()
    {
        return grammar.toString();
    }
    public static void main(String[] args)
    {
        GrammarResolver g = new GrammarResolver(new File("level.cfg"));
        Random random = new Random();
        long seed = random.nextLong();
        System.out.println(seed);
        g.generateToFile(seed);
    }


}
