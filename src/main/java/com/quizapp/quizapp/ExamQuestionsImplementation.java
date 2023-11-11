package com.quizapp.quizapp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExamQuestionsImplementation implements ExamQuestionsInterface {
    HashMap<String, List<ExamQuestions>> examMap = new HashMap<>();
    List<ExamQuestions> examQuestionsList = new ArrayList<>();
    @Override
    public boolean inset(ExamQuestions examQuestions) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO exam(id, name, question, option1, option2, option3, option4, correctanswer) VALUE('"+examQuestions.getId()+"', '"+examQuestions.getName()+"', '"+examQuestions.getQuestion()+"', '"+examQuestions.getOption1()+"', '"+examQuestions.getOption2()+"', '"+examQuestions.getOption3()+"', '"+examQuestions.getOption4()+"', '"+examQuestions.getCorrectAnswer()+"');";
            statement.execute(query);
            System.out.println(examQuestions.getId());
        return true;
        } catch (SQLException e) {
            System.err.println("Failed to connect Database.");
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(ExamQuestions examQuestions) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "UPDATE exam SET name='"+examQuestions.getName()+"', question='"+examQuestions.getQuestion()+"', option1='"+examQuestions.getOption1()+"', option2='"+examQuestions.getOption2()+"', option3='"+examQuestions.getOption3()+"', option4='"+examQuestions.getOption4()+"', correctanswer='"+examQuestions.getCorrectAnswer()+"' WHERE id='"+examQuestions.getId()+"');";
            statement.execute(query);
        } catch (SQLException e){
            System.err.println("Failed to connect Database.");
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean delete(ExamQuestions examQuestions) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "DELETE FROM exam WHERE id="+examQuestions.getId()+");";
            statement.execute(query);
        } catch (SQLException e) {
            System.err.println("Failed to connect Database.");
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public HashMap<String, List<ExamQuestions>> list() {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM exam;";
            ResultSet resultSet = statement.executeQuery(query);
            List<ExamQuestions> examQuestionList = new ArrayList<>();

            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String question = resultSet.getString("question");
                String option1 = resultSet.getString("option1");
                String option2 = resultSet.getString("option2");
                String option3 = resultSet.getString("option3");
                String option4 = resultSet.getString("option4");
                String correctanswer = resultSet.getString("correctanswer");

                ExamQuestions examQuestions = new ExamQuestions(id, name, question, option1, option2, option3, option4, correctanswer);
                if (examMap.containsKey(name)) {
                    // Add the Exam object to the existing list
                    examMap.get(name).add(examQuestions);
                } else {
                    // Create a new list with the Exam object and put it in the examMap
                    List<ExamQuestions> list = new ArrayList<>();
                    list.add(examQuestions);
                    examMap.put(name, list);
                }
            }
            resultSet.close();
            connection.close();
        return examMap;
        } catch (SQLException e) {
            System.err.println("Failed to connect Database.");
            throw new RuntimeException(e);
        }
    }

    @Override public List<ExamQuestions> list2() {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM exam;";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String question = resultSet.getString("question");
                String option1 = resultSet.getString("option1");
                String option2 = resultSet.getString("option2");
                String option3 = resultSet.getString("option3");
                String option4 = resultSet.getString("option4");
                String correctanswer = resultSet.getString("correctanswer");

                ExamQuestions examQuestions = new ExamQuestions(id, name, question, option1, option2, option3, option4, correctanswer);
                examQuestionsList.add(examQuestions);
            }
            resultSet.close();
            connection.close();
            return examQuestionsList;
        } catch (SQLException e) {
            System.err.println("Failed to connect Database.");
            throw new RuntimeException(e);
        }
    }

    @Override
    public ExamQuestions get(String id) {
        return null;
    }
}
