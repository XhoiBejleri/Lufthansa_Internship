package org.SurveyAssignment.model;

import java.util.List;

public class MultipleChoiceQuestion extends Question {

    public MultipleChoiceQuestion(String text, List<String> options) {
        super(text, options);
        if (options.isEmpty()) {
            throw new IllegalArgumentException("Options cannot be null or empty");
        }
    }

    @Override
    public void displayQuestion() {
        System.out.println(getText());
        List<String> options = getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }
}