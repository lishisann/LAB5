package LAB5;

import java.util.Arrays;
import java.util.Objects;

public class Participant {

    private String lastName;
    private String firstName;
    private int[] scores;
    private int totalScore;

    public Participant(String lastName, String firstName, int[] scores) {
        if (lastName == null || lastName.isBlank() || lastName.length() > 20) {
            throw new IllegalArgumentException("Некорректная фамилия");
        }
        if (firstName == null || firstName.isBlank() || firstName.length() > 15) {
            throw new IllegalArgumentException("Некорректное имя");
        }
        if (scores == null || scores.length != 4) {
            throw new IllegalArgumentException("Баллы должны содержать 4 значения");
        }
        for (int score : scores) {
            if (score < 0 || score > 10) {
                throw new IllegalArgumentException("Баллы должны быть от 0 до 10");
            }
        }
        this.lastName = lastName;
        this.firstName = firstName;
        this.scores = scores;
        this.totalScore = Arrays.stream(scores).sum();
    }

    public int getTotalScore() {
        return totalScore;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + ": " + totalScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;        // сравнение с самим собой
        if (!(o instanceof Participant)) return false;
        Participant that = (Participant) o;
        return totalScore == that.totalScore &&    // сравниваем общий балл
                Objects.equals(lastName, that.lastName) && // сравниваем фамилию
                Objects.equals(firstName, that.firstName) && // сравниваем имя
                Arrays.equals(scores, that.scores); // сравниваем массив оценок
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(lastName, firstName, totalScore); // хэш
        result = 31 * result + Arrays.hashCode(scores); // учитываем массив оценок
        return result;
    }
}
