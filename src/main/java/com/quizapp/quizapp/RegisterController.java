package com.quizapp.quizapp;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RegisterController {

    @FXML
    private TextField registerNameTextField;

    @FXML
    private TextField registerIdTextField;

    @FXML
    private TextField registerEmailTextField;

    @FXML
    PasswordField registerPasswordField;

//    private ObservableList<Student> studentObservableList;

    public void handleRegister(){
        String name = registerNameTextField.getText();
        int id = Integer.parseInt(registerIdTextField.getText());
        String email = registerEmailTextField.getText();
        String password = registerPasswordField.getText();

        System.out.println(name+" "+id+" "+email+" "+password);

        Student student = new Student(name, id, email, password);
//        studentObservableList.add(student);

        // Save data to a file
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("registered-students.txt", "rw");
            randomAccessFile.seek(randomAccessFile.length());
            // Move the cursor to the end of the file.
            randomAccessFile.writeBytes(name+", "+id+", "+email+", "+password+"\n");
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");;
        } catch (IOException e){
            System.err.println("Failed to read. :(");
        }
    }

    public void goToLogin() throws IOException{
        System.out.println("Going to Login");
        HelloApplication.changeScene("login.fxml");
    }
}
