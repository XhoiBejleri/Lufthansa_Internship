package org.SurveyAssignment.model;


public class Answer {
    private Question question;
    private String selectedOption;

    public Answer(Question question, String selectedOption) {
        if (question instanceof MultipleChoiceQuestion) {
            MultipleChoiceQuestion mcq = (MultipleChoiceQuestion) question;
            if (!mcq.getOptions().contains(selectedOption)) {
                throw new IllegalArgumentException("Invalid answer option");
            }
        }
        this.question = question;
        this.selectedOption = selectedOption;
    }


    public Question getQuestion() {
        return question;
    }

    public String getSelectedOption() {
        return selectedOption;
    }
}
