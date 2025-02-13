package org.SurveyAssignment.service;

import org.SurveyAssignment.model.Answer;
import org.SurveyAssignment.model.Candidate;
import org.SurveyAssignment.model.Survey;
import org.SurveyAssignment.model.Question;

import java.util.*;

public class SurveyService {
    private List<Survey> surveys = new ArrayList<>();

    public void addSurvey(Survey survey) {
        surveys.add(survey);
    }

    public String findMostCommonAnswer(Survey survey) {
        Map<String, Integer> answerCount = new HashMap<>();

        for (Candidate candidate : survey.getCandidates()) {
            for (Answer answer : candidate.getAnswers()) {
                String selectedOption = answer.getSelectedOption();
                answerCount.put(selectedOption, answerCount.getOrDefault(selectedOption, 0) + 1);
            }
        }

        return Collections.max(answerCount.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public Candidate findMostActiveCandidate() {
        Candidate topCandidate = null;
        int maxAnswers = 0;

        for (Survey survey : surveys) {
            for (Candidate candidate : survey.getCandidates()) {
                int answerCount = candidate.getAnswers().size();
                if (answerCount > maxAnswers) {
                    topCandidate = candidate;
                    maxAnswers = answerCount;
                }
            }
        }
        return topCandidate;
    }

    public void removeLowResponseQuestions(Survey survey) {
        List<Question> remainingQuestions = new ArrayList<>();

        for (Question question : survey.getQuestions()) {
            int answerCount = 0;

            for (Candidate candidate : survey.getCandidates()) {
                for (Answer answer : candidate.getAnswers()) {
                    if (answer.getQuestion().equals(question)) {
                        answerCount++;
                        break;
                    }
                }
            }

            if (answerCount > 1) {
                remainingQuestions.add(question);
            }
        }

        survey.setQuestions(remainingQuestions);
    }
}

