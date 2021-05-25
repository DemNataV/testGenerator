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
                            .replaceAll("[a-z]", "");
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
                        String is = avWithIS.get(i).getInitialStates().get(j).name().replaceAll("\"", "");
                        String acvarabb = avWithIS.get(i).getActionWithVariation().abb();

                        if (!acvar.equals("null null") && !is.equals("null null")) {
                            graph = graph + "\n" + "\"" + is + "\"" + "->" + "\"" + acvarabb + "\"" + " [shape=ellipse];";
                        }
                    }

                }
            }
            break;

            case "WithoutAssertAab": {
                postF = "WithoutAssertAbb";
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
                        color = "black";
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

            case "WithoutAssertAbb2": {
                postF = "WithoutAssertAbb2";
                ArrayList<String> initialStates = new ArrayList<>();
                //ArrayList<InitialState> initialStates = new ArrayList<>();
                graph = graph + "\n" + "label = <"+ "\n"
                        + "<table border=\"0\" cellborder=\"1\" cellspacing=\"0\">"+ "\n"
                        + "<tr><td bgcolor="+ "\"" + "green"+ "\"" + ">Килин</td></tr>"+ "\n"
                        + "<tr><td bgcolor="+ "\"" + "blanchedalmond"+ "\"" + ">Васенин</td></tr>"+ "\n"
                        + "<tr><td bgcolor="+ "\"" + "deepskyblue"+ "\"" + ">Сенишина</td></tr>"+ "\n"
                        + "<tr><td bgcolor="+ "\"" + "violet"+ "\"" + ">Лепешкин</td></tr>"+ "\n"
                        + "<tr><td bgcolor="+ "\"" + "yellow"+ "\"" + ">Вдовкин</td></tr>"+ "\n"
                        + "<tr><td bgcolor="+ "\"" + "orange"+ "\"" + ">Коноплев</td></tr>"+ "\n"
                        + " <tr><td bgcolor="+ "\"" + "white"+ "\"" + ">Состояния/общие события</td></tr>"+ "\n"
                        + "</table>>";

                for (int i = 0; i < avWithIS.size(); i++) {

                    //initialStates.addAll(avWithIS.get(i).getInitialStates());
                    String acvar = avWithIS.get(i).getActionWithVariation().abb().replaceAll("\"", "");
                    String bgcolor = "white";
                    if ("Килин".equals(avWithIS.get(i).getActionWithVariation().getAction().getLink())){
                        bgcolor = "green";}
                    if ("Васенин".equals(avWithIS.get(i).getActionWithVariation().getAction().getLink())){
                        bgcolor = "blanchedalmond";}
                    if ("Сенишина".equals(avWithIS.get(i).getActionWithVariation().getAction().getLink())){
                        bgcolor = "deepskyblue";}
                    if ("Лепешкин".equals(avWithIS.get(i).getActionWithVariation().getAction().getLink())){
                        bgcolor = "violet";}
                    if ("Вдовкин".equals(avWithIS.get(i).getActionWithVariation().getAction().getLink())){
                        bgcolor = "yellow";}
                    if ("Коноплев".equals(avWithIS.get(i).getActionWithVariation().getAction().getLink())){
                        bgcolor = "orange";}


                    for (int j = 0; j < avWithIS.get(i).getInitialStates().size(); j++) {

                        // String acvar = avWithIS.get(i).getActionWithVariation().name().replaceAll("\"", "");
                        String is = avWithIS.get(i).getInitialStates().get(j).name().replaceAll("\"", "");

                        //String acvarAb = avWithIS.get(i).getActionWithVariation().abb().replaceAll("\"", "");



                        if (!acvar.equals("null null") && !is.equals("null null")) {
                            // graph = graph + "\n" + acvarAb + " [label=\"" +acvar+ "\"]";
                            //graph = graph + "\n" + "\"" + is + "\"" + "->" + acvarAb + ";";
                            // graph = graph + "\n"  + acvarAb +  " [shape=box];";
                            graph = graph + "\n" + "\"" + is + "\"" + "->" + "\"" + acvar + "\"" + ";";
                            graph = graph + "\n" + "\"" + acvar + "\"" + " [shape=box,height=0.5,width=1, fixedsize=true, style=filled, fillcolor=" + bgcolor+"];";
                            graph = graph + "\n" + "\"" + is + "\"" + " [height=0.5,width=6, fixedsize=true];";

                            initialStates.add(is);
                        }
                    }
                }

                for (int i = 0; i < avWithFS.size(); i++) {
                    if (avWithFS.get(i).getFinalState().getObject().equals(object)) {
                        color = "blue";
                    } else {
                        color = "black";
                    }

                    //String acvarAb = avWithFS.get(i).getActionWithVariation().abb().replaceAll("\"", "");
                    String acvar = avWithFS.get(i).getActionWithVariation().abb().replaceAll("\"", "");
                    String fs = avWithFS.get(i).getFinalState().name().replaceAll("\"", "");

                    String bgcolor = "white";
                    if ("Килин".equals(avWithFS.get(i).getActionWithVariation().getAction().getLink())){
                        bgcolor = "green";}
                    if ("Васенин".equals(avWithFS.get(i).getActionWithVariation().getAction().getLink())){
                        bgcolor = "blanchedalmond";}
                    if ("Сенишина".equals(avWithFS.get(i).getActionWithVariation().getAction().getLink())){
                        bgcolor = "deepskyblue";}
                    if ("Лепешкин".equals(avWithFS.get(i).getActionWithVariation().getAction().getLink())){
                        bgcolor = "violet";}
                    if ("Вдовкин".equals(avWithFS.get(i).getActionWithVariation().getAction().getLink())){
                        bgcolor = "yellow";}
                    if ("Коноплев".equals(avWithFS.get(i).getActionWithVariation().getAction().getLink())){
                        bgcolor = "orange";}

                    if (!acvar.equals("null null") && !fs.equals("null null")) {
                        int n = 0;
                        for (int j = 0; j < initialStates.size(); j++) {
                            if (fs.equals(initialStates.get(j))) {
                                n++;
                            }
                        }
                        if (n != 0) {


                            //graph = graph + "\n" + "\"" + fs + "\"" + "->" +  acvarAb + ";";
                            //graph = graph + "\n" + "\"" + acvarAb + "\"" + " [shape=box];";
                            graph = graph + "\n" + "\"" + acvar + "\"" + "->" + "\"" + fs + "\"" + " [color=" + color + "];";
                            // graph = graph + "\n" + "\"" + acvar + "\"" + " [shape=box,height=0.5,width=3, fixedsize=true];";
                            graph = graph + "\n" + "\"" + acvar + "\"" + " [shape=box,height=0.5,width=1, fixedsize=true, style=filled, fillcolor=" + bgcolor+"];";
                            graph = graph + "\n" + "\"" + fs + "\"" + " [height=0.5,width=6, fixedsize=true];";
                        }

                    }
                }

            }
            break;

            case "WithoutAssertName": {
                postF = "WithoutAssertName";
                ArrayList<String> initialStates = new ArrayList<>();
                //ArrayList<InitialState> initialStates = new ArrayList<>();
                graph = graph + "\n" + "label = <"+ "\n"
                        + "<table border=\"0\" cellborder=\"1\" cellspacing=\"0\">"+ "\n"
                        + "<tr><td bgcolor="+ "\"" + "green"+ "\"" + ">Грузоперевозчик</td></tr>"+ "\n"
                        + "<tr><td bgcolor="+ "\"" + "blanchedalmond"+ "\"" + ">ЕРП</td></tr>"+ "\n"
                        + "<tr><td bgcolor="+ "\"" + "deepskyblue"+ "\"" + ">Грузовладелец</td></tr>"+ "\n"
                        + "<tr><td bgcolor="+ "\"" + "violet"+ "\"" + ">Координация1</td></tr>"+ "\n"
                        + "<tr><td bgcolor="+ "\"" + "yellow"+ "\"" + ">Координация2</td></tr>"+ "\n"
                        + "<tr><td bgcolor="+ "\"" + "orange"+ "\"" + ">Коноплев</td></tr>"+ "\n"
                        + " <tr><td bgcolor="+ "\"" + "white"+ "\"" + ">Состояния/общие события</td></tr>"+ "\n"
                        + "</table>>";

                double nh = 0.25;

                for (int i = 0; i < avWithIS.size(); i++) {

                    //initialStates.addAll(avWithIS.get(i).getInitialStates());
                    /*String acvar = avWithIS.get(i).getActionWithVariation().name().replaceAll("\"", "");
                    String acvar20 = new String();

                        int n1 = (int)Math.ceil(acvar.length()/20);
                    for (int o = 0; o < n1 +1 ; o++) {

                        if(o < n1){
                            String acvaro = acvar.substring(o*20, ((o+1)*20)-1);
                        acvar20 = acvar20 + acvaro + "\\n";
                        }
                        else {
                            String acvaro = acvar.substring(o*20, acvar.length());
                            acvar20 = acvar20 + acvaro;
                        }

                    }*/

                    String acvar20 = avWithIS.get(i).getActionWithVariation().name20();
                    double n1 = (avWithIS.get(i).getActionWithVariation().height()+1) * nh;


                    String bgcolor = "white";
                   if ("Килин".equals(avWithIS.get(i).getActionWithVariation().getAction().getLink())){
                       bgcolor = "green";}
                       if ("Васенин".equals(avWithIS.get(i).getActionWithVariation().getAction().getLink())){
                           bgcolor = "blanchedalmond";}
                           if ("Сенишина".equals(avWithIS.get(i).getActionWithVariation().getAction().getLink())){
                               bgcolor = "deepskyblue";}
                               if ("Лепешкин".equals(avWithIS.get(i).getActionWithVariation().getAction().getLink())){
                                   bgcolor = "violet";}
                                   if ("Вдовкин".equals(avWithIS.get(i).getActionWithVariation().getAction().getLink())){
                                       bgcolor = "yellow";}
                                       if ("Коноплев".equals(avWithIS.get(i).getActionWithVariation().getAction().getLink())){
                                           bgcolor = "orange";}


                    for (int j = 0; j < avWithIS.get(i).getInitialStates().size(); j++) {

                        // String acvar = avWithIS.get(i).getActionWithVariation().name().replaceAll("\"", "");
                       // String is = avWithIS.get(i).getInitialStates().get(j).name().replaceAll("\"", "");

                       // String is20 = new String();

                       // int n2 = (int)Math.ceil(is.length()/20);
                       // for (int o = 0; o < n2+1 ; o++) {

                          //  if(o < n2){
                            //    String iso = is.substring(o*20, ((o+1)*20-1));
                            //    is20 = is20 + iso + "\\n";
                           // }
                           // else {
                          //      String iso = is.substring(o*20, is.length());
                          //      is20 = is20 + iso;
                         //   }

                       // }

                        //String acvarAb = avWithIS.get(i).getActionWithVariation().abb().replaceAll("\"", "");
                        String is20 = avWithIS.get(i).getInitialStates().get(j).name20();
                        double n2 = (avWithIS.get(i).getInitialStates().get(j).height()+1) * nh;



                        if (!acvar20.equals("null null") && !is20.equals("null null")) {
                           // graph = graph + "\n" + acvarAb + " [label=\"" +acvar+ "\"]";
                            //graph = graph + "\n" + "\"" + is + "\"" + "->" + acvarAb + ";";
                           // graph = graph + "\n"  + acvarAb +  " [shape=box];";
                            graph = graph + "\n" + "\"" + is20 + "\"" + "->" + "\"" + acvar20 + "\"" + ";";
                            graph = graph + "\n" + "\"" + acvar20 + "\"" + " [shape=box,height=" + n1 + ",width=2, fixedsize=true, style=filled, fillcolor=" + bgcolor+"];";
                            graph = graph + "\n" + "\"" + is20 + "\"" + " [height=" + n2 + ",width=2, fixedsize=true, shape = " + "\"" + "Mrecord"+ "\"" + "];";

                            initialStates.add(is20);
                        }
                    }
                }

                for (int i = 0; i < avWithFS.size(); i++) {
                    if (avWithFS.get(i).getFinalState().getObject().equals(object)) {
                        color = "blue";
                    } else {
                        color = "black";
                    }

                    //String acvarAb = avWithFS.get(i).getActionWithVariation().abb().replaceAll("\"", "");
                    /*String acvar = avWithFS.get(i).getActionWithVariation().name().replaceAll("\"", "");

                    String acvar20 = new String();

                    int n1 = (int)Math.ceil(acvar.length()/20);
                    for (int o = 0; o < n1 +1 ; o++) {

                        if(o < n1){
                            String acvaro = acvar.substring(o*20, ((o+1)*20)-1);
                            acvar20 = acvar20 + acvaro + "\\n";
                        }
                        else {
                            String acvaro = acvar.substring(o*20, acvar.length());
                            acvar20 = acvar20 + acvaro;
                        }

                    }*/
                    String acvar20 = avWithFS.get(i).getActionWithVariation().name20();
                    double n1 = (avWithFS.get(i).getActionWithVariation().height()+1) * nh;


                    /*String fs = avWithFS.get(i).getFinalState().name().replaceAll("\"", "");

                    String fs20 = new String();

                    int n3 = (int)Math.ceil(fs.length()/20);
                    for (int o = 0; o < n3 +1 ; o++) {

                        if(o < n3){
                            String fso = fs.substring(o*20, ((o+1)*20)-1);
                            fs20 = fs20 + fso + "\\n";
                        }
                        else {
                            String fso = fs.substring(o*20, fs.length());
                            fs20 = fs20 + fso;
                        }

                    }*/
                    String fs20 = avWithFS.get(i).getFinalState().name20();
                    double n3 = (avWithFS.get(i).getFinalState().height()+1) * nh;

                    String bgcolor = "white";
                    if ("Килин".equals(avWithFS.get(i).getActionWithVariation().getAction().getLink())){
                        bgcolor = "green";}
                    if ("Васенин".equals(avWithFS.get(i).getActionWithVariation().getAction().getLink())){
                        bgcolor = "blanchedalmond";}
                    if ("Сенишина".equals(avWithFS.get(i).getActionWithVariation().getAction().getLink())){
                        bgcolor = "deepskyblue";}
                    if ("Лепешкин".equals(avWithFS.get(i).getActionWithVariation().getAction().getLink())){
                        bgcolor = "violet";}
                    if ("Вдовкин".equals(avWithFS.get(i).getActionWithVariation().getAction().getLink())){
                        bgcolor = "yellow";}
                    if ("Коноплев".equals(avWithFS.get(i).getActionWithVariation().getAction().getLink())){
                        bgcolor = "orange";}

                    if (!acvar20.equals("null null") && !fs20.equals("null null")) {
                        int n = 0;
                        for (int j = 0; j < initialStates.size(); j++) {
                            if (fs20.equals(initialStates.get(j))) {
                                n++;
                            }
                        }
                        if (n != 0) {


                            //graph = graph + "\n" + "\"" + fs + "\"" + "->" +  acvarAb + ";";
                            //graph = graph + "\n" + "\"" + acvarAb + "\"" + " [shape=box];";
                            graph = graph + "\n" + "\"" + acvar20 + "\"" + "->" + "\"" + fs20 + "\"" + " [color=" + color + "];";
                           // graph = graph + "\n" + "\"" + acvar + "\"" + " [shape=box,height=0.5,width=3, fixedsize=true];";
                            graph = graph + "\n" + "\"" + acvar20 + "\"" + " [shape=box,height=" + n1 + ",width=2, fixedsize=true, style=filled, fillcolor=" + bgcolor+"];";
                            graph = graph + "\n" + "\"" + fs20 + "\"" + " [height=" + n3 + ",width=2, fixedsize=true, shape = " + "\"" + "Mrecord"+ "\"" + "];";
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

                        String bgcolori = "white";
                        if ("Килин".equals(avWithIS.get(i).getActionWithVariation().getAction().getLink())){
                            bgcolori = "green";}
                        if ("Васенин".equals(avWithIS.get(i).getActionWithVariation().getAction().getLink())){
                            bgcolori = "blanchedalmond";}
                        if ("Сенишина".equals(avWithIS.get(i).getActionWithVariation().getAction().getLink())){
                            bgcolori = "deepskyblue";}
                        if ("Лепешкин".equals(avWithIS.get(i).getActionWithVariation().getAction().getLink())){
                            bgcolori = "violet";}
                        if ("Вдовкин".equals(avWithIS.get(i).getActionWithVariation().getAction().getLink())){
                            bgcolori = "yellow";}
                        if ("Коноплев".equals(avWithIS.get(i).getActionWithVariation().getAction().getLink())){
                            bgcolori = "orange";}


                        var AV = avWithFinalState.foundAV(avWithFS, avWithIS.get(i).getInitialStates().get(j));

                        for (int k = 0; k < AV.size(); k++) {

                            String acvarfsab = AV.get(k).abb();

                            if (!acvaris.equals("null null") && !is.equals("null null")) {
                                graph = graph + "\n" + "\"" + acvarisab + "\"" + "->" + "\"" + acvarfsab + "\"" + ";";
                                graph = graph + "\n" + "\"" + acvarisab + "\"" + " [shape=box,height=0.5,width=1, fixedsize=true, style=filled, fillcolor=" + bgcolori+"];";
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
