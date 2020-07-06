package WorkClass;

import java.util.ArrayList;

public class Test {
    ArrayList<Asserts> asserts;

    public Test() {
        this.asserts = new ArrayList<>();
    }

    public ArrayList<Asserts> getAsserts() {
        return asserts;
    }



    @Override
    public String toString() {
        return "Test{" +
                "asserts=" + asserts +
                '}';
    }
}
