package Generators;

import Parser.Parser;

public class TstGraph {
    public static void main(String[] args) {
        Parser parser = new Parser();
        var actions = parser.parserToObject("new_version_5.mm");

        GraphGenerator graphGenerator = new GraphGenerator();

        graphGenerator.createGraph(actions, "", "Allabb");
        //System.out.println(graphGenerator.toString());


    }
}
