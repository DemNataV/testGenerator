package Generators;

import Parser.Parser;

public class TstTest {
    public static void main(String[] args) {
        Parser parser = new Parser();
        var actions = parser.parserToObject("new_version.mm");

        TestGenerator testGenerator = new TestGenerator();
        int priority = 5;
        int repiad = 3;
        String roundS = "Max";
        String testD = "tree";

        testGenerator.APITest(testGenerator.TestPlanWithAssert(testGenerator.createTestPlanWithoutAssert(actions, roundS, repiad), testD, priority));



    }
}
