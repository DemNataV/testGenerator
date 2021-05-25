package WorkClass;

import ClassXml.Action;
import ClassXml.Variation;

import java.util.ArrayList;

public class ActionWithVariation implements Comparable<ActionWithVariation> {
    Action action;
    Variation variation;

    public ActionWithVariation(Action action, Variation variation) {
        this.action = action;
        this.variation = variation;
    }

    public ActionWithVariation() {

    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Variation getVariation() {
        return variation;
    }

    public void setVariation(Variation variation) {
        this.variation = variation;
    }

    public ArrayList<ActionWithVariation> creator(ArrayList<Action> actions){
        ArrayList<ActionWithVariation> actionWithVariations = new ArrayList<>();
        for (int i = 0; i < actions.size() ; i++) {
            for (int j = 0; j < actions.get(i).getVariations().size(); j++) {
                actionWithVariations.add(new ActionWithVariation(actions.get(i),actions.get(i).getVariations().get(j)));
            }

            }
        return actionWithVariations;
    }

    public String abbreviation(ActionWithVariation actionWithVariation){
        return actionWithVariation.getAction().getAbbreviation() + actionWithVariation.getVariation().getAbbreviation();
    }

    public String name(){
        return this.getAction().getText().replaceAll("\"", "") + " " + this.getVariation().getText().replaceAll("\"", "");
    }

   // public String nameN(){return this.getAction().getText() + "\\l" + this.getVariation().getText()+ "\\l";}

    public String nameN(){
        return this.getAction().getText() + "\\n" + this.getVariation().getText();
    }

    public String abb(){
        return this.getAction().getAbbreviation() + this.getVariation().getAbbreviation();
    }



    public int height() {
        return (int)Math.ceil(name().length()/20);
    }

    public String name20() {
        String acvar20 = new String();


        for (int o = 0; o < height() + 1; o++) {

            if (o < height()) {
                String acvaro = name().substring(o * 20, ((o + 1) * 20));
                acvar20 = acvar20 + acvaro + "\\n";
            } else {
                String acvaro = name().substring(o * 20, name().length());
                acvar20 = acvar20 + acvaro;
            }

        }
        return acvar20;
    }

    @Override
    public String toString() {
        return "ActionWithVariation{" +
                "action=" + action +
                ", variation=" + variation +
                '}';
    }

    @Override
    public int compareTo(ActionWithVariation o) {
        return ((this.getAction().getbValue()+this.getVariation().getBValue())-(o.getAction().getbValue()+o.getVariation().getBValue()));
    }


}
