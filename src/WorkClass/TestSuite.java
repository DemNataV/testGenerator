package WorkClass;

import java.util.ArrayList;

public class TestSuite {
    ArrayList<Scenario> scenarios;
    int n;

    public TestSuite(ArrayList<Scenario> scenarios, int n) {
        var list = new ArrayList<Scenario>(scenarios);
        this.scenarios = list;
        this.n = n;
    }

    public TestSuite() {}

    public ArrayList<Scenario> getScenarios() {
        return scenarios;
    }

    public void setScenarios(ArrayList<Scenario> scenarios) {
        this.scenarios = scenarios;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "TestSuite{" +
                "scenarios=" + scenarios +
                '}';
    }
}
