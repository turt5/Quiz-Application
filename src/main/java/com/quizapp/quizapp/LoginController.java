package com.quizapp.quizapp;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Objects;

public class LoginController {
    @FXML
    TextField loginEmailTextField;

    @FXML
    PasswordField loginPasswordField;

    public void handleLogin() throws IOException{
        String email = loginEmailTextField.getText();
        String password = loginPasswordField.getText();
        boolean isMatched = false;

        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("registered-students.txt", "rw");
            String line;
            while ((line = randomAccessFile.readLine()) != null){
                String[] lineArray = line.split(", ");
                if (Objects.equals(email, "istiack@seu.edu.bd")) {
                    if (Objects.equals(password, "seucse")) {
                        HelloApplication.changeScene("hello-view-admin.fxml");
                        System.out.println("Login Successfull.");
                        isMatched = true;
                    }
                } else if (Objects.equals(email, lineArray[2])) {
                    if (Objects.equals(password, lineArray[3])) {
                        HelloApplication.changeScene("hello-view.fxml");
                        System.out.println("Login Successfull.");
                        isMatched = true;
                    }
                } else {
                    System.err.println("Credential didn't match.");
                }
            }
        } catch (FileNotFoundException e){
            System.err.println("File not found!");
        }
    }

    public void goToRegister() throws IOException{
        System.out.println("Going to Register");
        HelloApplication.changeScene("register.fxml");
    }
}
