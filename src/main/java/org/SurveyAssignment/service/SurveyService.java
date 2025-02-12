package org.SurveyAssignment.service;

import org.SurveyAssignment.model.Answer;
import org.SurveyAssignment.model.Candidate;
import org.SurveyAssignment.model.Question;
import org.SurveyAssignment.model.Survey;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SurveyService {
    private List<Survey> surveys;

    public SurveyService() {
        this.surveys = new ArrayList<>();
    }

    public void addSurvey(Survey survey) {
        if (!survey.isValid()) {
            throw new IllegalStateException("Survey must have between 10 and 40 unique questions.");
        }
        surveys.add(survey);
    }

    public Map<String, Integer> getSurveyResults(Survey survey) {
        Map<String, Integer> resultMap = new HashMap<>();
        for (Question q : survey.getQuestions()) {
            for (String option : Question.getOptions()) {
                resultMap.put(q.getText() + " - " + option, 0);
            }
        }

        for (Candidate candidate : survey.getCandidates()) {
            for (Answer answer : candidate.getAnswersForSurvey(survey)) {
                String key = answer.getQuestion().getText() + " - " + answer.getSelectedOption();
                resultMap.put(key, resultMap.get(key) + 1);
            }
        }

        return resultMap;
    }

    public String findMostCommonAnswer(Survey survey) {
        Map<String, Integer> results = getSurveyResults(survey);
        return results.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No answers yet.");
    }

    public Candidate findMostActiveCandidate() {
        return surveys.stream()
                .flatMap(s -> s.getCandidates().stream())
                .max(Comparator.comparingInt(Candidate::getSurveyCount))
                .orElse(null);
    }

    public void removeLowResponseQuestions(Survey survey) {
        int totalCandidates = survey.getCandidates().size();
        survey.getQuestions().removeIf(q -> {
            long answerCount = survey.getCandidates().stream()
                    .flatMap(c -> c.getAnswersForSurvey(survey).stream())
                    .filter(a -> a.getQuestion().equals(q))
                    .count();
            return answerCount < totalCandidates / 2.0;
        });
    }
}
