package org.SurveyAssignment.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Candidate {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Map<Survey, List<Answer>> surveyAnswers;
    
    public Candidate(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.surveyAnswers = new HashMap<>();
    }
    
    public void takeSurvey(Survey survey, List<Answer> answers) {
        surveyAnswers.put(survey, answers);
    }
    
    public int getSurveyCount() {
        return surveyAnswers.size();
    }
    
    public List<Answer> getAnswersForSurvey(Survey survey) {
        return surveyAnswers.getOrDefault(survey, new ArrayList<>());
    }
    
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void add(Candidate candidate) {
    }
}
