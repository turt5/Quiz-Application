package com.quizapp.quizapp;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class HelloController implements Initializable {
    @FXML
    TableView<String> chooseExamTableView;
    @FXML
    TableColumn<String, String> examNameColumn;
    @FXML
    TableColumn<ExamQuestions, String> timeColumn;
    public static List<ExamQuestions> examList;
    public static List<ExamQuestions> getExamList() {
        return examList;
    }
    @FXML
    public void handleLogout() throws IOException {
        HelloApplication.changeScene("login.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ExamQuestionsImplementation examQuestionsImplementation = new ExamQuestionsImplementation();
        HashMap<String, List<ExamQuestions>> examMap = examQuestionsImplementation.list();
        chooseExamTableView.setItems(FXCollections.observableArrayList(examMap.keySet()));

        chooseExamTableView.setOnMouseClicked(event -> {
            String selectedExam = chooseExamTableView.getSelectionModel().getSelectedItem();
            examList = examMap.get(selectedExam);
            try {
                HelloApplication.changeScene("quizpage.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        examNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        timeColumn.setCellValueFactory(data -> new SimpleStringProperty(new ExamQuestions().getTime()));
    }
}