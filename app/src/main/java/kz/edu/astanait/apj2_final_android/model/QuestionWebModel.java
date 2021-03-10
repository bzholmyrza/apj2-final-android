package kz.edu.astanait.apj2_final_android.model;

import java.io.Serializable;
import java.util.List;

public class QuestionWebModel implements Serializable {
    private Question question;
    private List<Answer> answers;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return question.getQuestionText();
    }
}
