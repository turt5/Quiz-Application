package com.quizapp.quizapp;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class HelloControllerAdmin implements Initializable {
    @FXML TextField examNameTextField;
    @FXML TextField questionTextField;
    @FXML TextField op1TextField;
    @FXML TextField op2TextField;
    @FXML TextField op3TextField;
    @FXML TextField op4TextField;
    @FXML TextField correctAnswerTextField;
    @FXML TextField idTextField;
    @FXML TextField searchNameTextField;

    @FXML TableView<ExamQuestions> allQuizTableView;
    @FXML TableColumn<ExamQuestions, String> examNameColumn;
    @FXML TableColumn<ExamQuestions, String> questionColumn;
    @FXML TableColumn<ExamQuestions, String> op1Column;
    @FXML TableColumn<ExamQuestions, String> op2Column;
    @FXML TableColumn<ExamQuestions, String> op3Column;
    @FXML TableColumn<ExamQuestions, String> op4Column;
    @FXML TableColumn<ExamQuestions, String> correctAnswerColumn;
    ExamQuestionsImplementation examQuestionsImplementation = new ExamQuestionsImplementation();
    ObservableList<ExamQuestions> questions = FXCollections.observableList(examQuestionsImplementation.list2());
    ExamQuestions selectedItem;

    @FXML
    public void insertAction() {
        ExamQuestions examQuestions = new ExamQuestions(idTextField.getText(), examNameTextField.getText(), questionTextField.getText(), op1TextField.getText(), op2TextField.getText(), op3TextField.getText(), op4TextField.getText(), correctAnswerTextField.getText());
        ExamQuestionsImplementation examQuestionsImplementation = new ExamQuestionsImplementation();
        examQuestionsImplementation.inset(examQuestions);
        questions.clear();
        questions.addAll(examQuestionsImplementation.list2());
    }

    @FXML
    public void selectedAction() {
        selectedItem = allQuizTableView.getSelectionModel().getSelectedItem();
        idTextField.setText(String.valueOf(selectedItem.getId()));
        examNameTextField.setText(selectedItem.getName());
        questionTextField.setText(selectedItem.getQuestion());
        op1TextField.setText(selectedItem.getOption1());
        op2TextField.setText(selectedItem.getOption2());
        op3TextField.setText(selectedItem.getOption3());
        op4TextField.setText(selectedItem.getOption4());
        correctAnswerTextField.setText(selectedItem.getCorrectAnswer());
    }

    @FXML
    public void updateAction() {
        ExamQuestions examQuestions = new ExamQuestions(idTextField.getText(), examNameTextField.getText(), questionTextField.getText(), op1TextField.getText(), op2TextField.getText(), op3TextField.getText(), op4TextField.getText(), correctAnswerTextField.getText());
        ExamQuestionsImplementation examQuestionsImplementation = new ExamQuestionsImplementation();
        examQuestionsImplementation.update(examQuestions);
        questions.clear();
        questions.addAll(examQuestionsImplementation.list2());
    }

    @FXML
    public void deleteAction() {
        ExamQuestionsImplementation examQuestionsImplementation = new ExamQuestionsImplementation();
        examQuestionsImplementation.delete(selectedItem);
        questions.clear();
        questions.addAll(examQuestionsImplementation.list2());
    }

    @FXML
    public void searchByName() {
        String searchValue = searchNameTextField.getText();
        ExamQuestionsImplementation examQuestionsImplementation = new ExamQuestionsImplementation();
        List<ExamQuestions> filteredList;
        if (searchValue.isEmpty()) {
            filteredList = examQuestionsImplementation.list2();
        } else {
            filteredList = questions.stream()
                    .filter(question -> question.getName().toLowerCase().contains(searchValue.toLowerCase()))
                    .collect(Collectors.toList());
        }
        filteredList.sort(Comparator.comparing(ExamQuestions::getName).reversed());
        questions.clear();
        questions.addAll(filteredList);
    }

    @FXML
    public void handleLogout() throws IOException {
        HelloApplication.changeScene("login.fxml");
    }

    @FXML
    public void gotoStudentView() throws IOException {
        HelloApplication.changeScene("hello-view.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allQuizTableView.setItems(questions);
        examNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        questionColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getQuestion()));
        op1Column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOption1()));
        op2Column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOption2()));
        op3Column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOption3()));
        op4Column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOption4()));
        correctAnswerColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCorrectAnswer()));
    }
}
