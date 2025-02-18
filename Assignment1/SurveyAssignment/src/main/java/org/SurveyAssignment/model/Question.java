package org.SurveyAssignment.model;

import java.util.List;

public abstract class Question {
    protected String text;
    protected List<String> options;

    public Question(String text, List<String> options) {
        if (options == null || options.isEmpty()) {
            throw new IllegalArgumentException("Options list cannot be null or empty.");
        }
        this.text = text;
        this.options = options;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    public abstract void displayQuestion();
}
