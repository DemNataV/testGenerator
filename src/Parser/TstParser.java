package Parser;


public class TstParser {
    public static void main(String[] args) {
        Parser parser = new Parser();
        var actions = parser.parserToObject("Master14_05_21.mm");

        //System.out.println(graphGenerator.toString());
        System.out.println(actions.toString());

        for (int i = 0; i < actions.size(); i++) {
            for (int j = 0; j < actions.get(i).getVariations().size(); j++) {
                System.out.println(actions.get(i).getText() + "\t" + actions.get(i).getVariations().get(j).getText() + "\t");
                //System.out.println(actions.toArray()[i] + "\t");
            }
            System.out.println();

        }


    }
}

