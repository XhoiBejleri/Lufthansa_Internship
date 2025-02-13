package org.SurveyAssignment.utils;

public class SurveyValidator {

    public boolean isValidTitle(String title) {
        return title != null && !title.trim().isEmpty();
    }

    public boolean isValidDescription(String description) {
        return description != null && !description.trim().isEmpty();
    }

    public boolean isValidTopic(String topic) {
        return topic != null && !topic.trim().isEmpty();
    }

    public boolean isValidQuestion(String questionText) {
        return questionText != null && !questionText.trim().isEmpty();
    }
}
