package org.SurveyAssignment.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Survey {

    private String title;
    private String topic;
    private String description;
    private Set<Question> questions;
    private List<Candidate> candidates;

    public Survey(String title, String topic, String description) {
        this.title = title;
        this.topic = topic;
        this.description = description;
        this.questions = new HashSet<>();
        this.candidates = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        if (questions.size() >= 40) {
            throw new IllegalStateException("Cannot add more than 40 questions.");
        }
        questions.add(question);
    }

    public void removeQuestion(Question question) {
        questions.remove(question);
    }

    public void addCandidate(Candidate candidate) {
        candidates.add(candidate);
    }

    public int getQuestionCount() {
        return questions.size();
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public boolean isValid() {
        return questions.size() >= 10  && questions.size() <= 40;
    }

    public String getTitle() {
        return title;
    }

    public String getTopic() {
        return topic;
    }

    public String getDescription() {
        return description;
    }
}
