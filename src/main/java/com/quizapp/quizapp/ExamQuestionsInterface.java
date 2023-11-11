package com.quizapp.quizapp;

import java.util.HashMap;
import java.util.List;

public interface ExamQuestionsInterface {
    public boolean inset(ExamQuestions examQuestions);
    public boolean update(ExamQuestions examQuestions);
    public boolean delete(ExamQuestions examQuestions);
    HashMap<String, List<ExamQuestions>> list();
    List<ExamQuestions> list2();
    ExamQuestions get(String id);
}
