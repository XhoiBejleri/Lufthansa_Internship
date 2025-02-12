package org.SurveyAssignment.model;

import java.util.Arrays;
import java.util.List;

public class Question {
    private String text;
    private static final List<String> OPTIONS = Arrays.asList(
            "Agree", "Slightly Agree", "Slightly Disagree", "Disagree"
    );
    
    public Question(String text) {
        this.text = text;
    }
    
    public String getText() {
        return text;
    }
    
    public static List<String> getOptions() {
        return OPTIONS;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Question)) return false;
        Question other = (Question) obj;
        return this.text.equalsIgnoreCase(other.text);
    }
    
    @Override
    public int hashCode() {
        return text.toLowerCase().hashCode();
    }
}
