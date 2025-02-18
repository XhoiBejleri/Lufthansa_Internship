package org.SurveyAssignment.model;

import java.util.ArrayList;
import java.util.List;

public class Survey {

    private String title;
    private String topic;
    private String description;
    private List<Question> questions;
    private List<Candidate> candidates;

    public Survey(String title, String topic, String description) {
        this.title = title;
        this.topic = topic;
        this.description = description;
        this.questions = new ArrayList<>();
        this.candidates = new ArrayList<>();
    }


    public void addQuestion(Question question) {
        if (!questions.contains(question)) {
            questions.add(question);
        }
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void addCandidate(Candidate candidate) {
        candidates.add(candidate);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public int getQuestionCount() {
        return questions.size();
    }
}
