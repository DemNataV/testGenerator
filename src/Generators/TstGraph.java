package Generators;

import Parser.Parser;

public class TstGraph {
    public static void main(String[] args) {
        Parser parser = new Parser();
        var actions = parser.parserToObject("координация+кц2.mm");

        GraphGenerator graphGenerator = new GraphGenerator();

        graphGenerator.createGraph(actions, "", "WithoutAssertName");
        //System.out.println(graphGenerator.toString());


    }
}
