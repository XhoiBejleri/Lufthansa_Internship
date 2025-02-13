package org.SurveyAssignment.model;

import org.apache.commons.lang3.StringUtils;

public class SurveyValidator {

    public boolean isValidSurvey(String title, String description) {
        return StringUtils.isNotBlank(title) && StringUtils.isNotBlank(description);
    }

    public boolean isValidQuestion(String questionText) {
        return StringUtils.isNotBlank(questionText);
    }
}

