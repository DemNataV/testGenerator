package sample;

import Parser.Parser;
import TestPlan.TestGenerator;
import javafx.event.ActionEvent;

//import java.awt.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Controller {

    public Label helloWorld;

    public Button pathS;

    public RadioButton radiobattonAll;
    public RadioButton radiobattonMax;
    public RadioButton radiobattonRandom;

    public RadioButton radiobattonLeaf;
    public RadioButton radiobattonBranch;
    public RadioButton radiobattonTree;

    public TextField priorityInput;
    public TextField repiadInput;

    static String roundS;
    static String testD;
    static String path;


    public void sayHelloWorld(ActionEvent actionEvent) {
        helloWorld.setText("Hello World!");
    }


    public void generateTestPlan(ActionEvent actionEvent) {
        Parser parser = new Parser();
        var actions = parser.parserToObject();
        //System.out.println(actions);
        TestGenerator testGenerator = new TestGenerator();
        int priority = Integer.parseInt(priorityInput.getText());
        int repiad = Integer.parseInt(repiadInput.getText());

        testGenerator.APITest(testGenerator.TestPlanWithAssert(testGenerator.createTestPlanWithoutAssert(actions, roundS, repiad), testD, priority));
    }

    public void selectTests(ActionEvent actionEvent) {
        if (radiobattonLeaf.isSelected()){
            testD = "leaf";
        }
        if (radiobattonBranch.isSelected()){
            testD = "branch";
        }
        if (radiobattonTree.isSelected()){
            testD = "tree";
        }

    }

    public void selectRound(ActionEvent actionEvent) {
        if (radiobattonAll.isSelected()){
            roundS = "All";
        }
        if (radiobattonMax.isSelected()){
            roundS = "Max";
        }
        if (radiobattonRandom.isSelected()){
            roundS = "Random";
        }
    }

    public void selectPath(ActionEvent actionEvent) {
       
        FileChooser fileChooser = new FileChooser();
        //File file = fileChooser.showOpenDialog(primaryStage);
        //path = file.getPath();

    }
}
