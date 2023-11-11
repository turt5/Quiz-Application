package com.quizapp.quizapp;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class QuizPageController implements Initializable {
    @FXML Label questionLabel;
    @FXML RadioButton option1Radio;
    @FXML RadioButton option2Radio;
    @FXML RadioButton option3Radio;
    @FXML RadioButton option4Radio;
    @FXML Label timeLabel;
    @FXML ToggleGroup options;
    List<ExamQuestions> examList = HelloController.getExamList();
    ExamQuestions examQuestions;
    String correctAns;
    int counter = 0;
    static int correct = 0;
    static int wrong = 0;
    int duration;
    private Timer timer;
    private int secondsLeft = 600;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadQuestion(counter);
    }
    private void loadQuestion(int index){
        examQuestions = examList.get(index);
            String questions = examQuestions.getQuestion();
            String option1 = examQuestions.getOption1();
            String option2 = examQuestions.getOption2();
            String option3 = examQuestions.getOption3();
            String option4 = examQuestions.getOption4();
            correctAns = examQuestions.getCorrectAnswer();
            if (counter == index) {
                questionLabel.setText(questions);
                option1Radio.setText(option1);
                option2Radio.setText(option2);
                option3Radio.setText(option3);
                option4Radio.setText(option4);
            }
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (secondsLeft == 0) {
                    timer.cancel();
                    try {
                        HelloApplication.changeScene("result.fxml");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    secondsLeft--;
                    int minutes = secondsLeft / 60;
                    int seconds = secondsLeft % 60;
                    String timeString = String.format("%02d:%02d", minutes, seconds);
                    Platform.runLater(() -> timeLabel.setText(timeString));
                }
            }
        }, 0, 1000);
    }

    public void submitNextAction(){
        if (counter < examList.size()){
            RadioButton correctAnswer = (RadioButton) options.getSelectedToggle();
            String answer = correctAnswer.getText();
            if (Objects.equals(answer, correctAns)){
                correct++;
            } else {
                wrong++;
            }
            counter++;
            if(counter < examList.size()) {
                loadQuestion(counter);
            } else {
                try {
                    HelloApplication.changeScene("result.fxml");
                    timer.cancel();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Khela Shesh!");
            }
        }
    }
}