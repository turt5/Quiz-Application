module com.quizapp.quizapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.quizapp.quizapp to javafx.fxml;
    exports com.quizapp.quizapp;
}