package LAB5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class LetterCounter {

    private String fileName;
    private int uniqueLettersCount;

    public LetterCounter(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("Имя файла не может быть пустым.");
        }
        this.fileName = fileName;
        this.uniqueLettersCount = 0; // Счётчик
    }

    public void countLetters() throws IOException {
        Set<Character> letters = new HashSet<>(); // Множество для уникальных букв

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int c;
            while ((c = reader.read()) != -1) { // Читаем файл посимвольно
                char ch = (char) c; // Преобразуем int в char

                if (isRussianLetter(ch)) { // Проверяем, русская ли буква
                    letters.add(Character.toLowerCase(ch)); // Добавляем в множество
                }
            }
        }

        uniqueLettersCount = letters.size();
    }

    private boolean isRussianLetter(char ch) {
        return (ch >= 'А' && ch <= 'я') || ch == 'Ё' || ch == 'ё'; // Проверка диапазона русских букв
    }

    public int getUniqueLettersCount() {
        return uniqueLettersCount;
    }

    @Override
    public String toString() {
        return "LetterCounter{fileName='" + fileName
                + "', uniqueLettersCount=" + uniqueLettersCount + "}";
    }
}
