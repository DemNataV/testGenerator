package TestPlan;

import Parser.Parser;

public class TstTest {
    public static void main(String[] args) {
        Parser parser = new Parser();
        var actions = parser.parserToObject();

        TestGenerator testGenerator = new TestGenerator();
        int priority = 5;
        int repiad = 3;
        String roundS = "All";
        String testD = "branch";

        testGenerator.APITest(testGenerator.TestPlanWithAssert(testGenerator.createTestPlanWithoutAssert(actions, roundS, repiad), testD, priority));



    }
}
