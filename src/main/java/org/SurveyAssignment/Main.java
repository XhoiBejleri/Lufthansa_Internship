package org.SurveyAssignment;

import org.SurveyAssignment.model.Answer;
import org.SurveyAssignment.model.Candidate;
import org.SurveyAssignment.model.MultipleChoiceQuestion;
import org.SurveyAssignment.model.Question;
import org.SurveyAssignment.model.Survey;
import org.SurveyAssignment.service.SurveyService;
import org.SurveyAssignment.utils.SurveyValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SurveyService manager = new SurveyService();
        SurveyValidator validator = new SurveyValidator();

        System.out.print("Enter survey title: ");
        String title = scanner.nextLine();
        if (!validator.isValidTitle(title)) {
            System.out.println("Survey title cannot be empty.");
            return;
        }

        System.out.print("Enter survey description: ");
        String description = scanner.nextLine();
        if (!validator.isValidDescription(description)) {
            System.out.println("Survey description cannot be empty.");
            return;
        }

        System.out.print("Enter survey topic: ");
        String topic = scanner.nextLine();
        if (!validator.isValidTopic(topic)) {
            System.out.println("Survey topic cannot be empty.");
            return;
        }

        Survey survey = new Survey(title, topic, description);

        System.out.print("Enter the number of questions: ");
        int numQuestions = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= numQuestions; i++) {
            System.out.print("Enter question " + i + ": ");
            String questionText = scanner.nextLine();
            if (!validator.isValidQuestion(questionText)) {
                System.out.println("Question cannot be empty.");
                i--;
                continue;
            }
            List<String> options = Arrays.asList("Agree", "Slightly Agree", "Slightly Disagree", "Disagree");
            survey.addQuestion(new MultipleChoiceQuestion(questionText, options));
        }

        System.out.print("Enter First name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();

        Candidate candidate = new Candidate(firstName, lastName, email, phone);

        List<Answer> answers = new ArrayList<>();
        for (Question q : survey.getQuestions()) {
            System.out.println(q.getText());
            System.out.println("1. Agree");
            System.out.println("2. Slightly Agree");
            System.out.println("3. Slightly Disagree");
            System.out.println("4. Disagree");
            System.out.print("Select an option (1-4): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            String selectedAnswer = switch (choice) {
                case 1 -> "Agree";
                case 2 -> "Slightly Agree";
                case 3 -> "Slightly Disagree";
                case 4 -> "Disagree";
                default -> "No answer";
            };
            answers.add(new Answer(q, selectedAnswer));
        }

        for (Answer answer : answers) {
            candidate.addAnswer(answer);
        }

        survey.addCandidate(candidate);

        manager.addSurvey(survey);

        System.out.println("Most common answer: " + manager.findMostCommonAnswer(survey));

        Candidate topCandidate = manager.findMostActiveCandidate();
        if (topCandidate != null) {
            System.out.println("Most active candidate: " + topCandidate.getFullName());
        }

        manager.removeLowResponseQuestions(survey);
        System.out.println("Remaining questions: " + survey.getQuestionCount());

        scanner.close();
    }
}
