package Generators;

import Parser.Parser;

//import static org.graalvm.compiler.hotspot.stubs.StubUtil.printf;

public class TstGraph {
    public static void main(String[] args) {
        Parser parser = new Parser();
        var actions = parser.parserToObject("Master14_05_21.mm");

        GraphGenerator graphGenerator = new GraphGenerator();

        graphGenerator.createGraph(actions, "", "WithoutAssertName");
        //System.out.println(graphGenerator.toString());
        //System.out.printf(actions.toString());


    }
}
