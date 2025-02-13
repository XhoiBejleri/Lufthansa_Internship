package org.SurveyAssignment.model;

import java.util.ArrayList;
import java.util.List;

public class Candidate {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<Answer> answers;

    public Candidate(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.answers = new ArrayList<>();
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Candidate [Full Name: " + getFullName() + ", Email: " + email + ", Phone: " + phone + "]";
    }
}

