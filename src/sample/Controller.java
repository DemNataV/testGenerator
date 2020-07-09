package sample;

import Parser.Parser;
import Generators.GraphGenerator;
import Generators.TestGenerator;
import javafx.event.ActionEvent;

//import java.awt.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;

public class Controller {

    public Label helloWorld;
    public Label pathF;

    public Button pathS;

    public RadioButton radiobattonAll;
    public RadioButton radiobattonMax;
    public RadioButton radiobattonRandom;

    public RadioButton radiobattonLeaf;
    public RadioButton radiobattonBranch;
    public RadioButton radiobattonTree;

    public RadioButton All;
    public RadioButton WithoutAssert;
    public RadioButton AllObject;
    public RadioButton transitionAll;

    public TextField priorityInput;
    public TextField repiadInput;
    public TextField objectInput;

    static String roundS;
    static String testD;
    static String path;
    static String graphType;


    public void sayHelloWorld(ActionEvent actionEvent) {
        helloWorld.setText("Hello World!");
    }


    public void generateTestPlan(ActionEvent actionEvent) {
        Parser parser = new Parser();
        var actions = parser.parserToObject(path);
        //System.out.println(actions);
        TestGenerator testGenerator = new TestGenerator();
        int priority = Integer.parseInt(priorityInput.getText());
        int repiad = Integer.parseInt(repiadInput.getText());

        var testPlan = testGenerator.createTestPlanWithoutAssert(actions, roundS, repiad);
        var testPlanWA = testGenerator.TestPlanWithAssert(testPlan, testD, priority);

        testGenerator.APITest(testPlanWA);
    }

    public void generateGraph(ActionEvent actionEvent) {
        Parser parser = new Parser();
        var actions = parser.parserToObject(path);

        GraphGenerator graphGenerator = new GraphGenerator();

        String typeGraph = objectInput.getText();

        graphGenerator.createGraph(actions, typeGraph, graphType);
    }


    public void selectTests(ActionEvent actionEvent) {
        if (radiobattonLeaf.isSelected()) {
            testD = "leaf";
        }
        if (radiobattonBranch.isSelected()) {
            testD = "branch";
        }
        if (radiobattonTree.isSelected()) {
            testD = "tree";
        }

    }

    public void selectRound(ActionEvent actionEvent) {
        if (radiobattonAll.isSelected()) {
            roundS = "All";
        }
        if (radiobattonMax.isSelected()) {
            roundS = "Max";
        }
        if (radiobattonRandom.isSelected()) {
            roundS = "Random";
        }
    }

    public void selectGraph(ActionEvent actionEvent) {
        if (All.isSelected()) {
            graphType = "All";
        }
        if (WithoutAssert.isSelected()) {
            graphType = "WithoutAssert";
        }
        if (AllObject.isSelected()) {
            graphType = "AllObject";
        }
        if (transitionAll.isSelected()) {
            graphType = "transitionAll";
        }


    }


    public void selectPath(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(Main.prim);
        path = file.getPath();
        pathF.setText(path);

    }
}


