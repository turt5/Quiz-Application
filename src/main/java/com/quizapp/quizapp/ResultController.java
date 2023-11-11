package com.quizapp.quizapp;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ResultController implements Initializable {
    @FXML Label resultLable;
    @FXML ProgressIndicator correctProgressIndicator;
    @FXML ProgressIndicator wrongProgressIndicator;
    @FXML TableView<ExamQuestions> answerViewTable;
    @FXML TableColumn<ExamQuestions, String> idTableColumn;
    @FXML TableColumn<ExamQuestions, String> questionTableColumn;
    @FXML TableColumn<ExamQuestions, String> answerTableColumn;

    List<ExamQuestions> examList = HelloController.getExamList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resultLable.setText(QuizPageController.correct+"/"+examList.size());
        System.out.println((QuizPageController.correct+"/"+examList.size()));

        double correctProgress = (double) (QuizPageController.correct) /(examList.size());
        correctProgressIndicator.setProgress(correctProgress);
        System.out.println(correctProgress);

        double wrongProgress = (double) (QuizPageController.wrong) /(examList.size());
        wrongProgressIndicator.setProgress(wrongProgress);
        System.out.println(wrongProgress);

        answerViewTable.setItems(FXCollections.observableArrayList(examList));
        idTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId()));
        questionTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getQuestion()));
        answerTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCorrectAnswer()));
    }

    public void gotoHome(){
        try {
            HelloApplication.changeScene("hello-view.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void handleLogout(){
        try {
            HelloApplication.changeScene("login.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
