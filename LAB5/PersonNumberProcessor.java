package LAB5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class PersonNumberProcessor {

    private String fileName;
    private List<PersonNumber> persons;

    public PersonNumberProcessor(String fileName) {
        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException("Имя файла не может быть пустым");
        }
        this.fileName = fileName;
        this.persons = new ArrayList<>();
    }

    public void readFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            persons = reader.lines()
                    .map(String::trim)          // Убираем пробелы в начале и конце каждой строки
                    .filter(line -> !line.isEmpty())      // Пропускаем пустые строки
                    .map(this::parseLine)             // Преобразуем каждую строку в объект PersonNumber
                    .filter(Objects::nonNull)            // Исключаем строки (null)
                    .collect(Collectors.toList());        // Собираем результат в список
        }
    }

    private PersonNumber parseLine(String line) {
        String[] parts = line.split(":");        // Разделяем на 2 части: первая — имя, вторая — номер
        String name = parts[0].trim();             // Убираем пробелы спереди/сзади имени
        Integer number = null;                      // Сначала номер null

        if (parts.length > 1 && !parts[1].trim().isEmpty()) {  // Проверяем, есть ли номер
            try {
                number = Integer.parseInt(parts[1].trim());    // Делаем из строки число (из "1" - 1)
            } catch (NumberFormatException ignored) {          // Если номер некорректный, игнор
            }
        }

        return new PersonNumber(name, number);
    }

    public Map<Integer, List<String>> groupByNumber() {
        return persons.stream()
                .filter(p -> p.getNumber() != null)   // Оставляем только тех, у кого есть номер
                .collect(Collectors.groupingBy(        // Группируем по номеру
                        PersonNumber::getNumber,      // Берем номер
                        LinkedHashMap::new,           // Порядок такой как в файле
                        Collectors.mapping(PersonNumber::getName, Collectors.toList()) // Значение: список имен и тд.
                ));
    }

    public List<PersonNumber> getPersons() {
        return persons;
    }

    @Override
    public String toString() {
        return persons.toString();
    }
}
