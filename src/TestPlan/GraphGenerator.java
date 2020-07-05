package TestPlan;

import ClassXml.Action;
import ClassXml.InitialState;
import WorkClass.AVWithFinalState;
import WorkClass.AVWithInitialStates;
import WorkClass.ActionWithVariation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GraphGenerator {
    String graph;

    ActionWithVariation actionWithVariation = new ActionWithVariation();
    AVWithInitialStates avWithInitialStates = new AVWithInitialStates();
    AVWithFinalState avWithFinalState = new AVWithFinalState();

    public String getGraph() {
        return graph;
    }

    public void setGraph(String graph) {
        this.graph = graph;
    }

    public void createGraph(ArrayList<Action> actions, String object, String type) {

        var av = actionWithVariation.creator(actions);
        var avWithIS = avWithInitialStates.creator(av);
        var avWithFS = avWithFinalState.creator(av);

        String color = new String();

        switch (type) {
            case "All": {

                for (int i = 0; i < avWithFS.size(); i++) {
                    if (avWithFS.get(i).getFinalState().getObject().equals(object)) {
                        color = "blue";
                    } else {
                        color = "grey";
                    }

                    String acvar = avWithFS.get(i).getActionWithVariation().name().replaceAll("\"", "");
                    String fs = avWithFS.get(i).getFinalState().name().replaceAll("\"", "");

                    if (!acvar.equals("null null") && !fs.equals("null null")) {
                        graph = graph + "\n" + "\"" + acvar + "\"" + "->" + "\"" + fs + "\"" + " [color=" + color + "];";
                        graph = graph + "\n" + "\"" + acvar + "\"" + " [shape=box];";
                        if (avWithFS.get(i).getFinalState().getPriority() > 0) {
                            graph = graph + "\n" + "\"" + fs + "\"" + " [shape=triangle];";
                        }
                    }
                }

                for (int i = 0; i < avWithIS.size(); i++) {
                    for (int j = 0; j < avWithIS.get(i).getInitialStates().size(); j++) {

                        String acvar = avWithIS.get(i).getActionWithVariation().name().replaceAll("\"", "");
                        String is = avWithIS.get(i).getInitialStates().get(j).name().replaceAll("\"", "");

                        if (!acvar.equals("null null") && !is.equals("null null")) {
                            graph = graph + "\n" + "\"" + is + "\"" + "->" + "\"" + acvar + "\"" + ";";
                        }
                    }

                }
            }

            case "WithoutAssert": {
                ArrayList<String> initialStates = new ArrayList<>();
                //ArrayList<InitialState> initialStates = new ArrayList<>();

                for (int i = 0; i < avWithIS.size(); i++) {

                    //initialStates.addAll(avWithIS.get(i).getInitialStates());

                    for (int j = 0; j < avWithIS.get(i).getInitialStates().size(); j++) {

                        String acvar = avWithIS.get(i).getActionWithVariation().name().replaceAll("\"", "");
                        String is = avWithIS.get(i).getInitialStates().get(j).name().replaceAll("\"", "");

                        if (!acvar.equals("null null") && !is.equals("null null")) {
                            graph = graph + "\n" + "\"" + is + "\"" + "->" + "\"" + acvar + "\"" + ";";
                            graph = graph + "\n" + "\"" + acvar + "\"" + " [shape=box];";

                            initialStates.add(is);
                        }
                    }
                }

                for (int i = 0; i < avWithFS.size(); i++) {
                    if (avWithFS.get(i).getFinalState().getObject().equals(object)) {
                        color = "blue";
                    } else {
                        color = "grey";
                    }

                    String acvar = avWithFS.get(i).getActionWithVariation().name().replaceAll("\"", "");
                    String fs = avWithFS.get(i).getFinalState().name().replaceAll("\"", "");

                    if (!acvar.equals("null null") && !fs.equals("null null")) {
                        int n = 0;
                        for (int j = 0; j < initialStates.size(); j++) {
                            if (fs.equals(initialStates.get(j))) {
                                n++;
                            }
                        }
                        if (n != 0) {

                            graph = graph + "\n" + "\"" + acvar + "\"" + "->" + "\"" + fs + "\"" + " [color=" + color + "];";
                            graph = graph + "\n" + "\"" + acvar + "\"" + " [shape=box];";
                        }

                    }
                }

            }
            case "AllObject": {

                for (int i = 0; i < avWithFS.size(); i++) {
                    if (avWithFS.get(i).getFinalState().getObject().equals(object)) {

                        color = "grey";

                        String acvar = avWithFS.get(i).getActionWithVariation().name().replaceAll("\"", "");
                        String fs = avWithFS.get(i).getFinalState().name().replaceAll("\"", "");

                        if (!acvar.equals("null null") && !fs.equals("null null")) {
                            graph = graph + "\n" + "\"" + acvar + "\"" + "->" + "\"" + fs + "\"" + " [color=" + color + "];";
                            graph = graph + "\n" + "\"" + acvar + "\"" + " [shape=box];";
                            if (avWithFS.get(i).getFinalState().getPriority() > 0) {
                                graph = graph + "\n" + "\"" + fs + "\"" + " [shape=triangle];";
                            }
                        }
                    }
                }

                for (int i = 0; i < avWithIS.size(); i++) {
                    for (int j = 0; j < avWithIS.get(i).getInitialStates().size(); j++) {
                        if (avWithIS.get(i).getActionWithVariation() != null &&
                                avWithIS.get(i).getInitialStates().get(j) != null &&
                                avWithIS.get(i).getInitialStates().get(j).getObject() != null &&
                                avWithIS.get(i).getInitialStates().get(j).getObject().equals(object)) {

                            String acvar = avWithIS.get(i).getActionWithVariation().name().replaceAll("\"", "");
                            String is = avWithIS.get(i).getInitialStates().get(j).name().replaceAll("\"", "");

                            if (!acvar.equals("null null") && !is.equals("null null")) {
                                graph = graph + "\n" + "\"" + is + "\"" + "->" + "\"" + acvar + "\"" + ";";
                            }
                        }
                    }

                }
            }
            case "transitionAll": {

                for (int i = 0; i < avWithFS.size(); i++) {
                    var avF = avWithFinalState.foundAV(avWithFS, avWithFS.get(i).getFinalState());




                }


            }

            String path = "Graph" + LocalDateTime.now() + ".gv";

            try (FileOutputStream fos = new FileOutputStream(path)) {
                byte[] buffer = graph.getBytes();

                fos.write(buffer, 0, buffer.length);
            } catch (IOException ex) {

                System.out.println(ex.getMessage());
            }
            System.out.println("The file has been written");
        }
    }





    @Override
    public String toString() {
        return "digraph G {" + graph  + '}';
    }
}
