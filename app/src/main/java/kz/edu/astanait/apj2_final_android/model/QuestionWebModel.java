package kz.edu.astanait.apj2_final_android.model;

import java.util.List;

public class QuestionWebModel {
    private Question question;
    private List<Answer> answers;

    @Override
    public String toString() {
        return question.getQuestionText();
    }
}
