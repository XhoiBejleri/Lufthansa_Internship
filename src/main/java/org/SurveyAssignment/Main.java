package org.SurveyAssignment;

import org.SurveyAssignment.model.Answer;
import org.SurveyAssignment.model.Candidate;
import org.SurveyAssignment.model.Question;
import org.SurveyAssignment.model.Survey;
import org.SurveyAssignment.service.SurveyService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SurveyService manager = new SurveyService();

        Survey survey = new Survey("Java Best Practice", "Java", "A survey about Java Programming.");
        for (int i = 1; i <= 10; i++) {
            survey.addQuestion(new Question("Question " + i + "?"));
        }

        System.out.println("Survey Title: " + survey.getTitle());
        System.out.println("Survey Topic: " + survey.getTopic());
        System.out.println("Survey Description: " + survey.getDescription());

        Candidate xhoi = new Candidate("Xhoi", "Bejleri", "xhoi@gmail.com", "0123456789");
        List<Answer> xhoiAnswers = new ArrayList<>();
        for (Question q : survey.getQuestions()) {
            xhoiAnswers.add(new Answer(q, "Agree"));
        }
        xhoi.takeSurvey(survey, xhoiAnswers);
        survey.addCandidate(xhoi);

        manager.addSurvey(survey);

        System.out.println("Most common answer: " + manager.findMostCommonAnswer(survey));

        Candidate topCandidate = manager.findMostActiveCandidate();
        if (topCandidate != null) {
            System.out.println("Most active candidate: " + topCandidate.getFullName());
        }

        manager.removeLowResponseQuestions(survey);
        System.out.println("Remaining questions: " + survey.getQuestionCount());
    }
}