package LAB5;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Задание 1
        System.out.println("Задание 1:");

        try {
            int numerator = readInt(scanner, "Введите числитель: ");
            int denominator = readInt(scanner, "Введите знаменатель: ");

            Fraction fraction = new Fraction(numerator, denominator);
            System.out.println("Обычная дробь: " + fraction);
            System.out.println("Её вещественное значение: " + fraction.getValue());

            CachedFraction cachedFraction = new CachedFraction(numerator, denominator);
            System.out.println("\nКэшируемая дробь: " + cachedFraction);
            System.out.println("Первый расчёт: " + cachedFraction.getValue());
            System.out.println("Получение из кэша: " + cachedFraction.getValue());

            System.out.println("\nИзменяем числитель на 10:");
            cachedFraction.setNumerator(10);
            System.out.println("После изменения: " + cachedFraction);
            System.out.println("Новое значение: " + cachedFraction.getValue());

            Fraction another = new Fraction(10, denominator); // Создаём ещё одну дробь для сравнения
            System.out.println("\nСравнение дробей:");
            System.out.println("Результат сравнения: " + cachedFraction.equals(another));

            // Задание 2
            System.out.println("\nЗадание 2:");
            System.out.print("Введите имя кота: ");
            String catName = scanner.nextLine();

            Cat cat = new Cat(catName);
            MeowCounter catCounter = new MeowCounter(cat);

            System.out.println();
            MeowUtils.makeAllMeow(catCounter, catCounter, catCounter); // Мяу 3 раза

            System.out.println("\nКот мяукнул всего " + catCounter.getCount() + " раз(а).");

            // Задание 3
            System.out.println("\nЗадание 3:");

            int sizeL1 = readPositiveInt(scanner, "Введите количество элементов списка L1: ");
            int[] arrayL1 = new int[sizeL1];    // Создание массива для L1
            for (int i = 0; i < sizeL1; i++) {
                arrayL1[i] = readInt(scanner, (i + 1) + ": ");
            }
            OrderedList list1 = new OrderedList(arrayL1);

            int sizeL2 = readPositiveInt(scanner, "Введите количество элементов списка L2: ");
            int[] arrayL2 = new int[sizeL2];
            for (int i = 0; i < sizeL2; i++) {
                arrayL2[i] = readInt(scanner, (i + 1) + ": ");
            }
            OrderedList list2 = new OrderedList(arrayL2);

            System.out.println("Список L1: " + list1);
            System.out.println("Список L2: " + list2);

            list1.insertAll(list2);
            System.out.println("Объединённый упорядоченный список: " + list1);

            // Задание 4
            System.out.println("\nЗадание 4:");

            CompetitionProcessor processor = new CompetitionProcessor();

            try {
                processor.readParticipants("src/LAB5/students.txt");
                List<Participant> topParticipants = processor.getTopParticipants(3); // Топ-3
                System.out.println("Лучшие участники многоборья:");
                for (Participant p : topParticipants) {
                    System.out.println(p.getFullName() + " - " + p.getTotalScore());
                }
            } catch (IOException | IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }

            // Задание 5
            System.out.println("\nЗадание 5:");

            String lettersFileName = "src/LAB5/students.txt";
            LetterCounter letterCounter = new LetterCounter(lettersFileName);
            try {
                letterCounter.countLetters();
                System.out.println("Количество различных букв в файле: "
                        + letterCounter.getUniqueLettersCount());
            } catch (IOException e) {
                System.out.println("Ошибка чтения файла: " + e.getMessage());
            }

            // Задание 6
            System.out.println("\nЗадание 6:");

            int sizeL = readPositiveInt(scanner, "Введите количество элементов списка L: ");
            int[] sourceArray = new int[sizeL];
            for (int i = 0; i < sizeL; i++) {
                sourceArray[i] = readInt(scanner, (i + 1) + ": ");
            }

            QueueBuilder queueBuilder = new QueueBuilder(sourceArray);
            queueBuilder.buildQueue();
            System.out.println("Полученная очередь: " + queueBuilder.getResultQueue());

            // Задание 7.1
            System.out.println("\nЗадание 7.1:");

            Point[] points = {
                    new Point(1, -2),
                    new Point(3, 4),
                    new Point(2, -1),
                    new Point(1, -2),
                    new Point(5, -3)
            };

            Polyline polyline = new Polyline(   // Создание ломаной линии
                    java.util.Arrays.stream(points)
                            .map(p -> new Point(p.getX(), Math.abs(p.getY()))) // Берём только положительные значения y
                            .distinct() // Убираем дубликаты
                            .sorted(java.util.Comparator.comparingDouble(Point::getX)) // Сортируем по x
                            .toArray(Point[]::new) // Преобразуем обратно в массив
            );

            System.out.println("Результат: " + polyline);

            // Задание 7.2
            System.out.println("\nЗадание 7.2:");

            PersonNumberProcessor personProcessor = new PersonNumberProcessor("src/LAB5/persons.txt");

            try {
                personProcessor.readFile();
                Map<Integer, List<String>> grouped = personProcessor.groupByNumber(); // группируем с помощью стрима
                System.out.println("Группировка по номеру: " + grouped);
            } catch (IOException e) {
                System.out.println("Ошибка чтения файла: " + e.getMessage());
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        scanner.close();
    }

    private static int readInt(Scanner scanner, String message) { // Метод для ввода целого числа
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число!");
            }
        }
    }

    private static int readPositiveInt(Scanner scanner, String message) { // Метод для ввода положительного числа
        int value;
        while (true) {
            value = readInt(scanner, message);
            if (value > 0) break;
            System.out.println("Ошибка: число должно быть положительным!");
        }
        return value;
    }
}
