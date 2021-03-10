package kz.edu.astanait.apj2_final_android.model;

import java.io.Serializable;

public class Answer implements Serializable {
    private long answerId;
    private String answerText;

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}
