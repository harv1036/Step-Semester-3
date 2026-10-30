import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class OnlineExaminationSystem {

    public static void main(String[] args) {
        Examination examA = new Examination("Exam A");
        examA.addQuestion(new MultipleChoiceQuestion("Q1", 5, "C"));
        examA.addQuestion(new TrueFalseQuestion("Q2", 5, true));

        Attempt attempt = examA.start("Student 1");
        System.out.println("Exam A started by Student 1.");

        attempt.answer("Q1", "C");
        System.out.println("Answer recorded for Question 1.");

        attempt.answer("Q2", "true");
        System.out.println("Answer recorded for Question 2.");

        attempt.submit();
        System.out.println("Exam A submitted by Student 1.");

        attempt.printResult();

        boolean changed = attempt.answer("Q1", "A");
        if (!changed) {
            System.out.println("Cannot change answers for a submitted examination.");
        }
    }
}

abstract class Question {
    private final String id;
    private final int points;

    Question(String id, int points) {
        this.id = id;
        this.points = points;
    }

    String getId() { return id; }
    int getPoints() { return points; }

    abstract boolean evaluate(String givenAnswer);
}

class MultipleChoiceQuestion extends Question {
    private final String correctOption;

    MultipleChoiceQuestion(String id, int points, String correctOption) {
        super(id, points);
        this.correctOption = correctOption;
    }

    boolean evaluate(String givenAnswer) {
        return correctOption.equalsIgnoreCase(givenAnswer);
    }
}

class TrueFalseQuestion extends Question {
    private final boolean correctAnswer;

    TrueFalseQuestion(String id, int points, boolean correctAnswer) {
        super(id, points);
        this.correctAnswer = correctAnswer;
    }

    boolean evaluate(String givenAnswer) {
        return Boolean.toString(correctAnswer).equalsIgnoreCase(givenAnswer);
    }
}

class ShortAnswerQuestion extends Question {
    private final String correctAnswer;

    ShortAnswerQuestion(String id, int points, String correctAnswer) {
        super(id, points);
        this.correctAnswer = correctAnswer;
    }

    boolean evaluate(String givenAnswer) {
        return correctAnswer.trim().equalsIgnoreCase(givenAnswer == null ? "" : givenAnswer.trim());
    }
}

class Examination {
    private final String name;
    private final List<Question> questions;

    Examination(String name) {
        this.name = name;
        this.questions = new ArrayList<>();
    }

    void addQuestion(Question question) {
        questions.add(question);
    }

    List<Question> getQuestions() { return questions; }

    Attempt start(String studentName) {
        return new Attempt(studentName, this);
    }
}

enum AttemptStatus { IN_PROGRESS, SUBMITTED }

class Attempt {
    private final String studentName;
    private final Examination examination;
    private final Map<String, String> answers;
    private AttemptStatus status;
    private int totalScore;

    Attempt(String studentName, Examination examination) {
        this.studentName = studentName;
        this.examination = examination;
        this.answers = new HashMap<>();
        this.status = AttemptStatus.IN_PROGRESS;
    }

    boolean answer(String questionId, String response) {
        if (status == AttemptStatus.SUBMITTED) {
            return false;
        }
        answers.put(questionId, response);
        return true;
    }

    void submit() {
        if (status == AttemptStatus.SUBMITTED) {
            return;
        }
        status = AttemptStatus.SUBMITTED;
        totalScore = 0;
        for (Question q : examination.getQuestions()) {
            if (q.evaluate(answers.get(q.getId()))) {
                totalScore += q.getPoints();
            }
        }
    }

    void printResult() {
        StringBuilder sb = new StringBuilder("Result: ");
        int maxScore = 0;
        boolean first = true;
        for (Question q : examination.getQuestions()) {
            maxScore += q.getPoints();
            boolean correct = q.evaluate(answers.get(q.getId()));
            if (!first) sb.append(", ");
            sb.append(q.getId()).append(": ").append(correct ? "Correct" : "Incorrect")
                    .append(" (").append(correct ? q.getPoints() : 0).append(" points)");
            first = false;
        }
        System.out.println(sb + ". Total score: " + totalScore + "/" + maxScore + ".");
    }
}
