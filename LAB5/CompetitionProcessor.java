package LAB5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CompetitionProcessor {

    private List<Participant> participants; // Поле со списом всех участников

    public CompetitionProcessor() {
        participants = new ArrayList<>();
    }

    public void readParticipants(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {  // Проходим по каждой строке (пустые пропускаем)
                lineNumber++;
                line = line.trim();     // Убираем пробелы по краям
                if (line.isEmpty()) continue;

                String[] parts = line.split(" "); // Разделяем по пробелам
                if (parts.length != 6) { // Проверяем формат строки (6 частей - фамилия, имя, 4 оценки)
                    throw new IllegalArgumentException("Ошибка в формате строки " + lineNumber + ": " + line);
                }

                String lastName = parts[0].trim();
                String firstName = parts[1].trim();
                int[] scores = new int[4]; // Массив для оценок
                for (int i = 0; i < 4; i++) {
                    scores[i] = Integer.parseInt(parts[i + 2]); // Считаем со 2 индекса и делаем в int
                }

                participants.add(new Participant(lastName, firstName, scores));
            }
        }
    }

    public List<Participant> getTopParticipants(int topN) {
        List<Participant> sorted = participants.stream()
                .sorted(Comparator.comparingInt(Participant::getTotalScore).reversed()) // Сортировка по сумме баллов
                .collect(Collectors.toList());

        List<Participant> topList = new ArrayList<>(); // Список лучших участников
        if (sorted.isEmpty()) return topList; // Если нет участников

        int count = 0;  // счётчик участников в топе
        int lastScore = -1; // баллы последнего добавленного участника
        for (Participant p : sorted) {
            if (count < topN) { // Добавляем пока не достигли topN
                topList.add(p);
                lastScore = p.getTotalScore(); // Запоминаем последний балл
                count++;
            } else if (p.getTotalScore() == lastScore) { // Добавляем участников с тем же баллом
                topList.add(p);
            } else {
                break;
            }
        }

        return topList;
    }

    public void printParticipants() {      //метод для вывода всех участников
        for (Participant p : participants) {
            System.out.println(p);
        }
    }
}
