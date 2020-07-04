package WorkClass;

import java.util.ArrayList;

public class Test {
    ArrayList<Asserts> asserts;

    public ArrayList<Asserts> getAsserts() {
        return asserts;
    }

    public void setAsserts(ArrayList<Asserts> asserts) {
        this.asserts = asserts;
    }

    @Override
    public String toString() {
        return "Test{" +
                "asserts=" + asserts +
                '}';
    }
}
