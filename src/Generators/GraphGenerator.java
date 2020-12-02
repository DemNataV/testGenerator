package Generators;

import ClassXml.Action;
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
        graph = "digraph G {" + "\n" + "size=500";
        String postF = new String();

        switch (type) {
            case "All": {

                postF = "All";

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
                            graph = graph + "\n" + "\"" + is + "\"" + "->" + "\"" + acvar + "\"" + " [shape=ellipse];";
                        }
                    }

                }
            }
            break;
            case "Allabb": {

                postF = "Allabb";

                for (int i = 0; i < avWithFS.size(); i++) {
                    if (avWithFS.get(i).getFinalState().getObject().equals(object)) {
                        color = "blue";
                    } else {
                        color = "black";
                    }

                    String acvar = avWithFS.get(i).getActionWithVariation().name().replaceAll("\"", "");
                    String fs = avWithFS.get(i).getFinalState().name().replaceAll("\"", "")
                            .replaceAll("ж", "").replaceAll("б", "").replaceAll("в", "")
                            .replaceAll("г", "").replaceAll("д", "").replaceAll("е", "")
                            .replaceAll("з", "").replaceAll("с", "").replaceAll("к", "")
                            .replaceAll("л", "").replaceAll("м", "").replaceAll("н", "")
                            .replaceAll("т", "").replaceAll("п", "").replaceAll("р", "")
                            .replaceAll("х", "").replaceAll("ш", "").replaceAll("ф", "")
                            .replaceAll("[a-z]", "");;
                    String acvarabb = avWithFS.get(i).getActionWithVariation().abb();

                    if (!acvar.equals("null null") && !fs.equals("null null")) {
                        graph = graph + "\n" + "\"" + acvarabb + "\"" + "->" + "\"" + fs + "\"" + " [color=" + color + "];";
                        graph = graph + "\n" + "\"" + acvarabb + "\"" + " [shape=box];";
                        if (avWithFS.get(i).getFinalState().getPriority() > 0) {
                            graph = graph + "\n" + "\"" + fs + "\"" + " [shape=triangle];";
                        }
                    }
                }

                for (int i = 0; i < avWithIS.size(); i++) {
                    for (int j = 0; j < avWithIS.get(i).getInitialStates().size(); j++) {

                        String acvar = avWithIS.get(i).getActionWithVariation().name().replaceAll("\"", "");
                        String is = avWithIS.get(i).getInitialStates().get(j).name().replaceAll("\"", "")
                                .replaceAll("ж", "").replaceAll("б", "").replaceAll("в", "")
                                .replaceAll("г", "").replaceAll("д", "").replaceAll("е", "")
                                .replaceAll("з", "").replaceAll("с", "").replaceAll("к", "")
                                .replaceAll("л", "").replaceAll("м", "").replaceAll("н", "")
                                .replaceAll("т", "").replaceAll("п", "").replaceAll("р", "")
                                .replaceAll("х", "").replaceAll("ш", "").replaceAll("ф", "")
                                .replaceAll("[a-z]", "");
                        String acvarabb = avWithIS.get(i).getActionWithVariation().abb();

                        if (!acvar.equals("null null") && !is.equals("null null")) {
                            graph = graph + "\n" + "\"" + is + "\"" + "->" + "\"" + acvarabb + "\"" + " [shape=ellipse];";
                        }
                    }

                }
            }
            break;

            case "WithoutAssert": {
                postF = "WithoutAssert";
                ArrayList<String> initialStates = new ArrayList<>();
                //ArrayList<InitialState> initialStates = new ArrayList<>();

                for (int i = 0; i < avWithIS.size(); i++) {

                    //initialStates.addAll(avWithIS.get(i).getInitialStates());

                    for (int j = 0; j < avWithIS.get(i).getInitialStates().size(); j++) {

                        // String acvar = avWithIS.get(i).getActionWithVariation().name().replaceAll("\"", "");
                        String is = avWithIS.get(i).getInitialStates().get(j).name().replaceAll("\"", "");

                        String acvar = avWithIS.get(i).getActionWithVariation().abb().replaceAll("\"", "");


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
            break;
            case "AllObject": {
                postF = "AllObject";

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
            break;
            case "transitionAll": {
                postF = "transitionAll";

                for (int i = 0; i < avWithFS.size(); i++) {
                    var avF = avWithFinalState.foundAV(avWithFS, avWithFS.get(i).getFinalState());


                }


            }

            case "WithoutAssertAndState": {
                postF = "WithoutAssertAndState";
                ArrayList<String> initialStates = new ArrayList<>();
                //ArrayList<InitialState> initialStates = new ArrayList<>();

                for (int i = 0; i < avWithIS.size(); i++) {

                    //initialStates.addAll(avWithIS.get(i).getInitialStates());

                    for (int j = 0; j < avWithIS.get(i).getInitialStates().size(); j++) {

                        String acvaris = avWithIS.get(i).getActionWithVariation().name().replaceAll("\"", "");
                        String is = avWithIS.get(i).getInitialStates().get(j).name().replaceAll("\"", "");

                        String acvarisab = avWithIS.get(i).getActionWithVariation().abb();


                        var AV = avWithFinalState.foundAV(avWithFS, avWithIS.get(i).getInitialStates().get(j));

                        for (int k = 0; k < AV.size(); k++) {

                            String acvarfsab = AV.get(k).abb();

                            if (!acvaris.equals("null null") && !is.equals("null null")) {
                                graph = graph + "\n" + "\"" + acvarisab + "\"" + "->" + "\"" + acvarfsab + "\"" + ";";
                                // graph = graph + "\n" + "\"" + acvar + "\"" + " [shape=box];";

                                initialStates.add(is);
                            }
                        }
                    }
                }

                for (int i = 0; i < avWithFS.size(); i++) {
                    if (avWithFS.get(i).getFinalState().getObject().equals(object)) {
                        color = "blue";
                    } else {
                        color = "grey";
                    }

                    String acvarfs = avWithFS.get(i).getActionWithVariation().name().replaceAll("\"", "");
                    String acvarfsab = avWithFS.get(i).getActionWithVariation().abb().replaceAll("\"", "");
                    String fs = avWithFS.get(i).getFinalState().name().replaceAll("\"", "");

                    if (!acvarfs.equals("null null") && !fs.equals("null null")) {
                        int n = 0;
                        for (int j = 0; j < initialStates.size(); j++) {
                            if (fs.equals(initialStates.get(j))) {
                                //n++;


                               // graph = graph + "\n" + "\"" + acvaris + "\"" + "->" + "\"" + acvarfsab + "\"" + " [color=" + color + "];";
                            }
                        }
                       /* if (n != 0) {

                            graph = graph + "\n" + "\"" + acvar + "\"" + "->" + "\"" + fs + "\"" + " [color=" + color + "];";
                            graph = graph + "\n" + "\"" + acvar + "\"" + " [shape=box];";
                        }*/

            }
        }

            }
            break;
        }
            String date = String.valueOf(LocalDateTime.now());
            date=date.replaceAll(":","");

            graph = graph + "}";

            String path = "Graph/" + postF + date +".gv";

            try (FileOutputStream fos = new FileOutputStream(path)) {
                byte[] buffer = graph.getBytes();

                fos.write(buffer, 0, buffer.length);
            } catch (IOException ex) {

                System.out.println(ex.getMessage());
            }
            System.out.println("The file has been written" + path);
        }






    @Override
    public String toString() {
        return graph ;
    }
}
