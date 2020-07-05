package TestPlan;

import Parser.Parser;

public class TstMain {
    public static void main(String[] args) {
        Parser parser = new Parser();
        var actions = parser.parserToObject();

        GraphGenerator graphGenerator = new GraphGenerator();

        graphGenerator.createGraph(actions, "заявка", "AllObject");
        System.out.println(graphGenerator.toString());

    }
}
