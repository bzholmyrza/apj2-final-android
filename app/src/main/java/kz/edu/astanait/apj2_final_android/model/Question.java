package kz.edu.astanait.apj2_final_android.model;

import java.io.Serializable;

public class Question implements Serializable {
    private long questionId;
    private String questionText;

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
}
